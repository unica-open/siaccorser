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

import it.csi.siac.siaccommonser.integration.entity.SiacTBase;

/**
 * The persistent class for the siac_t_ente_proprietario database table.
 * 
 */
@Entity
@Table(name = "siac_t_ente_proprietario")
public class SiacTEnteProprietario extends SiacTBase {

	private static final long serialVersionUID = -5556601361994075842L;

	@Id
	@SequenceGenerator(name = "SIAC_T_ENTE_PROPRIETARIO_ENTEPROPRIETARIOID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_ENTE_PROPRIETARIO_ENTE_PROPRIETARIO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_ENTE_PROPRIETARIO_ENTEPROPRIETARIOID_GENERATOR")
	@Column(name = "ente_proprietario_id")
	private Integer enteProprietarioId;

	@Column(name = "codice_fiscale")
	private String codiceFiscale;

	@Column(name = "ente_denominazione")
	private String enteDenominazione;

	@OneToMany(mappedBy = "ente")
	private List<SiacTOperazioneAsincrona> siacTOperazioneAsincronas;

	public Integer getEnteProprietarioId() {
		return enteProprietarioId;
	}

	public void setEnteProprietarioId(Integer enteProprietarioId) {
		this.enteProprietarioId = enteProprietarioId;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getEnteDenominazione() {
		return enteDenominazione;
	}

	public void setEnteDenominazione(String enteDenominazione) {
		this.enteDenominazione = enteDenominazione;
	}

	public List<SiacTOperazioneAsincrona> getSiacTOperazioneAsincronas() {
		return siacTOperazioneAsincronas;
	}

	public void setSiacTOperazioneAsincronas(
			List<SiacTOperazioneAsincrona> siacTOperazioneAsincronas) {
		this.siacTOperazioneAsincronas = siacTOperazioneAsincronas;
	}

	@Override
	public Integer getUid() {
		return enteProprietarioId;
	}

	@Override
	public void setUid(Integer uid) {
		this.enteProprietarioId = uid;

	}

}