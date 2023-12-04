/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.frontend.webservice.client.CoreServiceClient;
import it.csi.siac.siaccorser.frontend.webservice.msg.ExecAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.ExecAzioneRichiestaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccounts;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccountsResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiestaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateVariabiliDiProcesso;
import it.csi.siac.siaccorser.frontend.webservice.msg.UpdateVariabiliDiProcessoResponse;
import it.csi.siac.siaccorser.integration.bpm.BpmServiceRestImpl;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcesso;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcessoResponse;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.TipoAzione;
import it.csi.siac.siaccorser.model.TipologiaGestioneLivelli;
import it.csi.siac.siaccorser.model.VariabileProcesso;

/**
 * Test dell'implementazione di coreService tramite un client di WS
 * 
 * E' fatto: 
 * - puntando al server il cui endpoint è configurato nell'application context
 * - facendo riferimento al processo 
 * http://subversion.csi.it:10090/dsp/siac/siacbilpro/trunk/src/xml/VariazioneDiBilancio--1.0.bar
 * in particolare alla release 1176

 * 
 * @author alagna
 * @version $Id: $
 */
public class CoreServiceWsTest extends BaseJunit4TestCase{

	private static final String NOME_PROCESSO="VariazioneDiBilancio";
	private static final String NOME_VARIABILE="descrizione";
	private static final String VALORE_VARIABILE="Descrizione del processo a a a";
	

	private CoreServiceClient coreServiceClient;
	private BpmServiceRestImpl bpmService;
	
	@Before
	@Override
	public void setUp() throws Exception {
		coreServiceClient = applicationContext.getBean("coreServiceClient", CoreServiceClient.class);
		
		bpmService = (BpmServiceRestImpl)applicationContext.getBean("bpmService");		
		// è necessario impostare l'endpoint perchè normalmente esso è settato dal
		// meccanismo che fa il replacement dei placeholder
		bpmService.setEndpointRest("");
	}

	/**
	 * Verifica la richiesta di avvio di un processo senza variabili
	 */
	@Test
	public void testExecAzioneRichiestaAvvioProcessoNoVar() {
		
		ExecAzioneRichiesta req = new ExecAzioneRichiesta();
		Azione azione = new Azione();
		azione.setTipo(TipoAzione.AVVIO_PROCESSO);
		azione.setNomeProcesso(NOME_PROCESSO);
		AzioneRichiesta azioneRichiesta = new AzioneRichiesta();
		azioneRichiesta.setAzione(azione);
		req.setAzioneRichiesta(azioneRichiesta);
		ExecAzioneRichiestaResponse res = coreServiceClient.getPort().execAzioneRichiesta(req);
		assertTrue(!res.isFallimento());	
	}
	
	/**
	 * Verifica la richiesta di avvio di un processo con impostazione di variabili
	 */
	@Test
	public void testExecAzioneRichiestaAvvioProcessoConVar() {
		
		ExecAzioneRichiesta req = new ExecAzioneRichiesta();
		Azione azione = new Azione();
		azione.setTipo(TipoAzione.AVVIO_PROCESSO);
		azione.setNomeProcesso(NOME_PROCESSO);
		AzioneRichiesta azioneRichiesta = new AzioneRichiesta();
		azioneRichiesta.setAzione(azione);
		VariabileProcesso variabileProcesso = new VariabileProcesso();
		variabileProcesso.setNome(NOME_VARIABILE);
		variabileProcesso.setValore(VALORE_VARIABILE);
		azioneRichiesta.getVariabiliProcesso().add(variabileProcesso);
		req.setAzioneRichiesta(azioneRichiesta);
		ExecAzioneRichiestaResponse res = coreServiceClient.getPort().execAzioneRichiesta(req);
		assertTrue(!res.isFallimento());
		
	}
	
