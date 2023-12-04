/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import it.csi.siac.siaccorser.integration.entity.SiacTBil;


/**
 * Dao per l'accesso alla lista degli anni di bilancio di un ente
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
public interface SiacTBilDao {

	List<SiacTBil> findAnniBilancioByEnte(int idEnte);

}
