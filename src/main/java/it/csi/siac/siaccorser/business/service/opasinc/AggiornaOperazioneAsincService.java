/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.opasinc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccorser.frontend.webservice.msg.AggiornaOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.AggiornaOperazioneAsincResponse;
import it.csi.siac.siaccorser.integration.dad.OperazioneAsincronaDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AggiornaOperazioneAsincService extends
		BaseService<AggiornaOperazioneAsinc, AggiornaOperazioneAsincResponse> {

	@Autowired
	private OperazioneAsincronaDad operazioneAsincronaDad;

	@Transactional
	public AggiornaOperazioneAsincResponse executeService(AggiornaOperazioneAsinc serviceRequest, AggiornaOperazioneAsincResponse serviceResponse) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		String methodName = "execute";
		log.debugStart(methodName, " Start ");

		try {

			log.debug(methodName, " Chiama aggiorna opAsinc");
			operazioneAsincronaDad.aggiornaOperazioneAsinc(req.getIdOperazioneAsinc(),
					req.getStato(),req.getIdEnte());
			
			// E' gia' successo di default
		} catch (Exception e) {
			log.error(methodName,ErroreCore.ERRORE_DI_SISTEMA.getErrore("Errore di sistema"));
			res.addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore("Errore di sistema"));
			res.setEsito(Esito.FALLIMENTO);
			
		}finally{
			log.debugEnd(methodName, " End");
		}
		

	}

}
