/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dad;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccorser.integration.dao.SiacTOperazioneAsincronaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTAccount;
import it.csi.siac.siaccorser.integration.entity.SiacTAzione;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siaccorser.integration.entity.SiacTOperazioneAsincrona;
import it.csi.siac.siaccorser.integration.entity.SiacTOperazioneAsincronaDet;
import it.csi.siac.siaccorser.integration.entity.SiacTVariazione;
import it.csi.siac.siaccorser.integration.entity.enumeration.SiacTOperazioneAsincronaEnum;
import it.csi.siac.siaccorser.integration.entitymapping.CorMapId;
import it.csi.siac.siaccorser.integration.repository.SiacTOperazioneAsincronaRepository;
import it.csi.siac.siaccorser.model.DettaglioOperazioneAsincrona;
import it.csi.siac.siaccorser.model.NotificaOperazioneAsincrona;
import it.csi.siac.siaccorser.model.OperazioneAsincrona;
import it.csi.siac.siaccorser.model.paginazione.ListaPaginata;
import it.csi.siac.siaccorser.model.paginazione.ParametriPaginazione;
import it.csi.siac.siaccorser.model.ricerca.CriteriRicercaOperazioneAsincrona;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class OperazioneAsincronaDad extends BaseCoreDad {

	@Autowired
	private SiacTOperazioneAsincronaDao operazioneAsincronaDao;
	
	@Autowired
	private SiacTOperazioneAsincronaRepository operazioneAsincronaRepository;
	

	public Integer inserisciOperazioneAsinc(
			Integer accountId, Integer azioneId,Integer variazioneId, Integer enteId, String loginOperazione) {
		String methodName ="inserisciOperazioneAsinc";
		SiacTOperazioneAsincrona op = new SiacTOperazioneAsincrona();
		
		Date now = new Date();
		
		op.setOpasAvvio(now);
		op.setDataCreazione(now);
		op.setDataModifica(now);
		
		op.setOpasStato(SiacTOperazioneAsincronaEnum.OPASINC_AVVIATA.getCodice());
		op.setOpasMsg(SiacTOperazioneAsincronaEnum.OPASINC_MESSAGGIO.getCodice()+" " + SiacTOperazioneAsincronaEnum.OPASINC_AVVIATA.getCodice());
		op.setOpasMsgNotificato(Boolean.FALSE);
		
		SiacTAccount accountDto = new SiacTAccount();
		accountDto.setUid(accountId);
		op.setSiacTAccount(accountDto);
		
		SiacTAzione azioneDto = new SiacTAzione();
		azioneDto.setUid(azioneId);
		op.setSiacTAzione(azioneDto);
		
		SiacTEnteProprietario enteDto = new SiacTEnteProprietario();
		enteDto.setUid(enteId);
		op.setEnte(enteDto);
		
		if(variazioneId != null && variazioneId.intValue() != 0) {
			SiacTVariazione siacTVariazione = new SiacTVariazione();
			siacTVariazione.setUid(variazioneId);
			op.setSiacTVariazione(siacTVariazione);
		}
		
		
		op.setLoginOperazione(loginOperazione);
		
		Integer idOpAsinc = operazioneAsincronaDao.inserisciOperazioneAsinc(op);
		log.debug(methodName, "InseritaOperazioneAsinc ID: " + idOpAsinc);
		
		return idOpAsinc;
	}

	
	
	public void inserisciDettaglioOperazioneAsinc(
			Integer idOpAsinc, 
			String codice,
			String descrizione,
			String esito, 
			String msgErrore,
			String serviceResponse,
			Integer idEnte, String loginOperazione) {
		String methodName ="inserisciDettaglioOperazioneAsinc";
		log.debug(methodName, "Start");
		SiacTOperazioneAsincronaDet opdett = new SiacTOperazioneAsincronaDet();
		
		SiacTOperazioneAsincrona op = new SiacTOperazioneAsincrona();
		op.setUid(idOpAsinc);
		opdett.setSiacTOperazioneAsincrona(op);
		
		//SIAC-7232
		opdett.setDataModificaInserimento(new Date());
		
		opdett.setOpasDetCode(codice);
		opdett.setOpasDetDesc(descrizione);
		opdett.setOpasDetEsito(esito);
		opdett.setOpasDetMsg(msgErrore);
		opdett.setLoginOperazione(loginOperazione);
		opdett.setServiceResponse(serviceResponse);
		
		SiacTEnteProprietario ente = new SiacTEnteProprietario();
		ente.setUid(idEnte);
		opdett.setEnte(ente);

		
		operazioneAsincronaDao.inserisciDettaglioOperazioneAsinc(opdett);
		log.debug(methodName, "End");
	}

	

	public OperazioneAsincrona controllaStatoOperazioneAsinc(Integer idOpAsinc) {
		
		String methodName = "controllaStatoOperazioneAsinc";
		
		log.debug(methodName, "Start");
		OperazioneAsincrona op = new OperazioneAsincrona();
		SiacTOperazioneAsincrona opAsinc = operazioneAsincronaDao.findById(idOpAsinc);

		if (opAsinc !=null) {
			op.setMessaggio(opAsinc.getOpasMsg());
			op.setStato(opAsinc.getOpasStato());
				
		}
		log.debug(methodName, "End");
		return op;
	}
	
	public ListaPaginata<NotificaOperazioneAsincrona> getNotificheOperazioneAsincrona(CriteriRicercaOperazioneAsincrona criteriRicerca, ParametriPaginazione pp) {
		
		// Si ordina per uid descrescente
		Page<SiacTOperazioneAsincrona> result =  operazioneAsincronaDao.findMsgOpAsincDaNotificare(criteriRicerca.getAccountId(), 
				criteriRicerca.getAzioneId(), 
				criteriRicerca.getEnteProprietarioId(), 
				criteriRicerca.getDataDa(),
				criteriRicerca.getDataA(),
				criteriRicerca.getCodiceStato(),
				criteriRicerca.getFlagAltriUtenti(),
				toPageable(pp));
		
		ListaPaginata<NotificaOperazioneAsincrona> pages = super.toListaPaginata(result, NotificaOperazioneAsincrona.class, CorMapId.SiacTOperazioneAsincrona_NotificaOperazioneAsincrona);
		
		for (int i = 0; i< pages.size() ; i++) {
			
			List<Object[]> totali =operazioneAsincronaRepository.getCountDettaglioOpAsincGroupByEsito(pages.get(i).getUid());
	 		
			if(totali != null && !totali.isEmpty()){
				for(Object[] obj : totali) {
					
					if(((String)obj[0]).equalsIgnoreCase(SiacTOperazioneAsincronaEnum.OPASINC_ESITO_FALLIMENTO.getCodice()))
						pages.get(i).setTotaleFalliti(Integer.valueOf(((Long)obj[1]).intValue()));
					
					if(((String)obj[0]).equalsIgnoreCase(SiacTOperazioneAsincronaEnum.OPASINC_ESITO_SUCCESS.getCodice()))
						pages.get(i).setTotaleSuccess(Integer.valueOf(((Long)obj[1]).intValue()));
					
				}
				
				
			}else{
				
				pages.get(i).setTotaleFalliti(0);
				pages.get(i).setTotaleSuccess(0);
			}
			
					
	 	}
		
		return pages;
		
		
	}
	
	public ListaPaginata<DettaglioOperazioneAsincrona> getDettaglioNotificaOperazioneAsincrona(Integer idOpAsinc, String codice, ParametriPaginazione pp) {
		

		Page<SiacTOperazioneAsincronaDet> result = operazioneAsincronaRepository.findDettaglioByIdOpAsinc(idOpAsinc, codice, toPageable(pp, new Sort(Direction.DESC, "uid")));

		return super.toListaPaginata(result, DettaglioOperazioneAsincrona.class, CorMapId.SiacTOperazioneAsincronaDet_DettaglioOperazioneAsincrona);
	}
	
	
	
	public void aggiornaOperazioneAsinc(
			Integer idOperazioneAsinc, String stato, Integer idEnte) {
		SiacTOperazioneAsincrona op = new SiacTOperazioneAsincrona();

		Date now = new Date();
		op.setOpasFine(now);
		op.setDataModifica(now);
		op.setUid(idOperazioneAsinc);
		
	
		op.setOpasStato(stato);
		op.setOpasMsg(SiacTOperazioneAsincronaEnum.OPASINC_MESSAGGIO.getCodice() + " " + stato);
		op.setOpasMsgNotificato(Boolean.TRUE);

		op.setLoginOperazione("admin");
		SiacTEnteProprietario ente = new SiacTEnteProprietario();
		ente.setUid(idEnte);
		op.setEnte(ente);
		
		op.setUid(idOperazioneAsinc);
		
		operazioneAsincronaDao.aggiornaOperazioneAsinc(op);
		
	}


}
