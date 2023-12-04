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
import it.csi.siac.siaccorser.frontend.webservice.msg.GetParametroConfigurazioneEnte;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetParametroConfigurazioneEnteResponse;
import it.csi.siac.siaccorser.integration.dad.CoreDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GetParametroConfigurazioneEnteService extends BaseService<GetParametroConfigurazioneEnte, GetParametroConfigurazioneEnteResponse> {
	@Autowired
	private CoreDad coreDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getNomeParametro(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("nomeParametro"));
	}

	@Transactional
	@Override
	public GetParametroConfigurazioneEnteResponse executeService(GetParametroConfigurazioneEnte serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void init() {
		coreDad.setEnte(req.getRichiedente().getAccount().getEnte());
	}

	@Override
	protected void execute() {
		res.setValoreParametro(coreDad.getParametroConfigurazioneEnte(req.getNomeParametro()));
		res.setEsito(Esito.SUCCESSO);
	}

}
