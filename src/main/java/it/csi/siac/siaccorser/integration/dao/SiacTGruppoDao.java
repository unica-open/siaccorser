/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import it.csi.siac.siaccorser.integration.entity.SiacTGruppo;

/**
 * Dao per l'accesso all'entità Gruppo
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
public interface SiacTGruppoDao {

	SiacTGruppo saveGruppo(SiacTGruppo gruppo);

	SiacTGruppo deleteGruppo(SiacTGruppo gruppo);

	SiacTGruppo findGruppoById(int uuid);

}
