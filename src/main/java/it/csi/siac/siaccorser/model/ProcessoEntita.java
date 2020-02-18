/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 * Descrittore del processo associato ad una entita.
 */ 
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class ProcessoEntita implements Serializable {

	private static final long serialVersionUID = 3723004267819428737L;

	private String nome;
	private String id;
	private List<TaskEntita> tasks = new ArrayList<TaskEntita>();
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the tasks
	 */
	public List<TaskEntita> getTasks() {
		return tasks;
	}
	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<TaskEntita> tasks) {
		this.tasks = tasks;
	} 
	
	/**
	 * restituisce la lista delle azioneRichiesta relativa ad una azioneConsentita 
	 * nel caso in cui il task di processo corrisponda al task indicato 
	 * nell'azione consentita 
	 * 
	 * @param azioneConsentita
	 * @return
	 */
	public List<AzioneRichiesta> getAzioniRichieste(AzioneConsentita azioneConsentita){
		List<AzioneRichiesta> azioniRichieste = new ArrayList<AzioneRichiesta>();
		AzioneRichiesta azioneRichiesta = null;
		Azione azione = azioneConsentita.getAzione();
		if (azione!=null && azione.getNomeProcesso()!=null && azione.getNomeProcesso().equals(nome)) {
			for (TaskEntita task:tasks) {
				if(azione.getNomeTask()!=null && azione.getNomeTask().equals(task.getNome())) {
					azioneRichiesta = new AzioneRichiesta();
					azioneRichiesta.setAzione(azione);
					/**
					 * TODO: allineare idAttivita e idProcesso
					 */
					azioneRichiesta.setIdAttivita(getId());
					azioneRichiesta.setDaCruscotto(false);
					azioneRichiesta.setData(new Date());
					azioniRichieste.add(azioneRichiesta);
				}
			}	
		}
		return azioniRichieste;
	}
}
