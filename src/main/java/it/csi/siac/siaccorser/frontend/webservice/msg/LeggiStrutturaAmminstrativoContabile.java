/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di richiesta per il tree struttura amministrativo contabile
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class LeggiStrutturaAmminstrativoContabile extends ServiceRequest {

	private int idEnteProprietario;
	private int anno;
	private int idFamigliaTree;
	//SIAC-6884: implementata ricerca SAC by Id, poi diventata superflua
	//private int sacUid;
	/*
	public int getSacUid() {
		return sacUid;
	}

	public void setSacUid(int sacUid) {
		this.sacUid = sacUid;
	}*/

	public int getIdFamigliaTree() {
		return idFamigliaTree;
	}

	public void setIdFamigliaTree(int idFamigliaTree) {
		this.idFamigliaTree = idFamigliaTree;
	}

	public int getIdEnteProprietario() {
		return idEnteProprietario;
	}

	public void setIdEnteProprietario(int idEnteProprietario) {
		this.idEnteProprietario = idEnteProprietario;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

}
