/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTSoggetto;

/**
 * Implementazione del DAO per Soggetto. ATTENZIONE i Dao sollevano
 * della unchecked exceptions: per catturarle occorre catturare le
 * RuntimeException
 * 
 * @author 
 */
@Component
@Transactional
public class SiacTSoggettoDaoImpl extends JpaDao<SiacTSoggetto, Integer> implements SiacTSoggettoDao {

	@Override
	public SiacTSoggetto saveSoggetto(SiacTSoggetto siacTSoggetto) {
		final String methodName = "saveSoggetto";
		entityManager.persist(siacTSoggetto);
		log.debug(methodName, siacTSoggetto.toString());
		return siacTSoggetto;
	}

	@Override
	public SiacTSoggetto deleteSoggetto(SiacTSoggetto siacTSoggetto) {
		final String methodName = "deleteSoggetto";
		siacTSoggetto.setDataFineValidita(new Date());
		entityManager.persist(siacTSoggetto);
		log.debug(methodName, siacTSoggetto.toString());
		return siacTSoggetto;
	}

	@Override
	public SiacTSoggetto findSoggettoById(int uid) {
		final String methodName = "findSoggettoById";
		SiacTSoggetto siacTSoggetto = entityManager.find(SiacTSoggetto.class, uid);
		String found="Soggetto not found";
		if (siacTSoggetto!=null) {
			found = siacTSoggetto.toString();
		}
		log.debug(methodName, found);
		return siacTSoggetto;
	}
	
	@Override
	public List<SiacTSoggetto> findSoggettiByCodiceFiscale(String codiceFiscale) {
		TypedQuery<SiacTSoggetto> query = entityManager
				.createQuery("select s  from SiacTSoggetto s " +
						"where s.codiceFiscale = :codiceFiscale", SiacTSoggetto.class);
		query.setParameter("codiceFiscale", codiceFiscale);
		return query.getResultList();
	}
}
