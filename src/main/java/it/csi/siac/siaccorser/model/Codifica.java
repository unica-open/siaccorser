/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * Base di tutte le codifiche
 * 
 * @author rmontuori
 * @version @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public abstract class Codifica extends Entita {

	private static final long serialVersionUID = 8258774710011260031L;

	private String codice;
	private String descrizione;
	private Integer annoCatalogazione;

	public Codifica() {
		super();
	}

	public Codifica(String codice, String descrizione) {
		this();
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public Codifica(Codifica c) {
		this(c.codice, c.descrizione);
		this.annoCatalogazione = c.annoCatalogazione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getAnnoCatalogazione() {
		return annoCatalogazione;
	}

	public void setAnnoCatalogazione(Integer annoCatalogazione) {
		this.annoCatalogazione = annoCatalogazione;
	}

}
