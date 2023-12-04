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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_r_soggetto_ruolo")
public class SiacRSoggettoRuolo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2558028296960994555L;
	
	@Id
	@Column(name = "soggeto_ruolo_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_RUOLO_SOGGETTO_SOGGETTO")
	@SequenceGenerator(name = "SEQ_T_RUOLO_SOGGETTO_SOGGETTO", allocationSize=1, sequenceName = "siac_r_soggetto_ruolo_soggeto_ruolo_id_seq")
	private Integer uid;
    @ManyToOne
    @JoinColumn(name = "soggetto_id")
    private SiacTSoggetto soggetto;

    @ManyToOne
    @JoinColumn(name = "ruolo_id")
    private SiacDRuolo ruolo;
    
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
	 * @return the soggetto
	 */
	public SiacTSoggetto getSoggetto() {
		return soggetto;
	}
	/**
	 * @param soggetto the soggetto to set
	 */
	public void setSoggetto(SiacTSoggetto soggetto) {
		this.soggetto = soggetto;
	}
	/**
	 * @return the ruolo
	 */
	public SiacDRuolo getRuolo() {
		return ruolo;
	}
	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(SiacDRuolo ruolo) {
		this.ruolo = ruolo;
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
