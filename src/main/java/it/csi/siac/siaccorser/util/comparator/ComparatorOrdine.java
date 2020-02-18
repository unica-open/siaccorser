/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import it.csi.siac.siaccorser.model.ClassificatoreGerarchico;

/**
 * Classe di comparazione tra Classificazioni Gerarchiche via il campo ordine.
 * 
 * @author Alessandro Marchino
 * @version 1.0.0
 *
 * @param <T> la classe da comparare, estendente {@link ClassificatoreGerarchico}
 */
public class ComparatorOrdine<T extends ClassificatoreGerarchico> implements Comparator<T>, Serializable {

	/** Per la serializzazione */
	private static final long serialVersionUID = 6901341292042716902L;

	/** Costruttore vuoto di default */
	public ComparatorOrdine() {
		super();
	}
	
	@Override
	public int compare(T classificatoreGerarchico1, T classificatoreGerarchico2) {
		int result = 0;
		/* Check dei valori null
		 * Un valore null è considerato inferiore di un qualsiasi valore non-null
		 */
		if(classificatoreGerarchico1 == null) {
			// Se i valori sono entrambi null, ritorna 0 (identità)
			// In caso contrario, ritorna -1 (null < non-null)
			result = (classificatoreGerarchico2 == null ? 0 : -1);
		} else if(classificatoreGerarchico2 == null) {
			// Se entita2 è null, ritorna 1 (non-null > null)
			result = 1;
		} else {
			CompareToBuilder compareToBuilder = new CompareToBuilder();
			compareToBuilder.append(classificatoreGerarchico1.getOrdine(), classificatoreGerarchico2.getOrdine());
			result = compareToBuilder.toComparison(); 
		}
		return result;
	}

}