	/**
	 * Esegue un passo di processo e non imposta variabili
	 */
	@Test
	public void testExecAzioneRichiestaExecTaskNoVar() {
		
		AvviaProcesso apReq = new AvviaProcesso();
		apReq.setNomeProcesso(NOME_PROCESSO);
		AvviaProcessoResponse apRes =  bpmService.avviaProcesso(apReq);
		assertTrue("Impossibile avviare il processo", !apRes.isFallimento());

		String idTask = 
			bpmService.getProssimaActivity(apRes.getIdProcesso(), true).get(0).getUUID().getValue();
		
		System.out.println("id task da eseguire: " + idTask);
		
		ExecAzioneRichiesta req = new ExecAzioneRichiesta();
		Azione azione = new Azione();
		azione.setTipo(TipoAzione.ATTIVITA_PROCESSO);
		AzioneRichiesta azioneRichiesta = new AzioneRichiesta();
		azioneRichiesta.setAzione(azione);
		azioneRichiesta.setIdAttivita(idTask);
		
		req.setAzioneRichiesta(azioneRichiesta);
		ExecAzioneRichiestaResponse res = coreServiceClient.getPort().execAzioneRichiesta(req);
		assertTrue(!res.isFallimento());
	}
	
	
	/**
	 * Esegue un passo di processo con variabili d processo
	 */
	@Test
	public void testExecAzioneRichiestaExecTaskConVar() {
		
		List<VariabileProcesso> vars = new ArrayList<VariabileProcesso>();
		
		VariabileProcesso variabileProcesso = new VariabileProcesso();
		variabileProcesso.setNome("descrizione");
		variabileProcesso.setValore("Descrizione - testExecAzioneRichiestaExecTaskConVar " );
		vars.add(variabileProcesso);
		
		variabileProcesso = new VariabileProcesso();
		variabileProcesso.setNome("siacEnteProprietarioProcesso");
		variabileProcesso.setValore(VariabileProcesso.PREFISSO_VALORE_VARIABILE_ENTE_PROPRIETARIO+1);
		vars.add(variabileProcesso);
		
		variabileProcesso = new VariabileProcesso();
		variabileProcesso.setNome("siacSacProcesso");
		variabileProcesso.setValore(VariabileProcesso.PREFISSO_VALORE_VARIABILE_SAC+1);
		vars.add(variabileProcesso);
		
		
		variabileProcesso = new VariabileProcesso();
		//come sarà..variabileProcesso.setNome("invioConsiglio"); 
		variabileProcesso.setNome("invioOrganoAmministrativo"); 
		variabileProcesso.setValore(Boolean.TRUE);
		vars.add(variabileProcesso);
		
//		variabileProcesso = new VariabileProcesso();
//		variabileProcesso.setNome("quadraturaVariazioneDiBilancio"); 
//		variabileProcesso.setValore(Boolean.TRUE);
//		vars.add(variabileProcesso);
//		
//		variabileProcesso = new VariabileProcesso();
//		variabileProcesso.setNome("invioGiunta");
//		variabileProcesso.setValore(Boolean.TRUE);
//		vars.add(variabileProcesso);
//		
		
		
//		AvviaProcesso apReq = new AvviaProcesso();
//		apReq.setNomeProcesso(NOME_PROCESSO);
//		apReq.setVariabiliProcesso(vars);
//		
//		
//		
//		AvviaProcessoResponse apRes =  bpmService.avviaProcesso(apReq);
//		assertTrue("Impossibile avviare il processo", !apRes.isFallimento());
//
//		String idTask = 
//			bpmService.getProssimaActivity(apRes.getIdProcesso(), true).get(0).getUUID().getValue();
		
		String idTask ="VariazioneDiBilancio--1.0--307--VariazioneDiBilancio_AggiornamentoVariazioneOrganoAmministrativo--it1--mainActivityInstance--noLoop";
		System.out.println("id task da eseguire: " + idTask);
		
		ExecAzioneRichiesta req = new ExecAzioneRichiesta();
		Azione azione = new Azione();
		azione.setTipo(TipoAzione.ATTIVITA_PROCESSO);
		AzioneRichiesta azioneRichiesta = new AzioneRichiesta();
		azioneRichiesta.setAzione(azione);
		azioneRichiesta.setIdAttivita(idTask);
		
				
		azioneRichiesta.setVariabiliProcesso(vars);
		req.setAzioneRichiesta(azioneRichiesta);
		ExecAzioneRichiestaResponse res = coreServiceClient.getPort().execAzioneRichiesta(req);
		assertTrue(!res.isFallimento());
		
		if(!res.isFallimento()){
			System.out.println("id Task successivo: " + res.getIdTask());
			System.out.println("Nome Task successivo: " + res.getNomeTask());
		}
		
		
	}
	
	
	/**
	 * Verifica la richiesta di variazioni variabili di processo senza completare il task
	 */
	@Test
	public void testUpdateVariabiliDiProcesso() {
		
		UpdateVariabiliDiProcesso req = new UpdateVariabiliDiProcesso();
		AzioneRichiesta ar = new AzioneRichiesta();
		//azione.setTipo(TipoAzione.AVVIO_PROCESSO);
		ar.setIdAttivita("VariazioneDiBilancio--1.0--257--VariazioneDiBilancio_InserimentoVariazione--it1--mainActivityInstance--noLoop");
		List<VariabileProcesso> vars = new ArrayList<VariabileProcesso>();
		
		VariabileProcesso variabileProcesso = new VariabileProcesso();
		variabileProcesso.setNome("descrizione");
		variabileProcesso.setValore("Ricerca variazione per ente - Numero: 001 (corretto)");
		vars.add(variabileProcesso);
		
		variabileProcesso = new VariabileProcesso();
		variabileProcesso.setNome("siacEnteProprietarioProcesso");
		variabileProcesso.setValore(VariabileProcesso.PREFISSO_VALORE_VARIABILE_ENTE_PROPRIETARIO+1);
		vars.add(variabileProcesso);
		
//		variabileProcesso = new VariabileProcesso();
//		variabileProcesso.setNome("siacAnnoEsercizioProcesso");
//		variabileProcesso.setValore(VariabileProcesso.PREFISSO_VALORE_VARIABILE_ANNO_ESERCIZIO+2013);
//		vars.add(variabileProcesso);
		
		variabileProcesso = new VariabileProcesso();
		variabileProcesso.setNome("siacSacProcesso");
		variabileProcesso.setValore(VariabileProcesso.PREFISSO_VALORE_VARIABILE_SAC+1);
		vars.add(variabileProcesso);
		
		ar.setVariabiliProcesso(vars);
		req.setAzioneRichiesta(ar);
		UpdateVariabiliDiProcessoResponse res = coreServiceClient.getPort().updateVariabiliDiProcesso(req);
		assertTrue(!res.isFallimento());
		
	}
	
