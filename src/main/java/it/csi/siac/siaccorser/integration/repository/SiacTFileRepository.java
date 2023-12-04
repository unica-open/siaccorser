/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siaccorser.integration.entity.SiacTFile;

public interface SiacTFileRepository extends JpaRepository<SiacTFile, Integer> {
	@Query("SELECT f FROM SiacTFile f "
			+ "LEFT OUTER JOIN f.stati s "
			+ " WHERE f.ente=:ente AND f.dataCancellazione IS NULL "
			+ " AND s.ente=:ente AND s.dataCancellazione IS NULL "
			+ " AND f.uid=:uid ")
	SiacTFile find(
			@Param("ente") SiacTEnteProprietario ente,
			@Param("uid") Integer uid);
	

	@Query("SELECT f FROM SiacTFile f "
			+ "INNER JOIN f.stati fs "
			+ " WHERE f.ente=:ente AND f.dataCancellazione IS NULL "
			+ " AND fs.ente=:ente AND fs.dataCancellazione IS NULL "
	       	+ " AND (COALESCE(:stato, '')='' OR fs.stato.codice=CAST(:stato AS text)) "
			+ " AND (COALESCE(:nome, '')='' OR UPPER(f.nome) LIKE UPPER(CAST(:nome AS text) || '%')) "
			+ " AND (:uid IS NULL OR f.uid=CAST(CAST(:uid AS string) AS integer)) "
			+ " AND (COALESCE(:codice, '')='' OR UPPER(f.codice)=UPPER(CAST(:codice AS text))) "
			+ " AND (:idTipo IS NULL OR f.tipo.uid=CAST(CAST(:idTipo AS string) AS integer)) "
			+ " AND (COALESCE(:dataUpload, '')='' OR DATE_TRUNC('day', f.dataCreazione)=TO_TIMESTAMP(:dataUpload, 'DD/MM/YYYY')) ")
	Page<SiacTFile> ricercaFile(
			@Param("ente") SiacTEnteProprietario ente,
			@Param("uid") Integer uid, 
			@Param("codice") String codice, 
			@Param("nome") String nome, 
			@Param("idTipo") Integer idTipo,
			@Param("stato") String stato,
			@Param("dataUpload") String dataUpload, 
			Pageable pageable);


	@Modifying
	@Query("UPDATE SiacTFile f "
			+ " SET f.dataCancellazione=NOW(), f.loginOperazione=:loginOperazione"
			+ " WHERE f.uid=:uid ")
	void eliminaFile(
			@Param("uid") Integer uid, 
			@Param("loginOperazione") String loginOperazione);
	
	
}
