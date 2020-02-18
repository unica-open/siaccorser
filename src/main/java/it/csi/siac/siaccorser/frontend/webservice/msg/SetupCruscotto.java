/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di richiesta delle informazioni visualizzate nel cruscotto di un
 * operatore
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class SetupCruscotto extends ServiceRequest {
	// Nulla da aggiungere
}
