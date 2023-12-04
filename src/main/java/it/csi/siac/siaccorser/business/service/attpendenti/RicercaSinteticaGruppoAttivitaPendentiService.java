/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.attpendenti;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.business.service.BaseCoreService;
import it.csi.siac.siaccorser.frontend.webservice.msg.attpendenti.RicercaSinteticaGruppoAttivitaPendentiVariazione;
import it.csi.siac.siaccorser.frontend.webservice.msg.attpendenti.RicercaSinteticaGruppoAttivitaPendentiVariazioneResponse;
import it.csi.siac.siaccorser.integration.dad.ClassificatoreDad;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.AttivitaPendente;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.model.paginazione.ListaPaginata;
import it.csi.siac.siaccorser.model.paginazione.ParametriPaginazione;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RicercaSinteticaGruppoAttivitaPendentiService extends BaseCoreService<RicercaSinteticaGruppoAttivitaPendentiVariazione, RicercaSinteticaGruppoAttivitaPendentiVariazioneResponse> {
	
	/** The variazioni dad. */
	@Autowired
	private ClassificatoreDad classificatoreDad;
	
	private Account account;
	
	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkCondition(req.isSoloTotali() ^ req.getParametriPaginazione() != null, ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("totale o paginazione"));
		if(!req.isSoloTotali()) {
			checkParametriPaginazione(req.getParametriPaginazione(), false);
		}
		checkNotNull(req.getAnnoBilancio(),ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("anno bilancio"));
		checkNotNull(req.getAzioneConsentita(), ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("azione consentita attivita pendenti"), true);
		checkCondition(req.getAzioneConsentita().getAzione() != null && req.getAzioneConsentita().getAzione().getUid() != 0,  ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("azione consentita attivita pendenti"));
	}
	
	@Override
	protected void init() {
		super.init();
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public RicercaSinteticaGruppoAttivitaPendentiVariazioneResponse executeService(RicercaSinteticaGruppoAttivitaPendentiVariazione serviceRequest) {
		return super.executeService(serviceRequest);
	}
	
	@Override
	protected void execute() {
		res.setAzioneConsentita(req.getAzioneConsentita());
		
		checkAccount();
		
		String stato = caricaStatoCodeByAzione(req.getAzioneConsentita().getAzione());
		List<Integer> uidSACDirezioneCollegate = caricaUidSACDirezioneCollegate();
		caricaTotaliSeNecessario(stato, req.getAzioneConsentita().getAzione(),uidSACDirezioneCollegate);
		caricaListaSeNecessario(stato, req.getAzioneConsentita().getAzione(),uidSACDirezioneCollegate);

	}

	private List<Integer> caricaUidSACDirezioneCollegate() {
		final String methodName ="caricaUidSACDirezioneCollegate";
		if(!req.getAzioneConsentita().getAzione().getFlagVerificaSac()) {
			return null;
		}
		List<Integer> uidsCDR = new ArrayList<Integer>();
				
		classificatoreDad.popolaListaConCDRDirettamenteCollegateAdAccount(account, req.getAnnoBilancio(), uidsCDR);
		classificatoreDad.popolaListaCDRConCDCFigliCollegatiAdAccount(account,  req.getAnnoBilancio(), uidsCDR, uidsCDR);
		return uidsCDR;
	}

	protected void checkAccount() {
		account = coreDad.findAccountById(req.getRichiedente().getAccount().getUid(), req.getAnnoBilancio()); 
		variazioniDad.setEnte(account.getEnte());
		String codiceFiscale = req.getRichiedente().getOperatore().getCodiceFiscale();
		Operatore operatore = account.getOperatore();
		
		if (!operatore.getCodiceFiscale().equals(codiceFiscale)) {
			throw new BusinessException(ErroreCore.OPERAZIONE_NON_CONSENTITA
					.getErrore("L'account non appartiene all'operatore indicato nel richiedente (CF: " + codiceFiscale + ")"));
		}
	}

	private String caricaStatoCodeByAzione(Azione azione) {
		final String methodName ="caricaStatoByAzione";
		 String statoOp = variazioniDad.caricaStatoCodeByAzionePendente(azione);
		 if(StringUtils.isBlank(statoOp)) {
			 log.error(methodName, "impossibile trovare uno stato per l'azione indicata [uid: " + azione.getUid() + " ].");
			 throw new BusinessException(ErroreCore.ERRORE_DI_SISTEMA.getErrore("impossibile trovare uno stato per l'azione indicata"));
		 }
		 return statoOp;
	}

	private void caricaListaSeNecessario(String statoCode, Azione azione, List<Integer> uidSACDirezioneCollegate) {
		if(req.getParametriPaginazione() == null) {
			return;
		}
		ListaPaginata<AttivitaPendente> attivitaPendenti = variazioniDad.caricaAttivitaPendenti(statoCode, azione,uidSACDirezioneCollegate, req.getAnnoBilancio().toString(), req.getRichiedente().getAccount().getUid(), req.getParametriPaginazione());
		if(attivitaPendenti == null || attivitaPendenti.isEmpty()) {
			return;
		}
		res.setAttivitaPendenti(attivitaPendenti);
	}

	private void caricaTotaliSeNecessario(String statoCode, Azione azione, List<Integer> uidSACDirezioneCollegate ) {
		if(!req.isSoloTotali()) {
			return;
		}
		Long countTotale = variazioniDad.caricaTotaleGruppoAttivitaPendenti(statoCode, Boolean.TRUE.equals(azione.getFlagVerificaSac()), uidSACDirezioneCollegate,  req.getAnnoBilancio().toString());
		res.setTotali(countTotale != null? countTotale.intValue() : 0);
	}
	
	private void checkParametriPaginazione(ParametriPaginazione parametriPaginazione, boolean toThrow) throws ServiceParamError {
		checkNotNull(parametriPaginazione, ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("parametri paginazione"), toThrow);
		checkCondition(parametriPaginazione == null || parametriPaginazione.getNumeroPagina() >= 0, ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("numero pagina parametri paginazione"), toThrow);
		checkCondition(parametriPaginazione == null || parametriPaginazione.getElementiPerPagina() > 0, ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("elementi per pagina parametri paginazione"), toThrow);
	}

}
