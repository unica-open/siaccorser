/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.ow2.bonita.facade.def.majorElement.ProcessDefinition;
import org.ow2.bonita.facade.runtime.ActivityInstance;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcesso;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcessoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTask;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTaskResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.GetTaskInfo;
import it.csi.siac.siaccorser.integration.bpm.msg.GetTaskInfoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.ParametriRicercaTaskBpm;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendenti;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendentiResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.TipoRicercaTaskBpm;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.AzioneConsentita;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;
import it.csi.siac.siaccorser.model.TipoVerificaSAC;
import it.csi.siac.siaccorser.model.VariabileProcesso;


/**
 * Test del client REST delle api bonita.
 * &Eacute; fatto:
 * <ul>
 *     <li>puntando al server il cui endpoint &eacute; configurato nell'application context</li>
 *     <li>facendo riferimento al processo</li>
 * </ul>
 * http://subversion.csi.it:10090/dsp/siac/siacbilpro/trunk/src/xml/VariazioneDiBilancio--1.0.bar
 * in particolare alla release 1176
 * 
 * @author alagna
 * @version $Id: $
 */
public class BpmServiceTest extends BaseJunit4TestCase{
	
	@Autowired
	private BpmService bpmService;

	private static String NOME_PROCESSO = "VariazioneDiBilancio";
	
	@Test
	public void getUltimaVersione() {
		final String methodName = "getUltimaVersione";
		ProcessDefinition ultimaVersione = bpmService.getUltimaVersione(NOME_PROCESSO);
		assertNotNull(ultimaVersione);
		log.info(methodName, ToStringBuilder.reflectionToString(ultimaVersione));
	}
	
	@Test
	public void eseguiTaskTest(){
		final String methodName = "eseguiTaskTest";
		// avvio un processo
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);
		AvviaProcessoResponse res = bpmService.startProcesso(req);
		assertTrue("errore nell'avviare il processo", !res.isFallimento());
		String idIstanzaProcesso = res.getIdProcesso();
		
		// ottengo il primo task
		ActivityInstance task = bpmService.getProssimaActivity(idIstanzaProcesso, true).get(0);
		assertTrue("impossibile ottenere il prossimo task del processo", task!=null);
		
