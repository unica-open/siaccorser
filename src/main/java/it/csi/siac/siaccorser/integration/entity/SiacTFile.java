/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_file")
public class SiacTFile extends SiacTEnteBase {
	private static final long serialVersionUID = -8194089787467412831L;

	@Id
	@SequenceGenerator(name = "SIAC_T_FILE_FILEID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_FILE_FILE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_FILE_FILEID_GENERATOR")
	@Column(name = "file_id")
	private Integer uid;

	@Column(name = "file_size")
	private Long dimensione;

	@Column(name = "file_type")
	private String mimeType;

	@Column(name = "file_code")
	private String codice;

	@Column(name = "file_name")
	private String nome;

	@Column(name = "file_note")
	private String note;

	@Column(name = "file")
	private byte[] file;

	@OneToMany(mappedBy = "file", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private List<SiacRFileStato> stati;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "file_tipo_id")
	private SiacDFileTipo tipo;

	public SiacTFile() {
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

	public void setTipoId(Integer tipoId) {
		tipo = new SiacDFileTipo(tipoId);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getTipoId() {
		return tipo.getUid();
	}

	public void setStatoId(Integer statoId) {
		SiacDFileStato statoFile = new SiacDFileStato(statoId);

		if (stati == null) {
			stati = new ArrayList<SiacRFileStato>();
			SiacRFileStato rfs = new SiacRFileStato(statoFile);
			rfs.setDataModificaInserimento(new Date());
			rfs.setFile(this);
			stati.add(rfs);
		} else
			stati.get(0).setStato(statoFile);
	}

	public SiacDFileStato getStato() {
		try {
			return stati.iterator().next().getStato();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setDatiStati() {
		for (SiacRFileStato s : stati) {
			s.setEnte(getEnte());
			s.setLoginOperazione(getLoginOperazione());
		}
	}

	public Long getDimensione() {
		return dimensione;
	}

	public void setDimensione(Long dimensione) {
		this.dimensione = dimensione;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public List<SiacRFileStato> getStati() {
		return stati;
	}

	public void setStati(List<SiacRFileStato> stati) {
		this.stati = stati;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SiacDFileTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDFileTipo tipo) {
		this.tipo = tipo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

}