/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.file;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.CORDataDictionary;
import it.csi.siac.siaccorser.model.Entita;
import it.csi.siac.siaccorser.model.file.StatoFile.CodiceStatoFile;

/**
 * 
 * @author AR
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class File extends Entita {
	private static final long serialVersionUID = 4327989251662285082L;

	private String nome;
	private String codice;
	private String note;
	private String mimeType;
	private Long dimensione;
	private byte[] contenuto;
	private TipoFile tipo;
	private StatoFile statoFile;

	public File() {
		super();
	}

	public void setIdTipo(Integer idTipo) {
		setTipo(new TipoFile(idTipo));
	}

	public void setCodiceTipo(String codiceTipo) {
		setTipo(new TipoFile(codiceTipo));
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Long getDimensione() {
		return dimensione;
	}

	public void setDimensione(Long dimensione) {
		this.dimensione = dimensione;
	}

	public byte[] getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		if (contenuto != null)
			setContenuto(contenuto.getBytes());
	}

	public void setContenuto(byte[] contenuto) {
		this.contenuto = contenuto;
		this.dimensione = Long.valueOf(contenuto.length);
	}

	public StatoFile getStatoFile() {
		return statoFile;
	}

	public void setStatoFile(StatoFile statoFile) {
		this.statoFile = statoFile;
	}

	public TipoFile getTipo() {
		return tipo;
	}

	public void setTipo(TipoFile tipo) {
		this.tipo = tipo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setStatoFile(CodiceStatoFile codice) {
		setStatoFile(new StatoFile(codice));
	}

	public boolean isDaElaborare() {
		return statoFile.getCodiceEnum() == CodiceStatoFile.DA_ELABORARE;
	}

	public boolean isAnnullato() {
		return statoFile.getCodiceEnum() == CodiceStatoFile.ANNULLATO;
	}

	public boolean isInElaborazione() {
		return statoFile.getCodiceEnum() == CodiceStatoFile.IN_ELABORAZIONE;
	}

	public boolean isElaborato() {
		return statoFile.getCodiceEnum() == CodiceStatoFile.ELABORATO;
	}

	public boolean isElaboratoConErrori() {
		return statoFile.getCodiceEnum() == CodiceStatoFile.ELABORATO_CON_ERRORI;
	}

}
