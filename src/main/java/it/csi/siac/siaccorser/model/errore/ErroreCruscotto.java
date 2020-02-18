/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.errore;

import java.text.MessageFormat;

import it.csi.siac.siaccorser.model.Errore;

public enum ErroreCruscotto implements TipoErrore {

	UTENTE_NON_LOGGATO("ERR_CRU_0001","Utente non loggato"),
	IDENTITA_DIGITALE_NON_CONFORME("ERR_CRU_0002","Identità digitale non conforme"),
	UTENTE_NON_ABILITATO("ERR_CRU_0003","Utente non abilitato all'accesso");
	
    private final String codice;
    private final String messaggio;
	
    ErroreCruscotto(String codice, String messaggio) {
		this.codice = codice;
		this.messaggio = messaggio;
	}
    
    public String getCodice() {
    	return codice;
    }

	@Override
	public Errore getErrore(Object ... args) {
		
		return new Errore(codice, MessageFormat.format(messaggio, args));
	}

}
