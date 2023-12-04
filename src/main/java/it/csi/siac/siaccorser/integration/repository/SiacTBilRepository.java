/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacTBil;

public interface SiacTBilRepository extends JpaRepository<SiacTBil, Integer> {
	
	@Query("SELECT b FROM SiacTBil b "
			+ " WHERE b.periodo.anno=:anno " +
			" AND b.ente.enteProprietarioId=:idEnte " +
			" AND b.dataCancellazione IS NULL " +
			" AND b.dataFineValidita IS NULL ")
	SiacTBil ricercaBilancio(
			@Param("idEnte") Integer idEnte,
			@Param("anno") String anno
			);
	}
