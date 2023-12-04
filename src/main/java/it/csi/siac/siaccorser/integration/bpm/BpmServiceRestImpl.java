/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.ow2.bonita.facade.def.majorElement.DataFieldDefinition;
import org.ow2.bonita.facade.def.majorElement.ProcessDefinition;
import org.ow2.bonita.facade.runtime.ActivityInstance;
import org.ow2.bonita.facade.runtime.ActivityState;
import org.ow2.bonita.facade.runtime.ProcessInstance;
import org.ow2.bonita.facade.uuid.ProcessInstanceUUID;
import org.ow2.bonita.light.LightActivityInstance;
import org.ow2.bonita.search.SearchQueryBuilder;
import org.ow2.bonita.search.index.ActivityInstanceIndex;
import org.ow2.bonita.search.index.ProcessInstanceIndex;
import org.ow2.bonita.util.Base64;
import org.ow2.bonita.util.xml.XStreamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.XStream;

import it.csi.siac.siaccommon.util.log.LogUtil;
import it.csi.siac.siaccorser.frontend.webservice.exception.SystemException;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcesso;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcessoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTask;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTaskResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.GetTaskInfo;
import it.csi.siac.siaccorser.integration.bpm.msg.GetTaskInfoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.ParametriRicercaTaskBpm;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendenti;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendentiResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaNotifichePendenti;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaNotifichePendentiResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.TerminaProcesso;
import it.csi.siac.siaccorser.integration.bpm.msg.TerminaProcessoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.TipoRicercaTaskBpm;
import it.csi.siac.siaccorser.integration.dad.VariazioniDad;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.AttivitaPendente;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.AzioneConsentita;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;
import it.csi.siac.siaccorser.model.VariabileProcesso;
import it.csi.siac.siaccorser.model.Variazione;
import it.csi.siac.siaccorser.model.errore.ErroreCore;


/**
 * Implementazione del servizio che permette di accedere al BPM server bonita,
 * che utilizza le chiamate rest XML dirette (senza API java Bonita).
 * 
 * La lista completa delle API si trova in
 * 
 * @see http://www.bonitasoft.org/docs/javadoc/rest/5.9/API/index.html gli
 *      oggetti java usati in
 * @see http://www.bonitasoft.org/docs/javadoc/bpm_engine/5.9/index.html
 * 
 * @author alagna
 */
@Component
@Transactional
public class BpmServiceRestImpl implements BpmService {
	
	private final LogUtil log = new LogUtil(getClass());

	// --- configurazione del log delle risposte ---
	private static final boolean TAGLIA_RIPOSTA_HTTP = true;
	private static final int MAX_LEN_RISPOSTA_HTTP = 2000;

	/** Endpoint rest del server bonita: varia a seconda degli ambienti */
	private String endpointRest;

	/** Utente utilizzato nell'accesso a bonita */
	private String utenteBpm;

	// query di ricerca sulle definizioni
	private static final String QUERY_DEFINITION_API = "/queryDefinitionAPI";
	private static final String QUERY_PROCESSO_PIU_RECENTE = QUERY_DEFINITION_API + "/getLastProcess/";

	// query di ricerca sul runtime
	private static final String QUERY_RUNTIME_API = "/queryRuntimeAPI";
	private static final String QUERY_PARZIALE = "/searchByMaxResult";
	private static final String QUERY_PARZIALE_OFFSET = QUERY_PARZIALE + "?firstResult=";
	private static final String QUERY_PARZIALE_SIZE = "maxResults=";
	private static final String QUERY_TOTALE = "/search";
	private static final String QUERY_PROSSIMO_TASK = QUERY_RUNTIME_API + "/getActivityInstances/";
	private static final String QUERY_GET_ACTIVITY = QUERY_RUNTIME_API + "/getActivityInstance/";
	private static final String QUERY_VARS_DI_PROC = QUERY_RUNTIME_API + "/getProcessInstanceVariables/";
	private static final String QUERY_ISTANZE_DI_PROC = QUERY_RUNTIME_API + "/getProcessInstances/";
	private static final String QUERY_VARS_DI_ISTANZA = QUERY_RUNTIME_API + "/getActivityInstanceVariables/";
	private static final String QUERY_DESC_PROCESSO = QUERY_DEFINITION_API + "/getProcess/";

	// comandi
	private static final String RUNTIME_API = "/runtimeAPI";
	private static final String COMANDO_ISTANZA_PROCESSO = RUNTIME_API + "/instantiateProcess/";
	private static final String COMANDO_SET_VAR_DI_PROC = RUNTIME_API + "/setProcessInstanceVariable/";
	private static final String COMANDO_SET_VAR_DI_ISTANZA = RUNTIME_API + "/setVariable/";
	private static final String COMANDO_CANCELLA_ISTANZA_PROCESSO = RUNTIME_API + "/deleteProcessInstance/";
	private static final String COMANDO_ESEGUE_TASK = RUNTIME_API + "/executeTask/";

	private final String caratterePerRimpiazzareEu;

	/** decoder dell'xml in risposta dal server BPM */
	private final XStream xstream = XStreamUtil.getDefaultXstream();

	/**
	 * Nomi delle variabili da estrarre dall'istanza di processo utilizzate
	 * nelle descrizioni nel metodo ricercaAttivitaPendenti()
	 */
	private final List<String> nomiVariabiliDescrizione = new ArrayList<String>();
	
	@Autowired
	private VariazioniDad variazioniDad;
	

