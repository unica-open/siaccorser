/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Profilo dell'utente
 * 
 * @author alagna, AR
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class Account extends Entita {

	private static final long serialVersionUID = 3585679632121316048L;
	
	private String codice;
	private String nome;
	private String descrizione;
	private String indirizzoMail;
	@XmlTransient
	private Operatore operatore;
	private Ente ente;
	@XmlTransient
	private List<Gruppo> gruppi = new ArrayList<Gruppo>();
	@XmlTransient
	private List<RuoloAccount> ruoli = new ArrayList<RuoloAccount>();

	private List<StrutturaAmministrativoContabile> struttureAmministrativeContabili = new ArrayList<StrutturaAmministrativoContabile>();

	private List<CassaEconomaleCruscotto> casseEconomali = new ArrayList<CassaEconomaleCruscotto>();

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIndirizzoMail() {
		return indirizzoMail;
	}

	public void setIndirizzoMail(String indirizzoMail) {
		this.indirizzoMail = indirizzoMail;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public List<Gruppo> getGruppi() {
		return gruppi;
	}

	public void setGruppi(List<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}

	public List<RuoloAccount> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<RuoloAccount> ruoli) {
		this.ruoli = ruoli;
	}

	public Operatore getOperatore() {
		return operatore;
	}

	public void setOperatore(Operatore operatore) {
		this.operatore = operatore;
	}

	public String getId() {
		return "" + getUid();
	}

	public List<StrutturaAmministrativoContabile> getStruttureAmministrativeContabili() {
		return struttureAmministrativeContabili;
	}

	public void setStruttureAmministrativeContabili(List<StrutturaAmministrativoContabile> struttureAmministrativeContabili) {
		this.struttureAmministrativeContabili = struttureAmministrativeContabili;
	}



	public List<CassaEconomaleCruscotto> getCasseEconomali() {
		return casseEconomali;
	}

	public void setCasseEconomali(List<CassaEconomaleCruscotto> casseEconomali) {
		this.casseEconomali = casseEconomali;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

}