/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Descrittore di un task attivo di un processo associato 
 * ad una entita.
 */ 
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class TaskEntita implements Serializable{

	private static final long serialVersionUID = -3912574932973080963L;

	String nome;
	String id;
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
