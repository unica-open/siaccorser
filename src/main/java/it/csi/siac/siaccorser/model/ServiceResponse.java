/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.errore.ErroreCore;

/**
 * Base di tutte le risposte
 * 
 * @author alagna
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public abstract class ServiceResponse {

	private Richiedente richiedente;

	private Date dataOra;

	/** Il valore di default del risultato e' SUCCESSO */
	private Esito esito = Esito.SUCCESSO;

	/**
	 * Lista degli errori. Popolata solo se il risultato e' FALLIMENTO
	 */
	private List<Errore> errori = new ArrayList<Errore>();

	/**
	 * Nel caso del risultato di una ricerca: - se derivante dall'applicazione
	 * del pattern di paginazione, indica la cardinalit&agrave; complessiva della
	 * risposta (senza l'applicazione del pattern). - altrimenti indica la
	 * cardinalit&agrave; dell'entit&agrave; primaria restituita.
	 * 
	 * Nel caso di un risultato non di ricerca varr&agrave; 1.
	 */
	private int cardinalitaComplessiva = 1;

	/**
	 * Verifica se si tratta di un fallimento
	 * 
	 * @return
	 */
	public boolean isFallimento() {
		return esito.equals(Esito.FALLIMENTO);
	}

	public Esito getEsito() {
		return esito;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}

	public List<Errore> getErrori() {
		return errori;
	}

	public void setErrori(List<Errore> errori) {
		this.errori = errori;
	}

	public void addErrore(Errore errore) {
		errori.add(errore);
	}

	public void addErrori(List<Errore> errori) {
		this.errori.addAll(errori);
	}

	public void addErroreDiSistema(Throwable e) {
		errori.add(ErroreCore.ERRORE_DI_SISTEMA.getErrore(e.getMessage()));
	}

	public boolean verificatoErroreDiSistema() {
		return verificatoErrore(ErroreCore.ERRORE_DI_SISTEMA.getCodice());
	}

	public boolean verificatoErrore(ErroreCore errore) {
		return verificatoErrore(errore.getCodice());
	}

	public boolean verificatoErrore(String codiceErrore) {
		for (Errore errore : this.errori) {
			if (codiceErrore.equals(errore.getCodice())) {
				return true;
			}
		}
		return false;
	}

	public boolean verificatoErrore(String... codiciErrore) {
		for (String codiceErrore : codiciErrore) {
			if (verificatoErrore(codiceErrore)) {
				return true;
			}
		}
		return false;
	}

	public String getDescrizioneErrori() {
		StringBuilder sb = new StringBuilder("");
		for (Errore errore : errori) {
			sb.append(errore.getTesto()).append(", ");
		}
		String result = sb.toString();
		if (result.length() > 2) {
			result = result.substring(0, result.length() - 2);
		}
		return result;
	}

	public boolean hasErrori() {
		return !errori.isEmpty();
	}

	public Richiedente getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(Richiedente richiedente) {
		this.richiedente = richiedente;
	}

	public void setCardinalitaComplessiva(int cardinalitaComplessiva) {
		this.cardinalitaComplessiva = cardinalitaComplessiva;
	}

	public int getCardinalitaComplessiva() {
		return cardinalitaComplessiva;
	}

	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

	/** data e ora della richiesta **/
	@XmlSchemaType(name = "dateTime")
	public Date getDataOra() {
		return dataOra;
	}

	// @Override
	// public String toString() {
	// return ToStringBuilder.reflectionToString(this,
	// ToStringStyle.NO_FIELD_NAMES_STYLE);
	// }

}
