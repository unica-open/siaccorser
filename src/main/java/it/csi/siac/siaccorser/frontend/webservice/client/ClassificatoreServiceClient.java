/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.frontend.webservice.ClassificatoreService;
import it.csi.siac.siaccorser.frontend.webservice.exception.SystemException;
import it.csi.siac.siaccorser.model.Errore;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

/**
 * Client del servizio ClassificatoreService
 * 
 * @author rmontuori
 * 
 */
@Deprecated
public class ClassificatoreServiceClient extends BaseServiceClient {

	private ClassificatoreService port;

	@Deprecated
	public ClassificatoreServiceClient(){
		super();
	}
	
	/**
	 * Creazione del servizio
	 * 
	 *
	 */
	private void createService() {
		try {
			wsdlURL = new URL(endpoint+"?wsdl");
			serviceName = new QName(CORSvcDictionary.NAMESPACE,
					"ClassificatoreService");
			service = Service.create(wsdlURL, serviceName);
			port = service.getPort(ClassificatoreService.class);
			((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

		} catch (MalformedURLException e) {
			throw new SystemException(
					new Errore(ErroreCore.ERRORE_DI_SISTEMA.toString(),
							wsdlURL.toString()));
		}
	}

	@Deprecated
	public ClassificatoreService getPort() {
		if (service==null)
			createService();
		
		return port;
	}
		
}
