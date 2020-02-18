/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

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

@WebService(targetNamespace = CORSvcDictionary.NAMESPACE, name = "FileService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface FileService {

	@WebMethod
	@WebResult
	UploadFileResponse uploadFile(@WebParam UploadFile parameters);

	@WebMethod
	@WebResult
	RicercaFileResponse ricercaFile(@WebParam RicercaFile parameters);

	@WebMethod
	@WebResult
	GetElencoTipoFileResponse getElencoTipoFileByAccount(@WebParam GetElencoTipoFile parameters);

	@WebMethod
	@WebResult
	GetElencoStatoFileResponse getElencoStatoFile(@WebParam GetElencoStatoFile parameters);

	@WebMethod
	@WebResult
	ModificaStatoFileResponse modificaStatoFile(@WebParam ModificaStatoFile req);

	@WebMethod
	@WebResult
	RicercaTipoFileResponse ricercaTipoFile(RicercaTipoFile arg);

	@WebMethod
	@WebResult
	EliminaFileResponse eliminaFile(@WebParam EliminaFile parameters);
}
