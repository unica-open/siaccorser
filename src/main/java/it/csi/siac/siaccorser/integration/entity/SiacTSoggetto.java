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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_soggetto")
public class SiacTSoggetto extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4843941377769646022L;
	
	@Id
	@Column(name = "soggetto_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_SOGGETTO")
	@SequenceGenerator(name = "SEQ_T_SOGGETTO", allocationSize=1, sequenceName = "siac_t_soggetto_soggetto_id_seq")
	private Integer uid;
	
	@Column(name = "codice_fiscale")
	private String codiceFiscale;
	
	@Column(name = "soggetto_desc")
	private String nome;
    
	@OneToMany(mappedBy = "soggetto")
	private List<SiacRSoggettoRuolo> ruoli;
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
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	/**
	 * @return the ruoli
	 */
	public List<SiacRSoggettoRuolo> getRuoli() {
		return ruoli;
	}
	/**
	 * @param ruoli the ruoli to set
	 */
	public void setRuoli(List<SiacRSoggettoRuolo> ruoli) {
		this.ruoli = ruoli;
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
	
	

}
