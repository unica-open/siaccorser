/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

/**
 * Attività che un Operatore può completare
 * 
 * @author alagna
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class AttivitaPendente implements Serializable {

	private static final long serialVersionUID = 6019990786793533533L;
	
	/** id univoco usato da cruApplication */
	private String id;
	private String descrizione;
	private String descrizioneBreve;
	private Boolean urgente;
	/** id assegnato dal server bpm */
	private String idAttivita;
	private Azione azione;

	private Date dataAperturaProposta;
	private Date dataChiusuraProposta;
	private String direzioneProponente;
	
	
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizioneBreve() {
		return descrizioneBreve;
	}

	public void setDescrizioneBreve(String descrizioneBreve) {
		this.descrizioneBreve = descrizioneBreve;
	}

	/**
	 * @return the idProcesso
	 */
	public String getIdAttivita() {
		return idAttivita;
	}

	/**
	 * @param idProcesso the idProcesso to set
	 */
	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}

	public Azione getAzione() {
		return azione;
	}

	public void setAzione(Azione azione) {
		this.azione = azione;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getUrgente() {
		return urgente;
	}

	public void setUrgente(Boolean urgente) {
		this.urgente = urgente;
	}

	/**
	 * @return the dataAperturaProposta
	 */
	public Date getDataAperturaProposta() {
		return dataAperturaProposta;
	}

	/**
	 * @param dataAperturaProposta the dataAperturaProposta to set
	 */
	public void setDataAperturaProposta(Date dataAperturaProposta) {
		this.dataAperturaProposta = dataAperturaProposta;
	}

	/**
	 * @return the dataChiusuraProposta
	 */
	public Date getDataChiusuraProposta() {
		return dataChiusuraProposta;
	}

	/**
	 * @param dataChiusuraProposta the dataChiusuraProposta to set
	 */
	public void setDataChiusuraProposta(Date dataChiusuraProposta) {
		this.dataChiusuraProposta = dataChiusuraProposta;
	}

	/**
	 * @return the direzioneProponente
	 */
	public String getDirezioneProponente() {
		return direzioneProponente;
	}

	/**
	 * @param direzioneProponente the direzioneProponente to set
	 */
	public void setDirezioneProponente(String direzioneProponente) {
		this.direzioneProponente = direzioneProponente;
	}

}