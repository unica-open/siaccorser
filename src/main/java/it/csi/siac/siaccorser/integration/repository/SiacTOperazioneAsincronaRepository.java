/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacTOperazioneAsincrona;
import it.csi.siac.siaccorser.integration.entity.SiacTOperazioneAsincronaDet;

public interface SiacTOperazioneAsincronaRepository extends JpaRepository<SiacTOperazioneAsincrona, Integer> {

	@Query("SELECT opasinc FROM SiacTOperazioneAsincrona opasinc "
			+ " WHERE opasinc.uid = :uid and "
			+ " (opasinc.opasStato=:statoConcluso or opasinc.opasStato=:statoErrore)") 
	SiacTOperazioneAsincrona findOne(@Param("uid") Integer uid, @Param("statoConcluso") String stato1, @Param("statoErrore") String stato2);


	@Query(" SELECT opdett.opasDetEsito , COUNT(*) "
			+" FROM SiacTOperazioneAsincronaDet opdett " 
			+" WHERE opdett.siacTOperazioneAsincrona.uid=:idOpAsinc "
			+" GROUP BY opdett.opasDetEsito ")
	List<Object[]> getCountDettaglioOpAsincGroupByEsito(@Param("idOpAsinc") Integer idOpAsinc);
	
	
	@Query(" SELECT COUNT(*) "
			+" FROM SiacTOperazioneAsincrona op " 
			+" WHERE op.siacTAccount.uid = :idAccount "
			+ " AND op.siacTAzione.uid = :idAzione "
			+ " AND op.ente.enteProprietarioId = :idEnte "
			+ " AND op.opasFine is not null " 
			+ "and op.opasMsgNotificato is True " )
	Long getCountOperazioneAsincronaDaNotificare(
			@Param("idAccount") Integer idAccount,
			@Param("idAzione") Integer idAzione, 
			@Param("idEnte") Integer idEnte
			);
	
		
	/**
	 * Ricerca le operazioni asincrone per idAzione e idAccount
	 * @param idAccount
	 * @param idAzione
	 * @param idEnte
	 * @param pageable
	 * @return
	 */
	@Query("SELECT op FROM SiacTOperazioneAsincrona op "
			+ " WHERE op.siacTAccount.uid = :idAccount "
			+ " AND op.siacTAzione.uid = :idAzione "
			+ " AND op.ente.enteProprietarioId = :idEnte "
			+ " AND op.opasFine is not null and op.opasMsgNotificato is True " 
			//+ " ORDER BY op.siacTAzione.titolo ASC, op.opasAvvio ASC " //non si puo fare qui, ma nel Pageable. Vedi il dad
			+ "" )
	Page<SiacTOperazioneAsincrona> findMsgOpAsincDaNotificare(
			@Param("idAccount") Integer idAccount,
			@Param("idAzione") Integer idAzione, 
			@Param("idEnte") Integer idEnte, 
			Pageable pageable);
	
	/**
	 * Ricerca il dettaglio relativo all'operazione asincrona
	 * @param idOpAsinc
	 * @param pageable
	 * @return
	 */
	@Query("SELECT dett FROM SiacTOperazioneAsincronaDet dett "
				+ " WHERE dett.siacTOperazioneAsincrona.uid = :idOpAsinc " +
				" AND ( :codice = '' OR :codice IS NULL  "
			+ "              OR dett.opasDetCode = :codice ) " )
	Page<SiacTOperazioneAsincronaDet> findDettaglioByIdOpAsinc(
			@Param("idOpAsinc") Integer idOpAsinc,
			@Param("codice") String codice,
			Pageable pageable);
	
}
