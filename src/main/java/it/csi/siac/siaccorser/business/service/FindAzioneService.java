/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.frontend.webservice.msg.FindAzione;
import it.csi.siac.siaccorser.frontend.webservice.msg.FindAzioneResponse;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class FindAzioneService extends
		BaseCoreService<FindAzione, FindAzioneResponse> {
	
	@Override
	protected void checkServiceParam() throws ServiceParamError {
		super.checkServiceParam();
		if(req.getNomeAzione() != null) {
			checkCondition(req.getEnte() != null && req.getEnte().getUid() != 0, ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Ente"));
			checkCondition(req.getRichiedente() != null && req.getRichiedente().getAccount() != null 
					&& req.getRichiedente().getAccount().getUid() != 0, ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Account"));
			
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public FindAzioneResponse executeService(FindAzione serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		if (req.getIdAzione() != null) {
			res.setAzione(coreDad.findAzioneById(req.getIdAzione()));
			return;
		}

		if (req.getNomeAzione() != null) {
			List<Azione> azioni = coreDad.findAzioneByNome(req.getNomeAzione(), req.getEnte().getUid(), req.getRichiedente().getAccount().getUid());
			res.setAzione(azioni.isEmpty() ? null : azioni.get(0));
//			res.setCardinalitaComplessiva(res.getAzione() == null ? 0 : 1);
			return;
		}
	}
}
