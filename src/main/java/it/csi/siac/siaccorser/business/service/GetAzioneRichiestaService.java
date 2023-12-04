/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccommonser.util.misc.TimeoutValue;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiestaResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.GetTaskInfo;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.AzioneConsentita;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.ParametroAzioneRichiesta;
import it.csi.siac.siaccorser.model.TipoAzione;
import it.csi.siac.siaccorser.model.TipologiaGestioneLivelli;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.util.Costanti;

@Service
@Scope("prototype")
public class GetAzioneRichiestaService extends BaseCoreService<GetAzioneRichiesta, GetAzioneRichiestaResponse> {

	@Transactional(timeout = TimeoutValue.INTERVAL_5_MIN)
	@Override
	public GetAzioneRichiestaResponse executeService(GetAzioneRichiesta serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void checkServiceParam() throws ServiceParamError {		
		checkNotNull(req.getAzioneRichiesta(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("azione"));
	}
	
	@Override
	protected void execute() {

		int idAzioneRichiesta = req.getAzioneRichiesta().getUid();		
		AzioneRichiesta azioneRichiesta = coreDad.findAzioneRichiestaById(idAzioneRichiesta); 
		
		if (azioneRichiesta == null) {
			throw new BusinessException(ErroreCore.OPERAZIONE_NON_CONSENTITA
					.getErrore("azione richiesta non valida"));
		}
		
		Operatore operatore = azioneRichiesta.getAccount().getOperatore(); 
		String codiceFiscale = req.getRichiedente().getOperatore().getCodiceFiscale();
		
		
		
		if (!operatore.getCodiceFiscale().equals(codiceFiscale)) {
			throw new BusinessException(ErroreCore.OPERAZIONE_NON_CONSENTITA
					.getErrore("l'account non appartiene all'operatore indicato nel richiedente (CF: " + codiceFiscale + ")"));
		}
		Ente ente = azioneRichiesta.getAccount().getEnte();
		if (ente==null) {
			throw new BusinessException(ErroreCore.ERRORE_DI_SISTEMA
					.getErrore("l'operatore non e' associato ad alcun ente"));
		}
			
		if (azioneRichiesta.getAzione().getTipo()==TipoAzione.ATTIVITA_PROCESSO) {
			GetTaskInfo getTaskInfoReq = new GetTaskInfo();
			getTaskInfoReq.setIdAttivita(azioneRichiesta.getIdAttivita());
//			GetTaskInfoResponse getTaskInfoRes = bpmService.getTaskInfo(getTaskInfoReq);
//			azioneRichiesta.setVariabiliProcesso(getTaskInfoRes.getVariabiliProcesso());
		}
		
		res.setAzioneRichiesta(azioneRichiesta);
		
		Integer annoBilancio = Integer.parseInt(azioneRichiesta.findParametro(ParametroAzioneRichiesta.ANNO_BILANCIO).getValore());
		
		Account account = coreDad.findAccountById(azioneRichiesta.getAccount().getUid(), annoBilancio);
		estraiGestioneLivello(account, ente);
		readParametriConfigurazioneEnte(ente);
		Map<String, AzioneConsentita> azioniConsentite = getAzioniConsentite(account,  annoBilancio);
		res.setAzioniConsentite(new ArrayList<AzioneConsentita>(azioniConsentite.values()));
		
		if(res.getAzioneRichiesta()!=null){
			res.getAzioneRichiesta().setAccount(account);
			res.getAzioneRichiesta().cleanUnmarshallableVariabileProcessoValue();
		}
		
		log.logXmlTypeObject(res, "Returning");
	}

	private void readParametriConfigurazioneEnte(Ente ente) {
		ente.setParametriConfigurazione(coreDad.getParametriConfigurazioneEnte(ente.getUid()));
	}

	/**
	 * Recupero la configurazione dell'ente
	 * @param azioneRichiesta
	 * @param ente
	 */
	private void estraiGestioneLivello(Account account, Ente ente) {
		Map<TipologiaGestioneLivelli, String> mapGestioneLivello = coreDad.findGestioneLivelloByIdEnte(ente.getUid());
		if(mapGestioneLivello!=null &&
				!mapGestioneLivello.isEmpty()){
			
			for(Entry<TipologiaGestioneLivelli, String> entry : mapGestioneLivello.entrySet()) {
				String value = entry.getValue();
				log.debug("execute", " configurazione gestione livelli: " + value);
			}
		}
		ente.setGestioneLivelli(mapGestioneLivello);
		account.setEnte(ente);
	}
	
	
	
	private boolean skipBonitaCheck(List<ParametroAzioneRichiesta> listParam){
		boolean res = false;
		if(listParam!= null && !listParam.isEmpty()){
						for(int k=0;k<listParam.size();k++){
				if(Costanti.STATO_BONITA_NAME.equals(listParam.get(k).getNome()) && 
						Costanti.SKIP_BONITA_VALUE.equals(listParam.get(k).getValore())){
					res = true;
					break;
				}
			}
		}
		return res;
	}
	

}

