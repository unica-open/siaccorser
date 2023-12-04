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
@Table(name = "siac_r_account_ruolo_op")
public class SiacRAccountRuoloOp extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8502045165161261192L;

	@Id
	@Column(name = "account_ruolo_op_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_ACCOUNT")
	@SequenceGenerator(name = "SEQ_T_ACCOUNT", allocationSize=1, sequenceName = "siac_r_account_ruolo_op_account_ruolo_op_id_seq")
	private Integer uid;
    
	@ManyToOne
    @JoinColumn(name = "account_id")
	private SiacTAccount account;
    
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
	 * @return the account
	 */
	public SiacTAccount getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(SiacTAccount account) {
		this.account = account;
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
