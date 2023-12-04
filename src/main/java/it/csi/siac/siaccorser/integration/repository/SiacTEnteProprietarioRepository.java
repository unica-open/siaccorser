/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;

public interface SiacTEnteProprietarioRepository extends JpaRepository<SiacTEnteProprietario, Integer> {
	
	@Query("SELECT e FROM SiacTEnteProprietario e "
			+ " WHERE e.codiceFiscale=:codiceFiscale AND e.dataCancellazione IS NULL " +
			" AND e.dataFineValidita IS NULL ")
	SiacTEnteProprietario ricercaEnte(
			@Param("codiceFiscale") String codiceFiscale);
	
}
