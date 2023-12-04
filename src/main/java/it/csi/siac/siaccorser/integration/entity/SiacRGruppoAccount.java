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


/**
 * The persistent class for the siac_r_gruppo_account database table.
 * 
 */
@Entity
@Table(name="siac_r_gruppo_account")
public class SiacRGruppoAccount extends SiacTEnteBase {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1613531088248683451L;

	@Id
	@SequenceGenerator(name="SIAC_R_GRUPPO_ACCOUNT_GRUPPOACCOUNT", allocationSize=1,sequenceName="SIAC_R_GRUPPO_ACCOUNT_GRUPPO_ACCOUNT_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_GRUPPO_ACCOUNT_GRUPPOACCOUNT")
	@Column(name="gruppo_account_id")
	private Integer uid;

	
	//bi-directional many-to-one association to SiacTAccount
	@ManyToOne
	@JoinColumn(name="account_id")
	private SiacTAccount siacTAccount;

	//bi-directional many-to-one association to SiacTGruppo
	@ManyToOne
	@JoinColumn(name="gruppo_id")
	private SiacTGruppo siacTGruppo;

	public SiacTAccount getSiacTAccount() {
		return this.siacTAccount;
	}

	public void setSiacTAccount(SiacTAccount siacTAccount) {
		this.siacTAccount = siacTAccount;
	}

	public SiacTGruppo getSiacTGruppo() {
		return this.siacTGruppo;
	}

	public void setSiacTGruppo(SiacTGruppo siacTGruppo) {
		this.siacTGruppo = siacTGruppo;
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