	public BpmServiceRestImpl() {
		nomiVariabiliDescrizione.add(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE);
		nomiVariabiliDescrizione.add(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE_BREVE);

		byte[] euroBytes = { -30, -126, -84 };
		caratterePerRimpiazzareEu = new String(euroBytes);
	}

	/**
	 * Ottiene la lista dei task in stato ready, interrogando le API del bpm
	 * server
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public RicercaAttivitaPendentiResponse ricercaAttivitaPendenti(RicercaAttivitaPendenti request) {
		final String methodName = "ricercaAttivitaPendenti";
		RicercaAttivitaPendentiResponse res = new RicercaAttivitaPendentiResponse();

		ParametriRicercaTaskBpm parametriRicerca = request
				.getParametriRicerca();

		AzioneConsentita azioneConsentita = parametriRicerca.getAzione();
		Azione azione = azioneConsentita.getAzione();
		Account account = request.getRichiedente().getAccount();

		int offset = parametriRicerca.getOffset();
		int size = parametriRicerca.getSize();
		
		// costruzione della url della query BPM
		String url;
		boolean risultatoInLista = true;
		if (parametriRicerca.getTipoRicerca().equals(
				TipoRicercaTaskBpm.ATTIVITA)) {
			url = endpointRest
					+ QUERY_RUNTIME_API
					// attenzione, le api bonita considerano come primo elemento
					// l'1-esimo
					// e non lo 0-esimo
					+ QUERY_PARZIALE_OFFSET + (offset) + "&"
					+ QUERY_PARZIALE_SIZE + (size);
		} else if (parametriRicerca.getTipoRicerca().equals(TipoRicercaTaskBpm.TOTALE_ATTIVITA)) {
			url = endpointRest + QUERY_RUNTIME_API + QUERY_TOTALE;
			risultatoInLista = false;
		} else {
			log.warn(methodName, "tipo di ricerca non compatibile con il metodo scelto");
			return res;
		}
		log.debug(methodName, "Url:\n" + url);
		

		// costruzione della query
		StringBuilder parametersBuilder = new StringBuilder();
		parametersBuilder.append("options=user:");
		parametersBuilder.append(utenteBpm);
		
		SearchQueryBuilder query = new SearchQueryBuilder(new ActivityInstanceIndex());
		query.criterion(ActivityInstanceIndex.STATE).equalsTo("READY");
		query.and();
		query.criterion(ActivityInstanceIndex.NAME).equalsTo(azione.getNomeTask());

		aggiungiCriteriPerSAC(parametriRicerca, azione, query, account.getStruttureAmministrativeContabili());
		aggiungiCriteriPerAnnoEsercizio(parametriRicerca, query);
		aggiungiCriteriPerEnteProprietario(parametriRicerca, query);

		parametersBuilder.append("&query=");
		parametersBuilder.append(query.toString());
		
		String parameters = parametersBuilder.toString();
		log.debug(methodName, "Parametri:\n" + parameters);
		
		// esecuzione della query BPM
		String risultatoServerBpm = "";
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			risultatoServerBpm = processResponse(connection);
		} catch (SystemException e) {
			res.setEsito(Esito.FALLIMENTO);
			res.setErrori(e.getErrori());
			return res;
		}

		// composizione della risposta
		if (risultatoInLista) {
			// unmarshalling della risposta
			@SuppressWarnings({ "unchecked" })
			List<LightActivityInstance> lRes = (List<LightActivityInstance>) xstream.fromXML(risultatoServerBpm);

			// salvataggio del risultato del server BPM nella risposta
			res.setTotali(lRes.size());
			for (LightActivityInstance activityInstance : lRes) {
				Map<String, VariabileProcesso> mapVars = getVariabiliDiProcesso(
						activityInstance.getProcessInstanceUUID().getValue(),
						nomiVariabiliDescrizione);

				AttivitaPendente attivitaPendente = new AttivitaPendente();

				Object valoreDescrizione = mapVars.get(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE)
						.getValore();
				if (valoreDescrizione != null)
					attivitaPendente.setDescrizione(valoreDescrizione.toString());

				Object valoreDescrizioneBreve = mapVars.get(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE_BREVE)
						.getValore();
				if (valoreDescrizioneBreve != null)
					attivitaPendente.setDescrizioneBreve(valoreDescrizioneBreve.toString());

				attivitaPendente.setIdAttivita(activityInstance.getUUID().getValue());
				attivitaPendente.setUrgente(activityInstance.getPriority() > 0);

				attivitaPendente.setAzione(parametriRicerca.getAzione().getAzione());
				
				//SIAC-6884 VIK
				GetTaskInfo getTaskInfoReq = new GetTaskInfo();
				getTaskInfoReq.setIdAttivita(attivitaPendente.getIdAttivita());
				GetTaskInfoResponse getTaskInfoRes = getTaskInfo(getTaskInfoReq);
				List<VariabileProcesso> variabiliProcesso = getTaskInfoRes.getVariabiliProcesso();
				for (VariabileProcesso variabile : variabiliProcesso) {
					if ("variazioneDiBilancioDem".equals(variabile.getNome())) {
						//System.out.println("load dati per variazione ... " + variabile.getValore());
						String uidVarBonita = (String) variabile.getValore();
						String[] parts = uidVarBonita.split("\\|");
						if(parts!= null && parts.length>1){
							String uidVAr = parts[0];
							Integer uidVAriaizoneSiac = Integer.parseInt(uidVAr);
							//QUERY....farne una con condizione in IN e manipolare i dati
							Variazione variazione = variazioniDad.findVariazioneByUid(uidVAriaizoneSiac);
							if(variazione!= null){
								attivitaPendente.setDataAperturaProposta(variazione.getDataApertura());
								attivitaPendente.setDataChiusuraProposta(variazione.getDataChiusura());
								attivitaPendente.setDirezioneProponente(variazione.getDirezione());
							}
						}
						break;
					}
				}
				
				
				
				
				res.getAttivitaPendenti().add(attivitaPendente);
			}

		} else {
			// unmarshalling della risposta
			int iRes = Integer.parseInt(risultatoServerBpm);

			// salvataggio del risultato del server BPM nella risposta
			// nel caso in cui usassi l'indice Activity occorrerebbe sottrarre
			// al risultato di bonita 1 perchè bonita rende sempre
			// un'istanza in più (erroneamente)
//			if(azione.getVerificaSAC()!=TipoVerificaSAC.SAC){
//				iRes=iRes==0?0:iRes-1;
//			}
			res.setTotali(iRes);
		}

		return res;
	}

	/**
	 * Se serve aggiunge alla query i parametri per SAC
	 * 
	 * @param parametriRicerca
	 * @param azione
	 * @param query
	 * @param sacs
	 */
	private void aggiungiCriteriPerSAC(
			ParametriRicercaTaskBpm parametriRicerca, Azione azione,
			SearchQueryBuilder query,
			List<StrutturaAmministrativoContabile> sacs) {
		final String methodName = "aggiungiCriteriPerSAC";
		log.debug(methodName, "FlagVerificaSac : " + azione.getFlagVerificaSac());

		if (azione.getFlagVerificaSac() ) {
			if (sacs.isEmpty()) {
				log.warn(methodName, "l'azione ha indicato come TipoVerifica SAC,"
						+ " ma l'AzioneConsentita non contiene SAC, quindi il controllo non è aggiunto");
			} else {
				query.and();
				query.leftParenthesis();
				boolean isFirst = true;
				for (StrutturaAmministrativoContabile struttura : sacs) {
					if (isFirst)
						isFirst = false;
					else
						query.or();
					query.criterion(ProcessInstanceIndex.VARIABLE_VALUE)
							.equalsTo(VariabileProcesso.PREFISSO_VALORE_VARIABILE_SAC + struttura.getUid());
				}
				query.rightParenthesis();
			}
		}
	}

