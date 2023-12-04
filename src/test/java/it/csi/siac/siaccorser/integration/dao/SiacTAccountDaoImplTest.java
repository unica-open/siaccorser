/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.integration.entity.SiacTAccount;
import it.csi.siac.siaccorser.integration.entity.SiacTAzione;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siaccorser.integration.entity.SiacTGruppo;
import it.csi.siac.siaccorser.integration.repository.SiacTAccountRepository;
import it.csi.siac.siaccorser.integration.repository.SiacTGruppoRepository;

public class SiacTAccountDaoImplTest extends BaseJunit4TestCase {
	
	@Autowired
	private SiacTAccountDao accountDao;
	
	@Autowired
	private SiacTAzioneDao azioneDao;
	
	@Autowired
	private SiacTAccountRepository accountRepositoryDao;
	
	@Autowired
	private SiacTGruppoRepository gruppoRepository;
	
	@Test
	public void testCreateAccount() {
		try {
			SiacTAccount  accountDto= new SiacTAccount();
			
			accountDto = accountDao.saveAccount(accountDto);
			assertNull("AccountDto trovato: ", accountDto);
		} catch (Exception e) {
			System.out.println("errore in testCreateAccount(): "+e);
			fail();
		}
	}
	
	
	@Test
	public void testFinAccountById() {
		try {
			SiacTAccount  accountDto= new SiacTAccount();
			
			SiacTEnteProprietario ente = new SiacTEnteProprietario();
			ente.setUid(1);
			//accountDto = accountRepositoryDao.findAccountById(1, ente);
			
			assertNull("AccountDto trovato: ", accountDto);
		} catch (Exception e) {
			System.out.println("errore in testCreateAccount(): "+e);
			fail();
		}
	}
	
	
	@Test
	public void testFindAzioniByRuoloOp() {
		try {
			SiacTEnteProprietario ente = new SiacTEnteProprietario();
			ente.setUid(1);
			List<SiacTAzione> azioni= azioneDao.findAzioniByRuoloOp(100, ente);
			
			assertNull("SiacTAzione lista: ", !azioni.isEmpty());
		} catch (Exception e) {
			System.out.println("errore in testFindAzioniByRuoloOp(): "+e);
			fail();
		}
	}
	
	
	@Test
	public void testFindGruppiByIdAccount(){
		try {
			List<SiacTGruppo>  gruppi = new ArrayList<SiacTGruppo>();
			
			gruppi = gruppoRepository.findGruppiByIdAccount(6);
			
			assertNull("lista trovata: ", gruppi.size());
		} catch (Exception e) {
			System.out.println("errore in testFindGruppiByIdAccount(): "+e);
			fail();
		}
		
	}

	
	
//	@Test
//	public void testFindGruppiById(){
//		try {
//			List<SiacTGruppo>  gruppi = new ArrayList<SiacTGruppo>();
//			
//			gruppi = gruppoRepository.findGruppiById(6);
//			
//			assertNull("lista trovata: ", gruppi.size());
//		} catch (Exception e) {
//			System.out.println("errore in testFindGruppiById(): "+e);
//			fail();
//		}
//		
//	}
}
