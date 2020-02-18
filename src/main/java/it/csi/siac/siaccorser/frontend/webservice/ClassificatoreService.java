/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice;

import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabile;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabileResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

@WebService(targetNamespace = CORSvcDictionary.NAMESPACE, name = "ClassificatoreService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface ClassificatoreService {

	
	@WebMethod
	@WebResult
	LeggiStrutturaAmminstrativoContabileResponse leggiStrutturaAmminstrativoContabile(@WebParam LeggiStrutturaAmminstrativoContabile request);

	//SIAC-6884: implementata ricerca SAC by Id, poi diventata superflua
	/*@WebMethod
	@WebResult
	LeggiStrutturaAmminstrativoContabileResponse leggiStrutturaAmminstrativoContabileById(@WebParam LeggiStrutturaAmminstrativoContabile request);*/
	

}



