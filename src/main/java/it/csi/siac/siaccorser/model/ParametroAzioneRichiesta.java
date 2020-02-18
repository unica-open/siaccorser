/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * Parametri aggiuntivi associati ad una richiesta di azione.
 * 
 * @author alagna
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class ParametroAzioneRichiesta extends Entita {

	private static final long serialVersionUID = -814627245761446975L;
	
	public static final String ID_BILANCIO = "it.csi.siac.siaccorser.model.idBilancio";
	public static final String ANNO_BILANCIO = "it.csi.siac.siaccorser.model.annoBilancio";
	public static final String DESCRIZIONE_ANNO_BILANCIO = "it.csi.siac.siaccorser.model.descrizioneAnnoBilancio";
	public static final String CODICE_FASE_ANNO_BILANCIO = "it.csi.siac.siaccorser.model.codiceFaseAnnoBilancio";
	public static final String CODICE_FASE_ANNO_BILANCIO_PRECEDENTE = "it.csi.siac.siaccorser.model.codiceFaseAnnoBilancioPrecedente";
	// SIAC-5022
	public static final String BILANCIO_ANNO_PRECEDENTE = "it.csi.siac.siaccorser.model.bilancioAnnoPrecedente";

	private String nome;
	private String valore;
	private Ente ente;
	
	

	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

}
