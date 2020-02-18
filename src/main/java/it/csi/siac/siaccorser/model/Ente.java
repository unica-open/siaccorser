/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

/**
 * Ente cui appartiene un utente
 * 
 * @author alagna
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class Ente extends Entita {

	private static final long serialVersionUID = 3218244659403162540L;
	private String nome;
	private String codiceFiscale;
	private String direttore;
	private String indirizzoEmail;
	private String numeroFax;
	private String numeroTelefono;
	private String partitaIva;
	private String presidente;
	private String sitoWeb;
	private String codice;
	private String annoAvvioSistema;
	private List<StrutturaAmministrativoContabile> strutture = new ArrayList<StrutturaAmministrativoContabile>();
	//private List<GestioneLivello> gestioneLivello = new ArrayList<GestioneLivello>();
	private Map<TipologiaGestioneLivelli, String> gestioneLivelli = new HashMap<TipologiaGestioneLivelli, String>();
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setStrutture(List<StrutturaAmministrativoContabile> strutture) {
		this.strutture = strutture;
	}

	public List<StrutturaAmministrativoContabile> getStrutture() {
		return strutture;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDirettore() {
		return direttore;
	}

	public void setDirettore(String direttore) {
		this.direttore = direttore;
	}

	public String getIndirizzoEmail() {
		return indirizzoEmail;
	}

	public void setIndirizzoEmail(String indirizzoEmail) {
		this.indirizzoEmail = indirizzoEmail;
	}

	public String getNumeroFax() {
		return numeroFax;
	}

	public void setNumeroFax(String numeroFax) {
		this.numeroFax = numeroFax;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	public String getSitoWeb() {
		return sitoWeb;
	}

	public void setSitoWeb(String sitoWeb) {
		this.sitoWeb = sitoWeb;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getAnnoAvvioSistema() {
		return annoAvvioSistema;
	}

	public void setAnnoAvvioSistema(String annoAvvioSistema) {
		this.annoAvvioSistema = annoAvvioSistema;
	}
	public String getNome() {
		return nome;
	}

	public Map<TipologiaGestioneLivelli, String> getGestioneLivelli() {
		return gestioneLivelli;
	}

	public void setGestioneLivelli(
			Map<TipologiaGestioneLivelli, String> gestioneLivelli) {
		this.gestioneLivelli = gestioneLivelli;
	}

	


	
}
