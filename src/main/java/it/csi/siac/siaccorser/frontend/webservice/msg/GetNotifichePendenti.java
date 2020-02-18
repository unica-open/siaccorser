/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di richiesta della lista delle notifiche pendenti di 
 * un account
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetNotifichePendenti extends ServiceRequest {
	
	private Azione azione;
	private int offset = 0;
	private int size=10;
	/**
	 * @return the azione
	 */
	public Azione getAzione() {
		return azione;
	}
	/**
	 * @param azione the azione to set
	 */
	public void setAzione(Azione azione) {
		this.azione = azione;
	}
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
