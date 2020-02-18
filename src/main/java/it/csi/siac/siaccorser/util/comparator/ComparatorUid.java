/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import it.csi.siac.siaccorser.model.Entita;

/**
 * Classe di comparazione tra Entit&agrave; via il campo codice.
 * 
 * @author Alessandro Marchino
 * @version 1.0.0
 *
 * @param <T> la classe da comparare, estendente {@link Entita}
 */
public class ComparatorUid<T extends Entita> implements Comparator<T>, Serializable {
	
	/** Per la serializzazione */
	private static final long serialVersionUID = -7836999775212659631L;

	/** Costruttore vuoto di default */
	public ComparatorUid() {
		super();
	}

	@Override
	public int compare(T entita1, T entita2) {
		int result = 0;
		/* Check dei valori null
		 * Un valore null è considerato inferiore di un qualsiasi valore non-null
		 */
		if(entita1 == null) {
			// Se i valori sono entrambi null, ritorna 0 (identità)
			// In caso contrario, ritorna -1 (null < non-null)
			result = (entita2 == null ? 0 : -1);
		} else if(entita2 == null) {
			// Se entita2 è null, ritorna 1 (non-null > null)
			result = 1;
		} else {
			CompareToBuilder compareToBuilder = new CompareToBuilder();
			compareToBuilder.append(entita1.getUid(), entita2.getUid());
			result = compareToBuilder.toComparison(); 
		}
		return result;
	}
	
}
