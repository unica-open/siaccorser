/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.VariabileProcesso;

/**
 * Messaggio di risposta alla richiesta di esecuzione di una azione richiesta
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class ExecAzioneRichiestaResponse extends ServiceResponse implements
		Serializable {

	private static final long serialVersionUID = -8820854398880211446L;

	private String idTask;
	private String nomeTask;

	private List<VariabileProcesso> variabiliDiProcesso = new ArrayList<VariabileProcesso>();

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

	public List<VariabileProcesso> getVariabiliDiProcesso() {
		return variabiliDiProcesso;
	}

	public void setVariabiliDiProcesso(
			List<VariabileProcesso> variabiliDiProcesso) {
		this.variabiliDiProcesso = variabiliDiProcesso;
	}
	
	
	/**
	 * Imposta a null i valori delle variabili di processo che impedirebbero il marshalling dell'oggetto AzioneRichiesta. 
	 */
	public void cleanUnmarshallableVariabileProcessoValue(){
		if(variabiliDiProcesso!=null) {
			for (VariabileProcesso variabileProcesso : variabiliDiProcesso) {
				if(variabileProcesso.getValore() instanceof AbstractMap){
					variabileProcesso.setValore(null);					
				}
			}
		}
	}
	
	/**
	 * Ricerca tra le variabili di processo quella avente il nome specificato come parametro. 
	 *  
	 * @param nomeVariabile
	 * @return null se la variabile non viene trovata
	 */
	public VariabileProcesso findVariabileProcessoByName(String nomeVariabile) {
		if(variabiliDiProcesso!=null) {
			for (VariabileProcesso variabileProcesso : variabiliDiProcesso) {
				if(nomeVariabile.equals(variabileProcesso.getNome())){					
					return variabileProcesso;					
				}
			}
		}
		return null;
	}

}
