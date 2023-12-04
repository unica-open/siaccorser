/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.business.service.opasinc.InserisciDettaglioOperazioneAsincService;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciDettaglioOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciDettaglioOperazioneAsincResponse;

public class OperazioneAsincronaTest extends BaseJunit4TestCase {

	
	@Autowired InserisciDettaglioOperazioneAsincService inserisciDettaglioOperazioneAsincService;
	
	@Test
	public void testInserisciDettaglioOperazioneAsincByFile() {

		InserisciDettaglioOperazioneAsinc req = getTestFileObject(InserisciDettaglioOperazioneAsinc.class, 	"dettaglioOpAsync.xml");
		
//		req.setRichiedente(getRichiedenteTest());
//		req.setIdOperazioneAsincrona(9);
//		req.setIdEnte(1);
//		req.setEsito(Esito.SUCCESSO.toString());
//		req.setCodice("prova");
//		req.setDescrizione("prova");
		
		
		InserisciDettaglioOperazioneAsincResponse res = inserisciDettaglioOperazioneAsincService.executeService(req);
				
		//assertTrue(!res.isFallimento());
		System.out.println("esito: " + res.getEsito());
		System.out.println("esito: " + res);
		System.out.println("errori: " + res.getErrori());

	}

}
