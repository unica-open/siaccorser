/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * Relazione tra Account, Ruolo e SAC
 * 
 * @author alagna
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class RuoloAccount extends Entita {

	private static final long serialVersionUID = -8756798005677257672L;
	private Account account;
	private Ruolo ruolo;
//	private StrutturaAmministrativoContabile struttura;
//	private CassaEconomaleCruscotto ruoloCassaEconomale;
	
	
	

	/**
	 * @return the ruoloCassaEconomale
	 */
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
