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

import it.csi.siac.siaccorser.model.errore.ErroreCore;

/**
 * Ciò che un utente intende fare
 * 
 * @author alagna
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class Azione extends Entita {

	private static final long serialVersionUID = 6573375969852829500L;
	private String nome;
	private String titolo;
	private TipoAzione tipo;
	@XmlTransient
	private GruppoAzioni gruppo;
	private String urlApplicazione;
	private String nomeProcesso;
	private String nomeTask;
	private TipoVerificaSAC verificaSAC;
	
	
	/**
	 * RM 30/10/2014:
	 * verificaUo della siac_t_azione è stato modificato in un flag ed è obbligatorio  
	 */
	private Boolean flagVerificaSac;
	
	/**
	 * @return the flagVerificaSac
	 */
	public Boolean getFlagVerificaSac() {
		return flagVerificaSac;
	}

	/**
	 * @param flagVerificaSac the flagVerificaSac to set
	 */
	public void setFlagVerificaSac(Boolean flagVerificaSac) {
		this.flagVerificaSac = flagVerificaSac;
	}

	@XmlTransient
	private List<Ruolo> ruoli = new ArrayList<Ruolo>();

	@Override
	public List<Errore> valida() {
		List<Errore> errori = new ArrayList<Errore>();

		if (tipo == null)
			errori.add(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("tipo"));
		return errori;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome != null ? nome.trim() : null;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public TipoAzione getTipo() {
		return tipo;
	}

	public void setTipo(TipoAzione tipo) {
		this.tipo = tipo;
	}

	public String getUrlApplicazione() {
		return urlApplicazione;
	}

	public void setUrlApplicazione(String urlApplicazione) {
		this.urlApplicazione = urlApplicazione;
	}

	public String getNomeProcesso() {
		return nomeProcesso;
	}

	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}

	public String getNomeTask() {
		return nomeTask;
	}

	public void setNomeTask(String nomeTask) {
		this.nomeTask = nomeTask;
	}

	public TipoVerificaSAC getVerificaSAC() {
		return verificaSAC;
	}

	public void setVerificaSAC(TipoVerificaSAC verificaSAC) {
		this.verificaSAC = verificaSAC;
	}

	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public GruppoAzioni getGruppo() {
		return gruppo;
	}

	public void setGruppo(GruppoAzioni gruppo) {
		this.gruppo = gruppo;
	}

}
