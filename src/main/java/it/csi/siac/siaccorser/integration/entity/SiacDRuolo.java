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
@Table(name = "siac_d_ruolo")
public class SiacDRuolo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8646480774805714348L;

	@Id
	@Column(name = "ruolo_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_RUOLO_SOGGETTO")
	@SequenceGenerator(name = "SEQ_T_RUOLO_SOGGETTO", allocationSize=1, sequenceName = "siac_d_ruolo_ruolo_id_seq")
	private Integer uid;
	
	@Column(name = "ruolo_code")
	private String codice;
	
	@Column(name = "ruolo_desc")
	private String descrizione;
    
	@OneToMany(mappedBy = "ruoloSoggetto")
    private List<SiacTAccount> accounts;
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
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}
	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
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
	 * @return the accounts
	 */
	public List<SiacTAccount> getAccounts() {
		return accounts;
	}
	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<SiacTAccount> accounts) {
		this.accounts = accounts;
	}

}
