/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * Bilancio
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class Bilancio extends Entita {

	private static final long serialVersionUID = -4890171962466873894L;

	private int anno;
	
	private FaseEStatoAttualeBilancio faseEStatoAttualeBilancio;

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public FaseEStatoAttualeBilancio getFaseEStatoAttualeBilancio() {
		return faseEStatoAttualeBilancio;
	}

	public void setFaseEStatoAttualeBilancio(FaseEStatoAttualeBilancio faseEStatoAttualeBilancio) {
		this.faseEStatoAttualeBilancio = faseEStatoAttualeBilancio;
	}
	
	public static Bilancio getInstanceForUid(int uid) {
		Bilancio b = new Bilancio();
		b.setUid(uid);
		return b;
	}

}
