/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacRGruppoRuoloOp;

/**
 * Public Interface SiacRGruppoRuoloOpRepository
 * 
 * @author 1513
 * 
 */
public interface SiacRGruppoRuoloOpRepository extends JpaRepository<SiacRGruppoRuoloOp, Integer> {
	@Query("SELECT rGruppoRuoloOp FROM SiacRGruppoRuoloOp rGruppoRuoloOp WHERE " +
			" rGruppoRuoloOp.gruppo.uid=:idGruppo AND rGruppoRuoloOp.ente.enteProprietarioId =:idEnte AND rGruppoRuoloOp.dataCancellazione IS NULL and "+
			" rGruppoRuoloOp.ente = rGruppoRuoloOp.ruolo.ente and rGruppoRuoloOp.ruolo.dataCancellazione is null") 
	List<SiacRGruppoRuoloOp> findRelGruppoRuoliOpByIdGruppo(@Param("idGruppo") Integer idGruppo,@Param("idEnte") Integer idEnte);

}
