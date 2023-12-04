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

import it.csi.siac.siaccorser.business.service.opasinc.AggiornaOperazioneAsincService;
import it.csi.siac.siaccorser.business.service.opasinc.GetDettaglioOperazioneAsincService;
import it.csi.siac.siaccorser.business.service.opasinc.GetNotificheOperazioneAsincService;
import it.csi.siac.siaccorser.business.service.opasinc.GetOperazioneAsincService;
import it.csi.siac.siaccorser.business.service.opasinc.InserisciDettaglioOperazioneAsincService;
import it.csi.siac.siaccorser.business.service.opasinc.InserisciOperazioneAsincService;
import it.csi.siac.siaccorser.frontend.webservice.msg.AggiornaOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.AggiornaOperazioneAsincResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetDettaglioOperazioneAsincrona;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetDettaglioOperazioneAsincronaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetNotificheOperazioneAsincrona;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetNotificheOperazioneAsincronaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetOperazioneAsincResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciDettaglioOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciDettaglioOperazioneAsincResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciOperazioneAsincResponse;

/**
 * Implementazione del servizio che gestisce le chiamate asicrone
 * 
 * @author 
 * 
 */
@WebService(serviceName = "OperazioneAsincronaService",
portName = "OperazioneAsincronaServicePort", 
targetNamespace = CORSvcDictionary.NAMESPACE, 
endpointInterface = "it.csi.siac.siaccorser.frontend.webservice.OperazioneAsincronaService")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class OperazioneAsincronaServiceImpl implements OperazioneAsincronaService {

	
	@Autowired
	private ApplicationContext appCtx;
	
	
	@PostConstruct
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public InserisciOperazioneAsincResponse inserisciOperazioneAsinc(
			InserisciOperazioneAsinc request) {
		return appCtx.getBean(InserisciOperazioneAsincService.class).executeService(request);
	}

	@Override
	public AggiornaOperazioneAsincResponse aggiornaOperazioneAsinc(
			AggiornaOperazioneAsinc request) {
		return appCtx.getBean(AggiornaOperazioneAsincService.class).executeService(request);
	}

	@Override
	public GetOperazioneAsincResponse getOperazioneAsinc(GetOperazioneAsinc request) {
		return appCtx.getBean(GetOperazioneAsincService.class).executeService(request);
	}

	@Override
	public InserisciDettaglioOperazioneAsincResponse inserisciDettaglioOperazioneAsinc(
			InserisciDettaglioOperazioneAsinc request) {
		return appCtx.getBean(InserisciDettaglioOperazioneAsincService.class).executeService(request);
	}

	@Override
	public GetNotificheOperazioneAsincronaResponse getNotificheOperazioneAsincrona(
			GetNotificheOperazioneAsincrona request) {
		return appCtx.getBean(GetNotificheOperazioneAsincService.class).executeService(request);
	}

	
	@Override
	public GetDettaglioOperazioneAsincronaResponse getDettaglioOperazioneAsincrona(
			GetDettaglioOperazioneAsincrona request) {
		return appCtx.getBean(GetDettaglioOperazioneAsincService.class).executeService(request);
	}
}
