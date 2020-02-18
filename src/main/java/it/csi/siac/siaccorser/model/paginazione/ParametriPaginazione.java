/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.paginazione;

import java.io.Serializable;

public class ParametriPaginazione implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8208366179062196077L;

	private static final int DEFAULT_ELEMENTI_PER_PAGINA = 10;

	// numero pagina richiesto
	private int numeroPagina;

	// numero di elementi per pagina
	private int elementiPerPagina = DEFAULT_ELEMENTI_PER_PAGINA;

	public static final ParametriPaginazione TUTTI_GLI_ELEMENTI = new ParametriPaginazione(0, Integer.MAX_VALUE);

	public ParametriPaginazione() {
		super();
	}

	public ParametriPaginazione(int numeroPagina) {
		this(numeroPagina, DEFAULT_ELEMENTI_PER_PAGINA);
	}

	public ParametriPaginazione(int numeroPagina, int elementiPerPagina) {
		this();
		this.setElementiPerPagina(elementiPerPagina);
		this.setNumeroPagina(numeroPagina);
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public int getElementiPerPagina() {
		return elementiPerPagina;
	}

	public void setElementiPerPagina(int elementiPerPagina) {
		this.elementiPerPagina = elementiPerPagina;
	}

	public int getPrimoElemento() {
		return (numeroPagina - 1) * elementiPerPagina;
	}

}
