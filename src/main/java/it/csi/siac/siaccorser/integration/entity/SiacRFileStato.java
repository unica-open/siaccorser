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

@Entity
@Table(name = "siac_r_file_stato")
public class SiacRFileStato extends SiacTEnteBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5616411793537690214L;

	@Id
	@SequenceGenerator(name = "SIAC_R_FILE_STATO_FILESTATOID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_FILE_STATO_FILE_STATO_R_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_FILE_STATO_FILESTATOID_GENERATOR")
	@Column(name = "file_stato_r_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "file_id")
	private SiacTFile file;

	@ManyToOne
	@JoinColumn(name = "file_stato_id")
	private SiacDFileStato stato;

	public SiacRFileStato(SiacDFileStato stato) {
		this();
		this.stato = stato;
	}

	public SiacRFileStato() {
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

	public SiacTFile getFile() {
		return file;
	}

	public void setFile(SiacTFile file) {
		this.file = file;
	}

	public SiacDFileStato getStato() {
		return stato;
	}

	public void setStato(SiacDFileStato stato) {
		this.stato = stato;
	}

}