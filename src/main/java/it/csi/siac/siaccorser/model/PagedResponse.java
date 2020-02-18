/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.paginazione.ListaPaginata;
import it.csi.siac.siaccorser.model.paginazione.ListaPaginataImpl;

@XmlType(namespace = CORDataDictionary.NAMESPACE)
public abstract class PagedResponse<T> extends ServiceResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7680964952238709448L;
	
	@XmlElementWrapper(name = "listaPaginata")
	@XmlElement(name = "elementoDiPagina")
	private ListaPaginata<T> listaPaginata = new ListaPaginataImpl<T>();

	@XmlTransient
	public ListaPaginata<T> getElencoPaginato() {
		return listaPaginata;
	}

	public void setElencoPaginato(ListaPaginata<T> listaPaginata) {
		this.listaPaginata = listaPaginata;
	}

	public void setTotaleElementi(int totaleElementi) {
		((ListaPaginataImpl<T>) listaPaginata).setTotaleElementi(totaleElementi);
	}

	public int getTotaleElementi() {
		return listaPaginata.getTotaleElementi();
	}

	public int getPaginaCorrente() {
		return listaPaginata.getPaginaCorrente();
	}

	public int getTotalePagine() {
		return listaPaginata.getTotalePagine();
	}

	public int getNumeroElementoInizio() {
		return listaPaginata.getNumeroElementoInizio();
	}

	public int getNumeroElementoFine() {
		return listaPaginata.getNumeroElementoFine();
	}

	public boolean getHasPaginaPrecedente() {
		return listaPaginata.getHasPaginaPrecedente();
	}

	public boolean getHasPaginaSuccessiva() {
		return listaPaginata.getHasPaginaSuccessiva();
	}

	public void setPaginaCorrente(int paginaCorrente) {
		((ListaPaginataImpl<T>) listaPaginata).setPaginaCorrente(paginaCorrente);
	}

	public void setTotalePagine(int totalePagine) {
		((ListaPaginataImpl<T>) listaPaginata).setTotalePagine(totalePagine);
	}

	public void setNumeroElementoInizio(int numeroElementoInizio) {
		((ListaPaginataImpl<T>) listaPaginata).setNumeroElementoInizio(numeroElementoInizio);
	}

	public void setNumeroElementoFine(int numeroElementoFine) {
		((ListaPaginataImpl<T>) listaPaginata).setNumeroElementoFine(numeroElementoFine);
	}

	public void setHasPaginaPrecedente(boolean hasPaginaPrecedente) {
		((ListaPaginataImpl<T>) listaPaginata).setHasPaginaPrecedente(hasPaginaPrecedente);
	}

	public void setHasPaginaSuccessiva(boolean hasPaginaSuccessiva) {
		((ListaPaginataImpl<T>) listaPaginata).setHasPaginaSuccessiva(hasPaginaSuccessiva);
	}

}
