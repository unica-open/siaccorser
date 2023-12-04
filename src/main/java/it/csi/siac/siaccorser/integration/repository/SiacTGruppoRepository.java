/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacTGruppo;

/**
 * Public Interface SiacTAccountRepository
 * 
 * @author AR, 1513
 * 
 */
public interface SiacTGruppoRepository extends JpaRepository<SiacTGruppo, Integer> {
	/**
	 * Ricerca dei gruppi legati all'account
	 *  
	 * @param accountId
	 * @return List<SiacTGruppo>
	 */
	@Query("SELECT distinct g FROM SiacTGruppo g  " +
    	" 		LEFT OUTER JOIN g.ruoli rGruppoRuoloOp LEFT OUTER JOIN rGruppoRuoloOp.ruolo ruoloOp " +
	//	" 		LEFT OUTER JOIN g.siacRGruppoRuoloOpCassaEcons rGruppoRuoloOpCassaEcons LEFT OUTER JOIN rGruppoRuoloOpCassaEcons.siacDRuoloOp ruoloOpCassa " +
			" , in(g.siacRGruppoAccounts) rGruppoAcc" +
			" WHERE rGruppoAcc.siacTAccount.uid =:accountId AND " +
			" 		rGruppoAcc.ente = g.ente AND " +
			" 		rGruppoAcc.dataCancellazione is null AND g.dataCancellazione is null  " )
	List<SiacTGruppo> findGruppiByIdAccount(@Param("accountId") Integer accountId);
	

}
