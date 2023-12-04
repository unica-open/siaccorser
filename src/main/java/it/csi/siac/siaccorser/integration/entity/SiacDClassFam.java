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
@Table(name = "siac_d_class_fam")
public class SiacDClassFam extends SiacTEnteBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7657332427604982197L;

	@Id
	@Column(name = "classif_fam_id")
	@SequenceGenerator(name = "SEQ_D_CLASS_FAM", allocationSize=1, sequenceName = "siac_d_class_fam_classif_fam_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_D_CLASS_FAM")
	private Integer uid;
	
	@Basic
	@Column(name = "classif_fam_code")
	private String codice;
	
	@Basic
	@Column(name = "classif_fam_desc")
	private String descrizione;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid= uid;
	}

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
	
	

}
