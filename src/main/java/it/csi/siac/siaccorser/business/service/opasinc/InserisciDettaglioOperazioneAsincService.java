/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.opasinc;

import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.frontend.webservice.OperazioneAsincronaService;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciDettaglioOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciDettaglioOperazioneAsincResponse;
import it.csi.siac.siaccorser.integration.dad.OperazioneAsincronaDad;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class InserisciDettaglioOperazioneAsincService extends
		BaseService<InserisciDettaglioOperazioneAsinc, InserisciDettaglioOperazioneAsincResponse> {

	@Autowired
	protected Mapper dozerBeanMapper;

	@Autowired
	private OperazioneAsincronaDad operazioneAsincronaDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getIdEnte(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("id ente"));
		checkNotNull(req.getIdOperazioneAsincrona(),
				ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("id operazione asincrona"));
		checkCondition(req.getIdEnte() > 0, ErroreCore.VALORE_NON_CONSENTITO.getErrore("id ente"),
				false);
		checkCondition(req.getIdOperazioneAsincrona() > 0,
				ErroreCore.VALORE_NON_CONSENTITO.getErrore("id operazione asincrona"), false);
		checkCondition(StringUtils.isNotBlank(req.getCodice()) || StringUtils.isNotEmpty(req.getServiceResponse()),
				ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("codice"), false);
	}

	@Transactional
	@Override
	public InserisciDettaglioOperazioneAsincResponse executeService(InserisciDettaglioOperazioneAsinc serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		String methodName = "execute";
		log.debugStart(methodName, " Start");

		String codice = req.getCodice();
		
		if (req.getServiceResponse() != null)
				codice = OperazioneAsincronaService.CODICE_SERVICE_RESPONSE;
		
		operazioneAsincronaDad.inserisciDettaglioOperazioneAsinc(req.getIdOperazioneAsincrona(),
				codice, req.getDescrizione(), req.getEsito(), req.getMsgErrore(),
				req.getServiceResponse(), req.getIdEnte(), req.getRichiedente().getOperatore()
						.getCodiceFiscale());

		log.debugEnd(methodName, " End");
	}

}
