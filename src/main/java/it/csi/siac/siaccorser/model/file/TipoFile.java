/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.file;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.CORDataDictionary;
import it.csi.siac.siaccorser.model.Codifica;

/**
 * 
 * @author AR
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class TipoFile extends Codifica {
	private static final long serialVersionUID = 84523863103465881L;

	private Azione azioneServizio;

	public TipoFile(Integer uid) {
		this();
		setUid(uid);
	}

	public TipoFile() {
		super();
	}

	public TipoFile(String codice) {
		super(codice, null);
	}

	public Azione getAzioneServizio() {
		return azioneServizio;
	}

	public void setAzioneServizio(Azione azioneServizio) {
		this.azioneServizio = azioneServizio;
	}
}
