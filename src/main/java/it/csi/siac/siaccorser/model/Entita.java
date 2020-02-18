/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Capitolo
 * 
 * @author AR
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class Entita implements Serializable {

	private static final long serialVersionUID = -6322516699223113735L;

	private int uid;
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@XmlType(namespace = CORDataDictionary.NAMESPACE)
	public enum StatoEntita {
		IN_LAVORAZIONE, VALIDO, CANCELLATO
	}

	// Valore di default
	private StatoEntita stato = StatoEntita.VALIDO; 
	
	private Date dataCreazione;
	private Date dataFineValidita;

	
	//New 2014-11-11 - START
	private Date dataInizioValidita;
	private Date dataModifica;
	private Date dataCancellazione;
	private String loginOperazione;
	//New 2014-11-11 - END


	public boolean isValida() {
		return valida().isEmpty();
	}

	//@XmlSchemaType(name = "date")
	@XmlSchemaType(name="dateTime")
	public Date getDataCreazione() {
		return dataCreazione;
	}

	//@XmlSchemaType(name = "date")
	@XmlSchemaType(name="dateTime")
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public StatoEntita getStato() {
		return stato;
	}

	public void setStato(StatoEntita stato) {
		this.stato = stato;
	}

	public List<Errore> valida() {
		return new ArrayList<Errore>();
	}
	

	/**
	 * @return the dataInizioValidita
	 */
	@XmlSchemaType(name="dateTime")
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * @return the dataModifica
	 */
	@XmlSchemaType(name="dateTime")
	//@XmlJavaTypeAdapter
	public Date getDataModifica() {
		return dataModifica;
	}

	/**
	 * @param dataModifica the dataModifica to set
	 */
	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	/**
	 * @return the dataCancellazione
	 */
	@XmlSchemaType(name="dateTime")
	public Date getDataCancellazione() {
		return dataCancellazione;
	}

	/**
	 * @param dataCancellazione the dataCancellazione to set
	 */
	public void setDataCancellazione(Date dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	/**
	 * @return the loginOperazione
	 */
	public String getLoginOperazione() {
		return loginOperazione;
	}

	/**
	 * @param loginOperazione the loginOperazione to set
	 */
	public void setLoginOperazione(String loginOperazione) {
		this.loginOperazione = loginOperazione;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
