/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * 
 * TipoClassificatore, ientifica la tipologia di classificatore
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class TipoClassificatore extends Entita {

	private static final long serialVersionUID = 633878981451501294L;
	
	private String codice;
	private String descrizione;

	public TipoClassificatore(){
		// Costruttore vuoto
	}
	
	public TipoClassificatore(String codice) {
		this(codice, null);
	}

	public TipoClassificatore(String codice, String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
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
