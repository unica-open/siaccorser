/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * EntitaStoricizzata: L'entit&agrave; storicizzata &eacute; una particolare entit&agrave; che con la
 * sua prima istanza genera anche un' istanza di storicizzazione con i seguenti
 * attributi : - data inizio - atto amministrativo ( facoltativo ) - attributi
 * oggetto di storicizzazione tipici dell'entit&agrave;, se si tratta di una
 * specializzazione e implementazione dell'entit&agrave; di storicizzata di base ( qui
 * presente ). Quando avviene l'evento che da origine ad una storicizzazione (
 * es. passaggio di stato ), l'istanza corrente viene chiusa ( impostazione
 * della data fine ) e ne viene creata una nuova con gli attributi presenti sul
 * momento. Viene anche tenuto il legame con la precedente istanza di
 * storicizzazione.
 * 
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public abstract class EntitaStoricizzata extends Entita {

	private static final long serialVersionUID = 4328232461975335941L;

	// FIXME: verificare che sia corretta che estenda da Entita', sul concettuale
	// non c'e' e ha solo dtInizio e dtFine

}
