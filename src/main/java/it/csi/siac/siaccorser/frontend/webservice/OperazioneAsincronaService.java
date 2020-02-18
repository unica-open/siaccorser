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
 * Interfaccia del servizio per la gestione delle chiamate asincrone 
 * 
 * @author rmontuori
 */
@WebService(targetNamespace = CORSvcDictionary.NAMESPACE, name = "OperazioneAsincronaService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface OperazioneAsincronaService {

	
	String CODICE_SERVICE_RESPONSE = "SERVICE_RESPONSE";
	
	/**
	 * Inserisce le info relative all'operazione asincrona
	 * @param request
	 * @return
	 */
	@WebMethod
	@WebResult InserisciOperazioneAsincResponse inserisciOperazioneAsinc(
		@WebParam InserisciOperazioneAsinc request
	);
	
	
	/**
	 * Aggiorna le info relative all'operazione asincrona passata nella request
	 * @param request
	 * @return
	 */
	@WebMethod
	@WebResult AggiornaOperazioneAsincResponse aggiornaOperazioneAsinc(
		@WebParam AggiornaOperazioneAsinc request
	);
		
	/**
	 * Restituisce l'id dell'operazione asincrona ricercata
	 * @param request
	 * @return
	 */
	@WebMethod
	@WebResult GetOperazioneAsincResponse getOperazioneAsinc(
		@WebParam GetOperazioneAsinc request
	);

	
	/**
	 * Permette di tracciare un dettaglio dell'operazione massiva che si sta eseguendo
	 * @param request
	 * @return
	 */
	@WebMethod
	@WebResult InserisciDettaglioOperazioneAsincResponse inserisciDettaglioOperazioneAsinc(
		@WebParam InserisciDettaglioOperazioneAsinc request
	);
	
	
	/**
	 * Permette di trovare le notifiche delle operazini asicnrone
	 * @param request
	 * @return
	 */
	@WebMethod
	@WebResult GetNotificheOperazioneAsincronaResponse getNotificheOperazioneAsincrona(
		@WebParam GetNotificheOperazioneAsincrona request
	);
	
	
	
	/**
	 * Permette di trovare le notifiche delle operazini asicnrone
	 * @param request
	 * @return
	 */
	@WebMethod
	@WebResult GetDettaglioOperazioneAsincronaResponse getDettaglioOperazioneAsincrona(
		@WebParam GetDettaglioOperazioneAsincrona request
	);

}

