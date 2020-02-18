/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.paginazione;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;

/**
 * Definizione di una lista paginata, rappresentante un'unica pagina della stessa
 * @param <T> la tipizzazione della lista
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public interface ListaPaginata<T> extends List<T> {
	/**
	 * Numero pagina corrente richiesta
	 * @return la pagina corrente
	 */
	int getPaginaCorrente();
	
	/**
	 * numero complessivo degli elementi
	 * @return il totale degli elementi
	 */
	int getTotaleElementi();

	/**
	 * numero complessivo delle pagine
	 * @return il totale delle pagine
	 */
	int getTotalePagine();
	
	/**
	 * numero del primo elemento della pagina
	 * @return il numero dell'elemento iniziale
	 */
	int getNumeroElementoInizio();
	
	/**
	 * numero dell'ultimo elemento della pagina
	 * @return il numero dell'elemento finale
	 */
	int getNumeroElementoFine();

	/**
	 * presenza pagina precedente
	 * @return se ha una pagina precedente
	 */
	boolean getHasPaginaPrecedente();
	
	/**
	 * presenza pagina successiva
	 * @return se ha una pagina successiva
	 */
	boolean getHasPaginaSuccessiva();
	
}
