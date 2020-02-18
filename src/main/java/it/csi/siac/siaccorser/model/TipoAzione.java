/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/***
 * Tipo delle azioni.
 * 
 * @author alagna
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public enum TipoAzione {
	/** Attività non di processo */
	ATTIVITA_SINGOLA, 
	/** Attività che richiede l'inizio di un'istanza di processo */
	AVVIO_PROCESSO, 
	/** Singolo task di processo */
	ATTIVITA_PROCESSO, 
	/** 
	 * Invio ad un utente di un informazione, normalmente relativa ad un processo
	 * ma che NON richiede alcun intervento da parte del destinatario.
	 */
	NOTIFICA, 
	VISUALIZZAZIONE,
	AZIONE_SECONDARIA,
	SERVIZIO
}
