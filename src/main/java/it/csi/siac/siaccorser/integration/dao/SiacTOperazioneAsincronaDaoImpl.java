/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTOperazioneAsincrona;
import it.csi.siac.siaccorser.integration.entity.SiacTOperazioneAsincronaDet;
import it.csi.siac.siaccorser.integration.entity.enumeration.SiacTOperazioneAsincronaEnum;
import it.csi.siac.siaccorser.integration.repository.SiacTOperazioneAsincronaRepository;

/**
 * Implementazione del DAO per OperazioneAsincrona. 
 * 
 * @author rmontuori
 */
@Component
@Transactional
public class SiacTOperazioneAsincronaDaoImpl extends JpaDao<SiacTOperazioneAsincrona, Integer> implements SiacTOperazioneAsincronaDao {

	@Autowired
	private SiacTOperazioneAsincronaRepository siacTOperazioneAsincronaRepository;
	
	@Override
	public Integer inserisciOperazioneAsinc(SiacTOperazioneAsincrona opAsinc) {
		final String methodName = "inserisciOperazioneAsinc";
		try {
			log.trace(methodName, "inizio");
			
			opAsinc.setUid(null);
			entityManager.persist(opAsinc);
			
			log.debug(methodName, "save successful");
		} catch (RuntimeException re) {
			log.error(methodName, "save failed", re);
			throw re;
		} finally {
			log.debug(methodName, "fine");
		}
		
		return opAsinc.getUid();
	}
	
	
	@Override
	public void inserisciDettaglioOperazioneAsinc(SiacTOperazioneAsincronaDet opAsincDet) {
		final String methodName = "inserisciDettaglioOperazioneAsinc";
		try {
			log.trace(methodName, "inizio");
			
			opAsincDet.setUid(null);
			entityManager.persist(opAsincDet);
			
			log.debug(methodName, "save successful");
		} catch (RuntimeException re) {
			log.error(methodName, "save failed", re);
			throw re;
		} finally {
			log.debug(methodName, "fine");
		}
	}
	

	@Override
	public SiacTOperazioneAsincrona aggiornaOperazioneAsinc(SiacTOperazioneAsincrona entity) {
		
		final String methodName = "aggiornaOperazioneAsinc";
		try {
			log.trace(methodName, "inizio");
			SiacTOperazioneAsincrona result = super.update(entity);
			log.debug(methodName, "update successful");
			return result;
		} catch (RuntimeException re) {
			log.error(methodName, "update failed", re);
			throw re;
		} finally {
			log.debug(methodName, "fine");
		}
				
	}

	@Override
	public List<SiacTOperazioneAsincrona> findOperazioneAsincById(Integer idOpAsinc){
				
		final String methodName = "findOperazioneAsincByIdEStato";
		try {
			log.debug(methodName, "inizio");
			TypedQuery<SiacTOperazioneAsincrona> query = entityManager
					.createQuery("select opasinc from SiacTOperazioneAsincrona opasinc " +
							" where opasinc.uid=:idOpAsinc and (opasinc.opasStato=:stato1 or opasinc.opasStato=:stato2)", SiacTOperazioneAsincrona.class);
			query.setParameter("idOpAsinc", idOpAsinc);
			query.setParameter("stato1", SiacTOperazioneAsincronaEnum.OPASINC_CONCLUSA.getCodice());
			query.setParameter("stato2", SiacTOperazioneAsincronaEnum.OPASINC_ERRORE.getCodice());
			List<SiacTOperazioneAsincrona> ops = query.getResultList();
			
			return ops;

		} catch (RuntimeException re) {
			log.error(methodName, "findById failed", re);
			throw re;
		} finally {
			log.debug(methodName, "fine");
		}
		
	}

	@Override
	public SiacTOperazioneAsincrona findByIdEStato(Integer uid) {
		
		return siacTOperazioneAsincronaRepository.findOne(uid, 
				SiacTOperazioneAsincronaEnum.OPASINC_CONCLUSA.getCodice(), SiacTOperazioneAsincronaEnum.OPASINC_ERRORE.getCodice());
		
	}


	@Override
	public Page<SiacTOperazioneAsincrona> findMsgOpAsincDaNotificare(
			Integer idAccount, Integer idAzione, Integer idEnte, String dataDa,
			String dataA, String codiceStato, Boolean flagAltriUtenti,
			Pageable pageable) {
		final String methodName = "findMsgOpAsincDaNotificare";

		
		StringBuilder jpql = new StringBuilder();
		Map<String,Object> param = new HashMap<String, Object>();
				
		jpql.append("SELECT op FROM SiacTOperazioneAsincrona op "
						+ " WHERE op.siacTAzione.uid = :idAzione "
						+ " AND op.ente.enteProprietarioId = :idEnte ");

			
		// se flag è FALSE aggiungo l'account
		if(!flagAltriUtenti){
			jpql.append(" AND op.siacTAccount.uid = :idAccount ");
			param.put("idAccount", idAccount);
		}
		
		
		if(dataA == null && dataDa!=null){
			jpql.append(" AND op.opasFine = to_date(:dataDa,'dd/MM/yyyy') ");
			param.put("dataDa", dataDa);
		}
		
		if(dataDa!=null && dataA!=null){
			
			jpql.append(" AND date_trunc('day', op.opasFine) " +
					" BETWEEN date_trunc('day', to_timestamp(:dataDa,'dd/mm/yyyy')) " +
					" and date_trunc('day',to_timestamp(:dataA,'dd/MM/yyyy'))");
			
			param.put("dataDa", dataDa);
			param.put("dataA", dataA);
		}
		jpql.append(" AND op.opasFine is not null ");
		
		if(!StringUtils.isEmpty(codiceStato)){
			jpql.append(" AND op.opasStato = :stato ");
			param.put("stato", codiceStato);
		}else{
			jpql.append(" AND (op.opasStato = :statoErrore or op.opasStato = :statoConclusa)");
			param.put("statoErrore", SiacTOperazioneAsincronaEnum.OPASINC_ERRORE.getCodice());
			param.put("statoConclusa", SiacTOperazioneAsincronaEnum.OPASINC_CONCLUSA.getCodice());
		}
		
		jpql.append(" order by op.uid desc");
			
		param.put("idEnte", idEnte);
		param.put("idAzione", idAzione);
		
		log.debug(methodName, "Parametri ente: " + idEnte);
		log.debug(methodName, "Parametri idAccount: " + idAccount);
		log.debug(methodName, "Parametri idAzione: " + idAzione);
		
				
		return getPagedList(jpql.toString(), param, pageable);
	}
	
}
