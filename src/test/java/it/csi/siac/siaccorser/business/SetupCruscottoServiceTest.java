/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.business.service.SetupCruscottoService;
import it.csi.siac.siaccorser.frontend.webservice.msg.SetupCruscotto;
import it.csi.siac.siaccorser.frontend.webservice.msg.SetupCruscottoResponse;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.Richiedente;

public class SetupCruscottoServiceTest extends BaseJunit4TestCase {

	
	@Autowired
	private SetupCruscottoService setupCruscottoService;

	
	@Test
	public void testSetupCruscottoService() {
		SetupCruscotto req = new SetupCruscotto();
		req.setRichiedente(new Richiedente());
		req.getRichiedente().setAccount(create(Account.class, 52));
		req.getRichiedente().getAccount().setEnte(create(Ente.class, 2));
		req.getRichiedente().setOperatore(new Operatore());
		req.getRichiedente().getOperatore().setCodiceFiscale("AAAAAA00A11C000K");
	
		SetupCruscottoResponse res = setupCruscottoService.executeService(req);
		assertNotNull(res);
	}
	
	
	public Richiedente getRichiedenteTest(){
		Richiedente richiedente = new Richiedente();
		Operatore operatore = new Operatore();
		//operatore.setCodiceFiscale("AAAAAA00A11B000J"); // demo21
		operatore.setCodiceFiscale("AAAAAA00A11E000M"); // demo24
		//operatore.setCodiceFiscale("AAAAAA00A11K000S"); // aipo
		//operatore.setCodiceFiscale("AAAAAA00A11C000K"); // demo22
		richiedente.setOperatore(operatore);
		
		Account account = new Account();
		account.setUid(32);
		Ente ente = new Ente();
		ente.setUid(15);
		account.setEnte(ente);
		richiedente.setAccount(account);
		
		return richiedente;
	}
	
	

}
