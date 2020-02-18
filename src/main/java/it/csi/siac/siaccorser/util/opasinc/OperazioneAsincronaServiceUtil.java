/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.util.opasinc;

import java.util.Date;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciDettaglioOperazioneAsinc;
import it.csi.siac.siaccorser.frontend.webservice.msg.InserisciOperazioneAsinc;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Richiedente;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OperazioneAsincronaServiceUtil {

	public InserisciOperazioneAsinc buildInserisciDettaglioOperazioneAsincronaRequest(
			Account account, AzioneRichiesta azioneRichiesta, Ente ente, Richiedente richiedente) {
		InserisciOperazioneAsinc inserisciOperazioneAsinc = new InserisciOperazioneAsinc();

		inserisciOperazioneAsinc.setAccount(account);
		inserisciOperazioneAsinc.setAzioneRichiesta(azioneRichiesta);
		inserisciOperazioneAsinc.setDataOra(new Date());
		inserisciOperazioneAsinc.setEnte(ente);
		inserisciOperazioneAsinc.setRichiedente(richiedente);

		return inserisciOperazioneAsinc;
	}

	public InserisciDettaglioOperazioneAsinc buildInserisciDettaglioOperazioneAsincronaRequest(
			String codice, String descrizione, String esito, Ente ente, Richiedente richiedente) {
		InserisciDettaglioOperazioneAsinc inserisciDettaglioOperazioneAsinc = new InserisciDettaglioOperazioneAsinc();

		inserisciDettaglioOperazioneAsinc.setDataOra(new Date());
		inserisciDettaglioOperazioneAsinc.setIdEnte(ente.getUid());
		inserisciDettaglioOperazioneAsinc.setRichiedente(richiedente);

		return inserisciDettaglioOperazioneAsinc;
	}
}
