/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.file;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.CORDataDictionary;
import it.csi.siac.siaccorser.model.Codifica;

/**
 * 
 * @author AR
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class StatoFile extends Codifica {
	private static final long serialVersionUID = -5926685852724272116L;

	@XmlType(namespace = CORDataDictionary.NAMESPACE)
	public enum CodiceStatoFile {
		CARICATO, DA_ELABORARE, IN_ELABORAZIONE, ELABORATO, ANNULLATO, ELABORATO_CON_ERRORI
	}
	
	private CodiceStatoFile codice;

	public StatoFile(CodiceStatoFile codice) {
		this();
		this.codice = codice;
	}

	private StatoFile() {
		super();
	}

	public StatoFile(String codice) {
		this(CodiceStatoFile.valueOf(codice));
	}

	public CodiceStatoFile getCodiceEnum() {
		return codice;
	}
	
	@Override
	public String getCodice() {
		return codice.name();
	}

	@Override
	public void setCodice(String codice) {
		this.codice = CodiceStatoFile.valueOf(codice);
	}
}