/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity.enumeration;

/**
 * 
 * @author rmontuori
 * 
 */
public enum SiacTOperazioneAsincronaEnum {

	OPASINC_AVVIATA("AVVIATA"), 
	OPASINC_CONCLUSA("CONCLUSA"),
	OPASINC_ERRORE("ERRORE"),
	OPASINC_ESITO_FALLIMENTO("FALLIMENTO"),
	OPASINC_ESITO_SUCCESS("SUCCESSO"),
	OPASINC_MESSAGGIO("L'operazione asincrona e' in stato :"),
	;

	private String codice;

	SiacTOperazioneAsincronaEnum(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}

	public static SiacTOperazioneAsincronaEnum byCodice(String codice) {
		for (SiacTOperazioneAsincronaEnum e : SiacTOperazioneAsincronaEnum
				.values()) {
			if (e.getCodice().equals(codice)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Il codice " + codice
				+ " non ha un mapping corrispondente in SiacTOperazioneAsincronaEnum");
	}

}
