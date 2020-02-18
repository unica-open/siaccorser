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
import it.csi.siac.siaccorser.frontend.webservice.CoreService;
import it.csi.siac.siaccorser.frontend.webservice.exception.SystemException;
import it.csi.siac.siaccorser.model.Errore;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

/**
 * Client del servizio Core
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
public class CoreServiceClient extends BaseServiceClient {

	private CoreService port;

	public CoreServiceClient(){
		super();
	}
	
	/**
	 * Creazione del servizio
	 * 
	 * @throws SystemException
	 */
	private void createService() {
		try {
			wsdlURL = new URL(endpoint+"?wsdl");
			serviceName = new QName(CORSvcDictionary.NAMESPACE,
					"CoreService");
			service = Service.create(wsdlURL, serviceName);
			port = service.getPort(CoreService.class);
			((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

		} catch (MalformedURLException e) {
			throw new SystemException(
					new Errore(ErroreCore.ERRORE_DI_SISTEMA.toString(),
							wsdlURL.toString()));
		}
	}

	
	public CoreService getPort() {
		if (service==null)
			createService();
		
		return port;
	}
		
}
