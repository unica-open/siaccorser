/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacTParametroConfigEnte;

public interface SiacTParametroConfigEnteRepository extends JpaRepository<SiacTParametroConfigEnte, Integer> {
	
	@Query("FROM SiacTParametroConfigEnte tpce "
			+ " WHERE tpce.ente.enteProprietarioId=:idEnte "
			+ " AND tpce.parametroNome=:nomeParametro "
			+ " AND (CAST(:abilitato AS boolean) IS NULL OR tpce.parametroAbilitato=:abilitato) "
			+ " AND tpce.dataCancellazione IS NULL "
			+ " AND tpce.dataInizioValidita < CURRENT_TIMESTAMP "
			+ " AND (tpce.dataFineValidita IS NULL OR tpce.dataFineValidita > CURRENT_TIMESTAMP) "
	)
	SiacTParametroConfigEnte find(
			@Param("idEnte") Integer idEnte,
			@Param("nomeParametro") String nomeParametro,
			@Param("abilitato") Boolean abilitato
	);	

	@Query("FROM SiacTParametroConfigEnte tpce "
			+ " WHERE tpce.ente.enteProprietarioId=:idEnte "
			+ " AND (CAST(:abilitato AS boolean) IS NULL OR tpce.parametroAbilitato=:abilitato) "
			+ " AND tpce.dataCancellazione IS NULL "
			+ " AND tpce.dataInizioValidita < CURRENT_TIMESTAMP "
			+ " AND (tpce.dataFineValidita IS NULL OR tpce.dataFineValidita > CURRENT_TIMESTAMP) "
	)
	List<SiacTParametroConfigEnte> findAll(
			@Param("idEnte") Integer idEnte,
			@Param("abilitato") Boolean abilitato
	);	
}
