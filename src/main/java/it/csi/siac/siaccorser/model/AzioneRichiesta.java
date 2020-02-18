/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.errore.ErroreCore;

/**
 * Azione che l'Operatore deve eseguire a seguito di una selezione dal Cruscotto
 * o da una webapp.
 * 
 * @author alagna
 * @version $Id: $
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class AzioneRichiesta extends Entita {
	private static final long serialVersionUID = -755368917049908560L;
	/** Id univoco dell'istanza di attività */
	private String idAttivita;
	private Boolean daCruscotto = false;
	private Date data;
	private Azione azione;
	private Account account;
	private List<ParametroAzioneRichiesta> parametri = new ArrayList<ParametroAzioneRichiesta>();
	private Map<String, ParametroAzioneRichiesta> mapParametri;
	private List<VariabileProcesso> variabiliProcesso = new ArrayList<VariabileProcesso>();
	private Map<String, VariabileProcesso> mapVariabiliProcesso;
	
	private boolean daVariazioneDecentrata;
	
	public AzioneRichiesta() {
		super();
	}
	
	public AzioneRichiesta(Azione azione) {
		this();
		this.azione = azione;
	}

	@Override
	public List<Errore> valida() {
		List<Errore> errori = new ArrayList<Errore>();

		if (azione == null)
			errori.add(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("azione"));
		else
			errori.addAll(azione.valida());
		return errori;
	}

	/**
	 * Imposta a null i valori delle variabili di processo che impedirebbero il
	 * marshalling dell'oggetto AzioneRichiesta.
	 */
	public void cleanUnmarshallableVariabileProcessoValue() {
		if (variabiliProcesso != null) {
			for (VariabileProcesso variabileProcesso : variabiliProcesso) {
				if (variabileProcesso.getValore() instanceof AbstractMap) {
					variabileProcesso.setValore(null);
				}
			}
		}
	}

	/**
	 * @return the idAttivita
	 */
	public String getIdAttivita() {
		return idAttivita;
	}

	/**
	 * @param idAttivita
	 *            the idAttivita to set
	 */
	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}

	public boolean isDaCruscotto() {
		return daCruscotto;
	}

	public void setDaCruscotto(boolean daCruscotto) {
		this.daCruscotto = daCruscotto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Azione getAzione() {
		return azione;
	}

	public void setAzione(Azione azione) {
		this.azione = azione;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getId() {
		return "" + getUid();
	}

	/**
	 * @return the parametri
	 */
	public List<ParametroAzioneRichiesta> getParametri() {
		return parametri;
	}

	/**
	 * @param parametri
	 *            the parametri to set
	 */
	public void setParametri(List<ParametroAzioneRichiesta> parametri) {
		this.parametri = parametri;
	}

	/**
	 * Restituisce il parametro dal nome indicato
	 * 
	 * @param nome
	 *            il nome del parametro
	 */
	public ParametroAzioneRichiesta findParametro(String nome) {
		if (mapParametri == null) {
			mapParametri = new HashMap<String, ParametroAzioneRichiesta>();
			for (ParametroAzioneRichiesta parametro : parametri) {
				mapParametri.put(parametro.getNome(), parametro);
			}
		}
		return mapParametri.get(nome);
	}

	/**
	 * @return the variabiliProcesso
	 */
	public List<VariabileProcesso> getVariabiliProcesso() {
		return variabiliProcesso;
	}

	/**
	 * @param variabiliProcesso
	 *            the variabiliProcesso to set
	 */
	public void setVariabiliProcesso(List<VariabileProcesso> variabiliProcesso) {
		this.variabiliProcesso = variabiliProcesso;
	}

	/**
	 * Restituisce la variabile di processo dal nome indicato
	 * 
	 * @param nome
	 *            il nome della variabile di processo
	 */
	public VariabileProcesso findVariabileProcesso(String nome) {
		if (mapVariabiliProcesso == null) {
			mapVariabiliProcesso = new HashMap<String, VariabileProcesso>();
			for (VariabileProcesso variabileProcesso : variabiliProcesso) {
				mapVariabiliProcesso.put(variabileProcesso.getNome(), variabileProcesso);
			}
		}
		return mapVariabiliProcesso.get(nome);
	}

	/**
	 * @return the daVariazioneDecentrata
	 */
	public boolean isDaVariazioneDecentrata() {
		return daVariazioneDecentrata;
	}

	/**
	 * @param daVariazioneDecentrata the daVariazioneDecentrata to set
	 */
	public void setDaVariazioneDecentrata(boolean daVariazioneDecentrata) {
		this.daVariazioneDecentrata = daVariazioneDecentrata;
	}

	

}