		// lo eseguo
		log.debug(methodName, "eseguiTaskTest");
		EseguiTask etReq = new EseguiTask();
		etReq.setIdTask(task.getUUID().getValue());
		etReq.setIdIstanzaProcesso(idIstanzaProcesso);
		EseguiTaskResponse etRes = bpmService.eseguiTask(etReq);
		assertTrue("FALLIMENTO: "+ etRes.toString(), !etRes.isFallimento());
	}
	
	/**
	 * Ricerca la lista degli elementi
	 */
	@Test
	public void ricercaAttivitaPendentiListaTest(){
		final String methodName = "ricercaAttivitaPendentiListaTest";
		log.debug(methodName, "ricercaAttivitaPendentiListaTest 1");
		ricercaAttivita(avviaProcesso(true), TipoRicercaTaskBpm.ATTIVITA, 0, 0, 1);
		
		log.debug(methodName, "ricercaAttivitaPendentiListaTest 2");
		ricercaAttivita(avviaProcesso(false), TipoRicercaTaskBpm.ATTIVITA, 1, 1, 2);
	}

	/**
	 * ricerca il totale degli elementi
	 */
	@Test
	public void ricercaAttivitaPendentiTotaleTest(){
		final String methodName = "ricercaAttivitaPendentiTotaleTest";
		log.debug(methodName, "ricercaAttivitaPendentiTotaleTest 1");
		ricercaAttivita(avviaProcesso(true), TipoRicercaTaskBpm.TOTALE_ATTIVITA, 0, 0, 1);
		
		log.debug(methodName, "ricercaAttivitaPendentiTotaleTest 2");
		ricercaAttivita(avviaProcesso(false), TipoRicercaTaskBpm.TOTALE_ATTIVITA, 1, 1, 2);
	}

	
	private void ricercaAttivita(ActivityInstance task, TipoRicercaTaskBpm tipoRicercaTaskBpm, int istanzeSenzaControlli, int istanzeSac, int istanzeSacAe) {
		final String methodName = "ricercaAttivita";
		// ricerca senza controlli
		RicercaAttivitaPendenti rapReq = new RicercaAttivitaPendenti();
		ParametriRicercaTaskBpm parametriRicerca = new ParametriRicercaTaskBpm();
		parametriRicerca.setTipoRicerca(tipoRicercaTaskBpm);
		parametriRicerca.setOffset(0);
		parametriRicerca.setSize(10);
		setParametri(rapReq, parametriRicerca, task.getActivityName(), false);
		rapReq.setParametriRicerca(parametriRicerca);
		
		RicercaAttivitaPendentiResponse rapRes = bpmService.ricercaAttivitaPendenti(rapReq);
		assertTrue(!rapRes.isFallimento());
		assertTrue(rapRes.getTotali() == istanzeSenzaControlli);
		log.debug(methodName, "ricerca senza controlli: " + rapRes.getTotali());
		
		// ricerca con controllo SAC
		Azione azione = new Azione();
		azione.setNomeTask(task.getActivityName());
		azione.setVerificaSAC(TipoVerificaSAC.SAC);
		AzioneConsentita azioneConsentita = new AzioneConsentita();
		azioneConsentita.setAzione(azione);
		parametriRicerca.setAzione(azioneConsentita);
		rapRes = bpmService.ricercaAttivitaPendenti(rapReq);
		assertTrue(!rapRes.isFallimento());
		assertTrue(rapRes.getTotali()==istanzeSac);
		log.debug(methodName, "ricerca con controllo SAC: " + rapRes.getTotali());
		
		// ricerca con controllo SAC e annoEsercizio
		parametriRicerca.setAnnoEsercizio("2015");
		rapRes = bpmService.ricercaAttivitaPendenti(rapReq);
		assertTrue(!rapRes.isFallimento());
		assertTrue(rapRes.getTotali()==istanzeSacAe);
		log.debug(methodName, "ricerca con controllo SAC e anno: " + rapRes.getTotali());
	}

	/**
	 * Avvia un processo
	 * @return
	 */
	private ActivityInstance avviaProcesso(boolean conAnnoEsercizio) {
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);
		StrutturaAmministrativoContabile sac = new StrutturaAmministrativoContabile();
		sac.setUid(0);
		req.getStrutture().add(sac);
		
		VariabileProcesso var = new VariabileProcesso();
		var.setNome(VariabileProcesso.NOME_VARIABILE_SAC_PROCESSO);
		var.setValore(0);
		req.getVariabiliProcesso().add(var);
		
		if(conAnnoEsercizio) {
			VariabileProcesso var1 = new VariabileProcesso();
			var1.setNome(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO);
			var1.setValore(VariabileProcesso.PREFISSO_VALORE_VARIABILE_ANNO_ESERCIZIO+2015);
			req.getVariabiliProcesso().add(var1);
		}
		
		AvviaProcessoResponse res = bpmService.avviaProcesso(req);
		assertTrue("errore nell'avviare il processo", !res.isFallimento());
		String idIstanzaProcesso = res.getIdProcesso();
		
		// ottengo il primo task

		ActivityInstance task = bpmService.getProssimaActivity(idIstanzaProcesso, true).get(0);
		return task;
	}

	
	/**
	 * ricerca il totale di elementi (e non deve trovare nulla)
	 */
	@Test
	public void ricercaAttivitaPendentiTotaleNokTest(){
		final String methodName = "ricercaAttivitaPendentiTotaleNokTest";
		log.debug(methodName, "ricercaAttivitaPendentiTotaleNokTest");
		RicercaAttivitaPendenti req = new RicercaAttivitaPendenti();
		ParametriRicercaTaskBpm parametriRicerca = new ParametriRicercaTaskBpm();
		parametriRicerca.setTipoRicerca(TipoRicercaTaskBpm.TOTALE_ATTIVITA);
		setParametri(req, parametriRicerca, "Foo", false);
		req.setParametriRicerca(parametriRicerca);
		RicercaAttivitaPendentiResponse res = bpmService.ricercaAttivitaPendenti(req);
		assertTrue(!res.isFallimento());
		assertTrue(res.getTotali()==0);
	}

	/**
	 * Imposta i parametri per tutte le query
	 * @param rapReq 
	 * 
	 * @param parametriRicerca
	 * @param nomeTask
	 * @param verificaSAC
	 */
	private void setParametri(RicercaAttivitaPendenti rapReq, ParametriRicercaTaskBpm parametriRicerca, String nomeTask, 
		boolean verificaSAC) {
		Azione azione = new Azione();
		azione.setNomeProcesso(NOME_PROCESSO);
		azione.setNomeTask(nomeTask);
		if (verificaSAC)
			azione.setVerificaSAC(TipoVerificaSAC.SAC);
		AzioneConsentita azioneConsentita = new AzioneConsentita();
		azioneConsentita.setAzione(azione);
		parametriRicerca.setAzione(azioneConsentita);
		
		if(rapReq.getRichiedente() == null) {
			rapReq.setRichiedente(new Richiedente());
		}
		if(rapReq.getRichiedente().getAccount() == null) {
			rapReq.getRichiedente().setAccount(new Account());
		}
		StrutturaAmministrativoContabile sac = new StrutturaAmministrativoContabile();
		sac.setUid(0);
		rapReq.getRichiedente().getAccount().getStruttureAmministrativeContabili().add(sac);
	}
	

	/**
	 * Avvia un processo senza variabile
	 */
	@Test
	public void avviaProcessoSenzaVarTest(){
		final String methodName = "avviaProcessoSenzaVarTest";
		log.debug(methodName, "avviaProcessoSenzaVarTest");
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);

		AvviaProcessoResponse res = bpmService.avviaProcesso(req);
		assertTrue("FALLIMENTO: "+ res.toString(), !res.isFallimento());
	}
	
	/**
	 * Avvia un processo con SAC
	 */
	@Test
	public void avviaProcessoConSacTest(){
		final String methodName = "avviaProcessoConSacTest";
		log.debug(methodName, "avviaProcessoConSacTest");
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);

		VariabileProcesso var = new VariabileProcesso();
		var.setNome(VariabileProcesso.NOME_VARIABILE_SAC_PROCESSO);
		var.setValore(0);
		req.getVariabiliProcesso().add(var);
		AvviaProcessoResponse res = bpmService.avviaProcesso(req);
		assertTrue("FALLIMENTO: "+ res.toString(), !res.isFallimento());
	}
	
	/**
	 * Avvia un processo con SAC e AE
	 */
	@Test
	public void avviaProcessoConSacEAeTest(){
		final String methodName = "avviaProcessoConSacEAeTest";
		log.debug(methodName, "avviaProcessoConSacEAeTest");
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);

		VariabileProcesso var = new VariabileProcesso();
		var = new VariabileProcesso();
		var.setNome(VariabileProcesso.NOME_VARIABILE_SAC_PROCESSO);
		var.setValore(0);
		req.getVariabiliProcesso().add(var);
		VariabileProcesso var1 = new VariabileProcesso();
		var1.setNome(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO);
		var1.setValore("2015");
		req.getVariabiliProcesso().add(var1);
		AvviaProcessoResponse res = bpmService.avviaProcesso(req);
		assertTrue("FALLIMENTO: "+ res.toString(), !res.isFallimento());
	}
	
	/**
	 * Avvia un processo con AE
	 */
	@Test
	public void avviaProcessoConAeTest(){
		final String methodName = "avviaProcessoConAeTest";
		log.debug(methodName, "avviaProcessoConAeTest");
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);

		VariabileProcesso var1 = new VariabileProcesso();
		var1.setNome(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO);
		var1.setValore("2015");
		req.getVariabiliProcesso().add(var1);
		AvviaProcessoResponse res = bpmService.avviaProcesso(req);
		assertTrue("FALLIMENTO: "+ res.toString(), !res.isFallimento());
	}
	
	@Test
	public void getUuidUltimaVersioneTest(){
		final String methodName = "getUuidUltimaVersioneTest";
		log.debug(methodName, "getUuidUltimaVersioneTest");
		String res = bpmService.getUltimaVersione(NOME_PROCESSO).getUUID().getValue();
		assertTrue("FALLIMENTO", res != null);
	}
	
	@Test
	public void setVariabiliDiProcessoTest(){
		final String methodName = "setVariabiliDiProcessoTest";
		log.debug(methodName, "file encoding " + System.getProperty("file.encoding"));
		List<VariabileProcesso> variabiliDiProcesso = new ArrayList<VariabileProcesso>();
		VariabileProcesso v = new VariabileProcesso();
		v.setNome(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE);
		v.setValore("descrizione lunga");
		variabiliDiProcesso.add(v);
		
		VariabileProcesso v1 = new VariabileProcesso();
		v1.setNome(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE_BREVE);
		// Al momento non riesco a fare l'encoding del simbolo dell'eu, lo sostituisco quindi con la stringa "Eu"
		final String DESCRIZIONE_COMPLESSA = 
			"à è ì ò ù ! \" £ $ % & / ( ) + - < > * [] {} # @ = ' ? ^";
		v1.setValore(DESCRIZIONE_COMPLESSA+" €");
		
//		v1.setValore("à è ì ò ù");

		variabiliDiProcesso.add(v1);
		
		VariabileProcesso v2 = new VariabileProcesso();
		v2.setNome(VariabileProcesso.NOME_VARIABILE_SAC_PROCESSO);
		v2.setValore("SIAC-SAC-XXXXX");
		variabiliDiProcesso.add(v2);
		
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);
		AvviaProcessoResponse res = bpmService.startProcesso(req);

		log.debug(methodName, "setVariabiliDiProcessoTest");
		ServiceResponse vdpRes = bpmService.setVariabiliDiProcesso(
			res.getIdDefinizioneProcesso(),
			res.getIdProcesso(),
			variabiliDiProcesso);
		assertTrue("FALLIMENTO: "+ vdpRes.toString(), !vdpRes.isFallimento());
		
		// verifico il valore restituito dalla variabile con i valori particolari
		List<String> nomiVariabili = new ArrayList<String>();
		nomiVariabili.add(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE_BREVE);
		Map<String, VariabileProcesso> mapVars = 
			bpmService.getVariabiliDiProcesso(res.getIdProcesso(), nomiVariabili);

		String valoreDescrizioneComplessa = 
			mapVars.get(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE_BREVE).getValore()+"";
		log.debug(methodName, "Ottenuta la descrizione complessa: "+ valoreDescrizioneComplessa);
		assertTrue("La descrizione ottenuta non è uguale a quella impostata", 
			valoreDescrizioneComplessa.equals(DESCRIZIONE_COMPLESSA+" Euro"));
	}
	
	@Test
	public void getVariabileDiProcessoTest(){
		final String methodName = "getVariabileDiProcessoTest";
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);
		AvviaProcessoResponse res = bpmService.avviaProcesso(req);
		
		log.debug(methodName, "getVariabileDiProcessoTest");
		List<String> nomiVariabili = new ArrayList<String>();
		nomiVariabili.add(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE);
		nomiVariabili.add(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE_BREVE);
		Map<String, VariabileProcesso> mapRes = bpmService.getVariabiliDiProcesso(res.getIdProcesso(), nomiVariabili);
		log.debug(methodName, "trovate le seguenti tra le variabili cercate:" + mapRes);
		assertTrue("Variabili non trovate.", mapRes.size()==nomiVariabili.size());
	}
	
	@Test
	public void getIdIstanzaDiProcessoTest(){
		final String methodName = "getIdIstanzaDiProcessoTest";
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);
		AvviaProcessoResponse res = bpmService.startProcesso(req);

		List<ActivityInstance> activities = bpmService.getProssimaActivity(res.getIdProcesso(), true);
		String idIstanzaAttivita = activities.get(0).getUUID().getValue();
		
		log.debug(methodName, "getIdIstanzaDiProcessoTest");
		String idIstanzaProcesso = bpmService.getIdIstanzaProcesso(idIstanzaAttivita);
		assertTrue(res.getIdProcesso().equals(idIstanzaProcesso));
	}
	
	@Test
	public void getTaskInfoTest(){
		final String methodName = "getTaskInfoTest";
		AvviaProcesso req = new AvviaProcesso();
		req.setNomeProcesso(NOME_PROCESSO);
		AvviaProcessoResponse res = bpmService.startProcesso(req);

		List<ActivityInstance> activities = bpmService.getProssimaActivity(res.getIdProcesso(), true);
		String idIstanzaAttivita = activities.get(0).getUUID().getValue();
		
		log.debug(methodName, "getTaskInfoTest");
		GetTaskInfo taskInfoReq = new GetTaskInfo();
		taskInfoReq.setIdAttivita(idIstanzaAttivita);
		
		GetTaskInfoResponse taskInfoResponse = bpmService.getTaskInfo(taskInfoReq);
		assertTrue(!taskInfoResponse.isFallimento());
		
		// tra le variabili devo almeno trovare la descrizione
		boolean trovataDescrizione = false;
		for (VariabileProcesso variabileProcesso : taskInfoResponse.getVariabiliProcesso()) {
			if (variabileProcesso.getNome().equals(VariabileProcesso.NOME_VARIABILE_DESCRIZIONE)) {
				trovataDescrizione=true;
			}
			log.debug(methodName, variabileProcesso);
		}
		
		assertTrue("tra le variabili restituite non è stata trovata la descrizione: "+ 
				VariabileProcesso.NOME_VARIABILE_DESCRIZIONE, trovataDescrizione);
	}
	
 }