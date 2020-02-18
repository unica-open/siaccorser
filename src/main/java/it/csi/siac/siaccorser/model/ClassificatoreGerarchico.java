/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

/**
 * Classificatore: raggruppa tutte le codifiche che prevedono un livello , vedi
 * Missione, Programma
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class ClassificatoreGerarchico extends Codifica {

	private static final long serialVersionUID = -5519622555747300476L;

	private TipoClassificatore tipoClassificatore;
	private int livello;
	private String ordine;

	public int getLivello() {
		return livello;
	}

	public void setLivello(int livello) {
		this.livello = livello;
	}

	public String getOrdine() {
		return ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public ClassificatoreGerarchico() {
		super();
	}

	public ClassificatoreGerarchico(ClassificatoreGerarchico cg) {
		super(cg);
		this.tipoClassificatore = cg.tipoClassificatore;
		this.livello = cg.livello;
		this.ordine = cg.ordine;
	}

	public ClassificatoreGerarchico(String codice, String descrizione) {
		super(codice, descrizione);
	}

	public TipoClassificatore getTipoClassificatore() {
		return tipoClassificatore;
	}

	public void setTipoClassificatore(TipoClassificatore tipoClassificatore) {
		this.tipoClassificatore = tipoClassificatore;
	}
	
	public Date getDataFineValiditaClassifGerarchico() {
		return getDataFineValidita();
	}
	
	public void setDataFineValiditaClassifGerarchico(Date dataFineValidita) {
		setDataFineValidita(dataFineValidita);
	}

}
