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
import it.csi.siac.siaccorser.frontend.webservice.msg.file.UploadFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.UploadFileResponse;
import it.csi.siac.siaccorser.integration.dad.FileDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.model.file.File;
import it.csi.siac.siaccorser.model.file.TipoFile;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UploadFileService extends BaseService<UploadFile, UploadFileResponse> {

	@Autowired
	private FileDad fileDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getEnte(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("ente"));
		checkNotNull(req.getFile(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("file"));
		checkNotNull(req.getFile().getStatoFile(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("stato file"));
		checkNotNull(req.getFile().getTipo(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("tipo file"));
		checkCondition(req.getFile().getTipo().getUid() > 0 || req.getFile().getTipo().getCodice() != null,
				ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("uid tipo file o codice tipo file"));
	}

	@Override
	protected void init() {
		fileDad.setEnte(req.getEnte());
		fileDad.setLoginOperazione(req.getRichiedente().getOperatore().getCodiceFiscale());
	}

	@Transactional
	@Override
	public UploadFileResponse executeService(UploadFile serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		File file = req.getFile();
		TipoFile tipoFile = file.getTipo();

		if (tipoFile.getUid() == 0)
			file.setTipo(fileDad.ricercaTipoFile(tipoFile.getCodice()));

		res.setFile(fileDad.create(file));
		res.setEsito(Esito.SUCCESSO);
	}

}
