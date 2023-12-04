/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the siac_t_operazione_asincrona database table.
 * 
 */
@Entity
@Table(name = "siac_t_operazione_asincrona")
public class SiacTOperazioneAsincrona extends SiacTEnteBase  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8550122123735176010L;


	@Id
	@SequenceGenerator(name = "SIAC_T_OPERAZIONE_ASINCRONA_OPASID_GENERATOR", allocationSize=1, sequenceName = "SIAC_T_OPERAZIONE_ASINCRONA_OPAS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_OPERAZIONE_ASINCRONA_OPASID_GENERATOR")
	@Column(name = "opas_id")
	private Integer uid;

	@Column(name = "opas_avvio", updatable=false)
	private Date opasAvvio;

	@Column(name = "opas_fine")
	private Date opasFine;

	@Column(name = "opas_msg")
	private String opasMsg;

	@Column(name = "opas_msg_notificato")
	private Boolean opasMsgNotificato;

	@Column(name = "opas_stato")
	private String opasStato;

	// bi-directional many-to-one association to SiacTAccount
	@ManyToOne
	@JoinColumn(name = "account_id", updatable=false)
	private SiacTAccount siacTAccount;

	// bi-directional many-to-one association to SiacTAzione
	@ManyToOne
	@JoinColumn(name = "azione_id",updatable=false)
	private SiacTAzione siacTAzione;
	
	
	//bi-directional many-to-one association to SiacTOperazioneAsincronaDet
	@OneToMany(mappedBy="siacTOperazioneAsincrona", fetch=FetchType.EAGER)
	@Fetch ( value =  FetchMode . SELECT ) 
	@BatchSize ( size =  20 ) 
	private List<SiacTOperazioneAsincronaDet> siacTOperazioneAsincronaDets;
	
	@ManyToOne
	@JoinColumn(name = "variazione_id",updatable=false)
	private SiacTVariazione siacTVariazione;

	
	public List<SiacTOperazioneAsincronaDet> getSiacTOperazioneAsincronaDets() {
		return siacTOperazioneAsincronaDets;
	}

	public void setSiacTOperazioneAsincronaDets(
			List<SiacTOperazioneAsincronaDet> siacTOperazioneAsincronaDets) {
		this.siacTOperazioneAsincronaDets = siacTOperazioneAsincronaDets;
	}

	public Date getOpasAvvio() {
		return this.opasAvvio;
	}

	public void setOpasAvvio(Date opasAvvio) {
		this.opasAvvio = opasAvvio;
	}

	public Date getOpasFine() {
		return this.opasFine;
	}

	public void setOpasFine(Date opasFine) {
		this.opasFine = opasFine;
	}

	public String getOpasMsg() {
		return this.opasMsg;
	}

	public void setOpasMsg(String opasMsg) {
		this.opasMsg = opasMsg;
	}

	public Boolean getOpasMsgNotificato() {
		return this.opasMsgNotificato;
	}

	public void setOpasMsgNotificato(Boolean opasMsgNotificato) {
		this.opasMsgNotificato = opasMsgNotificato;
	}

	public String getOpasStato() {
		return this.opasStato;
	}

	public void setOpasStato(String opasStato) {
		this.opasStato = opasStato;
	}

	public SiacTAccount getSiacTAccount() {
		return siacTAccount;
	}

	public void setSiacTAccount(SiacTAccount siacTAccount) {
		this.siacTAccount = siacTAccount;
	}

	public SiacTAzione getSiacTAzione() {
		return siacTAzione;
	}

	public void setSiacTAzione(SiacTAzione siacTAzione) {
		this.siacTAzione = siacTAzione;
	}
	
	public SiacTVariazione getSiacTVariazione() {
		return siacTVariazione;
	}

	public void setSiacTVariazione(SiacTVariazione siacTVariazione) {
		this.siacTVariazione = siacTVariazione;
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	
	
	@Override
	@Transient
	public Date getDataInizioValidita() {
		return null;
	}

	@Override
	@Transient
	public Date getDataFineValidita() {
		return null;
	}

}