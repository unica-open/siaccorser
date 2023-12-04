/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFileResponse;
import it.csi.siac.siaccorser.integration.dad.FileDad;
import it.csi.siac.siaccorser.model.Esito;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RicercaFileService extends BaseService<RicercaFile, RicercaFileResponse> {

	@Autowired
	private FileDad fileDad;

	@Override
	protected void init() {
		fileDad.setEnte(req.getEnte());
	}

	@Transactional
	@Override
	public RicercaFileResponse executeService(RicercaFile serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		res.setElencoPaginato(fileDad.ricercaFile(req.getCriteri(), req.getParametriPaginazione()));
		res.setEsito(Esito.SUCCESSO);

	}

}
