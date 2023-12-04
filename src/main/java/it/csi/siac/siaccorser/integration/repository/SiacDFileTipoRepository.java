/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siaccorser.integration.entity.SiacDFileTipo;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;

public interface SiacDFileTipoRepository extends JpaRepository<SiacDFileTipo, Integer> {
	@Query("SELECT ft FROM SiacDFileTipo ft "
			+ " WHERE ft.ente=:ente AND ft.dataCancellazione IS NULL ")
	List<SiacDFileTipo> getElencoTipoFile(@Param("ente") SiacTEnteProprietario ente);

	@Query("SELECT DISTINCT ft FROM SiacDFileTipo ft, SiacRRuoloOpAzione raz "
			+ " WHERE ft.ente=:ente AND ft.dataCancellazione IS NULL "
			+ " AND ft.azione=raz.azione AND "
			+ " (raz.siacDRuoloOp IN "
			+ " (SELECT rac.ruolo FROM SiacRAccountRuoloOp rac"
			+ " WHERE rac.ente=ft.ente AND rac.dataCancellazione IS NULL AND rac.account.uid=:idAccount)"
			+ " OR raz.siacDRuoloOp IN "
			+ " (SELECT rgr.ruolo FROM SiacRGruppoRuoloOp rgr, SiacRGruppoAccount gac "
			+ " WHERE rgr.ente=ft.ente AND rgr.dataCancellazione IS NULL "
			+ " AND gac.ente=ft.ente AND gac.dataCancellazione IS NULL "
			+ " AND rgr.gruppo=gac.siacTGruppo AND gac.siacTAccount.uid=:idAccount)" + ") "
			+ " ORDER BY ft.descrizione")
	List<SiacDFileTipo> getElencoTipoFileByIdAccount(@Param("idAccount") Integer idAccount,
			@Param("ente") SiacTEnteProprietario ente);

	@Query("SELECT ft FROM SiacDFileTipo ft "
			+ " WHERE ft.ente=:ente AND ft.dataCancellazione IS NULL " + " AND ft.codice=:codice")
	SiacDFileTipo ricercaTipoFile(@Param("codice") String codice,
			@Param("ente") SiacTEnteProprietario ente);

}
