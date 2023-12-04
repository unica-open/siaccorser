/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Base di tutte le codifiche
 * 
 * @author rmontuori
 * @version @version $Id: $
 */

@Entity
@Table(name = "siac_t_class")
@SecondaryTables({
		@SecondaryTable(name = "siac_r_class_fam_tree", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "classif_id") }),
		@SecondaryTable(name = "siac_r_class_attr", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "classif_id") }) })
public class SiacTClass extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8611771229561709929L;

	@Id
	@Column(name = "classif_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_CLASS")
	@SequenceGenerator(name = "SEQ_T_CLASS", allocationSize=1, sequenceName = "siac_t_class_classif_id_seq")
	private Integer uid;

	@Basic
	@Column(name = "classif_code")
	private String codice;

	@Basic
	@Column(name = "classif_desc")
	private String descrizione;

	@ManyToOne
	@JoinColumn(name = "classif_tipo_id")
	private SiacDClassTipo tipoClassificatore;

	// mappa la relazione con r_class
	@OneToMany(mappedBy = "codificaFiglia")
	private Set<SiacRClass> codificheFiglie;

	@ManyToOne
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", table = "siac_r_class_attr", insertable = false, updatable = false)
	private SiacTAttr codificaAttributo;

	@ManyToOne
	@JoinColumn(name = "classif_fam_tree_id", referencedColumnName = "classif_fam_tree_id", table = "siac_r_class_fam_tree", insertable = false, updatable = false)
	private SiacTClassFamTree codificaFamiglia;

	@ManyToOne
	@JoinColumn(name = "classif_id_padre", referencedColumnName = "classif_id", table = "siac_r_class_fam_tree", insertable = false, updatable = false)
	private SiacTClass padre;

	@OneToMany(mappedBy = "padre")
	@OrderColumn
	private Set<SiacTClass> figli;
	
	
	//----------------------------------------------------------------------
	
	@OneToMany(mappedBy = "codifica2")
	private Set<SiacRClassFamTree> dtFineValRClassFamTree;
	
	public Set<SiacRClassFamTree> getDtFineValRClassFamTree() {
		return dtFineValRClassFamTree;
	}

	public void setDtFineValRClassFamTree(
			Set<SiacRClassFamTree> dtFineValRClassFamTree) {
		this.dtFineValRClassFamTree = dtFineValRClassFamTree;
	}
	
	//----------------------------------------------------------------------

	public SiacTAttr getCodificaAttributo() {
		return codificaAttributo;
	}

	public void setCodificaAttributo(SiacTAttr codificaAttributo) {
		this.codificaAttributo = codificaAttributo;
	}

	public SiacTClassFamTree getCodificaFamiglia() {
		return codificaFamiglia;
	}

	public void setCodificaFamiglia(SiacTClassFamTree codificaFamiglia) {
		this.codificaFamiglia = codificaFamiglia;
	}

	public SiacTClass() {
		// Empty constructor
	}

	public SiacTClass(String codice, String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
	}

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public SiacDClassTipo getTipoClassificatore() {
		return tipoClassificatore;
	}

	public void setTipoClassificatore(SiacDClassTipo tipoClassificatore) {
		this.tipoClassificatore = tipoClassificatore;
	}

	public SiacTClass getPadre() {
		return padre;
	}

	public void setPadre(SiacTClass padre) {
		this.padre = padre;
	}

	public Set<SiacTClass> getFigli() {
		return figli;
	}

	public void setFigli(Set<SiacTClass> figli) {
		this.figli = figli;
	}

	public void setCodificheFiglie(Set<SiacRClass> codificheFiglie) {
		this.codificheFiglie = codificheFiglie;
	}

	public Set<SiacRClass> getCodificheFiglie() {
		return codificheFiglie;
	}

}
