/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccounts;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccountsResponse;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.Richiedente;


public class CoreServiceClientTest {

	private final static String ENDPOINT = "http://localhost:8080/siaccorser/CoreService";
	private CoreServiceClient client;
	
	@Before
	public void setUp() throws Exception {
		
		client = new CoreServiceClient();
		client.setEndpoint(ENDPOINT);
	}

	@Test
	public void testGetAccounts() {
		
		Richiedente richiedente = new Richiedente();
		Operatore operatore = new Operatore();
		operatore.setCodiceFiscale("AAAAAA00A11B000J");	// demo 21
		richiedente.setOperatore(operatore);
		
		GetAccounts req = new GetAccounts();
		req.setRichiedente(richiedente);

		GetAccountsResponse res = client.getPort().getAccounts(req);
		Assert.assertTrue("Il servizio non restituisce alcun account", res.getAccounts().size()>0);
	}

}
