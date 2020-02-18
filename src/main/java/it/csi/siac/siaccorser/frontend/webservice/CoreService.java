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

import it.csi.siac.siaccorser.frontend.webservice.msg.AbortProcesso;
import it.csi.siac.siaccorser.frontend.webservice.msg.AbortProcessoResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.ExecAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.ExecAzioneRichiestaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.FindAzione;
import it.csi.siac.siaccorser.frontend.webservice.msg.FindAzioneResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccounts;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccountsResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAttivitaPendenti;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAttivitaPendentiResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiestaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetNotifichePendenti;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetNotifichePendentiResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetProcessiEntita;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetProcessiEntitaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetRichiedente;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetRichiedenteResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetRuoli;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetRuoliResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetVariazioni;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetVariazioniResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaAnnoEFaseBilancio;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaAnnoEFaseBilancioResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaBilancio;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaBilancioResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaEnte;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaEnteResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.SaveAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.SaveAzioneRichiestaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.SetupCruscotto;
import it.csi.siac.siaccorser.frontend.webservice.msg.SetupCruscottoResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateCruscotto;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateCruscottoResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateVariabiliDiProcesso;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateVariabiliDiProcessoResponse;

/**
 * SI del servizio Core
 * 
 * @author alagna
 * @version $Id: $
 */
@WebService(targetNamespace = CORSvcDictionary.NAMESPACE, name = "CoreService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface CoreService {

	@WebMethod
	@WebResult
	GetAccountsResponse getAccounts(@WebParam GetAccounts request);

	@WebMethod
	@WebResult
	SetupCruscottoResponse setupCruscotto(@WebParam SetupCruscotto request);

	@WebMethod
	@WebResult
	UpdateCruscottoResponse updateCruscotto(@WebParam UpdateCruscotto request);

	@WebMethod
	@WebResult
	SaveAzioneRichiestaResponse saveAzioneRichiesta(@WebParam SaveAzioneRichiesta request);

	@WebMethod
	@WebResult
	FindAzioneResponse findAzione(@WebParam FindAzione request);

	@WebMethod
	@WebResult
	GetAzioneRichiestaResponse getAzioneRichiesta(@WebParam GetAzioneRichiesta request);

	@WebMethod
	@WebResult
	ExecAzioneRichiestaResponse execAzioneRichiesta(@WebParam ExecAzioneRichiesta request);

	@WebMethod
	@WebResult
	UpdateVariabiliDiProcessoResponse updateVariabiliDiProcesso(@WebParam UpdateVariabiliDiProcesso request);

	@WebMethod
	@WebResult
	GetProcessiEntitaResponse getProcessiEntita(@WebParam GetProcessiEntita request);

	@WebMethod
	@WebResult
	AbortProcessoResponse abortProcesso(@WebParam AbortProcesso request);

	@WebMethod
	@WebResult
	GetAttivitaPendentiResponse getAttivitaPendenti(@WebParam GetAttivitaPendenti request);

	@WebMethod
	@WebResult
	GetNotifichePendentiResponse getNotifichePendenti(@WebParam GetNotifichePendenti request);

	@WebMethod
	@WebResult
	RicercaEnteResponse ricercaEnte(@WebParam RicercaEnte request);

	@WebMethod
	@WebResult
	RicercaBilancioResponse ricercaBilancio(@WebParam RicercaBilancio request);
	
	@WebMethod
	@WebResult
	RicercaAnnoEFaseBilancioResponse ricercaAnnoEFaseBilancio(@WebParam RicercaAnnoEFaseBilancio request);

	@WebMethod
	@WebResult
	GetRichiedenteResponse getRichiedente(GetRichiedente request);
	
	@WebMethod
	@WebResult
	GetRuoliResponse getRuoliAccount(@WebParam GetRuoli request);

	@WebMethod
	@WebResult
	GetAccountsResponse getAccount(@WebParam GetAccounts request);
	
	@WebMethod
	@WebResult
	GetVariazioniResponse getVariazioniBySac(@WebParam GetVariazioni request);

	
	

}
