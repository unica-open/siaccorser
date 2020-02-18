/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.util.comparator;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import it.csi.siac.siaccorser.model.ClassificatoreGerarchico;
import it.csi.siac.siaccorser.model.Codifica;
import it.csi.siac.siaccorser.model.Entita;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;

/**
 * Classe di utilit&agrave; per le comparazioni.
 * 
 * @author Alessandro Marchino
 * @version 1.0.0
 * 
 */
public final class ComparatorUtils {

	private static final Logger LOG = Logger.getLogger(ComparatorUtils.class);

	/** Non permettere l'instanziazione */
	private ComparatorUtils() {
		super();
	}

	/**
	 * Ordina una lista a partire dal codice.
	 * 
	 * @param list
	 *            la lista da ordinare
	 */
	public static <T extends Codifica> void sortByCodice(List<T> list) {
		if (list != null) {
			Collections.sort(list, new ComparatorCodice<T>());
		} else {
			LOG.debug("La lista fornita in input e' null");
		}
	}

	/**
	 * Ordina una lista in profondit&agrave; a partire dal codice.
	 * 
	 * @param list
	 *            la lista da ordinare
	 * 
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public static <T extends Codifica> void sortDeepByCodice(
			List<StrutturaAmministrativoContabile> list) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		if (list != null && !list.isEmpty())
			sortDeepByCodice(list, "getSubStrutture");
		else
			LOG.debug("La lista fornita in input e' null o vuota");

	}

	/**
	 * Ordina una lista in profondit&agrave; a partire dal codice.
	 * 
	 * @param list
	 *            la lista da ordinare
	 * @param il
	 *            nome del metodo da invocare
	 * 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private static <T extends Codifica> void sortDeepByCodice(List<T> list, String methodName)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (list != null) {
			Collections.sort(list, new ComparatorCodice<T>());
			for (T t : list) {
				@SuppressWarnings("unchecked")
				List<T> subList = (List<T>) t.getClass().getMethod(methodName).invoke(t);
				if (subList != null && !subList.isEmpty()) {
					sortDeepByCodice(subList, methodName);
				}
			}
		}
	}

	/**
	 * Ordina una lista a partire dall'ordine.
	 * 
	 * @param list
	 *            la lista da ordinare
	 */
	public static <T extends ClassificatoreGerarchico> void sortByOrdine(List<T> list) {
		if (list != null) {
			Collections.sort(list, new ComparatorOrdine<T>());
		} else {
			LOG.debug("La lista fornita in input e' null");
		}
	}

	/**
	 * Ordina una lista in profondit&agrave; a partire dal codice.
	 * 
	 * @param list
	 *            la lista da ordinare
	 * 
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public static <T extends ClassificatoreGerarchico> void sortDeepByOrdine(List<T> list)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (list != null && !list.isEmpty()) {
			if (list.get(0) instanceof StrutturaAmministrativoContabile) {
				sortDeepByOrdine(list, "getSubStrutture");
			} else {
				throw new UnsupportedOperationException("Il metodo non è attualmente implementato");
			}
		} else {
			LOG.debug("La lista fornita in input e' null o vuota");
		}
	}

	/**
	 * Ordina una lista in profondit&agrave; a partire dal codice.
	 * 
	 * @param list
	 *            la lista da ordinare
	 * @param methodName
	 *            il nome del metodo da invocare
	 * 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private static <T extends ClassificatoreGerarchico> void sortDeepByOrdine(List<T> list,
			String methodName) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (list != null) {
			Collections.sort(list, new ComparatorOrdine<T>());
			for (T t : list) {
				@SuppressWarnings("unchecked")
				List<T> subList = (List<T>) t.getClass().getMethod(methodName).invoke(t);
				if (subList != null && !subList.isEmpty()) {
					sortDeepByOrdine(subList, methodName);
				}
			}
		}
	}

	/**
	 * Ordina una lista a partire dall'uid.
	 * 
	 * @param list
	 *            la lista da ordinare
	 */
	public static <T extends Entita> void sortByUid(List<T> list) {
		if (list != null) {
			Collections.sort(list, new ComparatorUid<T>());
		} else {
			LOG.debug("La lista fornita in input e' null");
		}
	}

	/**
	 * Effettua una ricerca all'interno della lista tramite l'utilizzo del
	 * comparatore. La complessit&agrave; computazionale &eacute; pari a
	 * <code>O(n)</code>.
	 * 
	 * @param list
	 *            la lista in cui cercare il risultato
	 * @param entita
	 *            l'entit&agrave; da ricercare nella lista
	 * @param comparator
	 *            il comparatore per effettuare il confronto
	 * @param <T>
	 *            la classe estendente {@link Entita} dell'oggetto da ricercare
	 *            nella lista
	 * 
	 * @return l'entit&agrave; corrispondente a partire dalla lista, se
	 *         presente; l'entit&agrave; fornita come parametro in caso tale
	 *         elemento non sia reperito
	 */
	private static <T extends Entita> T search(List<T> list, T entita, Comparator<T> comparator) {
		if (entita == null || entita.getUid() == 0) {
			LOG.debug("Oggetto da cercare non valido");

			return null;
		}

		T result = entita;
		boolean found = false;
		if (list != null) {
			/* Complesità computazionale: O(n) */
			for (int i = 0; i < list.size() && !found; i++) {
				if (comparator.compare(entita, list.get(i)) == 0) {
					try {
						LOG.debug("Oggetto trovato in posizione " + i);
						found = true;
						result = list.get(i);
					} catch (ClassCastException e) {
						LOG.debug("Errore nel cast dell'oggetto");
						result = entita;
					}
				}
			}
		}
		LOG.debug("Oggetto trovato:" + result);
		return result;
	}

