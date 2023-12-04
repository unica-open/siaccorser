/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.integration.entity.SiacDGestioneLivello;

public class SiacDGestioneLivelloDaoImplTest extends BaseJunit4TestCase {

	@Autowired
	private SiacDGestioneLivelloDao gestioneLivelloDao;

	
	@Test
	public void findGestioneLivelloByIdEnte() {
		try {
			List<SiacDGestioneLivello> list= gestioneLivelloDao.findGestioneLivelloByIdEnte(1);
			assertTrue("lista vuota", !list.isEmpty());
			System.out.println("numeri di elementi trovati: " + list.size());
			
		} catch (Exception e) {
			log.error("errore in findGestioneLivelloByIdEnte():", e);
			fail();
		}
	}



}
