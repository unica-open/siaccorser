/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;



@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class Errore extends Entita {

	private static final long serialVersionUID = 5876476288257073816L;

	private String codice;
	private String descrizione;

	public Errore(){
		super();
	}
	
	public Errore(String codice, String descrizione) {
		this();
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public String getTesto() {
		return String.format("%s - %s", getCodice(), getDescrizione());
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
	
	
}
