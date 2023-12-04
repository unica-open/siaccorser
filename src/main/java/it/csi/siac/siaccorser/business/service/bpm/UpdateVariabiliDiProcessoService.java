/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.bpm;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.business.service.BaseCoreService;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateVariabiliDiProcesso;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateVariabiliDiProcessoResponse;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UpdateVariabiliDiProcessoService extends BaseCoreService<UpdateVariabiliDiProcesso, UpdateVariabiliDiProcessoResponse> {

	
	@Transactional
	@Override
	public UpdateVariabiliDiProcessoResponse executeService(UpdateVariabiliDiProcesso serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void checkServiceParam() throws ServiceParamError {		
		checkNotNull(req.getAzioneRichiesta(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("azione richiesta"));
		checkNotNull(req.getAzioneRichiesta().getIdAttivita(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("id attivita"));
	}
	
	@Override
	protected void execute() {
		final String methodName = "execute";


		AzioneRichiesta azioneRichiesta = req.getAzioneRichiesta();
		String idAttivita = azioneRichiesta.getIdAttivita();
						
//		// calolo l'id dell'istanza
//		String idIstanzaProcesso = bpmService.getIdIstanzaProcesso(idAttivita);
//		if (idIstanzaProcesso==null){
//			res.addErrore(ErroreCore.ENTITA_INESISTENTE.getErrore(idAttivita));
//			res.setEsito(Esito.FALLIMENTO);
//			return;
//		}
//		
//		/**
//		 *  Step 1. setto le variabili di processo
//		 */
//		ServiceResponse response = bpmService.setVariabiliDiProcesso(null, idIstanzaProcesso, azioneRichiesta.getVariabiliProcesso());
//		
//		
//		/**
//		 * Step 2. copio le variabili di processo in quelle di task (istanza)
//		 */
//		if(!response.isFallimento()){
//	    	List<String> vars = new ArrayList<String>();
//	    	vars.add(VariabileProcesso.NOME_VARIABILE_SAC_PROCESSO);
//	    	vars.add(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO);
//	    	vars.add(VariabileProcesso.NOME_VARIABILE_ENTE_PROPRIETARIO_PROCESSO);
//	    	
//	    	log.debug(methodName, "variabili da recuperare:"+vars);
//	    	Map<String, VariabileProcesso> mapVars = bpmService.getVariabiliDiProcesso(idIstanzaProcesso, vars);
//	    	
//			// recupero i prossimi task
//			List<ActivityInstance> tasks = bpmService.getProssimaActivity(idIstanzaProcesso, true);
//
//	    	// imposto la var di istanza SAC con il valore di quella di processo
//			bpmService.copiaVarIstanzaDaVarProcesso(mapVars, tasks, VariabileProcesso.NOME_VARIABILE_SAC_ATTIVITA, 
//	    		VariabileProcesso.NOME_VARIABILE_SAC_PROCESSO);
//	    	
//			bpmService.copiaVarIstanzaDaVarProcesso(mapVars, tasks, VariabileProcesso.NOME_VARIABILE_ENTE_PROPRIETARIO_ATTIVITA, 
//		    		VariabileProcesso.NOME_VARIABILE_ENTE_PROPRIETARIO_PROCESSO);
//	    		    	
//	    	// se il processo non ha la variabile anno di esercizio o è vuota, la setto con il valore di default
//	    	if (!mapVars.containsKey(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO) ||
//	    		mapVars.get(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO).getValore()==null
//	    		||"".equals(mapVars.get(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO).getValore())) {
//				VariabileProcesso var = new VariabileProcesso();
//				var.setNome(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO);
//				var.setValore(VariabileProcesso.VALORE_NULL_ANNO_ESERCIZIO);
//				mapVars.put(VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO, var);
//			}
//	    	
//	    	// imposto la var di istanza AnnoEsercizio con il valore di quella di processo
//	    	bpmService.copiaVarIstanzaDaVarProcesso(mapVars, tasks, 
//	    		VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_ATTIVITA, 
//	    		VariabileProcesso.NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO);
//	    }else{
//	    	response.setEsito(Esito.FALLIMENTO);
//			response.setErrori(response.getErrori());
//	    }
			
	}

	
}