	/**
	 * Se serve aggiunge alle query i parametri per l'Anno di Eservcizio
	 * 
	 * @param parametriRicerca
	 * @param azione
	 * @param query
	 */
	private void aggiungiCriteriPerAnnoEsercizio(ParametriRicercaTaskBpm parametriRicerca, SearchQueryBuilder query) {

		String annoEsercizio = parametriRicerca.getAnnoEsercizio();

		// l'anno di esercizio è specificato
		if (annoEsercizio != null && !"".equals(annoEsercizio)) {
			query.and();
			query.leftParenthesis();
			query.criterion(ProcessInstanceIndex.VARIABLE_VALUE).equalsTo(VariabileProcesso.PREFISSO_VALORE_VARIABILE_ANNO_ESERCIZIO + annoEsercizio);
			query.or();
			query.criterion(ProcessInstanceIndex.VARIABLE_VALUE).equalsTo(VariabileProcesso.VALORE_NULL_ANNO_ESERCIZIO);
			query.rightParenthesis();
		} else {
			// l'anno di esercizio non è specificato
			query.and();
			query.criterion(ProcessInstanceIndex.VARIABLE_VALUE).equalsTo(VariabileProcesso.VALORE_NULL_ANNO_ESERCIZIO);

		}
	}

	/**
	 * Aggiunge alle query i parametri per l'ente proprietario
	 * 
	 * @param parametriRicerca
	 * @param azione
	 * @param query
	 */
	private void aggiungiCriteriPerEnteProprietario(ParametriRicercaTaskBpm parametriRicerca, SearchQueryBuilder query) {

		Integer idEnteProprietario = parametriRicerca.getIdEnteProprietario();

		// l'idEnteProprietario è specificato
		if (idEnteProprietario != null && !idEnteProprietario.equals(0)) {
			query.and();
			query.leftParenthesis();
			query.criterion(ProcessInstanceIndex.VARIABLE_VALUE).equalsTo(VariabileProcesso.PREFISSO_VALORE_VARIABILE_ENTE_PROPRIETARIO + idEnteProprietario);
			query.or();
			query.criterion(ProcessInstanceIndex.VARIABLE_VALUE).equalsTo(VariabileProcesso.VALORE_NULL_ENTE_PROPRIETARIO);
			query.rightParenthesis();
		} else {
			// l'idEnteProprietario non è specificato
			query.and();
			query.criterion(ProcessInstanceIndex.VARIABLE_VALUE).equalsTo(VariabileProcesso.VALORE_NULL_ENTE_PROPRIETARIO);

		}
	}

