/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

import java.io.Serializable;

import it.csi.siac.siaccorser.model.ServiceResponse;

/**
 * Mappa nella response dell'esegui task l'idTask (che non è numerico ma è una stringa)
 * e il nome del task, utili per conoscere il task successivo 
 * 
 * @author rmontuori
 *
 */
public class EseguiTaskResponse extends ServiceResponse implements Serializable {

	private static final long serialVersionUID = 3785423670852856258L;

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

}
