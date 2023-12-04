/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_d_gestione_livello")
public class SiacDGestioneLivello extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7687691191677653454L;

	@Id
	@Column(name = "gestione_livello_id")
	@SequenceGenerator(name = "SEQ_D_GESTIONE_LIVELLO", allocationSize=1, sequenceName = "siac_d_gestione_livello_gestione_livello_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_D_GESTIONE_LIVELLO")
	private Integer uid;

	@Column(name = "gestione_livello_code")
	private String codice;

	@Column(name = "gestione_livello_desc")
	private String descrizione;

	@ManyToOne
	@JoinColumn(name = "gestione_tipo_id")
	private SiacDGestioneTipo gestioneTipo;
	
	@OneToMany
	@JoinColumn(name = "gestione_livello_id")
	private List<SiacRGestioneEnte> gestioneEnte;
	

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

	public SiacDGestioneTipo getGestioneTipo() {
		return gestioneTipo;
	}

	public void setGestioneTipo(SiacDGestioneTipo gestioneTipo) {
		this.gestioneTipo = gestioneTipo;
	}

	public List<SiacRGestioneEnte> getGestioneEnte() {
		return gestioneEnte;
	}

	public void setGestioneEnte(List<SiacRGestioneEnte> gestioneEnte) {
		this.gestioneEnte = gestioneEnte;
	}
	
	

}
