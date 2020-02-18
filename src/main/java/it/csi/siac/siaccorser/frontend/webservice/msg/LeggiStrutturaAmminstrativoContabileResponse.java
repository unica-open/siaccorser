/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;



/**
 * Messaggio di risposta per il tree struttura amministrativo contabile
 * 
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class LeggiStrutturaAmminstrativoContabileResponse extends ServiceResponse {
	
	private List<StrutturaAmministrativoContabile> listaStrutturaAmmContabile = new ArrayList<StrutturaAmministrativoContabile>();
	
	public List<StrutturaAmministrativoContabile> getListaStrutturaAmmContabile() {
		return listaStrutturaAmmContabile;
	}

	public void setListaStrutturaAmmContabile(
			List<StrutturaAmministrativoContabile> listaStrutturaAmmContabile) {
		this.listaStrutturaAmmContabile = listaStrutturaAmmContabile;
	}
	
	//SIAC-6884: implementata ricerca SAC by Id, poi diventata superflua
	//private StrutturaAmministrativoContabile strutturaAmmContabile = new StrutturaAmministrativoContabile();
	
	/*public void setStrutturaAmmContabile(StrutturaAmministrativoContabile strutturaAmmContabile) {
		this.strutturaAmmContabile = strutturaAmmContabile;
	}
	
	public StrutturaAmministrativoContabile getStrutturaAmmContabile() {
		return strutturaAmmContabile;
	}*/
}