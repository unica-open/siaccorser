/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacRAccountClass;
import it.csi.siac.siaccorser.integration.entity.SiacTAccount;

/**
 * Public Interface SiacTAccountRepository
 * 
 * @author AR, 1513
 * 
 */
public interface SiacTAccountRepository extends JpaRepository<SiacTAccount, Integer> {
	
	@Query("SELECT a FROM SiacTAccount a " + " WHERE a.codice=:codice "
			+ " AND a.dataCancellazione IS NULL " + " AND a.dataFineValidita IS NULL ")
	SiacTAccount ricercaAccount(@Param("codice") String codice);

	/**
	 * Ricerca dell'account by id  
	 * 
	 * @param accountId
	 * @return
	 */
	@Query("SELECT distinct a FROM SiacTAccount a  " +
			" LEFT OUTER JOIN a.ruoli ar WITH ar.dataCancellazione IS NULL " +
			" LEFT OUTER JOIN ar.ruolo r WITH r.dataCancellazione IS NULL " +
			" LEFT OUTER JOIN a.siacRAccountClasses asac WITH asac.dataCancellazione IS NULL " +
			" LEFT OUTER JOIN asac.strutturaAmministrativoContabile sac WITH sac.dataCancellazione IS NULL " +
			" LEFT OUTER JOIN a.siacRAccountCassaEcons acec WITH acec.dataCancellazione IS NULL " +
			" LEFT OUTER JOIN acec.cassaEconomale cec WITH cec.dataCancellazione IS NULL " +
			" WHERE a.uid = :accountId and a.dataCancellazione IS NULL  ")
	SiacTAccount findAccountById(@Param("accountId") Integer accountId);

	@Query("SELECT rc " + 
			" FROM SiacRAccountClass rc  " +
			" WHERE rc.account.uid = :accountId " + 
			" AND rc.account.dataCancellazione IS NULL  " + 
			" AND rc.dataCancellazione IS NULL " + 
			" AND (YEAR(CURRENT_DATE)!=:anno AND YEAR(rc.dataInizioValidita)<=:anno OR YEAR(CURRENT_DATE)=:anno AND DATE_TRUNC('day', rc.dataInizioValidita) <= CURRENT_DATE) " +
			"AND (rc.dataFineValidita IS NULL OR :anno<YEAR(CURRENT_DATE) AND rc.dataFineValidita>=DATE_TRUNC('day',TO_TIMESTAMP(CONCAT(:anno, ' 12 31'), 'YYYY MM DD')) OR :anno>=YEAR(CURRENT_DATE) AND DATE_TRUNC('day', rc.dataFineValidita)>=CURRENT_DATE)"
			)
	List<SiacRAccountClass> findSiacRAccountClassesValidi(@Param("accountId") Integer accountId, @Param("anno") Integer anno);
	
	
		

}
