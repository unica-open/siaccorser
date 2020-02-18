/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * Relazione tra Gruppo, Ruolo e SAC
 * 
 * @author alagna, AR
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class RuoloGruppo extends Entita {

	private static final long serialVersionUID = 6616924245959084398L;
	private Gruppo gruppo;
	private Ruolo ruolo;
//	private StrutturaAmministrativoContabile struttura;
//	
//	private CassaEconomaleCruscotto ruoloCassaEconomale;

//	/**
//	 * @return the ruoloCassaEconomale
//	 */
//	public CassaEconomaleCruscotto getRuoloCassaEconomale() {
//		return ruoloCassaEconomale;
//	}
//
//	/**
//	 * @param ruoloCassaEconomale the ruoloCassaEconomale to set
//	 */
//	public void setRuoloCassaEconomale(CassaEconomaleCruscotto ruoloCassaEconomale) {
//		this.ruoloCassaEconomale = ruoloCassaEconomale;
//	}

	public Gruppo getGruppo() {
		return gruppo;
	}

	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

//	public StrutturaAmministrativoContabile getStruttura() {
//		return struttura;
//	}
//
//	public void setStruttura(StrutturaAmministrativoContabile struttura) {
//		this.struttura = struttura;
//	}

}
