/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTBil;

/**
 * Implementazione del DAO per AnnoBilancio. ATTENZIONE i Dao sollevano
 * della unchecked exceptions: per catturarle occorre catturare le
 * RuntimeException
 * 
 * @author 
 */
@Component
@Transactional
public class SiacTBilDaoImpl extends JpaDao<SiacTBil,Integer> implements SiacTBilDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SiacTBil> findAnniBilancioByEnte(int idEnte) {

		List<SiacTBil> anniBilancio = new ArrayList<SiacTBil>();
		Query query = entityManager
				.createQuery("SELECT a FROM SiacTBil a "
						+ " WHERE a.ente.enteProprietarioId = :idEnte "
						+ " AND a.dataCancellazione is null "
						+ " ORDER BY a.periodo.anno DESC "
						+ "" );
		query.setParameter("idEnte",idEnte);
		List<SiacTBil> results = query.getResultList();
		for(SiacTBil result:results) {
			anniBilancio.add(result);
		}
		return anniBilancio;
	}

}
