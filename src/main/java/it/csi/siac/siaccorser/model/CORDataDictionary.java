/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

/**
 * Caratteristiche del Data Dictionary di COR. Contiene la versione corrente del
 * dd
 * 
 * 
 * @author alagna
 * 
 */
public interface CORDataDictionary {

	String VERSION = "1.0";
	String NAME = "cor";
	String NAMESPACE = "http://siac.csi.it/" + CORDataDictionary.NAME + "/data/" + CORDataDictionary.VERSION;
	String SERVICE_NAME = "CoreService";
	String PORT_NAME = CORDataDictionary.SERVICE_NAME + "Port";

}
