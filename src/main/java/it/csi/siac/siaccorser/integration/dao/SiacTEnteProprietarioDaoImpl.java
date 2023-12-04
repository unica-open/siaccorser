/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;

/**
 * Implementazione del DAO per Ente. ATTENZIONE i Dao sollevano
 * della unchecked exceptions: per catturarle occorre catturare le
 * RuntimeException
 * 
 * @author 
 */
@Component
@Transactional
public class SiacTEnteProprietarioDaoImpl extends JpaDao<SiacTEnteProprietario, Integer> implements SiacTEnteProprietarioDao {

	@Override
	public SiacTEnteProprietario saveEnte(SiacTEnteProprietario siacTEnteProprietario) {
		final String methodName = "saveEnte";
		entityManager.persist(siacTEnteProprietario);
		log.debug(methodName, siacTEnteProprietario.toString());
		return siacTEnteProprietario;
	}

	@Override
	public SiacTEnteProprietario deleteEnte(SiacTEnteProprietario siacTEnteProprietario) {
		final String methodName = "deleteEnte";
		siacTEnteProprietario.setDataFineValidita(new Date());
		entityManager.persist(siacTEnteProprietario);
		log.debug(methodName, siacTEnteProprietario.toString());
		return siacTEnteProprietario;
	}

	@Override
	public SiacTEnteProprietario findEnteById(int uid) {
		final String methodName = "findEnteById";
		SiacTEnteProprietario siacTEnteProprietario = entityManager.find(SiacTEnteProprietario.class, uid);
		String found="Ente not found";
		if (siacTEnteProprietario!=null) {
			found = siacTEnteProprietario.toString();
		}
		log.debug(methodName, found);
		return siacTEnteProprietario;
	}
}
