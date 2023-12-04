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
@Table(name = "siac_r_account_cassa_econ")
public class SiacRAccountCassaEcon extends SiacTEnteBase {

	private static final long serialVersionUID = -7601333858192973039L;

	@Id
	@Column(name = "account_cassaecon_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_R_ACCOUNT_CASSA_ECON")
	@SequenceGenerator(name = "SEQ_R_ACCOUNT_CASSA_ECON", allocationSize = 1, sequenceName = "siac_r_account_cassa_econ_account_cassaecon_id_seq")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private SiacTAccount account;

	@ManyToOne
	@JoinColumn(name = "cassaecon_id")
	private SiacTCassaEcon cassaEconomale;

	
	
	
	

	public SiacTCassaEcon getCassaEconomale() {
		return cassaEconomale;
	}

	public void setCassaEconomale(SiacTCassaEcon cassaEconomale) {
		this.cassaEconomale = cassaEconomale;
	}

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
	 * @return the account
	 */
	public SiacTAccount getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(SiacTAccount account) {
		this.account = account;
	}
	/**
	 * @return the ruolo
	 */

}
