/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.errore;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.csi.siac.siaccorser.model.Errore;

public enum ErroreCore implements TipoErrore {

	
	ERRORE_DI_SISTEMA("COR_ERR_0001", "Errore di sistema: {0}"),
	DATO_OBBLIGATORIO_OMESSO("COR_ERR_0002", "Dato obbligatorio omesso: {0}"),
	PARAMETRO_NON_INIZIALIZZATO("COR_ERR_0003", "Parametro {0} non inizializzato"),
	
	ENTITA_NON_TROVATA("COR_ERR_0004", "{0} : {1} non e'' presente in archivio"),
	
	ENTITA_NON_TROVATA_SINGOLO_MSG("COR_ERR_0004", "{0} non e'' presente in archivio"),
	OPERAZIONE_ABBANDONATA("COR_INFO_0005","Operazione di {0} e'' stata abbandonata"),
	NESSUN_DATO_REPERITO("COR_INF_0007", "Nessun dato trovato"),
	ENTITA_PRESENTE("COR_ERR_0008", "Operazione impossibile - {0} : {1} gia'' presente in archivio"),
	FORMATO_NON_VALIDO("COR_ERR_0009", "Il formato del parametro {0} non e'' valido {1}"),
	VALORE_NON_VALIDO("COR_ERR_0010", "Il valore del parametro {0} non e'' consentito {1}"),
	DESCRIZIONE_GIA_PRESENTE("COR_ERR_0011", "Operazione impossibile - La descrizione: {0} e'' collegata a {1} : {2}"),
	ENTITA_INESISTENTE("COR_ERR_0012", "Operazione impossibile - {0} : {1} non e'' presente in archivio"),
	PARAMETRO_ERRATO("COR_ERR_0013", "Parametro di ricerca errato - {0} : valore non ammesso: {1} - valori ammessi sono: {2}"),
	TRANSAZIONE_DI_STATO_NON_POSSIBILE("COR_ERR_0014", "Transazione di stato non possibile"),
	NESSUN_ELEMENTO_SELEZIONATO("COR_ERR_0015", "Nessun elemento selezionato"),
	SELEZIONARE_UN_SOLO_ELEMENTO("COR_ERR_0016", "Selezionare un solo elemento"),
	ANNULLAMENTO_ELEMENTI("COR_ERR_0017", "Tutti gli elementi selezionati saranno annullati. L''operazione e'' irreversibile. Si desidera procedere?"),
	NESSUN_CRITERIO_RICERCA("COR_ERR_0018", "Indicare almeno un criterio di ricerca"),
	ELABORAZIONE_ATTIVATA("COR_INF_0019", "L''elaborazione {0} e'' stata attivata."),
	ELABORAZIONE_IN_CORSO("COR_INF_0020", "L''elaborazione {0} e'' in corso. Controllare stato di avanzamento. Attendere e controllare avanzamento e esito, attraverso apposita funzionalita'' di consultazione."),
	ELABORAZIONE_BLOCCATA("COR_ERR_0021", "L''elaborazione {0} e'' bloccata"),
	ELABORAZIONE_AVVIATA("COR_INF_0022", "L''elaborazione {0} era gia'' stata avviata. Riprendera'' dal passo {1}. Attendere e controllare avanzamento e esito, attraverso apposita funzionalita'' di consultazione."),
	ELABORAZIONE_INTERROTTA("COR_ERR_0023", "L''elaborazione {0} numero {1} e'' stata interrotta al passo {2} {3}"),
	ESISTONO_ENTITA_COLLEGATE("COR_ERR_0024", "Cancellazione non possibile: esistono entita'' collegate"),
	ENTITA_NON_SELEZIONATA("COR_ERR_0025", "E'' necessario selezionare un''occorrenza per procedere"),
	RICERCA_TROPPO_ESTESA("COR_ERR_0026", "La ricerca e'' troppo estesa: raffinare i criteri di ricerca"),
	TIPO_AZIONE_NON_SUPPORTATA("COR_ERR_0027", "Al momento non e'' possibile eseguire un''azione di tipo {0}"),
	OPERAZIONE_INCOMPATIBILE_CON_STATO_ENTITA("COR_ERR_0028", "Operazione non consentita: {0} e'' in stato {1}"),
	INCONGRUENZA_NEI_PARAMETRI("COR_ERR_0029", "Esiste un''incongruenza tra i parametri di input: {0}"),
	INSERIMENTO_ENTITA_ANDATO_A_BUON_FINE("COR_ERR_0030", "L''operazione e'' stata completata con successo. {0} inserita"),
	AGGIORNAMENTO_NON_POSSIBILE("COR_ERR_0031", "{0} e'' {1}: aggiornamento non possibile"),
	AGGIORNAMENTO_CON_CONFERMA("COR_CON_0032", "{0} e'' {1}: si vuole procedere con l''aggiornamento?"),
	AGGIORNAMENTO_CON_CONFERMA_WARN("COR_CON_0032","{0} e'' {1}: si vuole procedere con {2}?"),
	INSERIMENTO_QUOTA_CON_CONFERMA("COR_CON_0032","L&apos; importo della quota &egrave; maggiore della disponibilit&agrave; dell&apos; accertamento: si vuole procedere con {0}?"),
	RELAZIONE_GIA_PRESENTE("COR_ERR_0033", "Operazione impossibile: relazione gia'' presente in archivio"),
	OPERAZIONE_EFFETTUATA("COR_INF_0034", "Operazione di {0} e'' stata effettuata {1}"),
	ATTRIBUTO_GIA_PRESENTE("COR_ERR_0035", "Operazione impossibile - {0}: {1} presente piu'' volte"),
	ELEMENTO_GIA_IN_ELABORAZIONE("COR_ERR_0036", "{0} {1} e'' gia'' stato preso in carico da altra elaborazione"),
	ELABORAZIONE_ASINCRONA_AVVIATA("COR_INF_0037", "L''elaborazione {0} e'' stata avviata {1}"),
	DATE_INCONGRUENTI("COR_ERR_0038", "Date incongruenti {0}"),
	