	/**
	 * Effettua una ricerca all'interno della lista a partire dall'uid.
	 * 
	 * @param list
	 *            la lista in cui cercare il risultato
	 * @param entita
	 *            l'entit&agrave; da ricercare nella lista
	 * @param <T>
	 *            la classe estendente {@link Entita} dell'oggetto da ricercare
	 *            nella lista
	 * 
	 * @return l'entit&agrave; corrispondente a partire dalla lista, se
	 *         presente; l'entit&agrave; fornita come parametro in caso tale
	 *         elemento non sia reperito
	 */
	public static <T extends Entita> T searchByUid(List<T> list, T entita) {
		return search(list, entita, new ComparatorUid<T>());
	}

	/**
	 * Effettua una ricerca all'interno della lista a partire dall'uid. Questo
	 * metodo cerca anche nei figli della lista fornita.
	 * 
	 * @param list
	 *            la lista in cui cercare il risultato
	 * @param entita
	 *            l'entit&agrave; da ricercare nella lista
	 * 
	 * @return l'entit&agrave; corrispondente a partire dalla lista, se
	 *         presente; l'entit&agrave; fornita come parametro in caso tale
	 *         elemento non sia reperito
	 */
	public static StrutturaAmministrativoContabile searchByUidWithChildren(
			List<StrutturaAmministrativoContabile> list, StrutturaAmministrativoContabile entita) {
		StrutturaAmministrativoContabile strutturaAmministrativoContabile = null;
		try {
			strutturaAmministrativoContabile = searchWithChildren(list, entita,
					new ComparatorUid<StrutturaAmministrativoContabile>(), true, "getSubStrutture");
		} catch (Exception e) {
			LOG.error("Errore: " + e.getMessage(), e);
		}
		return strutturaAmministrativoContabile;
	}

	/**
	 * Effettua una ricerca all'interno della lista di Elementi del Piano dei
	 * Conti tramite l'utilizzo del comparatore. Tale metodo effettua una
	 * ricerca anche all'interno delle sottoliste degli elementi presenti nella
	 * lista.
	 * 
	 * @param list
	 *            la lista in cui cercare il risultato
	 * @param entita
	 *            l'entit&agrave; da ricercare nella lista
	 * @param comparator
	 *            il comparatore per effettuare il confronto
	 * @param first
	 *            definisce la prima iterazione
	 * @param reflectionMethod
	 *            il nome del metodo da utilizzare nella Reflection
	 * @param <T>
	 *            la classe estendente {@link ClassificatoreGerarchico}
	 * 
	 * @return l'entit&agrave; corrispondente a partire dalla lista, se
	 *         presente; l'entit&agrave; fornita come parametro in caso tale
	 *         elemento non sia reperito
	 * 
	 * @throws NoSuchMethodException
	 *             nel caso in cui non esista il metodo considerato
	 * @throws InvocationTargetException
	 *             nel caso in cui non si possa invocare il metodo sul tagret
	 *             indicato
	 * @throws IllegalAccessException
	 *             nel caso in cui vi sia un accesso a un campo privato
	 * @throws SecurityException
	 *             nel caso di eccezione di sicurezza
	 * @throws IllegalArgumentException
	 *             nel caso in cui l'argomento della chiamata non sia legale
	 */
	@SuppressWarnings("unchecked")
	private static <T extends ClassificatoreGerarchico> T searchWithChildren(List<T> list,
			T entita, Comparator<T> comparator, boolean first, String reflectionMethod)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if (entita == null || entita.getUid() == 0) {
			LOG.debug("Oggetto da cercare non valido");
			return null;
		}

		T result = null;
		boolean found = false;
		if (list != null) {
			for (int i = 0; i < list.size() && !found; i++) {
				if (comparator.compare(entita, list.get(i)) == 0) {
					LOG.debug("Oggetto trovato in posizione " + i);
					found = true;
					result = list.get(i);
				} else {
					// Ricerca ricorsivamente sulla sottolista
					result = searchWithChildren(
							(List<T>) list.get(i).getClass().getMethod(reflectionMethod)
									.invoke(list.get(i)), entita, comparator, false,
							reflectionMethod);
					if (result != null) {
						found = true;
					}
				}
			}
		}

		if (first) {
			if (!found) {
				result = entita;
			}
			LOG.debug("Oggetto trovato (" + found + "): " + result);
		}
		return result;
	}

}
