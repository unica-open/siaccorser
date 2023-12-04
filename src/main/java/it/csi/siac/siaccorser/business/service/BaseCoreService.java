/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.frontend.webservice.CoreService;
import it.csi.siac.siaccorser.integration.dad.ClassificatoreDad;
import it.csi.siac.siaccorser.integration.dad.CoreDad;
import it.csi.siac.siaccorser.integration.dad.VariazioniDad;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.AnnoBilancio;
import it.csi.siac.siaccorser.model.AttivitaPendente;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.AzioneConsentita;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.Cruscotto;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Gruppo;
import it.csi.siac.siaccorser.model.GruppoAttivitaPendenti;
import it.csi.siac.siaccorser.model.GruppoAzioni;
import it.csi.siac.siaccorser.model.GruppoNotificaOperazioneAsincrona;
import it.csi.siac.siaccorser.model.GruppoNotifichePendenti;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.Ruolo;
import it.csi.siac.siaccorser.model.RuoloAccount;
import it.csi.siac.siaccorser.model.RuoloGruppo;
import it.csi.siac.siaccorser.model.ServiceRequest;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.TipoAzione;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.util.ComparatorUtil;
import it.csi.siac.siaccorser.util.Costanti;

/**
 * Fornisce funzionalità di base ai servizi del core
 * 
 * @author rmontuori, AR
 *
 * @param <REQ>
 * @param <RES>
 */
public abstract class BaseCoreService<REQ extends ServiceRequest,RES extends ServiceResponse> extends BaseService<REQ,RES> {

	@Autowired
	protected CoreDad coreDad;
	
	@Autowired
	protected VariazioniDad variazioniDad;
	@Autowired
	private ClassificatoreDad classificatoreDad;
	
	//SIAC-8332-REGP eliminato il bpm
	//@Autowired
	//protected BpmService bpmService;
	
