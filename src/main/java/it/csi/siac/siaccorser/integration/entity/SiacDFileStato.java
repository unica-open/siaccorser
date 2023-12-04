/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_d_file_stato")
public class SiacDFileStato extends SiacTEnteBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6668486077335603353L;

	@Id
	@SequenceGenerator(name = "SIAC_D_FILE_STATO_FILESTATOID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_D_FILE_STATO_FILE_STATO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_D_FILE_STATO_FILESTATOID_GENERATOR")
	@Column(name = "file_stato_id")
	private Integer uid;

	@Column(name = "file_stato_code")
	private String codice;

	@Column(name = "file_stato_desc")
	private String descrizione;

	@OneToMany(mappedBy = "stato")
	private List<SiacRFileStato> file;

	public SiacDFileStato(Integer uid) {
		this();
		this.uid = uid;
	}

	public SiacDFileStato() {
		super();
	}

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

	public List<SiacRFileStato> getFile() {
		return file;
	}

	public void setFile(List<SiacRFileStato> file) {
		this.file = file;
	}

}