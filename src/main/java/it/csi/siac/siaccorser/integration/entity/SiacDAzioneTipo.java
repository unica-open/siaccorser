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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_d_azione_tipo")
public class SiacDAzioneTipo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4653579517642722836L;

	@Id
	@Column(name = "azione_tipo_id")
	@SequenceGenerator(name = "SEQ_T_TIPO_AZIONE", allocationSize=1, sequenceName = "siac_d_azione_tipo_azione_tipo_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "SEQ_T_TIPO_AZIONE")
	private Integer uid;
	
	@Column(name = "azione_tipo_code")
	private String codice;
	
	@Column(name = "azione_tipo_desc")
	private String descrizione;
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
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}
	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
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
	
}
