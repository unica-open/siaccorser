/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Descrittore di una notifica generata dall'esecuzione di un processo.
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class NotificaPendente implements Serializable {

	private static final long serialVersionUID = 6899374679594865962L;

	private String id;
	private String descrizione;
	private String descrizioneBreve;
	private boolean urgente;
	private String idProcesso;
	private Azione azione;

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizioneBreve() {
		return descrizioneBreve;
	}

	public void setDescrizioneBreve(String descrizioneBreve) {
		this.descrizioneBreve = descrizioneBreve;
	}

	public boolean isUrgente() {
		return urgente;
	}

	public void setUrgente(boolean urgente) {
		this.urgente = urgente;
	}

	/**
	 * @return the idProcesso
	 */
	public String getIdProcesso() {
		return idProcesso;
	}

	/**
	 * @param idProcesso the idProcesso to set
	 */
	public void setIdProcesso(String idProcesso) {
		this.idProcesso = idProcesso;
	}

	public Azione getAzione() {
		return azione;
	}

	public void setAzione(Azione azione) {
		this.azione = azione;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
