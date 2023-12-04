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
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaEnte;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaEnteResponse;
import it.csi.siac.siaccorser.integration.dad.CoreDad;
import it.csi.siac.siaccorser.model.Esito;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RicercaEnteService extends BaseService<RicercaEnte, RicercaEnteResponse> {
	@Autowired
	private CoreDad coreDad;

	@Transactional
	@Override
	public RicercaEnteResponse executeService(RicercaEnte serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		res.setEnte(coreDad.ricercaEnte(req.getCodiceFiscale()));
		res.setEsito(Esito.SUCCESSO);
	}

}
