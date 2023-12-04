/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

/**
 * Permette di specificare il risultato atteso:
 * - TOTALE_X si aspetta un numero di attività o notifiche
 * - ATTIVITA o NOTIFICHE si aspetta la lista di oggetti
 *  
 * @author alagna
 * @version $Id: $
 */
public enum TipoRicercaTaskBpm {
	
	TOTALE_ATTIVITA,
	ATTIVITA,
	TOTALE_NOTIFICHE,
	NOTIFICHE;

}
