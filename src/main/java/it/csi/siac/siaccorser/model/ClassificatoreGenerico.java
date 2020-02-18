/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

/**
 * ClassificatoreGenerico: raggruppa tutte le codifiche (annualizzate e non) che
 * non hanno un livello
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class ClassificatoreGenerico extends Codifica {

	private static final long serialVersionUID = -3124439559937992887L;

	private TipoClassificatore tipoClassificatore;

	public ClassificatoreGenerico() {
		super();
	}

	public ClassificatoreGenerico(String codice, String descrizione) {
		super(codice, descrizione);
	}

	public ClassificatoreGenerico(TipoClassificatore tipo, String codice) {
		this.tipoClassificatore = tipo;
		super.setCodice(codice);
	}

	public TipoClassificatore getTipoClassificatore() {
		return tipoClassificatore;
	}

	public void setTipoClassificatore(TipoClassificatore tipoClassificatore) {
		this.tipoClassificatore = tipoClassificatore;
	}
	
	
	public Date getDataFineValiditaClassifGenerico() {
	 
		return getDataFineValidita();
	}
	
	public void setDataFineValiditaClassifGenerico(Date dataFineValidita) {
		setDataFineValidita(dataFineValidita);
	}

}
