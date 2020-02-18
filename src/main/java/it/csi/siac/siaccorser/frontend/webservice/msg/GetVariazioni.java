/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di richiesta della lista degli accounts di un operatore
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetVariazioni extends ServiceRequest {
	
	
	private int first;
	private int maxresult;
	private List<Integer> sacIdList;
	/**
	 * @return the first
	 */
	public int getFirst() {
		return first;
	}
	/**
	 * @param first the first to set
	 */
	public void setFirst(int first) {
		this.first = first;
	}
	/**
	 * @return the maxresult
	 */
	public int getMaxresult() {
		return maxresult;
	}
	/**
	 * @param maxresult the maxresult to set
	 */
	public void setMaxresult(int maxresult) {
		this.maxresult = maxresult;
	}
	/**
	 * @return the sacIdList
	 */
	public List<Integer> getSacIdList() {
		return sacIdList;
	}
	/**
	 * @param sacIdList the sacIdList to set
	 */
	public void setSacIdList(List<Integer> sacIdList) {
		this.sacIdList = sacIdList;
	}
	
	
}
