/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.messaggio;

import java.text.MessageFormat;

import it.csi.siac.siaccorser.model.Messaggio;

public enum MessaggioCore implements TipoMessaggio {

	// TODO: Messaggio di esempio
	MESSAGGIO_DI_SISTEMA("WAR_COR_0001", "Messaggio di sistema: {0}");

	private final String codice;
	private final String messaggio;

	MessaggioCore(String codice, String messaggio) {
		this.codice = codice;
		this.messaggio = messaggio;
	}

	@Override
	public String getCodice() {
		return codice;
	}

	@Override
	public Messaggio getMessaggio(Object... args) {

		Messaggio errore = new Messaggio(codice, MessageFormat.format(
				messaggio, args));
		return errore;
	}

}
