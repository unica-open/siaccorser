/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siaccorser.BaseJunit4TestCase;
import it.csi.siac.siaccorser.business.service.attpendenti.RicercaSinteticaGruppoAttivitaPendentiService;
import it.csi.siac.siaccorser.frontend.webservice.msg.attpendenti.RicercaSinteticaGruppoAttivitaPendentiVariazione;
import it.csi.siac.siaccorser.frontend.webservice.msg.attpendenti.RicercaSinteticaGruppoAttivitaPendentiVariazioneResponse;
import it.csi.siac.siaccorser.model.AttivitaPendente;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.AzioneConsentita;
import it.csi.siac.siaccorser.model.Errore;
import it.csi.siac.siaccorser.model.paginazione.ParametriPaginazione;

public class AttivitaPendentiTest extends BaseJunit4TestCase {

	
	@Autowired
	private RicercaSinteticaGruppoAttivitaPendentiService ricercaSinteticaGruppoAttivitaPendentiVariazione;
	   
   @Test
   public void testRicercaSinteticaGruppoAttivitaPendentiVariazioneService() {
	   
	   List<AzioneConsentita> listaAzioniConsentite = getListaAzioniConsentite(null); //Arrays.asList(StatoOperativoVariazioneDiBilancio.PRE_BOZZA));
	   
	   StringBuilder result = new StringBuilder();
	   
	   for (AzioneConsentita azioneConsentita : listaAzioniConsentite) {
		   RicercaSinteticaGruppoAttivitaPendentiVariazione req = new RicercaSinteticaGruppoAttivitaPendentiVariazione();
		   req.setDataOra(new Date());
		   req.setAnnoBilancio(Integer.valueOf(2022));
		   req.setSoloTotali(true);
		   req.setAzioneConsentita(azioneConsentita);
		   req.setRichiedente(getRichiedenteByProperties("consip", "regpdec"));
		   RicercaSinteticaGruppoAttivitaPendentiVariazioneResponse res = ricercaSinteticaGruppoAttivitaPendentiVariazione.executeService(req);
		   if(res.hasErrori()) {
			   for (Errore er : res.getErrori()) {
				result.append(er.getTesto()).append("\n");
			}
		   }
		   result.append(res.getAzioneConsentita().getAzione().getNome()).append(" ( ").append(res.getTotali()).append(" )\n");
	   }
	   System.out.println(result.toString());
	   
	   
   }

