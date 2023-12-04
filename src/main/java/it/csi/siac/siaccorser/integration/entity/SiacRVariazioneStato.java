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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the siac_r_variazione_stato database table.
 * 
 */
@Entity
@Table(name="siac_r_variazione_stato")
@NamedQuery(name="SiacRVariazioneStato.findAll", query="SELECT s FROM SiacRVariazioneStato s")
public class SiacRVariazioneStato extends SiacTEnteBase {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The variazione stato id. */
	@Id
	@SequenceGenerator(name="SIAC_R_VARIAZIONE_STATO_VARIAZIONESTATOID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_VARIAZIONE_STATO_VARIAZIONE_STATO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_VARIAZIONE_STATO_VARIAZIONESTATOID_GENERATOR")
	@Column(name="variazione_stato_id")
	private Integer variazioneStatoId;

	//bi-directional many-to-one association to SiacDVariazioneStato
	/** The siac d variazione stato. */
	@ManyToOne
	@JoinColumn(name="variazione_stato_tipo_id")
	private SiacDVariazioneStato siacDVariazioneStato;

	
	//bi-directional many-to-one association to SiacTVariazione
	/** The siac t variazione. */
	@ManyToOne
	@JoinColumn(name="variazione_id")
	private SiacTVariazione siacTVariazione;

	

	/**
	 * Instantiates a new siac r variazione stato.
	 */
	public SiacRVariazioneStato() {
	}
	
	
	

	/**
	 * Gets the variazione stato id.
	 *
	 * @return the variazione stato id
	 */
	public Integer getVariazioneStatoId() {
		return this.variazioneStatoId;
	}

	/**
	 * Sets the variazione stato id.
	 *
	 * @param variazioneStatoId the new variazione stato id
	 */
	public void setVariazioneStatoId(Integer variazioneStatoId) {
		this.variazioneStatoId = variazioneStatoId;
	}



	/**
	 * Gets the siac d variazione stato.
	 *
	 * @return the siac d variazione stato
	 */
	public SiacDVariazioneStato getSiacDVariazioneStato() {
		return this.siacDVariazioneStato;
	}

	/**
	 * Sets the siac d variazione stato.
	 *
	 * @param siacDVariazioneStato the new siac d variazione stato
	 */
	public void setSiacDVariazioneStato(SiacDVariazioneStato siacDVariazioneStato) {
		this.siacDVariazioneStato = siacDVariazioneStato;
	}

	




	/**
	 * Gets the siac t variazione.
	 *
	 * @return the siac t variazione
	 */
	public SiacTVariazione getSiacTVariazione() {
		return this.siacTVariazione;
	}

	/**
	 * Sets the siac t variazione.
	 *
	 * @param siacTVariazione the new siac t variazione
	 */
	public void setSiacTVariazione(SiacTVariazione siacTVariazione) {
		this.siacTVariazione = siacTVariazione;
	}

	

	

	/* (non-Javadoc)
	 * @see it.csi.siac.siaccommonser.integration.entity.SiacTBase#getUid()
	 */
	@Override
	public Integer getUid() {
		return variazioneStatoId;
	}

	/* (non-Javadoc)
	 * @see it.csi.siac.siaccommonser.integration.entity.SiacTBase#setUid(java.lang.Integer)
	 */
	@Override
	public void setUid(Integer uid) {
		this.variazioneStatoId = uid;
		
	}

}