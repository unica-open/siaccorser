/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.opasinc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetNotificheOperazioneAsincrona;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetNotificheOperazioneAsincronaResponse;
import it.csi.siac.siaccorser.integration.dad.OperazioneAsincronaDad;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.model.ricerca.CriteriRicercaOperazioneAsincrona;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GetNotificheOperazioneAsincService extends
		BaseService<GetNotificheOperazioneAsincrona, GetNotificheOperazioneAsincronaResponse> {

	@Autowired
	private OperazioneAsincronaDad operazioneAsincronaDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkCondition(req.getAccountId() > 0, ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("AccountId"), false);
		checkCondition(req.getAzioneId() > 0, ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("AzioneId"), false);
		checkCondition(req.getEnteProprietarioId() > 0, ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("EnteProprietarioId"), false);
	}

	@Transactional
	@Override
	public GetNotificheOperazioneAsincronaResponse executeService(GetNotificheOperazioneAsincrona serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		String methodName = "execute";
		log.debugStart(methodName, " Start");
		CriteriRicercaOperazioneAsincrona criteri = new CriteriRicercaOperazioneAsincrona();
		
		criteri.setAccountId(req.getAccountId());
		criteri.setAzioneId(req.getAzioneId());
		criteri.setEnteProprietarioId(req.getEnteProprietarioId());
		criteri.setDataDa(req.getDataDa());
		
		if(req.getDataA()!=null){
			criteri.setDataA(req.getDataA());
		}
		
		criteri.setCodiceStato(req.getCodiceStato());
		criteri.setFlagAltriUtenti(req.getFlagAltriUtenti());
		res.setElencoPaginato(operazioneAsincronaDad.getNotificheOperazioneAsincrona(criteri, req.getParametriPaginazione()));
		
		log.debugStart(methodName, " End");
	}

}
