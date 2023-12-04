/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import it.csi.siac.siaccorser.integration.entity.SiacTAzione;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;

/**
 * Dao per l'accesso all'entità Azione
 * 
 * @author 
 * 
 */
public interface SiacTAzioneDao {

	SiacTAzione saveAzione(SiacTAzione azione);

	SiacTAzione deleteAzione(SiacTAzione azione);

	SiacTAzione findAzioneById(int uuid);
	
	List<SiacTAzione> findAzioniByRuoloOp(Integer idRuoloOp, SiacTEnteProprietario ente);

	List<SiacTAzione> findAzioneByNome(String nomeAzione, Integer idEnte, Integer accountId);

}
