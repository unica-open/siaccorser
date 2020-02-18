/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * Descrittore di una notifica generata dall'esecuzione di un'operazione
 * asincrona
 * 
 * @author rmontuori
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class NotificaOperazioneAsincrona extends Entita {

	private static final long serialVersionUID = 6899374679594865962L;

	private String statoOperazione;
	private String messaggio;
	private Date dataAvvioOperazione;
	private Date dataFineOperazione;
	private Integer totaleSuccess;
	private Integer totaleFalliti;
	private String accountElaborazione;
	private boolean notificato;
	

	public NotificaOperazioneAsincrona() {
		super();
	}
	
	/**
	 * @return the accountElaborazione
	 */
	public String getAccountElaborazione() {
		return accountElaborazione;
	}

	/**
	 * @param accountElaborazione the accountElaborazione to set
	 */
	public void setAccountElaborazione(String accountElaborazione) {
		this.accountElaborazione = accountElaborazione;
	}

	@XmlSchemaType(name = "dateTime")
	public Date getDataAvvioOperazione() {
		return dataAvvioOperazione;
	}

	public void setDataAvvioOperazione(Date dataAvvioOperazione) {
		this.dataAvvioOperazione = dataAvvioOperazione;
	}

	@XmlSchemaType(name = "dateTime")
	public Date getDataFineOperazione() {
		return dataFineOperazione;
	}

	public void setDataFineOperazione(Date dataFineOperazione) {
		this.dataFineOperazione = dataFineOperazione;
	}

	public String getStatoOperazione() {
		return statoOperazione;
	}

	public void setStatoOperazione(String statoOperazione) {
		this.statoOperazione = statoOperazione;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public boolean isNotificato() {
		return notificato;
	}

	public void setNotificato(boolean notificato) {
		this.notificato = notificato;
	}

	public Integer getTotaleSuccess() {
		return totaleSuccess;
	}

	public void setTotaleSuccess(Integer totaleSuccess) {
		this.totaleSuccess = totaleSuccess;
	}

	public Integer getTotaleFalliti() {
		return totaleFalliti;
	}

	public void setTotaleFalliti(Integer totaleFalliti) {
		this.totaleFalliti = totaleFalliti;
	}

}
