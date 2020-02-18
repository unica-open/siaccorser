/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice;

/**
 * Caratteristiche del Service Dictionary di COR. Contiene la versione corrente
 * dei messaggi del servizio.
 * 
 * 
 * @author alagna
 * 
 */
public interface CORSvcDictionary {

	String VERSION = "1.0";
	String NAME = "cor";
	String NAMESPACE = "http://siac.csi.it/" + CORSvcDictionary.NAME + "/svc/" + CORSvcDictionary.VERSION;
}