	@Override
	protected void checkServiceParam() throws ServiceParamError {		
		checkNotNull(req.getRichiedente().getAccount(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("account"));
		checkNotNull(req.getRichiedente().getAccount().getEnte(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("ente"));
	}
	
	/**
	 * Recupera gli anni di bilancio
	 * @param account
	 * @return
	 */
	protected List<AnnoBilancio> getAnniBilancio(Account account) {
		List<AnnoBilancio> anniBilancio = coreDad.findAnniBilancio(account.getEnte().getUid());
		
		if(anniBilancio.isEmpty()){
			throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("anni di bilancio"));
		}
		return anniBilancio;
		
	}
	
	/**
	 * Mappa le azioni consentite per l'account in req
	 * @param account
	 * @return
	 */
	protected Map<String, AzioneConsentita> getAzioniConsentite(Account account, Integer annoBilancio) {
		Map<String, AzioneConsentita> azioniConsentite = new HashMap<String, AzioneConsentita>();
		
		for (RuoloAccount ruoloAccount : account.getRuoli()) {
			Ruolo ruolo = ruoloAccount.getRuolo();

			if (ruolo.getAnniBilancio().isEmpty() || 
					ruolo.getAnniBilancio().contains(annoBilancio))
				readAzioniConsentite(ruolo.getAzioni(), azioniConsentite, account);
		}
		
		for (Gruppo gruppo : account.getGruppi())
			for (RuoloGruppo ruoloGruppo : gruppo.getRuoli()) {
				Ruolo ruolo = ruoloGruppo.getRuolo();
				
				if (ruolo.getAnniBilancio().isEmpty() || 
					ruolo.getAnniBilancio().contains(annoBilancio))
						readAzioniConsentite(ruolo.getAzioni(), azioniConsentite, account);
			}

		return azioniConsentite;
	}

	private void readAzioniConsentite(List<Azione> azioni, Map<String, AzioneConsentita> azioniConsentite,
			Account account) {
		for (Azione azione : azioni) {
			AzioneConsentita azioneConsentita = new AzioneConsentita();

			azioneConsentita.setAzione(azione);
			
			String azioneKey = getIdAzione(azione);
			
			if (!azioniConsentite.containsKey(azioneKey))
				azioniConsentite.put(azioneKey, azioneConsentita);
		}
	}


	
	/**
	 * Struttura dati temporanea per raccogliere le azioni eseguibili da un account
	 */

	
	private String getIdAzione(Azione azione) {
		return "Azione-"+azione.getUid();
	}
	
	private String getIdGruppoAzioni(GruppoAzioni gruppoAzioni) {
		return "Gruppo-"+gruppoAzioni.getUid(); 
	}
	

	
	protected void updateGruppiAzioni(AzioneConsentita azioneConsentita, Map<String,GruppoAzioni>  gruppiAzioni ) {
		Azione azione = azioneConsentita.getAzione();
		
		if (azione.getGruppo() == null) {
			return;
		}
		
		String idGruppoAzioni = getIdGruppoAzioni(azione.getGruppo());
		GruppoAzioni gruppoAzioni = gruppiAzioni.get(idGruppoAzioni);
		
		if (gruppoAzioni == null) {
			gruppoAzioni = new GruppoAzioni();
			gruppoAzioni.setNome(azione.getGruppo().getNome());
			gruppoAzioni.setTitolo(azione.getGruppo().getTitolo());
			gruppoAzioni.setDescrizione(azione.getGruppo().getDescrizione());
			gruppoAzioni.setAzioni(new ArrayList<Azione>());
			gruppiAzioni.put(idGruppoAzioni,gruppoAzioni);
		}
		
		gruppoAzioni.getAzioni().add(azione);
	}
	
	protected void updateGruppiAttivitaPendentiVariazioni(AzioneConsentita azioneConsentita, Integer annoEsercizio, Account account, List<GruppoAttivitaPendenti>  gruppiAttivitaPendenti){
		final String methodName = "updateGruppiAttivitaPendentiVariazioni";
		if(azioneConsentita == null || azioneConsentita.getAzione() == null) {
			log.error(methodName, "azione consentita non passata, impossibile caricare attivita pendenti");
			return;
		}
		Azione azione = azioneConsentita.getAzione();
		variazioniDad.setEnte(new Ente());
		variazioniDad.getEnte().setUid(account.getEnte().getUid());
		String stato = caricaStatoCodeAttivitaPendendteByAzione(azione);
		List<Integer> uidSACDirezioneCollegate = caricaUidSACDirezioneCollegate(azione, account);
		int totaliAttivita = caricaTotaliAttivitaPendentiByStatoCode(stato, azione,account,uidSACDirezioneCollegate);
		
		
		if(totaliAttivita == 0) {
			return;
		}
		GruppoAttivitaPendenti gruppoAttivitaPendenti = buildGruppoAttivitaPendenti(azioneConsentita, totaliAttivita);
		gruppiAttivitaPendenti.add(gruppoAttivitaPendenti);
	}
	
	private GruppoAttivitaPendenti buildGruppoAttivitaPendenti(AzioneConsentita azioneConsentita, int totali) {
		GruppoAttivitaPendenti gruppoAttivitaPendenti = new GruppoAttivitaPendenti();
		gruppoAttivitaPendenti.setAzione(azioneConsentita.getAzione());
		gruppoAttivitaPendenti.setTotali(totali);
		return gruppoAttivitaPendenti;
	}
	
	private String caricaStatoCodeAttivitaPendendteByAzione(Azione azione) {
		final String methodName ="caricaStatoByAzione";
		 String statoOp = variazioniDad.caricaStatoCodeByAzionePendente(azione);
		 if(StringUtils.isBlank(statoOp)) {
			 log.error(methodName, "impossibile trovare uno stato per l'azione indicata [uid: " + azione.getUid() + " ].");
			 throw new BusinessException(ErroreCore.ERRORE_DI_SISTEMA.getErrore("impossibile trovare uno stato per l'azione indicata"));
		 }
		 return statoOp;
	}
	
	private int caricaTotaliAttivitaPendentiByStatoCode(String statoCode, Azione azione, Account account, List<Integer> uidSACDirezioneCollegate ) {
		//SIAC-8332-REGP
		Long countTotale = variazioniDad.caricaTotaleGruppoAttivitaPendenti(statoCode, Boolean.TRUE.equals(azione.getFlagVerificaSac()),uidSACDirezioneCollegate,  req.getAnnoBilancio().toString());
		return countTotale != null? countTotale.intValue() : 0;
	}
	
	private List<Integer> caricaUidSACDirezioneCollegate(Azione azione, Account account) {
		final String methodName ="caricaUidSACDirezioneCollegate";
		if(!azione.getFlagVerificaSac()) {
			return null;
		}
		List<Integer> uidsCDR = new ArrayList<Integer>();
				
		classificatoreDad.popolaListaConCDRDirettamenteCollegateAdAccount(account, req.getAnnoBilancio(), uidsCDR);
		classificatoreDad.popolaListaCDRConCDCFigliCollegatiAdAccount(account,  req.getAnnoBilancio(), uidsCDR, uidsCDR);
		return uidsCDR;
	}
	

	/**
	 * Update gruppi attivita pendenti.
	 *
	 * @param azioneConsentita the azione consentita
	 * @param annoEsercizio the anno esercizio
	 * @param idEntePropritaro the id ente propritaro
	 * @param gruppiAttivitaPendenti the gruppi attivita pendenti
	 * @deprecated by SIAC-8332
	 */
	protected void updateGruppiAttivitaPendenti(AzioneConsentita azioneConsentita, Integer annoEsercizio, Integer idEntePropritaro, List<GruppoAttivitaPendenti>  gruppiAttivitaPendenti){

//		Azione azione = azioneConsentita.getAzione();
//		GruppoAttivitaPendenti gruppoAttivitaPendenti = new GruppoAttivitaPendenti();
//		gruppoAttivitaPendenti.setAzione(azione);
//		ParametriRicercaTaskBpm parametriRicerca = new ParametriRicercaTaskBpm();
//		parametriRicerca.setTipoRicerca(TipoRicercaTaskBpm.TOTALE_ATTIVITA);
//		parametriRicerca.setAzione(azioneConsentita);
//		parametriRicerca.setAnnoEsercizio(String.valueOf(annoEsercizio));
//		
//		parametriRicerca.setIdEnteProprietario(idEntePropritaro);
//			
//		RicercaAttivitaPendenti bpmRequest= new RicercaAttivitaPendenti();
//		bpmRequest.setAnnoBilancio(req.getAnnoBilancio());
//		bpmRequest.setDataOra(new Date());
//		bpmRequest.setRichiedente(req.getRichiedente());
//		bpmRequest.setParametriRicerca(parametriRicerca);
//		RicercaAttivitaPendentiResponse bpmResponse = bpmService.ricercaAttivitaPendenti(bpmRequest);
//		int totali = bpmResponse.getTotali();
//		if (totali > 0) {
//			gruppoAttivitaPendenti.setTotali(totali);
//			gruppiAttivitaPendenti.add(gruppoAttivitaPendenti);
//		}
	}
	
	/**
	 * Update gruppi notifiche pendenti.
	 *
	 * @param azioneConsentita the azione consentita
	 * @param annoEsercizio the anno esercizio
	 * @param gruppiNotifichePendenti the gruppi notifiche pendenti
	 * @deprecatedby SIAC-8332
	 */
	@Deprecated
	protected void updateGruppiNotifichePendenti(AzioneConsentita azioneConsentita, Integer annoEsercizio, List<GruppoNotifichePendenti>  gruppiNotifichePendenti){
//		Azione azione = azioneConsentita.getAzione();
//		GruppoNotifichePendenti gruppoNotifichePendenti = new GruppoNotifichePendenti();
//		gruppoNotifichePendenti.setAzione(azione);
//		ParametriRicercaTaskBpm parametriRicerca = new ParametriRicercaTaskBpm();
//		parametriRicerca.setTipoRicerca(TipoRicercaTaskBpm.TOTALE_NOTIFICHE);
//		parametriRicerca.setAzione(azioneConsentita);
//		parametriRicerca.setAnnoEsercizio(String.valueOf(annoEsercizio));
//		RicercaNotifichePendenti bpmRequest= new RicercaNotifichePendenti();
//		bpmRequest.setParametriRicerca(parametriRicerca);
//		RicercaNotifichePendentiResponse bpmResponse = bpmService.ricercaNotifichePendenti(bpmRequest);
//		gruppoNotifichePendenti.setTotali(bpmResponse.getTotali());
//		gruppiNotifichePendenti.add(gruppoNotifichePendenti);		
	}
	
	
	protected void updateGruppiNotificheOperazioniAsincrone(AzioneConsentita azioneConsentita,
			Integer accountId, Integer enteProprietarioId, Richiedente richiedente, List<GruppoNotificaOperazioneAsincrona>  gruppiNotificheOperazioneAsincrona){

		Azione azione = azioneConsentita.getAzione();
		
		GruppoNotificaOperazioneAsincrona gruppoNotificaOpAsinc = new GruppoNotificaOperazioneAsincrona();  
		gruppoNotificaOpAsinc.setAzione(azione);
		
		
		// Qui prendo solo il count delle operazioni asincrone
		Integer totali = coreDad.getCountOperazioneAsincronaDaNotificare(accountId , azione.getUid(),
				enteProprietarioId);
		
		
		if (totali > 0 ) {
			gruppoNotificaOpAsinc.setTotali(totali);
			gruppiNotificheOperazioneAsincrona.add(gruppoNotificaOpAsinc);
		}
		
		
		Collections.sort(gruppiNotificheOperazioneAsincrona, new Comparator<GruppoNotificaOperazioneAsincrona>() {
			@Override
			public int compare(GruppoNotificaOperazioneAsincrona o1, GruppoNotificaOperazioneAsincrona o2) {
				return o1 == null || o1.getAzione() == null || o1.getAzione().getTitolo() == null ? 1 : 
					   o2 == null || o2.getAzione() == null || o2.getAzione().getTitolo() == null ? -1 : 
					   o1.getAzione().getTitolo().compareToIgnoreCase(o2.getAzione().getTitolo());
			}
		});
	}
	
	
	
	protected void updateAzioniFrequenti(Account account, List<Azione> azioniFrequenti ) {
		
		List<AzioneRichiesta> azioniRichieste = coreDad.findAzioniFrequenti(account.getUid());
		for(AzioneRichiesta azioneRichiesta:azioniRichieste) {
				azioniFrequenti.add(azioneRichiesta.getAzione());
		}
		
	}

	/**
	 * Imposta le liste dei gruppi di azioni, delle attivita pendenti, delle notifiche asinc e delle attivita frequenti
	 * @param account
	 * @param cruscotto
	 */
	protected void caricaListeAzioniEGruppi(Account account, Cruscotto cruscotto) {
		Map<String, GruppoAzioni> gruppiAzioni = new HashMap<String, GruppoAzioni>();
		List<GruppoAttivitaPendenti> gruppiAttivitaPendenti = new ArrayList<GruppoAttivitaPendenti>();
		List<GruppoNotifichePendenti> gruppiNotifichePendenti = new ArrayList<GruppoNotifichePendenti>();
		List<GruppoNotificaOperazioneAsincrona> gruppiNotificheOpAsinc = new ArrayList<GruppoNotificaOperazioneAsincrona>();
		
		Map<String, AzioneConsentita> azioniConsentite = getAzioniConsentite(account, cruscotto.getAnnoBilancio().getAnno());

		for (AzioneConsentita azioneConsentita : azioniConsentite.values()) {
			TipoAzione tipoAzione = azioneConsentita.getAzione().getTipo();
		
			if ((tipoAzione == TipoAzione.ATTIVITA_SINGOLA)
					|| (tipoAzione == TipoAzione.AVVIO_PROCESSO)) 
				updateGruppiAzioni(azioneConsentita, gruppiAzioni);
						
			if (tipoAzione == TipoAzione.ATTIVITA_PROCESSO) {

				updateGruppiAttivitaPendentiVariazioni(azioneConsentita, cruscotto
						.getAnnoBilancio().getAnno(), account, gruppiAttivitaPendenti);
			}
			//SIAC-8332
//			if (tipoAzione == TipoAzione.NOTIFICA) {
//				updateGruppiNotifichePendenti(azioneConsentita, cruscotto
//						.getAnnoBilancio().getAnno(), gruppiNotifichePendenti);
//			}
			
			
			/**
			 * RM 22/04/2014 aggiunta gestione updateGruppiNotificheOperazioniAsincrone
			 * le op. asincrone sono attività che non hanno un tipo (singola e processo) quindi 
			 * per ogni azione consentita controllo che abbia delle notifiche 
			 * 
			 */
			updateGruppiNotificheOperazioniAsincrone(azioneConsentita, account.getUid(),
					account.getEnte().getUid(), req.getRichiedente(), gruppiNotificheOpAsinc);
		}
		
		// RM: le azioni frequenti sono ordinate per count 
		List<Azione> azioniFrequenti = new ArrayList<Azione>();
		updateAzioniFrequenti(account, azioniFrequenti);
		
		// RM ordino i gruppi delle azioni (i box)
		List<GruppoAzioni> listagruppiazioni = new ArrayList<GruppoAzioni>(gruppiAzioni.values());
		Collections.sort(listagruppiazioni, ComparatorUtil.compare());
		
		// RM ordino le AttivitaPendenti in ordine alfabetico
		Collections.sort(gruppiAttivitaPendenti, ComparatorUtil.compareAttivitaPendenti());
		
		//RM odino le azioni dei gruppi 
		for (GruppoAzioni gruppoAzioni : listagruppiazioni) {
			List<Azione> listaOrdinata = new ArrayList<Azione>(gruppoAzioni.getAzioni());
			Collections.sort(listaOrdinata, ComparatorUtil.compareAzioni());
			gruppoAzioni.setAzioni(listaOrdinata);
		}
		
		
		cruscotto.setGruppiAzioni(listagruppiazioni);
		cruscotto.setGruppiAttivitaPendenti(gruppiAttivitaPendenti);
		cruscotto.setGruppiNotificheOperazioneAsincrona(gruppiNotificheOpAsinc);
		cruscotto.setGruppiNotifichePendenti(gruppiNotifichePendenti);
		cruscotto.setAzioniConsentite(new ArrayList<AzioneConsentita>(azioniConsentite.values()));
		cruscotto.setAzioniFrequenti(azioniFrequenti);
	}
	
	
	//SIAC 6884
	protected GruppoAttivitaPendenti buildGruppoAttivitaPendenti(Long totale, Azione azioneUtenteDec){
		GruppoAttivitaPendenti gap = new GruppoAttivitaPendenti();
		List<AttivitaPendente> apList = new ArrayList<AttivitaPendente>();
		AttivitaPendente ap = new AttivitaPendente();
		ap.setAzione(azioneUtenteDec);
		apList.add(ap);
		gap.setTotali(totale.intValue());
		gap.setAzione(azioneUtenteDec);
		gap.setAttivitaPendenti(apList);
		return gap;
	}
	
	
	
//	protected Azione azioneUtenteVariazioniDecentrate(List<RuoloAccount> ruoli){
//		
//		if(ruoli!= null &&	!ruoli.isEmpty()){
//			for(int z=0;z<ruoli.size();z++){
//				if(ruoli.get(z).getRuolo()!= null && ruoli.get(z).getRuolo().getAzioni()!= null &&
//					!ruoli.get(z).getRuolo().getAzioni().isEmpty()){
//					for(int k=0;k<ruoli.get(z).getRuolo().getAzioni().size();k++){
//						Azione azione = ruoli.get(z).getRuolo().getAzioni().get(k);
//						if(Costanti.CODICE_AZIONE_VARIAZIONE_DECENTRATO.equals(azione.getNome())){
//							return azione;
//						}
//					}
//					
//				}
//			}
//		}
//		return null;
//	}
	
	/*
	 * SIAC-7633
	 * Prendiamo le azione anche dai gruppi
	 */
	protected Azione azioneUtenteVariazioniDecentrate(Map<String, AzioneConsentita>  azioniMap){
		if(azioniMap!= null &&	!azioniMap.isEmpty()){
			 for (Map.Entry<String, AzioneConsentita> entry : azioniMap.entrySet()) {
			        //System.out.println(entry.getKey() + ":" + entry.getValue().getAzione().getNome());
			        if(entry.getValue()!= null && entry.getValue().getAzione() != null && Costanti.CODICE_AZIONE_VARIAZIONE_DECENTRATO.equals(entry.getValue().getAzione().getNome())){
						return entry.getValue().getAzione();
					}
			    }
		}
		return null;
	}

	
}
