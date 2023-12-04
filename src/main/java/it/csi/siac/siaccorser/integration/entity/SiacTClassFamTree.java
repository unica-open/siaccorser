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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_class_fam_tree")
public class SiacTClassFamTree extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7311036860570032206L;

	@Id
	@Column(name = "classif_fam_tree_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASS_FAM_TREE")
	@SequenceGenerator(name = "SEQ_CLASS_FAM_TREE", allocationSize=1, sequenceName = "siac_t_class_fam_tree_f_classif_fam_tree_id_seq")
	private Integer uid;

	@Basic
	@Column(name = "class_fam_code")
	private String codice;
	
	@Basic
	@Column(name = "class_fam_desc")
	private String descrizione;
	
	@ManyToOne
	@JoinColumn(name="classif_fam_id")
	private SiacDClassFam codiceCodificaFamiglia;
	
	
	public SiacDClassFam getCodiceCodificaFamiglia() {
		return codiceCodificaFamiglia;
	}

	public void setCodiceCodificaFamiglia(SiacDClassFam codiceCodificaFamiglia) {
		this.codiceCodificaFamiglia = codiceCodificaFamiglia;
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

	

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid= uid;
	}

	

}
