/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import it.csi.siac.siaccorser.integration.entity.SiacDRuoloOp;

/**
 * Dao per l'accesso all'entità Ruolo
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
public interface SiacDRuoloDao {

	SiacDRuoloOp saveRuolo(SiacDRuoloOp ruolo);

	SiacDRuoloOp deleteRuolo(SiacDRuoloOp ruolo);

	SiacDRuoloOp findRuoloById(int uuid);

}
