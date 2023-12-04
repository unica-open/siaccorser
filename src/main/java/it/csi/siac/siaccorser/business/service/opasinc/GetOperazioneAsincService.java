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
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetOperazioneAsincResponse;
import it.csi.siac.siaccorser.integration.dad.OperazioneAsincronaDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.OperazioneAsincrona;
import it.csi.siac.siaccorser.model.StatoOperazioneAsincronaEnum;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GetOperazioneAsincService extends
		BaseService<GetOperazioneAsinc, GetOperazioneAsincResponse> {

	@Autowired
	private OperazioneAsincronaDad operazioneAsincronaDad;

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getIdOperazione(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("ID OPERAZIONE ASINCRONA"));
	}

	@Transactional
	@Override
	public GetOperazioneAsincResponse executeService(GetOperazioneAsinc serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		String methodName = "execute";
		log.debugStart(methodName, " Start");
		
		try {
			
			OperazioneAsincrona opAsinc = operazioneAsincronaDad.controllaStatoOperazioneAsinc(req.getIdOperazione());
			res.setMessaggio(opAsinc.getMessaggio());
			res.setStato(opAsinc.getStato());
			
			//qui se non ci sono errorri l'esito è positivo di default

		} catch (Exception e) {

			log.error(methodName, ErroreCore.ERRORE_DI_SISTEMA.getErrore("Errore di sistema"));

			String error ="Operazione con errori!" + ErroreCore.ERRORE_DI_SISTEMA.getErrore("Errore di sistema");
			// errore = cancellata
			res.setStato(StatoOperazioneAsincronaEnum.STATO_OPASINC_ERRORE.getCodice());
			res.setMessaggio(error);
			res.addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore("Errore di sistema"));
			res.setEsito(Esito.FALLIMENTO);

		}finally{
			log.debugEnd(methodName, " End");
		}

	}

}
