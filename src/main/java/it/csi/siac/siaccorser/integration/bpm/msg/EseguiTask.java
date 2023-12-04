/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

import java.util.ArrayList;
import java.util.List;

import it.csi.siac.siaccorser.model.ServiceRequest;
import it.csi.siac.siaccorser.model.VariabileProcesso;

public class EseguiTask extends ServiceRequest{
	private String idTask;
	private List<VariabileProcesso> variabiliProcesso = new ArrayList<VariabileProcesso>();
	private String idIstanzaProcesso;

	public String getIdTask() {
		return idTask;
	}

	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	public List<VariabileProcesso> getVariabiliProcesso() {
		return variabiliProcesso;
	}

	public void setVariabiliProcesso(List<VariabileProcesso> variabiliProcesso) {
		this.variabiliProcesso = variabiliProcesso;
	}

	public String getIdIstanzaProcesso() {
		return idIstanzaProcesso;
	}

	public void setIdIstanzaProcesso(String idIstanzaProcesso) {
		this.idIstanzaProcesso = idIstanzaProcesso;
	}
	
	
}
