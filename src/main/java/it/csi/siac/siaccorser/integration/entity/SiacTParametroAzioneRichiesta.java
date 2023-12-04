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
@Table(name = "siac_t_parametro_azione_richiesta")
public class SiacTParametroAzioneRichiesta extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5753375364215120459L;
	@Id
	@Column(name = "parametro_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_PARAMETRO_AZIONE_RICHIESTA")
	@SequenceGenerator(name = "SEQ_T_PARAMETRO_AZIONE_RICHIESTA", allocationSize=1, sequenceName = "siac_t_parametro_azione_richiesta_parametro_id_seq")
	private Integer uid;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "valore")
	private String valore;
	
	@ManyToOne
	@JoinColumn(name = "azione_richiesta_id")
	private SiacTAzioneRichiesta azioneRichiesta;
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
	 * @return the valore
	 */
	public String getValore() {
		return valore;
	}
	/**
	 * @param valore the valore to set
	 */
	public void setValore(String valore) {
		this.valore = valore;
	}
	/**
	 * @return the azioneRichiesta
	 */
	public SiacTAzioneRichiesta getAzioneRichiesta() {
		return azioneRichiesta;
	}
	/**
	 * @param azioneRichiesta the azioneRichiesta to set
	 */
	public void setAzioneRichiesta(SiacTAzioneRichiesta azioneRichiesta) {
		this.azioneRichiesta = azioneRichiesta;
	}

}
