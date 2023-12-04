/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.integration.entity.SiacTClass;

public class SiacTClassDaoImplTest extends BaseJunit4TestCase {

	@Autowired
	private SiacTClassDao codificaDao;


	@Test
	public void testFindById() {
		try {
			SiacTClass c = codificaDao.findById(10);
			assertNull("SiacTClass : ", c);
		} catch (Exception e) {
			log.error("errore in testFindById():", e);
			fail();
		}
	}

	@Test
	public void testFindCodifiche() {
		try {
			List<SiacTClass> lista = codificaDao.findCodifiche(1, 1);
			assertNull("lista trovata: ", lista.size());
		} catch (Exception e) {
			log.error("errore in testFindCodifiche():", e);
			fail();
		}
	}

	@Test
	public void testFindCodificheByIdPadre() {
		try {
			List<SiacTClass> list = codificaDao.findTreeByCodiceFamigliaAndParentId(2020,
					2, 2);
			assertTrue("lista vuota", !list.isEmpty());
		} catch (Exception e) {
			log.error("errore in testFindCodificheByIdPadre():", e);
			fail();
		}
	}

	@Transactional
	@Test
	public void testFindTreeByCodiceFamiglia() {
		try {
			// famigliTreeId=5 � la struttura amministrativo contabile
			List<SiacTClass> list = codificaDao.findTreeByCodiceFamiglia(2014, 1);
			//assertTrue("trovata lista vuota", !list.isEmpty());

			for (SiacTClass codificaDto : list) {
				System.out.println(codificaDto.getUid());
				System.out.println(codificaDto.getDescrizione());
				if (null != codificaDto.getCodificaAttributo()) {
					System.out.println(codificaDto.getCodificaAttributo().getCodice());
				} 
				for (SiacTClass figlio : codificaDto.getFigli()) {
					System.out.println("  " + figlio.getUid());
					System.out.println("  " + figlio.getDescrizione());
					if (null != figlio.getCodificaAttributo()) {
						System.out.println("  " +figlio.getCodificaAttributo().getCodice());
					} 
				}
			}

		} catch (Exception e) {
			log.error("errore in findTreeByCodiceFamiglia():", e);
			fail();
		}
	}

}
