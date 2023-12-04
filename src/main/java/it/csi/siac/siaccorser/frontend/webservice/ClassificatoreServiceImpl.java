/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice;

import it.csi.siac.siaccorser.business.service.classificatori.LeggiStrutturaAmminstrativoContabileService;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabile;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabileResponse;

import javax.annotation.PostConstruct;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Implementazione del servizio ClassificatoreService, con metodi comuni ai vari
 * moduli BIL - FIN- ATT
 * 
 * 
 * @author rmontuori
 */

@WebService(serviceName = "ClassificatoreService", 
portName = "ClassificatoreServicePort", 
targetNamespace = CORSvcDictionary.NAMESPACE, 
endpointInterface = "it.csi.siac.siaccorser.frontend.webservice.ClassificatoreService")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class ClassificatoreServiceImpl implements ClassificatoreService {

	@Autowired
	private ApplicationContext appCtx;

	@PostConstruct
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public LeggiStrutturaAmminstrativoContabileResponse leggiStrutturaAmminstrativoContabile(
			@WebParam LeggiStrutturaAmminstrativoContabile request) {
		return appCtx.getBean(LeggiStrutturaAmminstrativoContabileService.class).executeService(
				request);
	}
	
	//SIAC-6884: implementata ricerca SAC by Id, poi diventata superflua
	/*
	@Override
	public LeggiStrutturaAmminstrativoContabileResponse leggiStrutturaAmminstrativoContabileById(
			@WebParam LeggiStrutturaAmminstrativoContabile request) {
		return appCtx.getBean(LeggiStrutturaAmminstrativoContabileByIdService.class).executeService(request);
	}*/

}
