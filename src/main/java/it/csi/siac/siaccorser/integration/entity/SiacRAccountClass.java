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
@Table(name = "siac_r_account_class")
public class SiacRAccountClass extends SiacTEnteBase {

	private static final long serialVersionUID = -7601333858192973039L;

	@Id
	@Column(name = "account_class_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_R_ACCOUNT_CLASS")
	@SequenceGenerator(name = "SEQ_R_ACCOUNT_CLASS", allocationSize = 1, sequenceName = "siac_r_account_class_account_class_id_seq")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private SiacTAccount account;

	@ManyToOne
	@JoinColumn(name = "classif_id")
	private SiacTClass strutturaAmministrativoContabile;

	

	

	public SiacTClass getStrutturaAmministrativoContabile() {
		return strutturaAmministrativoContabile;
	}

	public void setStrutturaAmministrativoContabile(SiacTClass strutturaAmministrativoContabile) {
		this.strutturaAmministrativoContabile = strutturaAmministrativoContabile;
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
