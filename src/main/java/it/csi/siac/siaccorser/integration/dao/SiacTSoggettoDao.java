/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import it.csi.siac.siaccorser.integration.entity.SiacTSoggetto;

/**
 * Dao per l'accesso al Dto dell'entità Soggetto 
 * 
 * @author
 *
 */
public interface SiacTSoggettoDao {
	
	SiacTSoggetto saveSoggetto(SiacTSoggetto siacTSoggetto);
	
	SiacTSoggetto deleteSoggetto(SiacTSoggetto siacTSoggetto);
	
	SiacTSoggetto findSoggettoById(int uid);
	
	List<SiacTSoggetto> findSoggettiByCodiceFiscale(String codiceFiscale);
	
}
