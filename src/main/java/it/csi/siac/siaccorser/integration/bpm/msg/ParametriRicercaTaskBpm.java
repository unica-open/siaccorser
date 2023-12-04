/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

import java.util.Map;

import it.csi.siac.siaccorser.model.AzioneConsentita;


/**
 * Descrittore contenente i parametri di ricerca dei task del bpm e
 * dei parametri di paginazione (offset e size)
 *
 */
public class ParametriRicercaTaskBpm {
	
	private TipoRicercaTaskBpm tipoRicerca;
	private AzioneConsentita azione;
	
	/** Se la paginazione è false, i parametri seguenti non hanno significato */
	private boolean paginazione=true;
	
	private int offset=0;
	private int size=10;
	private String annoEsercizio;
	private Integer idEnteProprietario;
	
	public Integer getIdEnteProprietario() {
		return idEnteProprietario;
	}
	public void setIdEnteProprietario(Integer idEnteProprietario) {
		this.idEnteProprietario = idEnteProprietario;
	}
	private Map<String,String> variabiliProcesso;
	
	/**
	 * @return the tipoRicerca
	 */
	public TipoRicercaTaskBpm getTipoRicerca() {
		return tipoRicerca;
	}
	/**
	 * @param tipoRicerca the tipoRicerca to set
	 */
	public void setTipoRicerca(TipoRicercaTaskBpm tipoRicerca) {
		this.tipoRicerca = tipoRicerca;
	}
	/**
	 * @return the azione
	 */
	public AzioneConsentita getAzione() {
		return azione;
	}
	/**
	 * @param azione the azione to set
	 */
	public void setAzione(AzioneConsentita azione) {
		this.azione = azione;
	}
	/**
	 * @return the paginazione
	 */
	public boolean isPaginazione() {
		return paginazione;
	}
	/**
	 * @param paginazione the paginazione to set
	 */
	public void setPaginazione(boolean paginazione) {
		this.paginazione = paginazione;
	}
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @return the variabiliProcesso
	 */
	public Map<String, String> getVariabiliProcesso() {
		return variabiliProcesso;
	}
	/**
	 * @param variabiliProcesso the variabiliProcesso to set
	 */
	public void setVariabiliProcesso(Map<String, String> variabiliProcesso) {
		this.variabiliProcesso = variabiliProcesso;
	}
	public String getAnnoEsercizio() {
		return annoEsercizio;
	}
	public void setAnnoEsercizio(String annoEsercizio) {
		this.annoEsercizio = annoEsercizio;
	}
}