	@Transactional
	@Test
	public void testFindGestioneLivelloById() {
		try {
			
			GetAzioneRichiesta params = new GetAzioneRichiesta();
			AzioneRichiesta azione = new AzioneRichiesta();
			azione.setUid(-1004312983);//
			params.setAzioneRichiesta(azione);
			//params.setCodiceGestioneLivello("BIL");
			GetAzioneRichiestaResponse res = coreServiceClient.getPort().getAzioneRichiesta(params);

			Ente ente = res.getAzioneRichiesta().getAccount().getEnte();
			if(ente.getGestioneLivelli()!=null && 
					!ente.getGestioneLivelli().isEmpty()){
				Map<TipologiaGestioneLivelli, String> map = ente.getGestioneLivelli();
				
				Set<TipologiaGestioneLivelli> keySet = map.keySet();
				for(TipologiaGestioneLivelli key:keySet){
				     String value = map.get(key);
				     System.out.println(value);
				}
			}
			

		} catch (Exception e) {
			log.error("errore in testFindGestioneLivelloById():", e);
			fail();
		}
	}
	
	@Test
	public void testGetAccounts() {
		try{
			GetAccounts req = new GetAccounts();
	
			req.setRichiedente(getRichiedenteTest());
			
			GetAccountsResponse res =coreServiceClient.getPort().getAccounts(req);
		
		} catch (Exception e) {
			log.error("errore in testFindGestioneLivelloById():", e);
			fail();
		}
	}
	
	
	protected Richiedente getRichiedenteTest() {
		Richiedente richiedente = new Richiedente();
		Operatore operatore = new Operatore();
		operatore.setCodiceFiscale("AAAAAA00A11B000J");
		richiedente.setOperatore(operatore);

		return richiedente;
	}

}
