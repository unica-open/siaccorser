/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceResponse;

/**
 * Messaggio di risposta per la lista dei classificatori ricercati per: anno, idEnteProprietario e idPadre (es: ricerco i programmi per l'idMissione)
 * 
 * ritorna una mappa con Key= TipologiaClassificatore (enum che contiene i codici dei tipi dei classificatori)
 * 						 Value = la lista di classificatori associata alla TipologiaClassificatore 	
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public abstract class LeggiClassificatoriByIdPadreResponse extends ServiceResponse {
	// Nulla da aggiungere
}
