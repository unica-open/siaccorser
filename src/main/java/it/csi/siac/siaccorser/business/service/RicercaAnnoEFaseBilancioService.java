/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaAnnoEFaseBilancio;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaAnnoEFaseBilancioResponse;
import it.csi.siac.siaccorser.model.AnnoBilancio;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RicercaAnnoEFaseBilancioService extends BaseCoreService<RicercaAnnoEFaseBilancio, RicercaAnnoEFaseBilancioResponse> {
	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getAnno(),
				ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("anno"));
	}

	@Transactional
	@Override
	public RicercaAnnoEFaseBilancioResponse executeService(RicercaAnnoEFaseBilancio serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		List<AnnoBilancio> anniBilancio = getAnniBilancio(req.getRichiedente().getAccount());
		
		for (AnnoBilancio annoBilancio : anniBilancio) {
			
			if(req.getAnno().toString().equals(annoBilancio.getAnno())){
				res.setAnnoEFaseBilancio(annoBilancio);
				break;
			}
		}
		
		res.setEsito(Esito.SUCCESSO);
	}

}
