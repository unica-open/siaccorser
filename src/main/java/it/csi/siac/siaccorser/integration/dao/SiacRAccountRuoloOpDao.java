/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import it.csi.siac.siaccorser.integration.entity.SiacRAccountRuoloOp;

/**
 * Dao per l'accesso all'entità RuoloAccount 
 * 
 * @author
 *
 */
public interface SiacRAccountRuoloOpDao {
	
	SiacRAccountRuoloOp saveRuoloAccount(SiacRAccountRuoloOp ruoloAccount);
	
	SiacRAccountRuoloOp deleteRuoloAccount(SiacRAccountRuoloOp ruoloAccount);
	
	SiacRAccountRuoloOp findRuoloAccountById(int uuid);
	
}
