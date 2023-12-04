/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;

/**
 * Dao per l'accesso al Dto dell'entità SiacTEnteProprietario 
 * 
 * @author 
 *
 */
public interface SiacTEnteProprietarioDao {
	
	SiacTEnteProprietario saveEnte(SiacTEnteProprietario enteDto);
	
	SiacTEnteProprietario deleteEnte(SiacTEnteProprietario enteDto);
	
	SiacTEnteProprietario findEnteById(int uid);
	
}
