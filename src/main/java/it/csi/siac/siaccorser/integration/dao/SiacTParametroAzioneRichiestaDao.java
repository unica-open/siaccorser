/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import it.csi.siac.siaccorser.integration.entity.SiacTParametroAzioneRichiesta;

/**
 * Dao per l'accesso all'entità SiacTParametroAzioneRichiesta
 * 
 * @author 
 * 
 */
public interface SiacTParametroAzioneRichiestaDao {

	SiacTParametroAzioneRichiesta saveParametroAzioneRichiesta(
			SiacTParametroAzioneRichiesta azione);

	SiacTParametroAzioneRichiesta deleteParametroAzioneRichiesta(
			SiacTParametroAzioneRichiesta azione);

	SiacTParametroAzioneRichiesta findParametroAzioneRichiestaById(int uuid);

}
