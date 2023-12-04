/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_attr")
public class SiacTAttr extends SiacTEnteBase {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6958200034899565703L;

	@Id
	@Column(name = "attr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_ATTR")
	@SequenceGenerator(name = "SEQ_T_ATTR", allocationSize=1, sequenceName = "siac_t_attr_attr_id_seq")
	private Integer uuid;

	@Basic
	@Column(name = "attr_code")
	private String codice;

	@Basic
	@Column(name = "attr_desc")
	private String descrizione;

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public Integer getUid() {
		return uuid;
	}

	@Override
	public void setUid(Integer uuid) {
		this.uuid = uuid;
	}

}
