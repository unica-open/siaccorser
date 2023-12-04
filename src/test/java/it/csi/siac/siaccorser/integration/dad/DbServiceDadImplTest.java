/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dad;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.model.Account;

public class DbServiceDadImplTest extends BaseJunit4TestCase {

	@Autowired
	private CoreDad coreDad;


	@Test
	public void testFindAccountById() {
		try {
			Account account = coreDad.findAccountById(8, 2014);
			
			assertNull("Account : ", account);
		} catch (Exception e) {
			log.error("errore in testFindAccountById():", e);
			fail();
		}
	}

}
