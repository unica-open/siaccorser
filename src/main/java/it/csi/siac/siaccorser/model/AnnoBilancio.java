/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Anno bilancio
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class AnnoBilancio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8018310059213971177L;
	private Integer idBilancio;
	private Integer idPeriodo;
	private Integer anno;
	private String descrizioneFase = "";
	private String codiceFase = "";

	/**
	 * @return the descrizioneFase
	 */
	public String getDescrizioneFase() {
		return descrizioneFase;
	}

	/**
	 * @param descrizioneFase
	 *            the descrizioneFase to set
	 */
	public void setDescrizioneFase(String descrizioneFase) {
		this.descrizioneFase = descrizioneFase;
	}

	/**
	 * @return the codiceFase
	 */
	public String getCodiceFase() {
		return codiceFase;
	}

	/**
	 * @param codiceFase
	 *            the codiceFase to set
	 */
	public void setCodiceFase(String codiceFase) {
		this.codiceFase = codiceFase;
	}

	public String getDescrizione() {
		if (anno == null) {
			return "";
		}
		return anno + " - " + descrizioneFase;
	}

	public Integer getIdBilancio() {
		return idBilancio;
	}

	public void setIdBilancio(Integer idBilancio) {
		this.idBilancio = idBilancio;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public Integer getAnno()
	{
		return anno;
	}

	public void setAnno(Integer anno)
	{
		this.anno = anno;
	}

	

}