/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import it.csi.siac.siaccommonser.integration.dao.base.Dao;
import it.csi.siac.siaccorser.integration.entity.SiacTAzioneRichiesta;

/**
 * Dao per l'accesso all'entità SiacTAzioneRichiesta 
 * 
 * @author 
 *
 */
public interface SiacTAzioneRichiestaDao  extends Dao<SiacTAzioneRichiesta, Integer> {
	
	SiacTAzioneRichiesta saveAzioneRichiesta(SiacTAzioneRichiesta azione);
	
	SiacTAzioneRichiesta deleteAzioneRichiesta(SiacTAzioneRichiesta azione);
	
	SiacTAzioneRichiesta findAzioneRichiestaById(int uuid);
	
	List<SiacTAzioneRichiesta> findAzioniFrequenti(int idAccount,
			int numeroGiorni, int numeroMaxAzioni);

	SiacTAzioneRichiesta findValidAzioneRichiestaById(int idAzioneRichiesta);
	
}