   @Test
   public void testRicercaSinteticaAttivitaPendentiVariazioneService() {
	   
	   List<AzioneConsentita> listaAzioniConsentite = getListaAzioniConsentite(Arrays.asList(StatoOperativoVariazioneDiBilancio.PRE_BOZZA));
	   
	   StringBuilder result = new StringBuilder();
	   
	   for (AzioneConsentita azioneConsentita : listaAzioniConsentite) {
		   RicercaSinteticaGruppoAttivitaPendentiVariazione req = new RicercaSinteticaGruppoAttivitaPendentiVariazione();
		   req.setDataOra(new Date());
		   req.setAnnoBilancio(Integer.valueOf(2022));
		   req.setSoloTotali(true);
		   /*
		   req.setParametriPaginazione(new ParametriPaginazione());
		   req.getParametriPaginazione().setElementiPerPagina(10);
		   req.getParametriPaginazione().setNumeroPagina(0);
		   // */
		   req.setAzioneConsentita(azioneConsentita);
		   req.setRichiedente(getRichiedenteByProperties("consip", "regpdec"));
		   RicercaSinteticaGruppoAttivitaPendentiVariazioneResponse res = ricercaSinteticaGruppoAttivitaPendentiVariazione.executeService(req);
		   assertFalse(res.hasErrori());
		   if(res.hasErrori()) {
			   for (Errore er : res.getErrori()) {
				result.append(er.getTesto()).append("\n");
			}
			   return;
		   }
		   result.append(res.getAzioneConsentita().getAzione().getNome()).append(" )\n");
		   for (AttivitaPendente ap : res.getAttivitaPendenti()) {
			   result.append(ap.getDescrizioneBreve()).append(" - ").append(ap.getDataAperturaProposta())
			   	.append(" - ").append(ap.getDataChiusuraProposta())
			   	.append(" - ").append(ap.getDirezioneProponente())
			   	.append("\n");
		   }
		   result.append("\n\n");
	   }
	   System.out.println(result.toString());
	   
	   
	   
   }

   
   protected List<AzioneConsentita> getListaAzioniConsentite(List<StatoOperativoVariazioneDiBilancio> statos) {
	   List<AzioneConsentita> azs = new ArrayList<>();
	   
	   
	   //PRE-BOZZA
	   if(statos == null || statos.contains(StatoOperativoVariazioneDiBilancio.PRE_BOZZA)) {
		   AzioneConsentita azDec = new AzioneConsentita();
		   azDec.setAzione(create(Azione.class, 13161));
		   azDec.getAzione().setFlagVerificaSac(Boolean.TRUE);
		   azDec.getAzione().setNome("Aggiorna Variazione Decentrata");
		   azs.add(azDec);
	   }
	   
	   
	   //BOZZA
	   if(statos == null || statos.contains(StatoOperativoVariazioneDiBilancio.BOZZA)) {
		   AzioneConsentita azBoz = new AzioneConsentita();
		   azBoz.setAzione(create(Azione.class, 1150));
		   azBoz.getAzione().setNome("Aggiorna Variazione di Bilancio");
		   azs.add(azBoz);
	   }
	   
	   //GIUNTA
	   if(statos == null || statos.contains(StatoOperativoVariazioneDiBilancio.GIUNTA)) {
		   AzioneConsentita azGiunta = new AzioneConsentita();
		   azGiunta.setAzione(create(Azione.class, 1152));
		   azGiunta.getAzione().setNome("Aggiorna Variazione Giunta");
		   azs.add(azGiunta);
	   }
	   
	   //CONSIGLIO
	   if(statos == null || statos.contains(StatoOperativoVariazioneDiBilancio.CONSIGLIO)) {
		   AzioneConsentita azConsiglio = new AzioneConsentita();
		   azConsiglio.setAzione(create(Azione.class, 1153));
		   azConsiglio.getAzione().setNome("Aggiorna Variazione Consiglio");
		   azs.add(azConsiglio);
	   }
	   
	   //PRE-DEFINITIVA
	   if(statos == null || statos.contains(StatoOperativoVariazioneDiBilancio.PRE_DEFINITIVA)) {
		   AzioneConsentita azDef = new AzioneConsentita();
		   azDef.setAzione(create(Azione.class, 1151));
		   azDef.getAzione().setNome("Definisce Variazione di Bilancio");
		   azs.add(azDef);
	   }
	   return azs;
   }
   
   private enum StatoOperativoVariazioneDiBilancio{
	 //SIAC-8332
		PRE_BOZZA("BOZZA-DEC"),
		BOZZA("BOZZA"),
		DEFINITIVA("DEFINITIVA"),
		ANNULLATA("ANNULLATA"),
		GIUNTA("GIUNTA"),
		CONSIGLIO("CONSIGLIO"),
		PRE_DEFINITIVA("PRE-DEFINITIVA", "PRE-DEFINITIVA"),
		;
		
		private final String descrizione;
		private final String variableName;
		
		/**
		 * Costruttore a partire dalla descrizione per l'utente.
		 * <br/>
		 * Il nome della variabile di processo &egrave; desunto a partire dal nome dell'istanza dell'enum.
		 * 
		 * @param descrizione la descrizione per l'utente
		 */
		private StatoOperativoVariazioneDiBilancio(String descrizione) {
			this.descrizione = descrizione;
			this.variableName = this.name();
		}
		/**
		 * Costruttore a partire dalla descrizione per l'utente e dal nome della variabile di processo.
		 * 
		 * @param descrizione la descrizione per l'utente
		 * @param variableName il nome della variabile di processo
		 */
		private StatoOperativoVariazioneDiBilancio(String descrizione, String variableName) {
			this.descrizione = descrizione;
			this.variableName = variableName;
		}
   }

}
