/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;

/**
 * Ruolo associato all'Operatore o ad un Gruppo.
 * 
 * @author alagna
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class Ruolo extends Entita {

	private static final long serialVersionUID = -2482360292119645811L;
	private String nome;
	private String descrizione;
	private List<RuoloAccount> accounts = new ArrayList<RuoloAccount>();
	private List<RuoloGruppo> gruppi = new ArrayList<RuoloGruppo>();
	private List<Azione> azioni = new ArrayList<Azione>();
	private Set<Integer> anniBilancio = new HashSet<Integer>();

	@Override
	public List<Errore> valida() {
		return new ArrayList<Errore>();
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
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
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the accounts
	 */
	public List<RuoloAccount> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<RuoloAccount> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the gruppi
	 */
	public List<RuoloGruppo> getGruppi() {
		return gruppi;
	}

	/**
	 * @param gruppi the gruppi to set
	 */
	public void setGruppi(List<RuoloGruppo> gruppi) {
		this.gruppi = gruppi;
	}

	/**
	 * @return the azioni
	 */
	public List<Azione> getAzioni() {
		return azioni;
	}

	/**
	 * @param azioni the azioni to set
	 */
	public void setAzioni(List<Azione> azioni) {
		this.azioni = azioni;
	}

	public Set<Integer> getAnniBilancio()
	{
		return anniBilancio;
	}

	public void setAnniBilancio(Set<Integer> anniBilancio)
	{
		this.anniBilancio = anniBilancio;
	}
}
