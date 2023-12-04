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
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaBilancio;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaBilancioResponse;
import it.csi.siac.siaccorser.integration.dad.CoreDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RicercaBilancioService extends BaseService<RicercaBilancio, RicercaBilancioResponse> {
	@Autowired
	private CoreDad coreDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getEnte(),
				ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("ente"));
		checkNotNull(req.getAnno(),
				ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("anno"));
	}

	@Transactional
	@Override
	public RicercaBilancioResponse executeService(RicercaBilancio serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		res.setBilancio(coreDad.ricercaBilancio(req.getEnte(), String.valueOf(req.getAnno())));
		res.setEsito(Esito.SUCCESSO);
	}

}
