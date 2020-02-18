/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = CORDataDictionary.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class Variazione extends Entita {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3114298225888920218L;
	
	private String descrizione;
	private String direzione;
	private Date dataApertura;
	private Date dataChiusura;
	private Integer numeroVariazione;
	/**
	 * @return the numeroVariazione
	 */
	public Integer getNumeroVariazione() {
		return numeroVariazione;
	}
	/**
	 * @param numeroVariazione the numeroVariazione to set
	 */
	public void setNumeroVariazione(Integer numeroVariazione) {
		this.numeroVariazione = numeroVariazione;
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
	/**
	 * @return the direzione
	 */
	public String getDirezione() {
		return direzione;
	}
	/**
	 * @param direzione the direzione to set
	 */
	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}
	/**
	 * @return the dataApertura
	 */
	public Date getDataApertura() {
		return dataApertura;
	}
	/**
	 * @param dataApertura the dataApertura to set
	 */
	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}
	/**
	 * @return the dataChiusura
	 */
	public Date getDataChiusura() {
		return dataChiusura;
	}
	/**
	 * @param dataChiusura the dataChiusura to set
	 */
	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}
	
	

}
