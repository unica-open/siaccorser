/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/***
 * Tipo delle variabili di processo.
 * 
 * @author Spin Servizi per l'Innovazione
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public enum TipoVariabileProcesso {
	STRINGA("Str"),INTERO("Int"),DECIMALE("Dec"),DATA("Dat"),BOOLEANO("Boo"),ENTITA("Ent");
	
	private String valore;
	
	TipoVariabileProcesso(String valore) {
		this.valore = valore;
	}

	/**
	 * @return the valore
	 */
	public String getValore() {
		return valore;
	}
	
}
