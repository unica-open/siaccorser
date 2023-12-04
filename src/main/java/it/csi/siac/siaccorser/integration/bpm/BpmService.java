/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm;

import java.util.List;
import java.util.Map;

import org.ow2.bonita.facade.def.majorElement.ProcessDefinition;
import org.ow2.bonita.facade.runtime.ActivityInstance;

import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcesso;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcessoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTask;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTaskResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.GetTaskInfo;
import it.csi.siac.siaccorser.integration.bpm.msg.GetTaskInfoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendenti;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendentiResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaNotifichePendenti;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaNotifichePendentiResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.TerminaProcesso;
import it.csi.siac.siaccorser.integration.bpm.msg.TerminaProcessoResponse;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.VariabileProcesso;

/**
 * Interfaccia del servizio di accesso al BPM
 * @deprecated by SIAC-8332-REGP
 * 
 * @author alagna
 */
@Deprecated 
public interface BpmService {

	RicercaAttivitaPendentiResponse ricercaAttivitaPendenti(
		RicercaAttivitaPendenti request);
	
	RicercaNotifichePendentiResponse ricercaNotifichePendenti(
		RicercaNotifichePendenti request);

	/**
	 * Richiama l'avvio di un processo sul server bpm bonita,
	 * setta le n variabili di processo
	 * ed esegue la prima attività (che può anche non essere un TASK UMANO)
	 */
	AvviaProcessoResponse avviaProcesso(AvviaProcesso request);
	
	/**
	 * Esegue semplicemente lo start del processo (SENZA FARE ALTRO).
	 * E' utilizzato da avviaProcesso, che si preoccupa poi di impostare le 
	 * variabili ed eseguire il primo task
	 * 
	 * @param request
	 * @return
	 */	
	AvviaProcessoResponse startProcesso(AvviaProcesso request);
	
	EseguiTaskResponse eseguiTask(EseguiTask request);

	TerminaProcessoResponse terminaProcesso(TerminaProcesso request);
	
	GetTaskInfoResponse getTaskInfo(GetTaskInfo getTaskInfoReq);
	
	ProcessDefinition getUltimaVersione(String nomeProcesso);
	
	List<ActivityInstance> getProssimaActivity(String idProcesso, boolean isTask);

	ServiceResponse setVariabiliDiProcesso(String idDefinizioneProcesso, String idIstanzaProcesso, 
		List<VariabileProcesso> variabiliDiProcesso);
	
	Map<String, VariabileProcesso> getVariabiliDiProcesso(String idIstanzaProcesso);
	Map<String, VariabileProcesso> getVariabiliDiProcesso(String idIstanzaProcesso, List<String> nomiVariabili);

	String getIdIstanzaProcesso(String idIstanzaAttivita);
	
	void copiaVarIstanzaDaVarProcesso(Map<String, VariabileProcesso> mapVars, 
			List<ActivityInstance> tasks, String nomeVarIstanza, String nomeVarProcesso);
}