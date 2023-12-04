/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the siac_t_operazione_asincrona_det database table.
 * 
 */
@Entity
@Table(name="siac_t_operazione_asincrona_det")
public class SiacTOperazioneAsincronaDet extends SiacTEnteBase {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_OPERAZIONE_ASINCRONA_DET_OPASDETID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_OPERAZIONE_ASINCRONA_DET_OPAS_DET_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_OPERAZIONE_ASINCRONA_DET_OPASDETID_GENERATOR")
	@Column(name="opas_det_id")
	private Integer uid;

	@Column(name="opas_det_code")
	private String opasDetCode;

	@Column(name="opas_det_desc")
	private String opasDetDesc;

	@Column(name="opas_det_esito")
	private String opasDetEsito;

	@Column(name="opas_det_msg")
	private String opasDetMsg;

	@Column(name="opas_srv_resp")
	private String serviceResponse;

	//bi-directional many-to-one association to SiacTOperazioneAsincrona
	@ManyToOne
	@JoinColumn(name="opas_id")
	private SiacTOperazioneAsincrona siacTOperazioneAsincrona;

	public String getOpasDetCode() {
		return this.opasDetCode;
	}

	public void setOpasDetCode(String opasDetCode) {
		this.opasDetCode = opasDetCode;
	}

	public String getOpasDetDesc() {
		return this.opasDetDesc;
	}

	public void setOpasDetDesc(String opasDetDesc) {
		this.opasDetDesc = opasDetDesc;
	}

	public String getOpasDetEsito() {
		return this.opasDetEsito;
	}

	public void setOpasDetEsito(String opasDetEsito) {
		this.opasDetEsito = opasDetEsito;
	}

	public String getOpasDetMsg() {
		return this.opasDetMsg;
	}

	public void setOpasDetMsg(String opasDetMsg) {
		this.opasDetMsg = opasDetMsg;
	}

	
	public SiacTOperazioneAsincrona getSiacTOperazioneAsincrona() {
		return this.siacTOperazioneAsincrona;
	}

	public void setSiacTOperazioneAsincrona(SiacTOperazioneAsincrona siacTOperazioneAsincrona) {
		this.siacTOperazioneAsincrona = siacTOperazioneAsincrona;
	}


	public String getServiceResponse() {
		return serviceResponse;
	}


	public void setServiceResponse(String serviceResponse) {
		this.serviceResponse = serviceResponse;
	}


	@Override
	public Integer getUid() {
		return uid;
	}


	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	
}