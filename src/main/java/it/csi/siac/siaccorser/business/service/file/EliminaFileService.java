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
import it.csi.siac.siaccorser.frontend.webservice.msg.file.EliminaFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.EliminaFileResponse;
import it.csi.siac.siaccorser.integration.dad.FileDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EliminaFileService extends
		BaseService<EliminaFile, EliminaFileResponse> {

	@Autowired
	private FileDad fileDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getUid(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("uid"));
	}

	@Transactional
	@Override
	public EliminaFileResponse executeService(EliminaFile serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		fileDad.eliminaFile(req.getUid(), req.getRichiedente().getOperatore().getCodiceFiscale());
		res.setEsito(Esito.SUCCESSO);
	}

}
