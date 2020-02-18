/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 * Gruppo cui può appartenere un Operatore.
 * 
 * @author alagna
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class Gruppo extends Entita {

	private static final long serialVersionUID = -1227817425217993647L;
	private String nome;
	private String descrizione;
	private List<Account> accounts = new ArrayList<Account>();
	private List<RuoloGruppo> ruoli = new ArrayList<RuoloGruppo>();
	private Ente ente;

	@Override
	public List<Errore> valida() {
		return new ArrayList<Errore>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<RuoloGruppo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<RuoloGruppo> ruoli) {
		this.ruoli = ruoli;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

}
