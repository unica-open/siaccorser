/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

import java.util.ArrayList;
import java.util.List;

import it.csi.siac.siaccorser.model.ServiceRequest;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;
import it.csi.siac.siaccorser.model.VariabileProcesso;

public class AvviaProcesso extends ServiceRequest{
	
	private String nomeProcesso;
	private String nomeTask;
	private List<VariabileProcesso> variabiliProcesso = 
		new ArrayList<VariabileProcesso>();
	/** SAC abilitate al processo */
	private List<StrutturaAmministrativoContabile> strutture = 
		new ArrayList<StrutturaAmministrativoContabile>();
	
	public String getNomeProcesso() {
		return nomeProcesso;
	}

	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}

	public String getNomeTask() {
		return nomeTask;
	}

	public void setNomeTask(String nomeTask) {
		this.nomeTask = nomeTask;
	}

	public List<VariabileProcesso> getVariabiliProcesso() {
		return variabiliProcesso;
	}

	public void setVariabiliProcesso(List<VariabileProcesso> variabiliProcesso) {
		this.variabiliProcesso = variabiliProcesso;
	}

	public List<StrutturaAmministrativoContabile> getStrutture() {
		return strutture;
	}

	public void setStrutture(List<StrutturaAmministrativoContabile> strutture) {
		this.strutture = strutture;
	}
	
	
}