	/**
	 * Ottiene la lista delle notifiche, che non sono un concetto di base del
	 * server bpm Bonita, ma che sono implementate in maniera custom.
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public RicercaNotifichePendentiResponse ricercaNotifichePendenti(RicercaNotifichePendenti request) {
		return new RicercaNotifichePendentiResponse();
	}

	/**
	 * Richiama l'avvio di un processo sul server bpm bonita, setta le n
	 * variabili di processo, nel caso di una gestione SAC imposta le variabili
	 * di istanza ed esegue la prima attività (che può anche non essere un TASK
	 * UMANO)
	 */
	@Override
	public AvviaProcessoResponse avviaProcesso(AvviaProcesso request) {
		AvviaProcessoResponse res = startProcesso(request);
		if (res.isFallimento())
			return res;

		// imposta le variabili di processo
		ServiceResponse sRes = setVariabiliDiProcesso(
				res.getIdDefinizioneProcesso(), res.getIdProcesso(),
				request.getVariabiliProcesso());
		if (sRes.isFallimento()) {
			res.setEsito(Esito.FALLIMENTO);
			res.setErrori(sRes.getErrori());
			return res;
		}

		// verifica il primo task da far partire
		List<ActivityInstance> tasks = getProssimaActivity(res.getIdProcesso(),
				true);
		if (tasks == null || tasks.isEmpty()) {
			String msg = new StringBuilder().append("Si e' verificato un errore sul server BPM. Tipo di errore riscontrato: ")
			.append(tasks==null? "errore durante la connessione." : " il server non ha fornito uno step successivo alla funzionalita' richiesta. ")
			.append("Il processo")
			.append( res.getIdProcesso())
			.append(" non puo' essere avviato.").toString();
			res.getErrori().add(ErroreCore.ERRORE_DI_SISTEMA.getErrore(msg));
			res.setEsito(Esito.FALLIMENTO);
			return res;
		}

		// esegue il task
		EseguiTask eseguiTaskReq = new EseguiTask();
		eseguiTaskReq.setIdTask(tasks.get(0).getUUID().getValue());
		eseguiTaskReq.setIdIstanzaProcesso(res.getIdProcesso());
		EseguiTaskResponse etRes = eseguiTask(eseguiTaskReq);

		// calcola la risposta
		if (etRes.isFallimento()) {
			res.setEsito(Esito.FALLIMENTO);
			res.setErrori(etRes.getErrori());
		} else {
			res.setIdTask(etRes.getIdTask());
			res.setNomeTask(etRes.getNomeTask());
		}

		return res;
	}

	/**
	 * Esegue semplicemente lo start del processo (NULL'ALTRO). E' utilizzato da
	 * avviaProcesso, che si preoccupa poi di impostare le variabili ed eseguire
	 * il primo task
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public AvviaProcessoResponse startProcesso(AvviaProcesso request) {
		final String methodName = "startProcesso";
		AvviaProcessoResponse res = new AvviaProcessoResponse();
		ProcessDefinition definizioneProcesso = getUltimaVersione(request
				.getNomeProcesso());
		if(definizioneProcesso == null) {
			res.setEsito(Esito.FALLIMENTO);
			res.setErrori(Arrays.asList(ErroreCore.ERRORE_DI_SISTEMA.getErrore("Impossibile avviare il processo sul server BPM.")));
			return res;
		}
		String uuidProcesso = definizioneProcesso.getUUID().getValue();

		// costruzione della url del comando BPM
		final String url = endpointRest + COMANDO_ISTANZA_PROCESSO + uuidProcesso;
		log.debug(methodName, "Url:\n" + url);

		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		String risultatoServerBpm = "";
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			risultatoServerBpm = processResponse(connection);
		} catch (SystemException e) {
			res.setEsito(Esito.FALLIMENTO);
			res.setErrori(e.getErrori());
			return res;
		}

		// calcola l'id del processo
		res.setIdProcesso(((ProcessInstanceUUID) xstream.fromXML(risultatoServerBpm)).getValue());

		return res;
	}

	/**
	 * Restituisce il descrittore della prossima attività umana eseguibile sul
	 * processo dato
	 * 
	 * @param idIstanzaProcesso
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityInstance> getProssimaActivity(String idIstanzaProcesso, boolean isTask) {
		final String methodName = "getProssimaActivity";
		List<ActivityInstance> res = new ArrayList<ActivityInstance>();

		// costruzione della url del comando BPM
		String url = endpointRest + QUERY_PROSSIMO_TASK + idIstanzaProcesso;
		log.debug(methodName, "Url:\n" + url);

		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		String risultatoServerBpm = "";
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			risultatoServerBpm = processResponse(connection);
		} catch (SystemException e) {
			log.error(methodName, "Errore nella getProssimaActivity: " + e.getMessage(), e);
			return null;
		}

		// unmarshalling della risposta
		for (ActivityInstance activityInstance : (Set<ActivityInstance>) xstream.fromXML(risultatoServerBpm)) {
			if (isTask && activityInstance.isTask() && activityInstance.getState().equals(ActivityState.READY)) {
				res.add(activityInstance);
			}

		}

		return res;
	}

	/**
	 * Restituisce il valore di una lista di variabili di processo.
	 * 
	 * Gli attributi descrizione e descrizioneBreve di una attività pendente,
	 * dovranno essere riempiti con le variabili di processo che si chiamano
	 * "descrizione" e "descrizioneBreve". In questo modo daremo la possibilità
	 * a chi scrive le web app di settare questi due campi che dovranno/potranno
	 * contenere testo con riferimenti ad oggetti di business specifici per il
	 * tipo di azione
	 * 
	 * Questo metodo serve allo scopo di reperire questi valori, con un'unica
	 * chiamata al server BPM.
	 * 
	 * @param idIstanzaProcesso
	 * @param nomeVariabile
	 * @return
	 */
	@Override
	public Map<String, VariabileProcesso> getVariabiliDiProcesso(String idIstanzaProcesso, List<String> nomiVariabili) {
		final String methodName = "getVariabiliDiProcesso";
		Map<String, VariabileProcesso> res = new HashMap<String, VariabileProcesso>();

		// costruzione della url del comando BPM
		String url = endpointRest + QUERY_VARS_DI_PROC + idIstanzaProcesso;
		log.debug(methodName, "Url:\n" + url);

		return getVariabili(nomiVariabili, res, url);
	}

