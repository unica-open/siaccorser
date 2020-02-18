/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccommon.util.CoreUtils;


/**
 * TipologiaGestione: enum che raccoglie i codici delle tipologie di gestione livelli
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public enum TipologiaGestioneLivelli {

	LIVELLO_GESTIONE_BILANCIO("GEST_BIL"),
	LIVELLO_GESTIONE_LIQUIDAZIONE("GEST_LIQ"),
	GESTIONE_ATTI_AMMINISTRATIVI("GEST_ATT"),
	GESTIONE_ACQUISTI("GEST_AQ"),
	LIVELLO_GESTIONE_ORDINATIVO("GEST_ORD"),
	MODIFICHE_POSITIVE_SPESA("MOD_PSP"),
	ESIGIBILITA_DIFFERITA_IVA_VENDITE("EDIV"),
	ESIGIBILITA_DIFFERITA_IVA_ACQUISTI("EDIA"),
	GESTIONE_IVA("GEST_IVA"),
	GESTIONE_PARERE_FINANZIARIO("GESTIONE_PARERE_FINANZIARIO"),
	INTEGRAZIONE_HR("INT_HR"),
	CARICA_MISSIONE_DA_ESTERNO("EXT_MISS"),
	GESTIONE_GSA("GESTIONE_GSA"),
	AGGIORNA_IMPORTO_LIQ("AGGIORNA_IMPORTO_LIQ"),
	GESTIONE_CONVALIDA_AUTOMATICA("GESTIONE_CONVALIDA_AUTOMATICA"),
	GESTIONE_EVASIONE_ORDINI("GESTIONE_EVASIONE_ORDINI"),
	PROGETTO_OBBLIGATORIO("PROGETTO_OBBLIGATORIO"),
	CODICE_PROGETTO_AUTOMATICO("CODICE_PROGETTO_AUTOMATICO"),
	
	//SIAC-4651
	//TRUE forza Distinta reversale oneri come il mandato (in emissione ordinativi incasso da ritenute)
	REV_ONERI_DISTINTA_MAN("REV_ONERI_DISTINTA_MAN"),
	//TRUE forza Conto Tesoreria reversale oneri come il mandato (in emissione ordinativi incasso da ritenute)
	REV_ONERI_CONTO_MAN("REV_ONERI_CONTO_MAN"),
	
	ACCREDITO_CONTO_BANCA("ACCREDITO_CONTO_BANCA"),
	ACCREDITO_CONTANTI("ACCREDITO_CONTANTI"),
	
	MESSAGGIO_INFORMATIVO("MESSAGGIO_INFORMATIVO"),
	
	// SIAC-6693
	BLOCCO_VINCOLO_DEC("BLOCCO_VINCOLO_DEC"),

	// SIAC-5480
	CONF_NUM_ANNI_BIL_PREV_INDIC("CONF_NUM_ANNI_BIL_PREV_INDIC"),
	
	// SIAC-4970
	GESTIONE_CONSULTAZIONE_CAP_PRENOTAZIONI("GESTIONE_CONSULTAZIONE_CAP_PRENOTAZIONI"),
	
	// SIAC-5376
	ORDINATIVI_MIF_TRASMETTI_SIOPE_PLUS("ORDINATIVI_MIF_TRASMETTI_SIOPE_PLUS"),
	ORDINATIVI_MIF_TRASMETTI_UNIIT("ORDINATIVI_MIF_TRASMETTI_UNIIT"),
	
	//SIAC-5333
	GESTIONE_PNOTA_DA_FIN("GESTIONE_PNOTA_DA_FIN"),
	
	//SIAC-6076
	FIRMA_CARTA_1("FIRMA_CARTA_1"),
	FIRMA_CARTA_2("FIRMA_CARTA_2"),
	
	//SIAC-6177
	VARIAZ_ORGANO_AMM("VARIAZ_ORGANO_AMM"),
	VARIAZ_ORGANO_LEG("VARIAZ_ORGANO_LEG"),
	
	//SIAC-6888
	ABILITAZIONE_INSERIMENTO_ACC_AUTOMATICO("ABILITAZIONE_INSERIMENTO_ACC_AUTOMATICO"),
	
	
	//SIAC-6884
	REGIONE_PIEMONTE_INS_CAP_VAR_DEC("REGIONE_PIEMONTE_INS_CAP_VAR_DEC")
	;
	
	private String codice;

	private static final Map<String, TipologiaGestioneLivelli> REVERSE_MAP = CoreUtils
			.getEnumMap(TipologiaGestioneLivelli.class);

	TipologiaGestioneLivelli(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}

	/**
	 * Attenzione: il fromCodice non ritorna la referenza di TipologiaGestioneLivelli 
	 * per codice uguale a GEST_BIL, xchè la mappa costruita dal CoreUtils.getEnumMap
	 * è costituita da Key= nome elemento enum (es. GESTIONE_LIVELLO_BILANCIO) e Value= Istanza tipologia gestione livello
	 * (corrispondente quindi per l'esempio della key sopra citata è GESTIONE_LIVELLO_BILANCIO)
	 * @param codice
	 * @return
	 */
	public static TipologiaGestioneLivelli fromCodice(String codice) {
		
//		for (String key : reverseMap.keySet()) {
//		    System.out.println("Key = " + key);
//		}
//		for (TipologiaGestioneLivelli value : reverseMap.values()) {
//		    System.out.println("Value = " + value);
//		    System.out.println("Value.codice = " + value.getCodice());
//		}
		return REVERSE_MAP.get(codice);
	}

}
