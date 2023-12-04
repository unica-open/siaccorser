/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import it.csi.siac.siaccorser.integration.entity.SiacDGruppoAzioni;

/**
 * Dao per l'accesso all'entità GruppoAzioni
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
public interface SiacDGruppoAzioniDao {

	SiacDGruppoAzioni saveGruppoAzioni(SiacDGruppoAzioni gruppoAzioni);

	SiacDGruppoAzioni deleteGruppoAzioni(SiacDGruppoAzioni gruppoAzioni);

	SiacDGruppoAzioni findGruppoAzioniById(int uuid);

}
