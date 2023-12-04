/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.util;



import java.io.Serializable;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.GruppoAttivitaPendenti;
import it.csi.siac.siaccorser.model.GruppoAzioni;

public final class ComparatorUtil{
	
	/** Costruttore privato per non avere l'instanziazione */
	private ComparatorUtil() {
		// Impedisce l'instanziazione
	}
	
	
	public static Comparator<GruppoAzioni> compare() {
		return new NumeroTitoloComparator();
	}

	public static Comparator<Azione> compareAzioni(){
		Comparator<Azione> result = new Comparator<Azione>() {
			@Override
			public int compare(Azione o1, Azione o2) {
				return o1.getTitolo().compareToIgnoreCase(o2.getTitolo());
			}
		};
		return result;
	}
	 
	public static Comparator<GruppoAttivitaPendenti> compareAttivitaPendenti(){
		Comparator<GruppoAttivitaPendenti> result = new Comparator<GruppoAttivitaPendenti>() {
			@Override
			public int compare(GruppoAttivitaPendenti o1, GruppoAttivitaPendenti o2) {
				return o1.getAzione().getTitolo().compareToIgnoreCase(o2.getAzione().getTitolo());
			}
		};
		return result;
	}
	
	private static class NumeroTitoloComparator implements Comparator<GruppoAzioni>, Serializable {
		/** Per la serializzazione */
		private static final long serialVersionUID = 4373978533183772245L;
		private static final Pattern NUMERO_TITOLO_PATTERN = Pattern.compile("^(\\d+)\\D*");
		
		NumeroTitoloComparator() {
			// Evita costruttore sintetico
		}
		
		@Override
		public int compare(GruppoAzioni o1, GruppoAzioni o2) {
			Integer n1 = getNumeroTitolo(o1);
			
			if (n1 == null)
				return -1;
			
			Integer n2 = getNumeroTitolo(o2);
			
			if (n2 == null)
				return 1;

			return n1.compareTo(n2);
		}

		private Integer getNumeroTitolo(GruppoAzioni o) {
			String titolo = o.getTitolo();
			
			if (titolo != null) {
				Matcher m = NUMERO_TITOLO_PATTERN.matcher(titolo);
				
				if (m.find())
					return Integer.parseInt(m.group(1));
			}
			
			return null; 
		}
	}
}
	
	


