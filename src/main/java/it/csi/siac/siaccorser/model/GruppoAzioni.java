/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 * Descrittore del gruppo di azioni che raccoglie le azioni secondo aree
 * funzionali
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class GruppoAzioni extends Entita {

	private static final long serialVersionUID = 574496165908360872L;
	private String nome;
	private String titolo;
	private String descrizione;
	private List<Azione> azioni = new ArrayList<Azione>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * @param titolo
	 *            the titolo to set
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Azione> getAzioni() {
		return azioni;
	}

	public void setAzioni(List<Azione> azioni) {
		this.azioni = azioni;
	}

	@Override
	public List<Errore> valida() {
		return null;
	}

}
