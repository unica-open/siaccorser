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
import it.csi.siac.siaccorser.frontend.webservice.msg.GetDettaglioOperazioneAsincrona;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetDettaglioOperazioneAsincronaResponse;
import it.csi.siac.siaccorser.integration.dad.OperazioneAsincronaDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GetDettaglioOperazioneAsincService extends
		BaseService<GetDettaglioOperazioneAsincrona,GetDettaglioOperazioneAsincronaResponse> {

	@Autowired
	private OperazioneAsincronaDad operazioneAsincronaDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkCondition(req.getOpAsincId() > 0, ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("operazione asincrona id"), false);
	}

	@Transactional
	@Override
	public GetDettaglioOperazioneAsincronaResponse executeService(GetDettaglioOperazioneAsincrona serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		res.setElencoPaginato(operazioneAsincronaDad.getDettaglioNotificaOperazioneAsincrona(req.getOpAsincId(), req.getCodice(), req.getParametriPaginazione()));
		res.setEsito(Esito.SUCCESSO);
	}

}
