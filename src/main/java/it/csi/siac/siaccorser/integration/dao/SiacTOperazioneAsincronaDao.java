/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.csi.siac.siaccommonser.integration.dao.base.Dao;
import it.csi.siac.siaccorser.integration.entity.SiacTOperazioneAsincrona;
import it.csi.siac.siaccorser.integration.entity.SiacTOperazioneAsincronaDet;



/**
 * Dao per l'accesso all'entity OperazioneAsincronaDao
 * 
 * @author rmontuori
 *
 */
public interface SiacTOperazioneAsincronaDao extends Dao<SiacTOperazioneAsincrona, Integer>{
	
	
	Integer inserisciOperazioneAsinc(SiacTOperazioneAsincrona opAsinc);
	
	void inserisciDettaglioOperazioneAsinc(SiacTOperazioneAsincronaDet opAsincDet);
	
	SiacTOperazioneAsincrona aggiornaOperazioneAsinc(SiacTOperazioneAsincrona opAsinc);
	
	SiacTOperazioneAsincrona findByIdEStato(Integer idOpAsinc);
	
	List<SiacTOperazioneAsincrona> findOperazioneAsincById(Integer idOpAsinc);
	
	Page<SiacTOperazioneAsincrona> findMsgOpAsincDaNotificare(Integer idAccount,Integer idAzione,Integer idEnte, 
			String dataDa, String dataA, String codiceStato, Boolean flagAltriUtenti, Pageable pageable);
	
	
}
