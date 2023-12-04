/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import it.csi.siac.siaccorser.integration.entity.SiacTAccount;

/**
 * Dao per l'accesso all'entità Account
 * 
 * @author lagna, montuori
 * 
 */
public interface SiacTAccountDao {

	SiacTAccount saveAccount(SiacTAccount account);

	SiacTAccount deleteAccount(SiacTAccount account);

	SiacTAccount findAccountById(int uuid);
	
}
