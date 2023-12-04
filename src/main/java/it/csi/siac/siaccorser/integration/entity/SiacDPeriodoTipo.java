/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "siac_d_periodo_tipo")
public class SiacDPeriodoTipo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -114365561667035841L;

	@Id
	@Column(name = "periodo_tipo_id")
	private Integer uid;

	@Column(name = "periodo_tipo_code")
	private String codice;
	
	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
}
