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

import it.csi.siac.siaccommonser.business.util.service.ServiceImplUtil;
import it.csi.siac.siaccorser.business.service.FindAzioneService;
import it.csi.siac.siaccorser.business.service.GetAccountService;
import it.csi.siac.siaccorser.business.service.GetAccountsService;
import it.csi.siac.siaccorser.business.service.GetAzioneRichiestaService;
import it.csi.siac.siaccorser.business.service.GetParametroConfigurazioneEnteService;
import it.csi.siac.siaccorser.business.service.GetRichiedenteService;
import it.csi.siac.siaccorser.business.service.GetRuoliAccountService;
import it.csi.siac.siaccorser.business.service.GetVariazioniService;
import it.csi.siac.siaccorser.business.service.RicercaAnnoEFaseBilancioService;
import it.csi.siac.siaccorser.business.service.RicercaBilancioService;
import it.csi.siac.siaccorser.business.service.RicercaEnteService;
import it.csi.siac.siaccorser.business.service.SaveAzioneRichiestaService;
import it.csi.siac.siaccorser.business.service.SetupCruscottoService;
import it.csi.siac.siaccorser.business.service.UpdateCruscottoService;
import it.csi.siac.siaccorser.business.service.attpendenti.RicercaSinteticaGruppoAttivitaPendentiService;
import it.csi.siac.siaccorser.business.service.bpm.ExecAzioneRichiestaService;
import it.csi.siac.siaccorser.business.service.bpm.GetAttivitaPendentiService;
import it.csi.siac.siaccorser.business.service.bpm.UpdateVariabiliDiProcessoService;
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
import it.csi.siac.siaccorser.frontend.webservice.msg.GetParametroConfigurazioneEnte;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetParametroConfigurazioneEnteResponse;
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
import it.csi.siac.siaccorser.frontend.webservice.msg.attpendenti.RicercaSinteticaGruppoAttivitaPendentiVariazione;
import it.csi.siac.siaccorser.frontend.webservice.msg.attpendenti.RicercaSinteticaGruppoAttivitaPendentiVariazioneResponse;

@WebService(serviceName = "CoreService",
portName = "CoreServicePort", 
targetNamespace = CORSvcDictionary.NAMESPACE, 
endpointInterface = "it.csi.siac.siaccorser.frontend.webservice.CoreService")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class CoreServiceImpl implements CoreService {

	@Autowired
	private ApplicationContext appCtx;

	@PostConstruct
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public RicercaEnteResponse ricercaEnte(RicercaEnte request) {
		return appCtx.getBean(RicercaEnteService.class).executeService(request);
	}

	
	@Override
	public RicercaBilancioResponse ricercaBilancio(RicercaBilancio request) {
		return appCtx.getBean(RicercaBilancioService.class).executeService(request);
	}


	
	@Override
	public GetRichiedenteResponse getRichiedente(GetRichiedente request) {
		return appCtx.getBean(GetRichiedenteService.class).executeService(request);
	}

	
	@Override
	public GetParametroConfigurazioneEnteResponse getParametroConfigurazioneEnte(GetParametroConfigurazioneEnte request) {
		return appCtx.getBean(GetParametroConfigurazioneEnteService.class).executeService(request);
	}

	@Override
	public GetAccountsResponse getAccounts(GetAccounts request) {
		return appCtx.getBean(GetAccountsService.class).executeService(request);
	}

	@Override
	public SetupCruscottoResponse setupCruscotto(SetupCruscotto request) {
		return appCtx.getBean(SetupCruscottoService.class).executeService(request);
	}

	@Override
	public UpdateCruscottoResponse updateCruscotto(UpdateCruscotto request) {
		return appCtx.getBean(UpdateCruscottoService.class).executeService(request);
	}

	@Override
	public SaveAzioneRichiestaResponse saveAzioneRichiesta(SaveAzioneRichiesta request) {
		return appCtx.getBean(SaveAzioneRichiestaService.class).executeService(request);
	}

	@Override
	public FindAzioneResponse findAzione(FindAzione request) {
		return appCtx.getBean(FindAzioneService.class).executeService(request);
	}

	@Override
	public GetAzioneRichiestaResponse getAzioneRichiesta(GetAzioneRichiesta request) {
		return appCtx.getBean(GetAzioneRichiestaService.class).executeService(request);
	}

	@Override
	public ExecAzioneRichiestaResponse execAzioneRichiesta(ExecAzioneRichiesta request) {
		return appCtx.getBean(ExecAzioneRichiestaService.class).executeService(request);
	}

	@Override
	public UpdateVariabiliDiProcessoResponse updateVariabiliDiProcesso( UpdateVariabiliDiProcesso request) {
		return appCtx.getBean(UpdateVariabiliDiProcessoService.class).executeService(request);
	}

	@Override
	public GetAttivitaPendentiResponse getAttivitaPendenti(GetAttivitaPendenti request) {
		return appCtx.getBean(GetAttivitaPendentiService.class).executeService(request);
	}

	@Override
	public GetProcessiEntitaResponse getProcessiEntita(GetProcessiEntita request) {
		// TODO: implementare correttamente
		return ServiceImplUtil.unimplementedServiceResponse(new GetProcessiEntitaResponse());
	}

	@Override
	public AbortProcessoResponse abortProcesso(AbortProcesso request) {
		// TODO: implementare correttamente
		return ServiceImplUtil.unimplementedServiceResponse(new AbortProcessoResponse());
	}

	@Override
	public GetNotifichePendentiResponse getNotifichePendenti(GetNotifichePendenti request) {
		// TODO: implementare correttamente
		return ServiceImplUtil.unimplementedServiceResponse(new GetNotifichePendentiResponse());
	}

	@Override
	public RicercaAnnoEFaseBilancioResponse ricercaAnnoEFaseBilancio(RicercaAnnoEFaseBilancio request) {
		return appCtx.getBean(RicercaAnnoEFaseBilancioService.class).executeService(request);
	}

	
	@Override
	public GetRuoliResponse getRuoliAccount(GetRuoli request) {
		return appCtx.getBean(GetRuoliAccountService.class).executeService(request);
	}
	
	
	@Override
	public GetAccountsResponse getAccount(GetAccounts request) {
		return appCtx.getBean(GetAccountService.class).executeService(request);
	}
	
	
	@Override
	public GetVariazioniResponse getVariazioniBySac(GetVariazioni request) {
		return appCtx.getBean(GetVariazioniService.class).executeService(request);
	}

	@Override
	public RicercaSinteticaGruppoAttivitaPendentiVariazioneResponse ricercaSinteticaGruppoAttivitaPendentiVariazione(RicercaSinteticaGruppoAttivitaPendentiVariazione request) {
		return appCtx.getBean(RicercaSinteticaGruppoAttivitaPendentiService.class).executeService(request);
	}
	
}
