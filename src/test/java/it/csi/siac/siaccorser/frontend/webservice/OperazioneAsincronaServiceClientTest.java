/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice;

import org.junit.Before;
import org.junit.Test;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.frontend.webservice.client.OperazioneAsincronaServiceClient;
import it.csi.siac.siaccorser.frontend.webservice.msg.AggiornaOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.AggiornaOperazioneAsincResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetDettaglioOperazioneAsincrona;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetDettaglioOperazioneAsincronaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetNotificheOperazioneAsincrona;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetNotificheOperazioneAsincronaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciDettaglioOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciDettaglioOperazioneAsincResponse;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.paginazione.ParametriPaginazione;

public class OperazioneAsincronaServiceClientTest  extends
	BaseJunit4TestCase{

	private final static String ENDPOINT = "http://tst-srv-consip.bilancio.csi.it/siaccorser/OperazioneAsincronaService";
	
	private OperazioneAsincronaServiceClient client;

	@Before
	@Override
	public void setUp() throws Exception {
		client = new OperazioneAsincronaServiceClient();
		client.setEndpoint(ENDPOINT);
	}


	
	/**
	 * Testiamo la chiamata per AggiornaOperazioneAsinc
	 * 
	 * 
	 */
	@Test
	public void testAggiornaOperazioneAsinc() {

		AggiornaOperazioneAsinc req = new AggiornaOperazioneAsinc();
		
		req.setRichiedente(getRichiedenteTest());
		//req.setIdOperazioneAsinc(9);
	//	req.setStato(StatoOperazioneAsincronaEnum.STATO_OPASINC_ERRORE.getCodice());
		
		AggiornaOperazioneAsincResponse res = client.getPort().aggiornaOperazioneAsinc(req);
				
		//assertTrue(!res.isFallimento());
		System.out.println("esito: " + res.getEsito());
		System.out.println("esito: " + res);
		System.out.println("errori: " + res.getErrori());

	}
	
	

	@Test
	public void testNotificheOperazioneAsincrona() {

		GetNotificheOperazioneAsincrona req = new GetNotificheOperazioneAsincrona();
		
		req.setRichiedente(getRichiedenteTest());
		req.setParametriPaginazione(new ParametriPaginazione());
		req.setAccountId(1);
		req.setAzioneId(1);
		req.setEnteProprietarioId(1);
		
		GetNotificheOperazioneAsincronaResponse res = client.getPort().getNotificheOperazioneAsincrona(req);
				
		System.out.println("esito: " + res.getEsito());
		System.out.println("esito: " + res);
		System.out.println("errori: " + res.getErrori());

	}
	
	@Test
	public void testGetDettaglioOperazioneAsincrona() {

		GetDettaglioOperazioneAsincrona req = new GetDettaglioOperazioneAsincrona();
		
		req.setRichiedente(getRichiedenteTest());
		req.setOpAsincId(7);
		req.setParametriPaginazione(new ParametriPaginazione());
		
		GetDettaglioOperazioneAsincronaResponse res = client.getPort().getDettaglioOperazioneAsincrona(req);
				
		//assertTrue(!res.isFallimento());
		System.out.println("esito: " + res.getEsito());
		System.out.println("esito: " + res);
		System.out.println("errori: " + res.getErrori());

	}
	
	
	
	@Test
	public void testGetNotificheOperazioneAsincrona() {

		GetNotificheOperazioneAsincrona req = new GetNotificheOperazioneAsincrona();
		
		req.setRichiedente(getRichiedenteTest());
		req.setEnteProprietarioId(1);
		req.setAccountId(1);
		req.setAzioneId(4357);
		req.setParametriPaginazione(new ParametriPaginazione(0));
		
		GetNotificheOperazioneAsincronaResponse res = client.getPort().getNotificheOperazioneAsincrona(req);
				
		//assertTrue(!res.isFallimento());
		System.out.println("esito: " + res.getEsito());
		System.out.println("esito: " + res);
		System.out.println("errori: " + res.getErrori());

	}
	
	@Test
	public void testInserisciDettaglioOperazioneAsincByFile() {

		InserisciDettaglioOperazioneAsinc req = getTestFileObject(InserisciDettaglioOperazioneAsinc.class, 	"dettaglioOpAsync.xml");
		
//		req.setRichiedente(getRichiedenteTest());
//		req.setIdOperazioneAsincrona(9);
//		req.setIdEnte(1);
//		req.setEsito(Esito.SUCCESSO.toString());
//		req.setCodice("prova");
//		req.setDescrizione("prova");
		
		
		InserisciDettaglioOperazioneAsincResponse res = client.getPort().inserisciDettaglioOperazioneAsinc(req);
				
		//assertTrue(!res.isFallimento());
		System.out.println("esito: " + res.getEsito());
		System.out.println("esito: " + res);
		System.out.println("errori: " + res.getErrori());

	}
	
	
	@Test
	public void testInserisciDettaglioOperazioneAsinc() {

		InserisciDettaglioOperazioneAsinc req = new InserisciDettaglioOperazioneAsinc();
		
		req.setRichiedente(getRichiedenteTest());
		req.setIdOperazioneAsincrona(9);
		req.setIdEnte(1);
		req.setEsito(Esito.SUCCESSO.toString());
		req.setCodice("prova");
		req.setDescrizione("prova");
		
		
		InserisciDettaglioOperazioneAsincResponse res = client.getPort().inserisciDettaglioOperazioneAsinc(req);
				
		//assertTrue(!res.isFallimento());
		System.out.println("esito: " + res.getEsito());
		System.out.println("esito: " + res);
		System.out.println("errori: " + res.getErrori());

	}
	protected Ente getEnteTest() {
		Ente ente = new Ente();
		ente.setUid(1);
		return ente;
	}

	protected Richiedente getRichiedenteTest() {
		Richiedente richiedente = new Richiedente();
		Operatore operatore = new Operatore();
		operatore.setCodiceFiscale("AAAAAA00A11B000J");
		richiedente.setOperatore(operatore);
		return richiedente;
	}

}

