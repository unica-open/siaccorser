/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.business.service.GetAccountsService;
import it.csi.siac.siaccorser.business.service.GetRichiedenteService;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccounts;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccountsResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetRichiedente;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetRichiedenteResponse;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.Richiedente;

public class GetAccountsServiceTest extends BaseJunit4TestCase {

	
	@Autowired
	private GetAccountsService getAccountsService;
	@Autowired
	private GetRichiedenteService getRichiedenteService;

	
	@Test
	public void testFindOperatoreByCodiceFiscale() {
		try {
			// famigliTreeId=5 � la struttura amministrativo contabile
			GetAccounts req = new GetAccounts();
			
			req.setRichiedente(getRichiedenteTest());
			
			
			GetAccountsResponse res = getAccountsService.executeService(req);
			
			assertTrue("trovata lista vuota", !res.getAccounts().isEmpty());

			

		} catch (Exception e) {
			log.error("errore in testFindOperatoreByCodiceFiscale():", e);
			fail();
		}
	}
	
	
	public Richiedente getRichiedenteTest(){
		Richiedente richiedente = new Richiedente();
		Operatore operatore = new Operatore();
		operatore.setCodiceFiscale("AAAAAA00A11E000M"); // demo24
		richiedente.setOperatore(operatore);
		return richiedente;
	}
	
	@Test
	public void testRichiedente() {
		GetRichiedente getRichiedente = new GetRichiedente();
		getRichiedente.setCodiceAccount(String.format("%s-%s", "GAMOP", "REGP"));

		GetRichiedenteResponse getrRichiedenteResponse = getRichiedenteService.executeService(getRichiedente);

	}
	
	

}
