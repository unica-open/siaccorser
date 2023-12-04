/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

import it.csi.siac.siaccorser.model.ServiceResponse;

public class AvviaProcessoResponse extends ServiceResponse {
	private String idProcesso;
	private String idDefinizioneProcesso;
	private String idTask;
	private String nomeTask;

	public String getIdTask() {
		return idTask;
	}

	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	public String getNomeTask() {
		return nomeTask;
	}

	public void setNomeTask(String nomeTask) {
		this.nomeTask = nomeTask;
	}

	public String getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(String idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getIdDefinizioneProcesso() {
		return idDefinizioneProcesso;
	}

	public void setIdDefinizioneProcesso(String idDefinizioneProcesso) {
		this.idDefinizioneProcesso = idDefinizioneProcesso;
	}

}