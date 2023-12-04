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
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.GetElencoStatoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.GetElencoStatoFileResponse;
import it.csi.siac.siaccorser.integration.dad.FileDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GetElencoStatoFileService extends
		BaseService<GetElencoStatoFile, GetElencoStatoFileResponse> {

	@Autowired
	private FileDad fileDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getEnte(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("ente"));
	}

	@Override
	protected void init() {
		fileDad.setEnte(req.getEnte());
	}

	@Transactional
	@Override
	public GetElencoStatoFileResponse executeService(GetElencoStatoFile serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		res.setElencoStatoFile(fileDad.getElencoStatoFile());
		res.setEsito(Esito.SUCCESSO);
	}

}
