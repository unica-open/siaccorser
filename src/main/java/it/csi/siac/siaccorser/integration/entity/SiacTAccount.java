/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import java.util.ArrayList;
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
@Table(name = "siac_t_account")
public class SiacTAccount extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6459518223614850473L;

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_ACCOUNT")
	@SequenceGenerator(name = "SEQ_T_ACCOUNT", allocationSize=1, sequenceName = "siac_t_account_account_id_seq")
	private Integer uid;

	@ManyToOne
    @JoinColumn(name = "soggeto_ruolo_id")
	private SiacRSoggettoRuolo ruoloSoggetto;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "account_code")
	private String codice;
	
	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "email")
	private String indirizzoMail;

	@OneToMany(mappedBy = "account")
	private List<SiacRAccountCassaEcon> siacRAccountCassaEcons = new ArrayList<SiacRAccountCassaEcon>();

	@OneToMany(mappedBy = "account")
	private List<SiacRAccountClass> siacRAccountClasses = new ArrayList<SiacRAccountClass>();

	@OneToMany(mappedBy = "siacTAccount")
	private List<SiacRGruppoAccount> siacRGruppoAccounts = new ArrayList<SiacRGruppoAccount>();

	@OneToMany(mappedBy = "account")
	private List<SiacRAccountRuoloOp> ruoli = new ArrayList<SiacRAccountRuoloOp>();

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
	 * @return the ruoloSoggetto
	 */
	public SiacRSoggettoRuolo getRuoloSoggetto() {
		return ruoloSoggetto;
	}

	/**
	 * @param ruoloSoggetto
	 *            the ruoloSoggetto to set
	 */
	public void setRuoloSoggetto(SiacRSoggettoRuolo ruoloSoggetto) {
		this.ruoloSoggetto = ruoloSoggetto;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione
	 *            the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the indirizzoMail
	 */
	public String getIndirizzoMail() {
		return indirizzoMail;
	}

	/**
	 * @param indirizzoMail
	 *            the indirizzoMail to set
	 */
	public void setIndirizzoMail(String indirizzoMail) {
		this.indirizzoMail = indirizzoMail;
	}

	/**
	 * @return the siacRGruppoAccounts
	 */
	public List<SiacRGruppoAccount> getSiacRGruppoAccounts() {
		return siacRGruppoAccounts;
	}

	/**
	 * @param siacRGruppoAccounts
	 *            the siacRGruppoAccounts to set
	 */
	public void setSiacRGruppoAccounts(
			List<SiacRGruppoAccount> siacRGruppoAccounts) {
		this.siacRGruppoAccounts = siacRGruppoAccounts;
	}


	/**
	 * @return the ruoli
	 */
	public List<SiacRAccountRuoloOp> getRuoli() {
		return ruoli;
	}

	/**
	 * @param ruoli
	 *            the ruoli to set
	 */
	public void setRuoli(List<SiacRAccountRuoloOp> ruoli) {
		this.ruoli = ruoli;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}

	public List<SiacRAccountCassaEcon> getSiacRAccountCassaEcons() {
		return siacRAccountCassaEcons;
	}

	public void setSiacRAccountCassaEcons(List<SiacRAccountCassaEcon> siacRAccountCassaEcons) {
		this.siacRAccountCassaEcons = siacRAccountCassaEcons;
	}

	public List<SiacRAccountClass> getSiacRAccountClasses() {
		return siacRAccountClasses;
	}

	public void setSiacRAccountClasses(List<SiacRAccountClass> siacRAccountClasses) {
		this.siacRAccountClasses = siacRAccountClasses;
	}

}
