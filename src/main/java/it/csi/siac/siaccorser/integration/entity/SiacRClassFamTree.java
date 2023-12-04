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
@Table(name = "siac_r_class_fam_tree")
public class SiacRClassFamTree extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2198716033622351416L;

	@Id
	@Column(name = "classif_classif_fam_tree_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASS_FAM_TREE")
	@SequenceGenerator(name = "SEQ_CLASS_FAM_TREE", allocationSize=1, sequenceName = "siac_r_class_fam_tree_classif_classif_fam_tree_id_seq")
	private Integer uid;

	@Basic
	@Column(name = "livello")
	private int livello;

	@Basic
	@Column(name = "ordine")
	private String ordine;

	@ManyToOne
	@JoinColumn(name = "classif_id", insertable = false, updatable = false)
	private SiacTClass codifica2;
	
	@ManyToOne
	@JoinColumn(name="classif_id_padre")
	private SiacTClass siacTClassPadre;
	
		
	public SiacTClass getCodifica2() {
		return codifica2;
	}

	public void setCodifica2(SiacTClass codifica2) {
		this.codifica2 = codifica2;
	}

	public String getOrdine() {
		return ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public int getLivello() {
		return livello;
	}

	public void setLivello(int livello) {
		this.livello = livello;
	}
	
	public SiacTClass getSiacTClassPadre() {
		return siacTClassPadre;
	}

	public void setSiacTClassPadre(SiacTClass siacTClassPadre) {
		this.siacTClassPadre = siacTClassPadre;
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
