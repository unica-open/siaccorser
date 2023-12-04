/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business;


import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.business.service.FindAzioneService;
import it.csi.siac.siaccorser.business.service.GetAzioneRichiestaService;
import it.csi.siac.siaccorser.business.service.RicercaBilancioService;
import it.csi.siac.siaccorser.frontend.webservice.msg.FindAzione;
import it.csi.siac.siaccorser.frontend.webservice.msg.FindAzioneResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiestaResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaBilancio;
import it.csi.siac.siaccorser.frontend.webservice.msg.RicercaBilancioResponse;
import it.csi.siac.siaccorser.model.AzioneRichiesta;

public class CoreServiceTest extends BaseJunit4TestCase {

	@Autowired
	private GetAzioneRichiestaService getAzioneRichiestaService;
	@Autowired
	private RicercaBilancioService ricercaBilancioService;
	@Autowired
	private FindAzioneService findAzioneService;

	@Test
	public void getAzioneRichiesta() {
		GetAzioneRichiesta req = new GetAzioneRichiesta();
		
//		req.setAnnoBilancio(Integer.valueOf(2017));
		req.setAzioneRichiesta(create(AzioneRichiesta.class, 66172509));
		req.setDataOra(new Date());
		req.setRichiedente(getRichiedenteByProperties("consip", "regp"));
		
		
		GetAzioneRichiestaResponse res = getAzioneRichiestaService.executeService(req);
		assertNotNull(res);
	}

	@Test
	public void ricercaBilancio() {
		RicercaBilancio req = new RicercaBilancio();
		
		req.setAnno(Integer.valueOf(2018));
		req.setRichiedente(getRichiedenteByProperties("consip", "regp"));
		req.setEnte(req.getRichiedente().getAccount().getEnte());
		
		RicercaBilancioResponse res = ricercaBilancioService.executeService(req);
		assertNotNull(res);
	}
	
	@Test
	public void findAzione() {
		FindAzione req = new FindAzione();
		
		req.setNomeAzione("OP-REP-ReportVariazioniBilancio-2020");
		req.setRichiedente(getRichiedenteByProperties("consip", "regp"));
		req.setEnte(req.getRichiedente().getAccount().getEnte());
		
		FindAzioneResponse res = findAzioneService.executeService(req);
		assertNotNull(res);
	}
	
}
