/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import it.csi.siac.siaccorser.integration.entity.SiacRGruppoRuoloOp;

/**
 * Dao per l'accesso all'entità RuoloGruppo 
 * 
 * @author 
 *
 */
public interface SiacRGruppoRuoloOpDao {
	
	SiacRGruppoRuoloOp saveRuoloGruppo(SiacRGruppoRuoloOp ruoloGruppo);
	
	SiacRGruppoRuoloOp deleteRuoloGruppo(SiacRGruppoRuoloOp ruoloGruppo);
	
	SiacRGruppoRuoloOp findRuoloGruppoById(int uuid);
	

}
