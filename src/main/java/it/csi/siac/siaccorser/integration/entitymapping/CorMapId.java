/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entitymapping;

import it.csi.siac.siaccommonser.util.dozer.MapId;

/**
 * Id dei mapping di coversione.
 * 
 * Naming convention: * 
 * Il nome dell'Entity ed il nome del Model concatenati da "_" 
 * 
 *
 */
public enum CorMapId implements MapId {
	
	SiacTOperazioneAsincrona_NotificaOperazioneAsincrona,
	SiacTOperazioneAsincronaDet_DettaglioOperazioneAsincrona,
	SiacTCassaEnum_RuoloCassaEconomale

}
