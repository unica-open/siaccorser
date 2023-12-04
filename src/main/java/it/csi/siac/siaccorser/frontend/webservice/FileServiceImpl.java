/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.siac.siaccorser.business.service.file.EliminaFileService;
import it.csi.siac.siaccorser.business.service.file.GetElencoStatoFileService;
import it.csi.siac.siaccorser.business.service.file.GetElencoTipoFileService;
import it.csi.siac.siaccorser.business.service.file.ModificaStatoFileService;
import it.csi.siac.siaccorser.business.service.file.RicercaFileService;
import it.csi.siac.siaccorser.business.service.file.RicercaTipoFileService;
import it.csi.siac.siaccorser.business.service.file.UploadFileService;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.EliminaFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.EliminaFileResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.GetElencoStatoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.GetElencoStatoFileResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.GetElencoTipoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.GetElencoTipoFileResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.ModificaStatoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.ModificaStatoFileResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFileResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaTipoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaTipoFileResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.UploadFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.UploadFileResponse;

@WebService(serviceName = "FileService", 
portName = "FileServicePort", 
targetNamespace = CORSvcDictionary.NAMESPACE, 
endpointInterface = "it.csi.siac.siaccorser.frontend.webservice.FileService")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class FileServiceImpl implements FileService {

	@Autowired
	private ApplicationContext appCtx;

	@PostConstruct
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public UploadFileResponse uploadFile(UploadFile arg) {
		return appCtx.getBean(UploadFileService.class).executeService(arg);
	}
	

	@Override
	public RicercaFileResponse ricercaFile(RicercaFile arg) {
		return appCtx.getBean(RicercaFileService.class).executeService(arg);

	}

  
	@Override
	public RicercaTipoFileResponse ricercaTipoFile(RicercaTipoFile arg) {
		return appCtx.getBean(RicercaTipoFileService.class).executeService(arg);

	}

	@Override
	public GetElencoTipoFileResponse getElencoTipoFileByAccount(GetElencoTipoFile arg) {
		return appCtx.getBean(GetElencoTipoFileService.class).executeService(arg);
	}

	@Override
	public GetElencoStatoFileResponse getElencoStatoFile(GetElencoStatoFile arg) {
		return appCtx.getBean(GetElencoStatoFileService.class).executeService(arg);
	}

	@Override
	public ModificaStatoFileResponse modificaStatoFile(ModificaStatoFile arg) {
		return appCtx.getBean(ModificaStatoFileService.class).executeService(arg);
	}

	@Override
	public EliminaFileResponse eliminaFile(EliminaFile arg) {
		return appCtx.getBean(EliminaFileService.class).executeService(arg);
	}

}
