/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

/**
 * Entità dematerializzata: rappresenta le Entità, come le usa il processo
 * 
 * @author alagna
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class EntitaDem extends Entita {

	private static final long serialVersionUID = 1875613984329513636L;

	/** Nome della classe dell'entità */
	private String className;

	/** Attributi significativi per il processo */
	private Map<String, Object> attributi = new HashMap<String, Object>();

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, Object> getAttributi() {
		return attributi;
	}

	public void setAttributi(Map<String, Object> attributi) {
		this.attributi = attributi;
	}

	/**
	 * Nel caso di un'entità dematerializzata non sono previste verifiche di
	 * validità di attributi
	 */

}
