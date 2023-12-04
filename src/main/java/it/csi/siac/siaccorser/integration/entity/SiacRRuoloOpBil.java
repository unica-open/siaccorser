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
@Table(name = "siac_r_ruolo_op_bil")
public class SiacRRuoloOpBil extends SiacTEnteBase {

	private static final long serialVersionUID = -177265731360984543L;

	@Id
	@Column(name = "ruolo_op_bil_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_RUOLO_BIL")
	@SequenceGenerator(name = "SEQ_T_RUOLO_BIL", allocationSize = 1, sequenceName = "siac_r_ruolo_op_bil_ruolo_op_bil_id_seq")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "ruolo_op_id")
	private SiacDRuoloOp siacDRuoloOp;

	@ManyToOne
	@JoinColumn(name = "bil_id")
	private SiacTBil bilancio;

	/**
	 * @return the uid
	 */
	@Override
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * @return the siacDRuoloOp
	 */
	public SiacDRuoloOp getSiacDRuoloOp() {
		return siacDRuoloOp;
	}

	/**
	 * @param siacDRuoloOp the siacDRuoloOp to set
	 */
	public void setSiacDRuoloOp(SiacDRuoloOp siacDRuoloOp) {
		this.siacDRuoloOp = siacDRuoloOp;
	}

	/**
	 * @return the bilancio
	 */
	public SiacTBil getBilancio() {
		return bilancio;
	}

	/**
	 * @param the bilancio to set
	 */
	public void setBilancio(SiacTBil bilancio) {
		this.bilancio = bilancio;
	}





}
