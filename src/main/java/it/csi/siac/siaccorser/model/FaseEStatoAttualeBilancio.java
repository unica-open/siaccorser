/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * FaseEStatoAttualeBilancio
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class FaseEStatoAttualeBilancio extends EntitaStoricizzata {

	private static final long serialVersionUID = -2157480192035205000L;

	private StatoBilancio statoBilancio;
	private FaseBilancio faseBilancio;

	@XmlType(namespace = CORDataDictionary.NAMESPACE)
	public enum StatoBilancio {
		PLURIENNALE, CARICAMENTO_BILANCIO_DI_PREVISIONE, BILANCIO_DI_PREVISIONE_STAMPATO_PER_GIUNTA, BILANCIO_DI_PREVISIONE_STAMPATO_PER_CONSIGLIO, ESERCIZIO_PROVVISORIO_CON_CARICAMENTO_BILANCIO_DI_PREVISIONE, ESERCIZIO_PROVVISORIO_CON_BILANCIO_DI_PREVISIONE_STAMPATO_PER_GIUNTA, BILANCIO_DI_PREVISIONE_APPROVATO_NON_ESECUTIVO, BILANCIO_DI_PREVISIONE_TRASFERITO_IN_GESTIONE, CARICAMENTO_ASSESTAMENTO_DI_BILANCIO, ASSESTAMENTO_DEL_BILANCIO_STAMPATO_PER_GIUNTA, ASSESTAMENTO_APPROVATO_NON_ESECUTIVO, ASSESTAMENTO_DI_BILANCIO_TRASFERITO_IN_GESTIONE, PREDISPOSIZIONE_CONSUNTIVO, ESERCIZIO_CHIUSO
	}

	@XmlType(namespace = CORDataDictionary.NAMESPACE)
	public enum FaseBilancio {
		PLURIENNALE, PREVISIONE, ESERCIZIO_PROVVISORIO, GESTIONE, ASSESTAMENTO, PREDISPOSIZIONE_CONSUNTIVO, CHIUSO
	}

	public StatoBilancio getStatoBilancio() {
		return statoBilancio;
	}

	public void setStatoBilancio(StatoBilancio statoBilancio) {
		this.statoBilancio = statoBilancio;
	}

	public FaseBilancio getFaseBilancio() {
		return faseBilancio;
	}

	public void setFaseBilancio(FaseBilancio faseBilancio) {
		this.faseBilancio = faseBilancio;

	}

}
