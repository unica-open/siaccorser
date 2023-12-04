/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.opasinc;


import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciOperazioneAsincResponse;
import it.csi.siac.siaccorser.integration.dad.OperazioneAsincronaDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.StatoOperazioneAsincronaEnum;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class InserisciOperazioneAsincService 
	extends BaseService<InserisciOperazioneAsinc, InserisciOperazioneAsincResponse> {

	@Autowired
	protected Mapper dozerBeanMapper;
	

	@Autowired
	private OperazioneAsincronaDad operazioneAsincronaDad;
		
	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getEnte(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("ente"));
		checkNotNull(req.getAccount(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("account"));
		checkNotNull(req.getAzioneRichiesta(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("azione richiesta"));
		checkNotNull(req.getAzioneRichiesta().getAzione(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("azione"));

		checkCondition(req.getEnte().getUid() > 0, ErroreCore.VALORE_NON_CONSENTITO.getErrore("id ente", req.getEnte().getUid()));
		checkCondition(req.getAccount().getUid() > 0, ErroreCore.VALORE_NON_CONSENTITO.getErrore("id account", req.getAccount().getUid()));
		checkCondition(req.getAzioneRichiesta().getAzione().getUid() > 0, ErroreCore.VALORE_NON_CONSENTITO.getErrore("id azione", req.getAzioneRichiesta().getAzione().getUid()));
	}
	
	


	@Transactional
	@Override
	public InserisciOperazioneAsincResponse executeService(InserisciOperazioneAsinc serviceRequest) {
		return super.executeService(serviceRequest);
	}
	
	
	@Override
	protected void execute() {
		String methodName ="execute";
		log.debugStart(methodName," Start");
		
		try {
			
			Integer idOpAsinc = operazioneAsincronaDad.inserisciOperazioneAsinc(
					req.getAccount().getUid(),req.getAzioneRichiesta().getAzione().getUid(), req.getUidVariazioneImportoCapitolo(), 
					req.getEnte().getUid(), req.getRichiedente().getOperatore().getCodiceFiscale());
			
			res.setMessaggio("L'operazione " + req.getAzioneRichiesta().getAzione().getNome() + " e' in stato: " + StatoOperazioneAsincronaEnum.STATO_OPASINC_AVVIATA.getCodice());
			res.setIdOperazione(idOpAsinc);
			res.setStato(StatoOperazioneAsincronaEnum.STATO_OPASINC_AVVIATA.getCodice()); // avviata
			
			// E' gia' successo di default
			log.debug(methodName,"idOpAsinc: " + idOpAsinc);
			
		} catch (Exception e) {
			log.error(methodName,ErroreCore.ERRORE_DI_SISTEMA.getErrore("Errore di sistema"));
			
			res.setStato(StatoOperazioneAsincronaEnum.STATO_OPASINC_ERRORE.getCodice());
			res.setMessaggio("Operazione con errori: " + ErroreCore.ERRORE_DI_SISTEMA.getErrore("Errore di sistema"));
			
			res.addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore("Errore di sistema"));
			res.setEsito(Esito.FALLIMENTO);
			
		}finally{
			log.debugEnd(methodName," End");
		}
		
	}
	

	
}
