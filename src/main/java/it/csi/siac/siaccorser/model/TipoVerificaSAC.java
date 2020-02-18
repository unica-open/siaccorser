/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * Tipo di verifica necessaria per associare alcune Azioni a specifiche Strutture Amministrativo Contabili.
 * 
 * @author alagna
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public enum TipoVerificaSAC {
	NESSUNO, SAC
}