	public Map<String, VariabileProcesso> getVariabiliDiIstanza(String idIstanzaAttivita, List<String> nomiVariabili) {
		final String methodName = "getVariabiliDiIstanza";
		Map<String, VariabileProcesso> res = new HashMap<String, VariabileProcesso>();

		// costruzione della url del comando BPM
		String url = endpointRest + QUERY_VARS_DI_ISTANZA + idIstanzaAttivita;
		log.debug(methodName, "Url:\n" + url);

		return getVariabili(nomiVariabili, res, url);
	}

	private Map<String, VariabileProcesso> getVariabili(List<String> nomiVariabili, Map<String, VariabileProcesso> res, String url) {
		final String methodName = "getVariabili";
		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		String risultatoServerBpm = "";
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			risultatoServerBpm = processResponse(connection);
		} catch (SystemException e) {
			log.error(methodName, "Errore nella getVariabileDiProcesso: " + e.getMessage(), e);
			return res;
		}

		// unmarshalling della risposta
		@SuppressWarnings("unchecked")
		Map<String, Object> mapVariabili = (Map<String, Object>) xstream
				.fromXML(risultatoServerBpm);
		for (String nomeVariabile : nomiVariabili) {
			if (mapVariabili.containsKey(nomeVariabile)) {
				VariabileProcesso var = new VariabileProcesso();
				var.setNome(nomeVariabile);
				var.setValore(mapVariabili.get(nomeVariabile));
				res.put(nomeVariabile, var);
			}
		}

