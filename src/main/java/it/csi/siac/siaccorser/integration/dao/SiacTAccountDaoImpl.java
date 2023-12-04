/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTAccount;

/**
 * Implementazione del DAO per Account. ATTENZIONE i Dao sollevano
 * della unchecked exceptions: per catturarle occorre catturare le
 * RuntimeException
 * 
 * @author 
 */

@Component
@Transactional
public class SiacTAccountDaoImpl extends JpaDao<SiacTAccount, Integer> implements SiacTAccountDao {

	
	
	@Override
	public SiacTAccount saveAccount(SiacTAccount siacTAccount) {
		entityManager.persist(siacTAccount);
		return siacTAccount;
	}

	@Override
	public SiacTAccount deleteAccount(SiacTAccount siacTAccount) {
		siacTAccount.setDataFineValidita(new Date());
		entityManager.persist(siacTAccount);
		return siacTAccount;
	}

	@Override
	public SiacTAccount findAccountById(int uuid) {
		return entityManager.find(SiacTAccount.class, uuid);
	}
	
	
}
