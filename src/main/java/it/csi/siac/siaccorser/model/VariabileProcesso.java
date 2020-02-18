/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Variabile di processo
 * 
 * @author Spin Servizi per l'Innovazion
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class VariabileProcesso extends Entita {

	private static final long serialVersionUID = 8947259527343912848L;

	// --- nomi delle variabili contenute nel processo
	
	public static final String NOME_VARIABILE_DESCRIZIONE="descrizione";
	public static final String NOME_VARIABILE_DESCRIZIONE_BREVE="descrizioneBreve";

	/** nome della variabile di processo in cui è contenuta la lista delle SAC */
	public static final String NOME_VARIABILE_SAC_PROCESSO = "siacSacProcesso";
	
	/** nome della variabile di istanza in cui è contenuta la lista delle SAC */
	public static final String NOME_VARIABILE_SAC_ATTIVITA = "siacSacAttivita";
	
	/** prefisso del valore della variabile che contiene le SAC */
	public static final String PREFISSO_VALORE_VARIABILE_SAC = "SIAC-SAC-";
	
	/** prefisso del valore della variabile che contiene l'anno di esercizio */
	public static final String PREFISSO_VALORE_VARIABILE_ANNO_ESERCIZIO = "SIAC-ANNO-ESERCIZIO-";
	
	
	/** nome della variabile di processo in cui è contenuta la lista delle SAC */
	public static final String NOME_VARIABILE_ANNO_ESERCIZIO_PROCESSO = "siacAnnoEsercizioProcesso";
	
	/** prefisso del valore della variabile che contiene l'idEnteProprietario */
	public static final String PREFISSO_VALORE_VARIABILE_ENTE_PROPRIETARIO = "SIAC-ENTE-PROPRIETARIO-";
	
	/** nome della variabile di istanza in cui è contenuta l'idEnteProprietario */
	public static final String NOME_VARIABILE_ENTE_PROPRIETARIO_PROCESSO = "siacEnteProprietarioProcesso";
	
	/** nome della variabile di istanza in cui è contenuto l'ente proprietario */
	public static final String NOME_VARIABILE_ENTE_PROPRIETARIO_ATTIVITA = "siacEnteProprietarioAttivita";
	
	/** Valore usato per indicare che l'activity non ha ente proprietario */
	public static final String VALORE_NULL_ENTE_PROPRIETARIO = PREFISSO_VALORE_VARIABILE_ENTE_PROPRIETARIO+"DEFAULT";

	/** nome della variabile di istanza in cui è contenuta la lista delle SAC */
	public static final String NOME_VARIABILE_ANNO_ESERCIZIO_ATTIVITA = "siacAnnoEsercizioAttivita";

	/** Valore usato per indicare che l'activity non ha anno di esercizio specifico */
	public static final String VALORE_NULL_ANNO_ESERCIZIO = PREFISSO_VALORE_VARIABILE_ANNO_ESERCIZIO+"DEFAULT";

	
	private String nome;
	private Object valore;
	private String codifica;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the valore
	 */
	public Object getValore() {
		return valore;
	}

	/**
	 * @param valore
	 *            the valore to set
	 * @return
	 */
	public synchronized void setValore(Object valore) {
		this.valore = valore;
	}

	/**
	 * @return the codifica
	 */
	public String getCodifica() {
		return codifica;
	}

	/**
	 * @param codifica
	 *            the codifica to set
	 */
	public synchronized void setCodifica(String codifica) {
		this.codifica = codifica;
	}

}