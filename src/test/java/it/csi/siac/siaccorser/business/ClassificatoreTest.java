/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.business.service.classificatori.LeggiStrutturaAmminstrativoContabileService;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabile;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabileResponse;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;

public class ClassificatoreTest extends BaseJunit4TestCase {

	
	@Autowired
	private LeggiStrutturaAmminstrativoContabileService leggiStrutturaAmminstrativoContabileService;

	
	@Test
	public void testLeggiStrutturaAmminstrativoContabile() {
		try {
			// famigliTreeId=5 � la struttura amministrativo contabile
			LeggiStrutturaAmminstrativoContabile req = new LeggiStrutturaAmminstrativoContabile();
			req.setAnno(2017);
			req.setIdEnteProprietario(2);
			//params.setCodiceFamigliaTree("00008");
			req.setRichiedente(getRichiedenteByProperties("consip", "regp"));
			
			LeggiStrutturaAmminstrativoContabileResponse res = leggiStrutturaAmminstrativoContabileService
					.executeService(req);
			
			List<StrutturaAmministrativoContabile> uos = res.getListaStrutturaAmmContabile();

			assertTrue("trovata lista vuota", !uos.isEmpty());

			for (StrutturaAmministrativoContabile uo : uos) {
				System.out.println(uo.getUid());
				System.out.println(uo.getDescrizione());

				if (null != uo.getAssessorato()) {
					System.out.println(uo.getAssessorato());
				}
				for (StrutturaAmministrativoContabile figlio : uo
						.getSubStrutture()) {
					System.out.println("  " + figlio.getUid());
					System.out.println("  " + figlio.getDescrizione());
					if (null != figlio.getAssessorato()) {
						System.out.println("  " + figlio.getAssessorato());
					}
				}
			}

		} catch (Exception e) {
			log.error("errore in testFindStrutturaAmminstrativoContabile():", e);
			fail();
		}
	}
	
	
	public Richiedente getRichiedenteTest(){
		Richiedente richiedente = new Richiedente();
		Operatore operatore = new Operatore();
		operatore.setCodiceFiscale("AAAAAA00A11B000J");
		richiedente.setOperatore(operatore);
		return richiedente;
	}
	
	

}
