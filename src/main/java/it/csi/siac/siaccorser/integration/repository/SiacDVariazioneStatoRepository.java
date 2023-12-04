/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacDVariazioneStato;

public interface SiacDVariazioneStatoRepository extends JpaRepository<SiacDVariazioneStato, Integer> {

	@Query("FROM SiacDVariazioneStato st "
			+ "WHERE st.ente.enteProprietarioId = :enteProprietarioUid "
			+ "AND st.dataCancellazione IS NULL " 
			+ "AND st.siacTAzionePendente.uid = :azioneUid "
			+ "AND st.dataInizioValidita < CURRENT_TIMESTAMP "
			+ "AND (st.dataFineValidita IS NULL OR st.dataFineValidita > CURRENT_TIMESTAMP) ")
	List<SiacDVariazioneStato> findByAzionePendenteUid(@Param("azioneUid") Integer azioneUid, @Param("enteProprietarioUid") Integer enteProprietarioUid);

}
