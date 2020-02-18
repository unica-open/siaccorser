/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.paginazione;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class ListaPaginataImpl<T> extends ArrayList<T> implements ListaPaginata<T> {
	private static final long serialVersionUID = -2196317924251371579L;

	private int paginaCorrente;
	private int totalePagine;
	private int totaleElementi;
	private int numeroElementoInizio;
	private int numeroElementoFine;
	private boolean hasPaginaPrecedente;
	private boolean hasPaginaSuccessiva;

	public ListaPaginataImpl(List<T> list) {
		super(list);
	}

	public ListaPaginataImpl() {
		super();
	}

	@Override
	public int getPaginaCorrente() {
		return paginaCorrente;
	}

	public void setPaginaCorrente(int paginaCorrente) {
		this.paginaCorrente = paginaCorrente;
	}

	@Override
	public int getTotalePagine() {
		return totalePagine;
	}

	public void setTotalePagine(int totalePagine) {
		this.totalePagine = totalePagine;
	}

	@Override
	public int getTotaleElementi() {
		return totaleElementi;
	}

	public void setTotaleElementi(int totaleElementi) {
		this.totaleElementi = totaleElementi;
	}

	@Override
	public int getNumeroElementoInizio() {
		return numeroElementoInizio;
	}

	public void setNumeroElementoInizio(int numeroElementoInizio) {
		this.numeroElementoInizio = numeroElementoInizio;
	}

	@Override
	public int getNumeroElementoFine() {
		return numeroElementoFine;
	}

	public void setNumeroElementoFine(int numeroElementoFine) {
		this.numeroElementoFine = numeroElementoFine;
	}

	@Override
	public boolean getHasPaginaPrecedente() {
		return hasPaginaPrecedente;
	}

	public void setHasPaginaPrecedente(boolean hasPaginaPrecedente) {
		this.hasPaginaPrecedente = hasPaginaPrecedente;
	}

	@Override
	public boolean getHasPaginaSuccessiva() {
		return hasPaginaSuccessiva;
	}

	public void setHasPaginaSuccessiva(boolean hasPaginaSuccessiva) {
		this.hasPaginaSuccessiva = hasPaginaSuccessiva;
	}

	public boolean isPrimaPagina() {
		return paginaCorrente == 0;
	}

	public boolean isUltimaPagina() {
		return paginaCorrente == totalePagine - 1;
	}

	
}
