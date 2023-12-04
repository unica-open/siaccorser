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
@Table(name = "siac_r_gruppo_ruolo_op")
public class SiacRGruppoRuoloOp extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -480452072249279112L;

	@Id
	@Column(name = "gruppo_ruolo_op_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_RUOLO_GRUPPO")
	@SequenceGenerator(name = "SEQ_T_RUOLO_GRUPPO", allocationSize=1, sequenceName = "siac_r_gruppo_ruolo_op_gruppo_ruolo_op_id_seq")
	private Integer uid;
   
	@ManyToOne
    @JoinColumn(name = "gruppo_id")
	private SiacTGruppo gruppo;
    
	@ManyToOne
    @JoinColumn(name = "ruolo_operativo_id")
	private SiacDRuoloOp ruolo;
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
	 * @return the gruppo
	 */
	public SiacTGruppo getGruppo() {
		return gruppo;
	}
	/**
	 * @param gruppo the gruppo to set
	 */
	public void setGruppo(SiacTGruppo gruppo) {
		this.gruppo = gruppo;
	}
	/**
	 * @return the ruolo
	 */
	public SiacDRuoloOp getRuolo() {
		return ruolo;
	}
	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(SiacDRuoloOp ruolo) {
		this.ruolo = ruolo;
	}
	
}