	ANNULLAMENTO_NON_POSSIBILE("COR_ERR_0039", "Annullamento non consentito. {0}"),
	STAMPA_NON_COMPLETATA("COR_ERR_0040", "Il sistema non ha elaborato la stampa. {0}"),
	ENTITA_NON_COMPLETA("COR_ERR_043", "{0} non e'' utilizzabile: {1}"),
	OPERAZIONE_NON_CONSENTITA("COR_ERR_0044", "Operazione non consentita: {0}"),
	
	IMPORTI_NON_VALIDI_PER_ENTITA("COR_ERR_0045", "Il valore degli importi {0} non &egrave; valido: {1}"),

	;

	private final String codice;
	private final String messaggio;

	private ErroreCore(String codice, String messaggio) {
		this.codice = codice;
		this.messaggio = messaggio;
	}

	public String getCodice() {
		return codice;
	}

	/*
	public Errore getErrore(Object... args) {
		Errore errore = new Errore(codice,
				MessageFormat.format(messaggio, args));
		return errore;
	}
	*/
	
	@Override
	public Errore getErrore(Object... args) {
		final String msg =  formatStringWithDefaultReplacements(this.messaggio, "", args);
		return new Errore(this.codice, msg);
	}

	private static final Pattern MESSAGE_FORMAT_PLACEHOLDER_PATTERN = Pattern.compile("\\{(\\d+)\\}");

	private static String formatStringWithDefaultReplacements(String format, String defaultSubstitution, Object... replacements) {
		final String msg = MessageFormat.format(format, replacements);
		final Matcher matcher = MESSAGE_FORMAT_PLACEHOLDER_PATTERN.matcher(msg);
		return matcher.replaceAll(defaultSubstitution);
	}

}
