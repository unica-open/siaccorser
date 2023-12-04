/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "siac_d_gruppo_azioni")
public class SiacDGruppoAzioni extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4657528119590074272L;

	@Id
	@Column(name = "gruppo_azioni_id")
	@SequenceGenerator(name = "SEQ_T_GRUPPO_AZIONI", allocationSize=1, sequenceName = "siac_d_gruppo_azioni_gruppo_azioni_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_GRUPPO_AZIONI")
	private Integer uid;
	
	@Column(name = "gruppo_azioni_code")
	private String nome;
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "gruppo_azioni_desc")
	private String descrizione;
	
	@Transient
	private List<SiacTAzione> azioni = new ArrayList<SiacTAzione>();
	/**
	 * @return the uid
	 */
	@Override
	public Integer getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}
	/**
	 * @param titolo the titolo to set
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * @return the azioni
	 */
	public List<SiacTAzione> getAzioni() {
		return azioni;
	}
	/**
	 * @param azioni the azioni to set
	 */
	public void setAzioni(List<SiacTAzione> azioni) {
		this.azioni = azioni;
	}
}
