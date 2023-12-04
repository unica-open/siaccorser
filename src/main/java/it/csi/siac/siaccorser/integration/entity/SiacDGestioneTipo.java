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
@Table(name="siac_d_gestione_tipo")
public class SiacDGestioneTipo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5256273786301837355L;

	@Id
	@Column(name = "gestione_tipo_id")
	@SequenceGenerator(name = "SEQ_D_GESTIONE_TIPO", allocationSize=1, sequenceName = "siac_d_gestione_tipo_gestione_tipo_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_D_GESTIONE_TIPO")
	private Integer uid;

	@Column(name = "gestione_tipo_code")
	private String codice;

	@Column(name = "gestione_tipo_desc")
	private String descrizione;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
