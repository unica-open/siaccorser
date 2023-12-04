/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

import java.util.ArrayList;
import java.util.List;

import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.VariabileProcesso;

/**
 * Risposta del moetodo getTaskInfo
 * 
 * @author alagna
 * @version $Id: $
 */
public class GetTaskInfoResponse extends ServiceResponse {
	
	List<VariabileProcesso> variabiliProcesso = new ArrayList<VariabileProcesso>();

	/**
	 * @return the variabiliProcesso
	 */
	public List<VariabileProcesso> getVariabiliProcesso() {
		return variabiliProcesso;
	}

	/**
	 * @param variabiliProcesso the variabiliProcesso to set
	 */
	public void setVariabiliProcesso(List<VariabileProcesso> variabiliProcesso) {
		this.variabiliProcesso = variabiliProcesso;
	}
}
