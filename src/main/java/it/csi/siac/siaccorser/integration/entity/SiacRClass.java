/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_r_class")
public class SiacRClass extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5329767632230091512L;

	@Id
	@Column(name = "classif_classif_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_R_CLASS")
	@SequenceGenerator(name = "SEQ_R_CLASS", allocationSize=1,sequenceName = "siac_r_class_classif_classif_id_seq")
	private Integer uid;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@ManyToOne
	@JoinColumn(name = "classif_a_id") 
	private SiacTClass codifica;

	@ManyToOne
	@JoinColumn(name = "classif_b_id") 
	private SiacTClass codificaFiglia;

	public SiacTClass getCodifica() {
		return codifica;
	}

	public void setCodifica(SiacTClass codifica) {
		this.codifica = codifica;
	}

	public SiacTClass getCodificaFiglia() {
		return codificaFiglia;
	}

	public void setCodificaFiglia(SiacTClass codificaFiglia) {
		this.codificaFiglia = codificaFiglia;
	}

}
