/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.bpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.business.service.BaseCoreService;
import it.csi.siac.siaccorser.frontend.webservice.msg.ExecAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.ExecAzioneRichiestaResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcesso;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcessoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTask;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTaskResponse;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.VariabileProcesso;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ExecAzioneRichiestaService extends BaseCoreService<ExecAzioneRichiesta, ExecAzioneRichiestaResponse> {

	@Transactional
	@Override
	public ExecAzioneRichiestaResponse executeService(ExecAzioneRichiesta serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void checkServiceParam() throws ServiceParamError {		
		checkNotNull(req.getAzioneRichiesta(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("azione richiesta"));
		checkNotNull(req.getAzioneRichiesta().getAzione(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("azione"));
	}
	
	@Override
	protected void execute() {

		AzioneRichiesta azioneRichiesta = req.getAzioneRichiesta();
		Azione azione = azioneRichiesta.getAzione();
		
		// gestione delle diverse tipologie di azione
		switch(azione.getTipo()) {

			case AVVIO_PROCESSO:
				avviaProcesso(azioneRichiesta, azione);
				break;
			case ATTIVITA_PROCESSO:
				attivitaProcesso(azioneRichiesta);
				break;
			// i TipoAzione seguenti non sono supportati
			case NOTIFICA:
			default:
				res.addErrore(ErroreCore.TIPO_AZIONE_NON_SUPPORTATA.getErrore(azione.getTipo().toString()));
				res.setEsito(Esito.FALLIMENTO);
				break;
		}
		
		if(res!=null ){
			res.cleanUnmarshallableVariabileProcessoValue();
		}
		log.logXmlTypeObject(res, "Returning");
		
	}

	private void avviaProcesso(AzioneRichiesta azioneRichiesta, Azione azione) {
//		AvviaProcesso avviaProcessoReq = new AvviaProcesso();
//		avviaProcessoReq.setNomeProcesso(azione.getNomeProcesso());
//		avviaProcessoReq.setNomeTask(azione.getNomeTask());
//		avviaProcessoReq.setVariabiliProcesso(azioneRichiesta.getVariabiliProcesso());
//		AvviaProcessoResponse avviaProcessoRes = bpmService.avviaProcesso(avviaProcessoReq);
//		if(avviaProcessoRes.isFallimento()){
//			res.setEsito(Esito.FALLIMENTO);
//			res.setErrori(avviaProcessoRes.getErrori());
//		}else{
//			res.setIdTask(avviaProcessoRes.getIdTask());
//			res.setNomeTask(avviaProcessoRes.getNomeTask());
//			
//			//Riottengo le variabili di processo aggiornate e le imposto nella response.
//			String idIstanzaProcesso = 	bpmService.getIdIstanzaProcesso(avviaProcessoRes.getIdTask());
//			log.debug("execAzioneRichiesta", "idIstanzaProcesso: "+ idIstanzaProcesso + " ["+avviaProcessoRes.getIdTask()+"]");	
//			Map<String, VariabileProcesso> variabiliDiProcessoAggiornate = bpmService.getVariabiliDiProcesso(idIstanzaProcesso);
//			log.info("execAzioneRichiesta", "nuove variabili di processo [AVVIO_PROCESSO]: "+ variabiliDiProcessoAggiornate);											
//			res.setVariabiliDiProcesso(toList(variabiliDiProcessoAggiornate));						
// 		}
	}
	
	private void attivitaProcesso(AzioneRichiesta azioneRichiesta) {
//		String idAttivita = azioneRichiesta.getIdAttivita();
//		EseguiTask eseguiTaskReq = new EseguiTask();
//
//		eseguiTaskReq.setIdTask(idAttivita);
//		eseguiTaskReq.setVariabiliProcesso(azioneRichiesta.getVariabiliProcesso());
//		
//		// calolo l'id dell'istanza
//		String idIstanzaProcesso = 
//			bpmService.getIdIstanzaProcesso(idAttivita);
//		if (idIstanzaProcesso==null){
//			res.addErrore(ErroreCore.ENTITA_INESISTENTE.getErrore(idAttivita));
//			res.setEsito(Esito.FALLIMENTO);						
//			return;
//		}
//		eseguiTaskReq.setIdIstanzaProcesso(idIstanzaProcesso);
//		
//		bpmService.setVariabiliDiProcesso(null, idIstanzaProcesso, eseguiTaskReq.getVariabiliProcesso());
//		
//		EseguiTaskResponse eseguiTaskRes = bpmService.eseguiTask(eseguiTaskReq);
//								
//		
//		if(eseguiTaskRes.isFallimento()){
//			res.setEsito(Esito.FALLIMENTO);
//			res.setErrori(eseguiTaskRes.getErrori());
//		}else{
//			
//			if(StringUtils.isNotEmpty(eseguiTaskRes.getIdTask())) {
//				res.setIdTask(eseguiTaskRes.getIdTask());
//				res.setNomeTask(eseguiTaskRes.getNomeTask());
//			}
//			//Riottengo le variabili di processo aggiornate e le imposto nella response.
//			Map<String, VariabileProcesso> variabiliDiProcessoAggiornate = bpmService.getVariabiliDiProcesso(idIstanzaProcesso);
//			log.info("execAzioneRichiesta", "nuove variabili di processo [ATTIVITA_PROCESSO]: "+ variabiliDiProcessoAggiornate);											
//			res.setVariabiliDiProcesso(toList(variabiliDiProcessoAggiornate));
//			
//		}
	}

	private List<VariabileProcesso> toList(Map<String, VariabileProcesso> variabiliDiProcesso) {
		List<VariabileProcesso> result = new ArrayList<VariabileProcesso>();
		if(variabiliDiProcesso!=null){
			for(VariabileProcesso vp : variabiliDiProcesso.values()){
				result.add(vp);							
			}
		}
		return result;
	}
	
}
