/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * Client di base dei servizi
 * 
 * @author alagna
 * 
 */

@Deprecated
public abstract class BaseServiceClient  {

	protected URL wsdlURL;
	protected QName serviceName;
	protected Service service;
	protected String endpoint;

	public BaseServiceClient(){
		// Costruttore vuoto
	}

	@Deprecated
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
}
