/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * La classe WebApp contiene tutte le informazioni necessarie per l'esecuzione
 * di un task
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class WebApp implements Serializable {

	private static final long serialVersionUID = 783947548461634479L;

	// Azione richiesta, account, variabili di processo,
	// azioni consentite
}
