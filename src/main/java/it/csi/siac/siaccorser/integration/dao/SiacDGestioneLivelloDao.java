/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import it.csi.siac.siaccorser.integration.entity.SiacDGestioneLivello;

/**
 * Dao per l'accesso al Dto dell'entita' GestioneLivello
 * 
 * @author rmontuori
 *
 */
public interface SiacDGestioneLivelloDao {

	List<SiacDGestioneLivello> findGestioneLivelloByIdEnte(int idEnte);
}
