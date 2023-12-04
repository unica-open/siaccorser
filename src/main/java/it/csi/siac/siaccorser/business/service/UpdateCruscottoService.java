/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccommonser.util.misc.TimeoutValue;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateCruscotto;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateCruscottoResponse;
import it.csi.siac.siaccorser.integration.dad.VariazioniDad;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.Cruscotto;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UpdateCruscottoService extends
		BaseCoreService<UpdateCruscotto, UpdateCruscottoResponse> {

	
	@Autowired
	protected VariazioniDad variazioniDad;
	
	@Transactional(timeout = TimeoutValue.INTERVAL_5_MIN)
	@Override
	public UpdateCruscottoResponse executeService(UpdateCruscotto serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {

		Account account = coreDad.findAccountById(req.getRichiedente().getAccount().getUid(), req.getAnnoBilancio()); 
		String codiceFiscale = req.getRichiedente().getOperatore().getCodiceFiscale();
		Operatore operatore = account.getOperatore();
		if (!operatore.getCodiceFiscale().equals(codiceFiscale)) {
			throw new BusinessException(ErroreCore.OPERAZIONE_NON_CONSENTITA
					.getErrore("L'account non appartiene all'operatore indicato nel richiedente (CF: " + codiceFiscale + ")"));
		}


		Cruscotto cruscotto = req.getCruscotto();
		caricaListeAzioniEGruppi(account,cruscotto);		
		
		
		//SIAC-6884 e SIAC-8332
//		Map<String, AzioneConsentita>  azioniMap = getAzioniConsentite( account,  req.getAnnoBilancio());
//		 if(azioniMap!= null && !azioniMap.isEmpty()){
//			Azione azioneUtenteDec = azioneUtenteVariazioniDecentrate(azioniMap);
//			//SAC
//			List<Integer> idSacList = new ArrayList<Integer>();
//		
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
//			
//			if(!idSacMap.isEmpty()){
//				idSacList = new ArrayList<Integer>(idSacMap.values());
//			}
//			
//			
//			if(azioneUtenteDec!= null  && !idSacList.isEmpty()){
//				Long totale = variazioniDad.getTotaleVariazione(idSacList, req.getAnnoBilancio(), account.getEnte().getUid());
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
//								
//								alreadyAddVariazioni = true;
//								break;
//						}
//					}
//					
//					//SE NON ABBIAMO AGGIORNA VARIAZIONE DI BILANCIO ...come dovrebbe essere
//					if(!alreadyAddVariazioni){
//						GruppoAttivitaPendenti gap = buildGruppoAttivitaPendenti( totale, azioneUtenteDec);
//						cruscotto.getGruppiAttivitaPendenti().add(gap);
//						
//					}
//					
//				}else{
//					List<GruppoAttivitaPendenti> gapList = new ArrayList<GruppoAttivitaPendenti>();
//					GruppoAttivitaPendenti gap = buildGruppoAttivitaPendenti( totale, azioneUtenteDec);
//					gapList.add(gap);
//					cruscotto.setGruppiAttivitaPendenti(gapList);
//					
//					
//				}
//				
//			  }//TOTOALE
//				
//			}
//		}
//		
		
		
		res.setGruppiAttivitaPendenti(cruscotto.getGruppiAttivitaPendenti());
		res.setGruppiNotifichePendenti(cruscotto.getGruppiNotifichePendenti());
		res.setGruppiNotificheOperazioneAsincrona(cruscotto.getGruppiNotificheOperazioneAsincrona());
		res.setAzioniFrequenti(cruscotto.getAzioniFrequenti());

	}
	
	

	
	
	

}
