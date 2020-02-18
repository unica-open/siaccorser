/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 * Descrittore dell'unit&agrave; elementare di una Struttura Amministrativa.
 * 
 * @author alagna
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class StrutturaAmministrativoContabile extends ClassificatoreGerarchico {

	private static final long serialVersionUID = 8686123555771865411L;

	private String assessorato;

	private List<StrutturaAmministrativoContabile> subStrutture = new ArrayList<StrutturaAmministrativoContabile>();

	public StrutturaAmministrativoContabile(String codice, String descrizione) {
		super(codice, descrizione);
	}

	public StrutturaAmministrativoContabile() {
		super();
	}

	public String getAssessorato() {
		return assessorato;
	}

	public void setAssessorato(String assessorato) {
		this.assessorato = assessorato;
	}

	public List<StrutturaAmministrativoContabile> getSubStrutture() {
		return subStrutture;
	}

	public void setSubStrutture(
			List<StrutturaAmministrativoContabile> subStrutture) {
		this.subStrutture = subStrutture;
	}
	
	public Date getDataFineValiditaSac() {
		return getDataFineValidita();
	}
	
	public void setDataFineValiditaSac(Date dataFineValidita) {
		setDataFineValidita(dataFineValidita);
	}

}