		return res;
	}
	
	@Override
	public Map<String, VariabileProcesso> getVariabiliDiProcesso(String idIstanzaProcesso) {
		final String methodName = "getVariabiliDiProcesso";
		// costruzione della url del comando BPM
		String url = endpointRest + QUERY_VARS_DI_PROC + idIstanzaProcesso;
		log.debug(methodName, "Url:\n" + url);
		return getVariabili(url);
	}
	
	private Map<String, VariabileProcesso> getVariabili(String url) {
		final String methodName = "getVariabili";
		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		String risultatoServerBpm = "";
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			risultatoServerBpm = processResponse(connection);
		} catch (SystemException e) {
			log.error(methodName, "Errore nella getVariabileDiProcesso: " + e.getMessage(), e);
			throw e;
		}

		// unmarshalling della risposta
		@SuppressWarnings("unchecked")
		Map<String, Object> mapVariabili = (Map<String, Object>) xstream
				.fromXML(risultatoServerBpm);
		Map<String, VariabileProcesso> res = new HashMap<String, VariabileProcesso>();
		
		for (Entry<String, Object> entry : mapVariabili.entrySet()) {		
				VariabileProcesso var = new VariabileProcesso();
				var.setNome(entry.getKey());
				var.setValore(entry.getValue());
				res.put(entry.getKey(), var);
			
		}

		return res;
	}

	/**
	 * Assegna il valore ad una lista di variabili di processo
	 * 
	 * @param variabiliDiProcesso
	 */
	@Override
	public ServiceResponse setVariabiliDiProcesso(String idDefinizioneProcesso, String idIstanzaProcesso, List<VariabileProcesso> variabiliDiProcesso) {
		final String methodName = "setVariabiliDiProcesso";
		if (variabiliDiProcesso.isEmpty()) {
			log.warn(methodName, "Non vengono impostate le variabili di processo perchè la lista è vuota");
			return new ServiceResponse() {
				// Empty
			};
		}

		// costruzione della url del comando BPM
		String url = endpointRest + COMANDO_SET_VAR_DI_PROC + idIstanzaProcesso;
		return loopSetVar(variabiliDiProcesso, url);
	}

	/**
	 * Loop di set del valore delle variabili, condiviso tra il set delle
	 * variabili di proc e di istanza
	 * 
	 * @param variabiliDiProcesso
	 * @param res
	 * @param url
	 */
	private ServiceResponse loopSetVar(List<VariabileProcesso> variabiliDiProcesso, String url) {
		final String methodName = "loopSetVar";
		ServiceResponse res = new ServiceResponse() {
			// Empty
		};

		for (VariabileProcesso variabileProcesso : variabiliDiProcesso) {
			String nomeVariabileProcesso = variabileProcesso.getNome();

			String sValoreVariabileProcesso = "";

			// TODO AL: vedere come settare l'array di valori
			// corrisponde a
			// http://www.bonitasoft.org/docs/javadoc/bpm_engine/5.9/org/ow2/bonita/facade/RuntimeAPI.html#setProcessInstanceVariable
			// %28org.ow2.bonita.facade.uuid.ProcessInstanceUUID,%20java.lang.String,%20java.lang.Object%29
			// dove però sembra sia possibile impostare anche array di valori
			Object valoreVariabileProcesso = variabileProcesso.getValore();
			if (valoreVariabileProcesso instanceof ArrayList<?>) {
				log.warn(methodName, "la variabile " + nomeVariabileProcesso + " ha un valore array non al momento supportato.");
				break;
			}
			try {
				sValoreVariabileProcesso = valoreVariabileProcesso + "";
				
				// // FIXME AL: l'euro è sostituito con Eu
				sValoreVariabileProcesso = sValoreVariabileProcesso
						.replaceAll(caratterePerRimpiazzareEu, "Euro");
				sValoreVariabileProcesso = URLEncoder.encode(
						sValoreVariabileProcesso, "ISO-8859-1");
				log.debug(methodName, "stringa urlencodata: " + sValoreVariabileProcesso);
				
			} catch (Exception e) {
				log.error(methodName, "ERRORE nell'encoding di " + valoreVariabileProcesso + ": " + e.getMessage(), e);
			}

			String parameters = "variableValue=" + sValoreVariabileProcesso
					+ "&variableId=" + nomeVariabileProcesso + "&options=user:"
					+ utenteBpm;
			log.debug(methodName, "Url:\n" + url);
			log.debug(methodName, "Parametri:\n" + parameters);

			// esecuzione della query BPM
			try {
				HttpURLConnection connection = getConnection(url, parameters);
				processResponse(connection);
			} catch (SystemException e) {
				res.setEsito(Esito.FALLIMENTO);
				res.setErrori(e.getErrori());
				return res;
			}
		}
		return res;
	}

	/**
	 * Set delle variabili di istanza
	 * 
	 * @param idIstanzaActivity
	 * @param variabiliDiProcesso
	 * @return
	 */
	public ServiceResponse setVariabiliDiIstanza(String idIstanzaActivity, List<VariabileProcesso> variabiliDiProcesso) {
		final String methodName = "setVariabiliDiIstanza";
		if (variabiliDiProcesso.isEmpty()) {
			log.warn(methodName, "Non vengono impostate le variabili di processo perchè la lista è vuota");
			return new ServiceResponse() {
				// Empty
			};
		}

		// costruzione della url del comando BPM
		String url = endpointRest + COMANDO_SET_VAR_DI_ISTANZA
				+ idIstanzaActivity;

		return loopSetVar(variabiliDiProcesso, url);
	}

	/**
	 * Restituisce il descrittore dell'ultima versione di un processo, dato il
	 * nome. Ha accesso package per poterlo testare con un test junit
	 * 
	 * @param nomeProcesso
	 * @return
	 */
	@Override
	public ProcessDefinition getUltimaVersione(String nomeProcesso) {
		final String methodName = "getUltimaVersione";
		// costruzione della url del comando BPM
		String url = endpointRest + QUERY_PROCESSO_PIU_RECENTE + nomeProcesso;
		log.debug(methodName, "Url:\n" + url);

		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		String risultatoServerBpm = "";
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			risultatoServerBpm = processResponse(connection);
		} catch (SystemException e) {
			log.error(methodName, "Errore nella getUltimaVersione: " + e.getMessage(), e);
			return null;
		}

		// unmarshalling della risposta
		return (ProcessDefinition) xstream.fromXML(risultatoServerBpm);
	}

	/**
	 * Esegue un task di un processo. Dopo averlo eseguito copia il valore delle
	 * variabili di processo nelle variabili di istanza relative.
	 * 
	 * Nel caso della variabile contenente l'anno di esercizio, può succedere
	 * che il processo non gestisca l'anno di esercizio e quindi il valore che
	 * viene usato per questa variabile è quello da
	 * VariabileProcesso.VALORE_NULL_ANNO_ESERCIZIO
	 * 
	 * @see VariabileProcesso.VALORE_NULL_ANNO_ESERCIZIO
	 */
	@Override
	public EseguiTaskResponse eseguiTask(EseguiTask request) {
		final String methodName = "eseguiTask";
		EseguiTaskResponse res = new EseguiTaskResponse();

		// costruzione della url del comando BPM
		// NOTA: serve aggiungere il parametro booleano altrimenti il
		// server rest non è in grado di interpretare la richiesta, restituendo
		// un HTTP 500
		String url = endpointRest + COMANDO_ESEGUE_TASK + request.getIdTask()
				+ "/true";
		log.debug(methodName, "Url:\n" + url);

		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			processResponse(connection);
		} catch (SystemException e) {
			res.setEsito(Esito.FALLIMENTO);
			res.setErrori(e.getErrori());
			return res;
		}

		if (!res.isFallimento()) {
			List<String> vars = new ArrayList<String>();
			vars.add(VariabileProcesso.NOME_VARIABILE_SAC_PROCESSO);
			vars.add(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO);
			vars.add(VariabileProcesso.NOME_VARIABILE_ENTE_PROPRIETARIO_PROCESSO);

			log.debug(methodName, "variabili da recuperare:" + vars);
			Map<String, VariabileProcesso> mapVars = getVariabiliDiProcesso(request.getIdIstanzaProcesso(), vars);

			// recupero i prossimi task
			List<ActivityInstance> tasks = getProssimaActivity(request.getIdIstanzaProcesso(), true);

			if (!tasks.isEmpty()) {
				// imposto la var di istanza SAC con il valore di quella di processo
				copiaVarIstanzaDaVarProcesso(mapVars, tasks,
						VariabileProcesso.NOME_VARIABILE_SAC_ATTIVITA,
						VariabileProcesso.NOME_VARIABILE_SAC_PROCESSO);
	
				// imposto la var di istanza Ente con il valore di quella di
				// processo
				copiaVarIstanzaDaVarProcesso(
						mapVars,
						tasks,
						VariabileProcesso.NOME_VARIABILE_ENTE_PROPRIETARIO_ATTIVITA,
						VariabileProcesso.NOME_VARIABILE_ENTE_PROPRIETARIO_PROCESSO);

				// se il processo non ha la variabile anno di esercizio o è vuota,
				// la setto con il valore di default
				if (!mapVars.containsKey(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO)
						|| mapVars.get(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO).getValore() == null
						|| "".equals(mapVars.get(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO).getValore())) {
					VariabileProcesso var = new VariabileProcesso();
					var.setNome(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO);
					var.setValore(VariabileProcesso.VALORE_NULL_ANNO_ESERCIZIO);
					mapVars.put(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO, var);
				}

				// imposto la var di istanza AnnoEsercizio con il valore di quella
				// di processo
				copiaVarIstanzaDaVarProcesso(mapVars, tasks,
					VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_ATTIVITA,
					VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO);

				//salvo le info del task successivo
				res.setIdTask(tasks.get(0).getUUID().getValue());
				res.setNomeTask(tasks.get(0).getActivityName());
			}
		}

		// unmarshalling della risposta
		return res;
	}

	/**
	 * Copia le variabili di processo nelle relative variabili di istanza del
	 * passo successivo
	 * 
	 * @param mapVars
	 * @param tasks
	 * @param nomeVarIstanza
	 * @param nomeVarProcesso
	 */
	@Override
	public void copiaVarIstanzaDaVarProcesso(
			Map<String, VariabileProcesso> mapVars,
			List<ActivityInstance> tasks, String nomeVarIstanza,
			String nomeVarProcesso) {
		if (mapVars.containsKey(nomeVarProcesso)
				&& mapVars.get(nomeVarProcesso).getValore() != null) {
			VariabileProcesso v = new VariabileProcesso();
			v.setNome(nomeVarIstanza);
			v.setValore(mapVars.get(nomeVarProcesso).getValore());
			List<VariabileProcesso> varProcs = new ArrayList<VariabileProcesso>();
			varProcs.add(v);
			// imposto la variabile d'istanza dei prossimi passi
			for (ActivityInstance activityInstance : tasks) {
				setVariabiliDiIstanza(activityInstance.getUUID().getValue(),
						varProcs);
			}
		}
	}

	/**
	 * Restituisce le informazioni del task associato ad un'attività pendente,
	 * in particolare la lista delle variabili di processo (con il relativo
	 * valore).
	 */
	@Override
	public GetTaskInfoResponse getTaskInfo(GetTaskInfo getTaskInfoReq) {
		GetTaskInfoResponse res = new GetTaskInfoResponse();
		String idIstanzaAttivita = getTaskInfoReq.getIdAttivita();

		// ottiene l'id di processo
		ActivityInstance activityInstance = getDescrittoreAttivita(idIstanzaAttivita);
		if (activityInstance == null)
			return res;
		String idIstanzaProcesso = activityInstance.getProcessInstanceUUID()
				.getValue();
		String idDefinizioneProcesso = activityInstance
				.getProcessDefinitionUUID().getValue();

		// ottiene la lista delle varibili
		ProcessDefinition processDefinition = getDescrittoreProcesso(idDefinizioneProcesso);
		Set<DataFieldDefinition> dataFieldDefSet = processDefinition
				.getDataFields();
		List<String> nomiVariabili = new ArrayList<String>();
		for (DataFieldDefinition dataFieldDefinition : dataFieldDefSet) {
			nomiVariabili.add(dataFieldDefinition.getName());
		}

		// ottiene il valore delle variabili di processo e copia il valore nella
		// risposta
		res.getVariabiliProcesso().addAll(
				getVariabiliDiProcesso(idIstanzaProcesso, nomiVariabili)
						.values());
		return res;
	}

	/**
	 * Dato l'id di definizione del processo, ritorna il descrittore del
	 * processo
	 * 
	 * @param idDefinizioneProcesso
	 * @return
	 */
	private ProcessDefinition getDescrittoreProcesso(String idDefinizioneProcesso) {
		final String methodName = "getDescrittoreProcesso";
		String risultatoServerBpm;

		// costruzione della url del comando BPM
		String url = endpointRest + QUERY_DESC_PROCESSO + idDefinizioneProcesso;
		log.debug(methodName, "Url:\n" + url);

		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			risultatoServerBpm = processResponse(connection);
		} catch (SystemException e) {
			log.error(methodName, "Errore nella getIdIstanzaProcesso: " + e.getMessage(), e);
			return null;
		}

		// unmarshalling della risposta
		return (ProcessDefinition) xstream.fromXML(risultatoServerBpm);
	}

	/**
	 * dato un'id di istanza di attività, restituisce l'id di istanza di
	 * processo corrispondente
	 */
	@Override
	public String getIdIstanzaProcesso(String idIstanzaAttivita) {
		ActivityInstance activity = getDescrittoreAttivita(idIstanzaAttivita);
		if (activity != null)
			return activity.getProcessInstanceUUID().getValue();
		return null;
	}

	/**
	 * Dato l'id di un'istanza di attività, ne ritorna il descrittore
	 * 
	 * @param idIstanzaAttivita
	 * @return
	 */
	private ActivityInstance getDescrittoreAttivita(String idIstanzaAttivita) {
		final String methodName = "getDescrittoreAttivita";
		String risultatoServerBpm;

		// costruzione della url del comando BPM
		String url = endpointRest + QUERY_GET_ACTIVITY + idIstanzaAttivita;
		log.debug(methodName, "Url:\n" + url);

		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			risultatoServerBpm = processResponse(connection);
		} catch (SystemException e) {
			log.error(methodName, "Errore nella getIdIstanzaProcesso: " + e.getMessage(), e);
			return null;
		}

		// unmarshalling della risposta
		return (ActivityInstance) xstream.fromXML(risultatoServerBpm);
	}

	/**
	 * Cancella un'istanza di processo
	 * 
	 * @param idIstanzaProcesso
	 * @return
	 */
	public boolean cancellaProcessInstance(String idIstanzaProcesso) {
		final String methodName = "cancellaProcessInstance";

		// costruzione della url del comando BPM
		String url = endpointRest + COMANDO_CANCELLA_ISTANZA_PROCESSO
				+ idIstanzaProcesso;
		log.debug(methodName, "Url:\n" + url);

		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			processResponse(connection);
		} catch (SystemException e) {
			log.error(methodName, "Errore nella cancellaProcessInstance: " + e.getMessage(), e);
			return false;
		}

		// unmarshalling della risposta
		return true;
	}

	@SuppressWarnings("unchecked")
	public Set<ProcessInstance> getIstanzeProcesso(String idProcesso) {
		final String methodName = "getIstanzeProcesso";
		Set<ProcessInstance> res = new HashSet<ProcessInstance>();

		// costruzione della url del comando BPM
		String url = endpointRest + QUERY_ISTANZE_DI_PROC + idProcesso;
		log.debug(methodName, "Url:\n" + url);

		// costruzione del comando
		String parameters = "options=user:" + utenteBpm;
		log.debug(methodName, "Parametri:\n" + parameters);

		// esecuzione della query BPM
		String risultatoServerBpm;
		try {
			HttpURLConnection connection = getConnection(url, parameters);
			risultatoServerBpm = processResponse(connection);
		} catch (SystemException e) {
			log.error(methodName, "Errore nella getIstanzeProcesso: " + e.getMessage(), e);
			return res;
		}

		// unmarshalling della risposta
		return (Set<ProcessInstance>) xstream.fromXML(risultatoServerBpm);
	}

	/**
	 * Al momento questo metodo non è implementato
	 */
	@Override
	public TerminaProcessoResponse terminaProcesso(TerminaProcesso request) {
		return null;
	}

	/**
	 * Costruisce la connessione verso il server REST bonita
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 * @throws SystemException
	 */
	private HttpURLConnection getConnection(final String url, final String parameters) {
		try {
			final HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty(
				"Authorization",
				"Basic " + Base64.encodeBytes("restuser:restbpm".getBytes()));

			final DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			byte[] bytes = parameters.getBytes("ISO-8859-1");

			output.write(bytes);
			output.flush();
			output.close();
			connection.disconnect();

			return connection;
		} catch (Exception e) {
			throw new SystemException(ErroreCore.ERRORE_DI_SISTEMA.getErrore("Il server BPM ha restituito " + e.getMessage()));
		}
	}

	/**
	 * Elabora la risposta ottenuta e nel caso di successo ritorna la stringa
	 * risultato
	 * 
	 * @param connection
	 * @throws IOException
	 * @throws SystemException
	 */
	private String processResponse(HttpURLConnection connection) {
		final String methodName = "processResponse";
		try {
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK
					&& responseCode != HttpURLConnection.HTTP_NO_CONTENT) {
				
				String res = readStream(connection.getErrorStream());
				if(res == null) {
					res = readStream(connection.getInputStream());
				}
				String msg= new StringBuilder()
						.append("Il server BPM ha restituito codice HTTP: ")
						.append(responseCode)
						.append( " ( message: ")
						.append(StringUtils.isNotBlank(res)? res : "null")
						.append(" ) ")
						.toString();
				log.error(methodName, msg);
				throw new SystemException(ErroreCore.ERRORE_DI_SISTEMA.getErrore(msg.toString()));
			}
			String res = readStream(connection.getInputStream());
			
			if (res.length() > MAX_LEN_RISPOSTA_HTTP && TAGLIA_RIPOSTA_HTTP)
				log.debug(methodName, "Risposta HTTP:\n" + res.substring(0, 2000)
				+ "\n....");
			else
				log.debug(methodName, "Risposta HTTP:\n" + res);
			
			return res;
		} catch (SystemException se) {
			throw se;
		} catch (Exception e) {
			String msgExceptionGenerica = "Il server BPM ha restituito " + e.getMessage();
			log.error(methodName, msgExceptionGenerica);
			throw new SystemException(ErroreCore.ERRORE_DI_SISTEMA.getErrore(msgExceptionGenerica));
		}
	}
	
	private String readStream(InputStream is) throws IOException {
		if(is == null) {
			return null;
		}
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuilder response = new StringBuilder();
		try {
			while ((line = reader.readLine()) != null) {
				response.append(line);
				response.append('\n');
			}
		} finally {
			reader.close();
			is.close();
		}

		return response.toString().trim();
	}

	public void setEndpointRest(String endpointRest) {
		this.endpointRest = endpointRest;
	}

	public void setUtenteBpm(String utenteBpm) {
		this.utenteBpm = utenteBpm;
	}

}