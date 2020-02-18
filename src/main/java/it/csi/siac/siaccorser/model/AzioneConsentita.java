/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Azione che l'Operatore può eseguire in funzione dei Ruoli o dei Gruppi a lui
 * associati.
 * 
 * @author alagna, AR
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class AzioneConsentita implements Serializable {

	private static final long serialVersionUID = 5723441380660830279L;

	private Azione azione;

	public Azione getAzione() {
		return azione;
	}

	public void setAzione(Azione azione) {
		this.azione = azione;
	}

}
