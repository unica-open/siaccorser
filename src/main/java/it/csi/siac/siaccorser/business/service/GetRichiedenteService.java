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
import it.csi.siac.siaccorser.frontend.webservice.msg.GetRichiedente;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetRichiedenteResponse;
import it.csi.siac.siaccorser.integration.dad.CoreDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GetRichiedenteService extends BaseService<GetRichiedente, GetRichiedenteResponse> {
	@Autowired
	private CoreDad coreDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getCodiceAccount(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("codice account"));
	}

	@Override
	protected void checkRichiedente() throws ServiceParamError {
		// no check for richiedente
	}

	@Transactional
	@Override
	public GetRichiedenteResponse executeService(GetRichiedente serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		Richiedente richiedente = coreDad.getRichiedente(req.getCodiceAccount());

		if (richiedente == null) {
			res.addErrore(ErroreCore.ENTITA_NON_TROVATA_SINGOLO_MSG.getErrore(req.getCodiceAccount()));
			res.setEsito(Esito.FALLIMENTO);
			
			return;
		}

		res.setRichiedente(richiedente);
		res.setEsito(Esito.SUCCESSO);
	}

}
