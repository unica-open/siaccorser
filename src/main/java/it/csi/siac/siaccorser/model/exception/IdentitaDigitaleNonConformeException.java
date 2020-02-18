/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.exception;

public class IdentitaDigitaleNonConformeException extends RuntimeException {

	private static final long serialVersionUID = -7311067685617513804L;
	
	private final Object identita;

	public IdentitaDigitaleNonConformeException(Object identita) {
		this.identita = identita;
	}

	/**
	 * @return the identita
	 */
	public Object getIdentita() {
		return identita;
	}
	
	

}
