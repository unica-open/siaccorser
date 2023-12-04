/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccommonser.util.misc.TimeoutValue;
import it.csi.siac.siaccorser.frontend.webservice.msg.SetupCruscotto;
import it.csi.siac.siaccorser.frontend.webservice.msg.SetupCruscottoResponse;
import it.csi.siac.siaccorser.integration.dad.VariazioniDad;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.AnnoBilancio;
import it.csi.siac.siaccorser.model.AttivitaPendente;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.AzioneConsentita;
import it.csi.siac.siaccorser.model.Cruscotto;
import it.csi.siac.siaccorser.model.GruppoAttivitaPendenti;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.RuoloAccount;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.util.Costanti;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SetupCruscottoService extends
		BaseCoreService<SetupCruscotto, SetupCruscottoResponse> {

	
	@Autowired
	protected VariazioniDad variazioniDad;
	
	@Transactional(timeout = TimeoutValue.INTERVAL_5_MIN)
	@Override
	public SetupCruscottoResponse executeService(SetupCruscotto serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {

		//Account account = coreDad.findAccountById(req.getRichiedente().getAccount().getUid(), req.getAnnoBilancio());
		Account account = coreDad.findAccountByIdNoRuoliGruppi(req.getRichiedente().getAccount().getUid());
		
		String codiceFiscale = req.getRichiedente().getOperatore().getCodiceFiscale();
		Operatore operatore = account.getOperatore();
		if (!operatore.getCodiceFiscale().equals(codiceFiscale)) {
			throw new BusinessException(ErroreCore.OPERAZIONE_NON_CONSENTITA
					.getErrore("L'account non appartiene all'operatore indicato nel richiedente (CF: " + codiceFiscale + ")"));
		}

		Cruscotto cruscotto = new Cruscotto();
		cruscotto.setAnniBilancio(getAnniBilancio(req.getRichiedente().getAccount()));
		
		/**
		 * Il sistema inizializza l'anno di bilancio corrente con un anno di bilancio vuoto. 
		 * Se la lista degli anni di bilancio associati all'account non è vuota, 
		 * il sistema inizializza l'anno di bilancio corrente con il primo anno della lista. 
		 * Se nella lista c'è un anno di
		 * bilancio corrispondente all'anno in corso o all'anno della request (se presente) 
		 * il sistema inizializza l'anno di bilancio corrente con tale anno di bilancio.
		 * 
		 */
		
		cruscotto.setAnnoBilancio(new AnnoBilancio());
		
		if (! cruscotto.getAnniBilancio().isEmpty()) {
			cruscotto.setAnnoBilancio(cruscotto.getAnniBilancio().get(0));
			
			Integer anno = 
					req.getAnnoBilancio() != null && req.getAnnoBilancio() != 0 ?  
					req.getAnnoBilancio() :  
					Calendar.getInstance().get(Calendar.YEAR);
			
			for (AnnoBilancio annoBilancio : cruscotto.getAnniBilancio()) {
				if (anno.equals(annoBilancio.getAnno())) {
					cruscotto.setAnnoBilancio(annoBilancio);
				}
			}
			coreDad.popolaRuoliGruppi(account, cruscotto.getAnnoBilancio().getAnno());
		}

		caricaListeAzioniEGruppi(account, cruscotto);
		
		
		
		//SIAC-6884 e SIAC-8332
//		 Map<String, AzioneConsentita>  azioniMap = getAzioniConsentite( account,  req.getAnnoBilancio());
//		 if(azioniMap!= null && !azioniMap.isEmpty()){
//			Azione azioneUtenteDec = azioneUtenteVariazioniDecentrate(azioniMap);
//			//SAC
//			List<Integer> idSacList = new ArrayList<Integer>();
//			Map<Integer, Integer> idSacMap = new HashMap<Integer, Integer>();
//			for(int n=0; n<account.getStruttureAmministrativeContabili().size();n++){
//				//idSacList.add(account.getStruttureAmministrativeContabili().get(n).getUid());
//				/*
//				* SIAC-7633: Se l’utente è profilato a n Settori, ai quali fa capo un’unica Direzione, il sistema valorizzerà in automatico
//				* il campo con la Direzione; in caso contrario l’utente dovrà selezionare una delle Direzioni proposte tra quelle associate all’utenza.
//				* Alla variazione decentrata potranno accedere tutti gli utenti decentrati associati alla Direzione indicata sulla variazione che,
//				* alla conferma del passo 2, non potrà più essere modificata
//				*/
//				idSacMap.put(account.getStruttureAmministrativeContabili().get(n).getUid(), account.getStruttureAmministrativeContabili().get(n).getUid());
//				if(account.getStruttureAmministrativeContabili().get(n).getUidPadre()!= null){
//					idSacMap.put(account.getStruttureAmministrativeContabili().get(n).getUidPadre(), account.getStruttureAmministrativeContabili().get(n).getUidPadre());
//				}
//			}
//			if(!idSacMap.isEmpty()){
//				idSacList = new ArrayList<Integer>(idSacMap.values());
//			}
//			
//			
//			
//			if(azioneUtenteDec!= null  && !idSacList.isEmpty()){
//				Long totale = variazioniDad.getTotaleVariazione(idSacList,cruscotto.getAnnoBilancio().getAnno(),account.getEnte().getUid());
//				if(totale != null && totale!=0){
//				/*
//				 * Se esistono attivita pendenti e se esistono aggiornamenti di variazioni di 
//				 * bilancio le sostituiamo con quelli da DB 
//				 * 
//				 */
//				if(cruscotto.getGruppiAttivitaPendenti()!= null && !cruscotto.getGruppiAttivitaPendenti().isEmpty()){
//					boolean alreadyAddVariazioni = false;
//					int attivitaPendentiSize = cruscotto.getGruppiAttivitaPendenti().size();
//					for(int i=0; i<attivitaPendentiSize;i++){
//						if(cruscotto.getGruppiAttivitaPendenti().get(i).getAzione()!= null 
//								&& Costanti.CRUSCOTTO_VARIAZIONE_BILANCIO_TASK_PROCESSO.equals(cruscotto.getGruppiAttivitaPendenti().get(i).getAzione().getNomeTask())){
//							
//								cruscotto.getGruppiAttivitaPendenti().get(i).setTotali(totale.intValue());
//								res.setUtenteVariaizoniDecentrato(true);
//								res.setIdAzionePerUtenteDecentrato(azioneUtenteDec.getUid());
//								alreadyAddVariazioni = true;
//								break;
//						}
//					}
//					
//					//SE NON ABBIAMO AGGIORNA VARIAZIONE DI BILANCIO ...come dovrebbe essere
//					if(!alreadyAddVariazioni){
//						GruppoAttivitaPendenti gap = buildGruppoAttivitaPendenti( totale, azioneUtenteDec);
//						cruscotto.getGruppiAttivitaPendenti().add(gap);
//						res.setUtenteVariaizoniDecentrato(true);
//						res.setIdAzionePerUtenteDecentrato(azioneUtenteDec.getUid());
//					}
//					
//				}else{
//					List<GruppoAttivitaPendenti> gapList = new ArrayList<GruppoAttivitaPendenti>();
//					GruppoAttivitaPendenti gap = buildGruppoAttivitaPendenti( totale, azioneUtenteDec);
//					gapList.add(gap);
//					cruscotto.setGruppiAttivitaPendenti(gapList);
//					res.setUtenteVariaizoniDecentrato(true);
//					res.setIdAzionePerUtenteDecentrato(azioneUtenteDec.getUid());
//					
//				}
//				
//			  }//TOTOALE
//				
//			}
//		}
		cruscotto.setMessaggioInformativo(coreDad.readMessaggioInformativo(account.getEnte().getUid()));
		res.setCruscotto(cruscotto);
	}

	
	
	

}
