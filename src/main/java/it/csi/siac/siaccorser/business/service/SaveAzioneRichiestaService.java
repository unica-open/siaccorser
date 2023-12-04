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

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.frontend.webservice.msg.SaveAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.SaveAzioneRichiestaResponse;
import it.csi.siac.siaccorser.integration.dad.CoreDad;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SaveAzioneRichiestaService extends BaseService<SaveAzioneRichiesta, SaveAzioneRichiestaResponse> {

	@Autowired
	protected CoreDad coreDad;
	
	@Transactional
	@Override
	public SaveAzioneRichiestaResponse executeService(SaveAzioneRichiesta serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {

		Account account = coreDad.getAccount(req.getRichiedente().getAccount().getUid()); 
		String codiceFiscale = req.getRichiedente().getOperatore().getCodiceFiscale();
		Operatore operatore = account.getOperatore();
		if (!operatore.getCodiceFiscale().equals(codiceFiscale)) {
			throw new BusinessException(ErroreCore.OPERAZIONE_NON_CONSENTITA
					.getErrore("L'account non appartiene all'operatore indicato nel richiedente (CF: " + codiceFiscale + ")"));
		}
		AzioneRichiesta azioneRichiesta = coreDad.saveAzioneRichiesta(req.getAzioneRichiesta(), account.getEnte(), codiceFiscale); 
		res.setAzioneRichiesta(azioneRichiesta);
	}

}
