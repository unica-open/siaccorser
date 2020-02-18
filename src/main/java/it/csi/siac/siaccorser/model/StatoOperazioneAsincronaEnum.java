/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * StatoOperazioneAsincronaEnum
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public enum StatoOperazioneAsincronaEnum {

	STATO_OPASINC_AVVIATA("AVVIATA"), 
	STATO_OPASINC_CONCLUSA("CONCLUSA"),
	STATO_OPASINC_ERRORE("ERRORE"),
	;

	private String codice;

	StatoOperazioneAsincronaEnum(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}

	public static StatoOperazioneAsincronaEnum byCodice(String codice) {
		for (StatoOperazioneAsincronaEnum e : StatoOperazioneAsincronaEnum
				.values()) {
			if (e.getCodice().equals(codice)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Il codice " + codice
				+ " non ha un mapping corrispondente in StatoOperazioneAsincronaEnum");
	}

}
