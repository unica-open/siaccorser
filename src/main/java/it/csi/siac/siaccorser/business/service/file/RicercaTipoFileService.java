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
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaTipoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaTipoFileResponse;
import it.csi.siac.siaccorser.integration.dad.FileDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.model.file.TipoFile;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RicercaTipoFileService extends BaseService<RicercaTipoFile, RicercaTipoFileResponse> {

	@Autowired
	private FileDad fileDad;

	@Override
	protected void init() {
		fileDad.setEnte(req.getEnte());
	}

	@Transactional
	@Override
	public RicercaTipoFileResponse executeService(RicercaTipoFile serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		TipoFile tipoFile = fileDad.ricercaTipoFile(req.getCodice());
		
		if (tipoFile == null) {
			res.addErrore(ErroreCore.ENTITA_NON_TROVATA_SINGOLO_MSG.getErrore(req.getCodice()));
			res.setEsito(Esito.FALLIMENTO);
			
			return;
		}
		
		res.setTipoFile(tipoFile);
		res.setEsito(Esito.SUCCESSO);

	}

}
