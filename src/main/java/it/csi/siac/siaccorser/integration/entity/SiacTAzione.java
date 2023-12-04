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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_azione")
public class SiacTAzione extends SiacTEnteBase {


	private static final long serialVersionUID = -2715619066024269093L;

	@Id
	@Column(name = "azione_id")
	@SequenceGenerator(name = "SIAC_T_AZIONE_AZIONEID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_AZIONE_AZIONE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_AZIONE_AZIONEID_GENERATOR")
	private Integer uid;
	
	@Column(name = "azione_code")
	private String nome;
	
	@Column(name = "azione_desc")
	private String titolo;
	
	@OneToOne
	@JoinColumn(name = "azione_tipo_id")
	private SiacDAzioneTipo tipo;
	
	@OneToOne
	@JoinColumn(name = "gruppo_azioni_id")
	private SiacDGruppoAzioni gruppo;
		
	@OneToMany(mappedBy="azione")
	private List<SiacRRuoloOpAzione> siacRRuoloOpAziones = new ArrayList<SiacRRuoloOpAzione>();
	
	@Column(name = "urlapplicazione")
	private String urlApplicazione;
	
	@Column(name = "nomeprocesso")
	private String nomeProcesso;
	
	@Column(name = "nometask")
	private String nomeTask;
	
	
	@Column(name = "verificauo")
	private Boolean verificaSAC;
	
	/** The siac t visibilitas. */
	@OneToOne(mappedBy="siacTAzionePendente")
	private SiacDVariazioneStato siacDVariazioneStato;


	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

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
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}
	/**
	 * @param titolo the titolo to set
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	/**
	 * @return the tipo
	 */
	public SiacDAzioneTipo getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(SiacDAzioneTipo tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the gruppo
	 */
	public SiacDGruppoAzioni getGruppo() {
		return gruppo;
	}
	/**
	 * @param gruppo the gruppo to set
	 */
	public void setGruppo(SiacDGruppoAzioni gruppo) {
		this.gruppo = gruppo;
	}
	/**
	 * @return the urlApplicazione
	 */
	public String getUrlApplicazione() {
		return urlApplicazione;
	}
	/**
	 * @param urlApplicazione the urlApplicazione to set
	 */
	public void setUrlApplicazione(String urlApplicazione) {
		this.urlApplicazione = urlApplicazione;
	}
	/**
	 * @return the nomeProcesso
	 */
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	/**
	 * @param nomeProcesso the nomeProcesso to set
	 */
	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}
	/**
	 * @return the nomeTask
	 */
	public String getNomeTask() {
		return nomeTask;
	}
	/**
	 * @param nomeTask the nomeTask to set
	 */
	public void setNomeTask(String nomeTask) {
		this.nomeTask = nomeTask;
	}
	/**
	 * @return the verificaSAC
	 */
	public Boolean getVerificaSAC() {
		return verificaSAC;
	}
	/**
	 * @param verificaSAC the verificaSAC to set
	 */
	public void setVerificaSAC(Boolean verificaSAC) {
		this.verificaSAC = verificaSAC;
	}

	/**
	 * @return the siacRRuoloOpAziones
	 */
	public List<SiacRRuoloOpAzione> getSiacRRuoloOpAziones() {
		return siacRRuoloOpAziones;
	}

	/**
	 * @param siacRRuoloOpAziones the siacRRuoloOpAziones to set
	 */
	public void setSiacRRuoloOpAziones(List<SiacRRuoloOpAzione> siacRRuoloOpAziones) {
		this.siacRRuoloOpAziones = siacRRuoloOpAziones;
	}

	
}
