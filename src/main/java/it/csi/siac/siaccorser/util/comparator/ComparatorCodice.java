/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import it.csi.siac.siaccorser.model.Codifica;

/**
 * Classe di comparazione tra Codifiche via il campo codice.
 * 
 * @author Alessandro Marchino
 * @version 1.0.0
 *
 * @param <T> la classe da comparare, estendente {@link Codifica}
 */
public class ComparatorCodice<T extends Codifica> implements Comparator<T>, Serializable {

	/** Per la serializzazione */
	private static final long serialVersionUID = -7989640425803703285L;

	/** Costruttore vuoto di default */
	public ComparatorCodice() {
		super();
	}
	
	@Override
	public int compare(T codifica1, T codifica2) {
		int result = 0;
		/* Check dei valori null
		 * Un valore null è considerato inferiore di un qualsiasi valore non-null
		 */
		if(codifica1 == null) {
			// Se i valori sono entrambi null, ritorna 0 (identità)
			// In caso contrario, ritorna -1 (null < non-null)
			result = (codifica2 == null ? 0 : -1);
		} else if(codifica2 == null) {
			// Se entita2 è null, ritorna 1 (non-null > null)
			result = 1;
		} else {
			CompareToBuilder compareToBuilder = new CompareToBuilder();
			compareToBuilder.append(codifica1.getCodice(), codifica2.getCodice());
			result = compareToBuilder.toComparison(); 
		}
		return result;
	}
	
}
