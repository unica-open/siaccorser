/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.bpm;

import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.business.service.BaseCoreService;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAttivitaPendenti;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAttivitaPendentiResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.ParametriRicercaTaskBpm;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendenti;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendentiResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.TipoRicercaTaskBpm;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GetAttivitaPendentiService extends BaseCoreService<GetAttivitaPendenti, GetAttivitaPendentiResponse> {

	
	@Transactional
	@Override
	public GetAttivitaPendentiResponse executeService(GetAttivitaPendenti serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void checkServiceParam() throws ServiceParamError {		
		checkNotNull(req.getAzioneConsentita(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("azione consentita"));
		checkNotNull(req.getAnnoBilancio(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("anno di bilancio"));
		checkNotNull(req.getIdEnteProprietario(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("id ente proprietario"));
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
		ParametriRicercaTaskBpm parametriRicerca = new ParametriRicercaTaskBpm();
		parametriRicerca.setTipoRicerca(TipoRicercaTaskBpm.ATTIVITA);
		parametriRicerca.setAzione(req.getAzioneConsentita());
		parametriRicerca.setAnnoEsercizio(ObjectUtils.toString(req.getAnnoBilancio()));
		parametriRicerca.setIdEnteProprietario(req.getIdEnteProprietario());
		parametriRicerca.setOffset(req.getOffset());
		parametriRicerca.setSize(req.getSize());
		RicercaAttivitaPendenti bpmRequest= new RicercaAttivitaPendenti();
		bpmRequest.setAnnoBilancio(req.getAnnoBilancio());
		bpmRequest.setDataOra(new Date());
		bpmRequest.setRichiedente(req.getRichiedente());
		bpmRequest.setParametriRicerca(parametriRicerca);
		
		RicercaAttivitaPendentiResponse bpmResponse = new RicercaAttivitaPendentiResponse(); // bpmService.ricercaAttivitaPendenti(bpmRequest);
		res.setAttivitaPendenti(bpmResponse.getAttivitaPendenti());
			
	}

	
}
