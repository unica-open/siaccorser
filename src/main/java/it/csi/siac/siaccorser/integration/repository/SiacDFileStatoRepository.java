/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacDFileStato;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;

public interface SiacDFileStatoRepository extends JpaRepository<SiacDFileStato, Integer> {
	@Query("SELECT fs FROM SiacDFileStato fs "
			+ " WHERE fs.ente=:ente AND fs.dataCancellazione IS NULL ")
	List<SiacDFileStato> getElencoStatoFile(
			@Param("ente") SiacTEnteProprietario ente
	);
	
	
	@Query("SELECT fs FROM SiacDFileStato fs "
			+ " WHERE fs.codice=:codice "
			+ " AND fs.ente=:ente AND fs.dataCancellazione IS NULL ")
	SiacDFileStato getStatoByCodice(
			@Param("ente") SiacTEnteProprietario ente,
			@Param("codice") String codice
	);
}
