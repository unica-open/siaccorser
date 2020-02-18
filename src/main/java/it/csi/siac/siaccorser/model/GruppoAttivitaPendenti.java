/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 * Descrittore del gruppo di azioni che raccoglie le azioni secondo aree
 * funzionali
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class GruppoAttivitaPendenti implements Serializable {

	private static final long serialVersionUID = -8057212098513063049L;

	private Azione azione;
	private int totali;
	private int urgenti;
	private List<AttivitaPendente> attivitaPendenti = new ArrayList<AttivitaPendente>();
	/**
	 * @return the azione
	 */
	public Azione getAzione() {
		return azione;
	}
	/**
	 * @param azione the azione to set
	 */
	public void setAzione(Azione azione) {
		this.azione = azione;
	}
	/**
	 * @return the totali
	 */
	public int getTotali() {
		return totali;
	}
	/**
	 * @param totali the totali to set
	 */
	public void setTotali(int totali) {
		this.totali = totali;
	}
	/**
	 * @return the urgenti
	 */
	public int getUrgenti() {
		return urgenti;
	}
	/**
	 * @param urgenti the urgenti to set
	 */
	public void setUrgenti(int urgenti) {
		this.urgenti = urgenti;
	}
	/**
	 * @return the attivitaPendenti
	 */
	public List<AttivitaPendente> getAttivitaPendenti() {
		return attivitaPendenti;
	}
	/**
	 * @param attivitaPendenti the attivitaPendenti to set
	 */
	public void setAttivitaPendenti(List<AttivitaPendente> attivitaPendenti) {
		this.attivitaPendenti = attivitaPendenti;
	}

	
}
