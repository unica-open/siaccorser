/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business;


import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccommon.util.CoreUtil;
import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.business.service.GetAzioneRichiestaService;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiesta;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAzioneRichiestaResponse;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.TipologiaGestioneLivelli;

public class GetAzioneRichiestaTest extends BaseJunit4TestCase {

	
	@Autowired
	private GetAzioneRichiestaService getAzioneRichiestaService;

	@Test
	public void getAzioneRichiesta() {
		final String methodName = "testGetAzioneRichiesta";
		log.debugStart(methodName, "");
		log.debug(methodName, "Creazione richiedente ed ente");
		Richiedente richiedente = getRichiedenteByProperties("consip", "regp");
		//Richiedente richiedente = getRichiedenteTest("AAAAAA00A11E000M", 751, 3);
		
		log.debug(methodName, "Creazione azione richiesta");
		AzioneRichiesta azioneRichiesta = getAzioneRichiestaTest();
		
		log.debug(methodName, "Creazione request");
		GetAzioneRichiesta request = new GetAzioneRichiesta();
		request.setAzioneRichiesta(azioneRichiesta);
		request.setRichiedente(richiedente);
		request.setDataOra(new Date());
		//request.setAnnoBilancio(2017);
		log.debug(methodName, "Invocazione del WS");
		GetAzioneRichiestaResponse response = getAzioneRichiestaService.executeService(request);
		log.debug(methodName, "WS invocato");
		
//		String risultato = ToStringBuilder.reflectionToString(response, ToStringStyle.MULTI_LINE_STYLE);
//		
//		log.debug(methodName, "Risultato ottenuto : " + risultato);
		assertNotNull("Response nulla dal WebService", response);
//		List<AzioneConsentita> azioniConsentite = response.getAzioniConsentite();
//		for (AzioneConsentita ac : azioniConsentite) {
//			String nome = ac.getAzione().getNome();
//			if(StringUtils.isNotEmpty(nome) && nome.equalsIgnoreCase("OP-SPE-gestProvvedimento")) {
//				log.debug(methodName, "YAYYYYY: trovata azioneeeee: " + nome);
//			}
//		}
		checkGestioneLivelli(response.getAzioneRichiesta());
		//'OP-GEN-ratei-riscontiGSA'
		assertEquals("La chiamata al WS si e' risolta in un fallimento", Esito.SUCCESSO, response.getEsito());
		log.debugEnd(methodName, "");
	}
	
	private void checkGestioneLivelli(AzioneRichiesta azioneRichiesta) {
		// TODO Auto-generated method stub
			 Map<TipologiaGestioneLivelli, String> gestioneLivelli = azioneRichiesta.getAccount().getEnte().getGestioneLivelli();
			 for (TipologiaGestioneLivelli tgl : gestioneLivelli.keySet()) {
				 System.out.println(tgl.name() + " : "  + gestioneLivelli.get(tgl));
			}
	}

	/**
	 * Crea un'Azione Richiesta di test.
	 * 
	 * @return l'Azione Richiesta creata 
	 */
	private AzioneRichiesta getAzioneRichiestaTest() {
		AzioneRichiesta ar = new AzioneRichiesta();
		ar.setUid(66612181);
		return ar;
	}
	
	public static void main(String[] args) {
		TipologiaGestioneLivelli tgc = TipologiaGestioneLivelli.fromCodice("GESTIONE_PNOTA_DA_FIN");
		for (TipologiaGestioneLivelli tgc1 : TipologiaGestioneLivelli.values()) {
			System.out.println(tgc1.getCodice() + " : " + (TipologiaGestioneLivelli.fromCodice(tgc1.getCodice()) != null? TipologiaGestioneLivelli.fromCodice(tgc1.getCodice()).name() : "null"));
		}
		System.out.println("quaaaaaaaaaaaaaaa");
		for (TipologiaGestioneLivelli tcg2 : TipologiaGestioneLivelli.class.getEnumConstants()) {
			System.out.println(tcg2.name());
		}
//		System.out.println(tgc!= null? tgc.getCodice() : "null");
		Map<String, TipologiaGestioneLivelli> enumMap = CoreUtil.getEnumMap(TipologiaGestioneLivelli.class);
//		for (String key : enumMap.keySet()) {
//			System.out.println(key + " - " + enumMap.get(key));
//		}
	}
	
	
	

}
