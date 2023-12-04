/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import it.csi.siac.siaccommonser.integration.entity.SiacTBase;

/**
 * Entita comune per i dto di corserverimpl
 * 
 * @author rmontuori, AR
 * 
 */
@MappedSuperclass
public abstract class SiacTEnteBase extends SiacTBase {

	private static final long serialVersionUID = 3332357108836128658L;

	@ManyToOne
	@JoinColumn(name = "ente_proprietario_id")
	private SiacTEnteProprietario ente;


	public SiacTEnteProprietario getEnte() {
		return ente;
	}

	public void setEnte(SiacTEnteProprietario ente) {
		this.ente = ente;
	}

}
