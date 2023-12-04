/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao.file;

import it.csi.siac.siaccommonser.integration.dao.base.Dao;
import it.csi.siac.siaccorser.integration.entity.SiacTFile;

public interface SiacTFileDao extends Dao<SiacTFile, Integer> {
	@Override
	SiacTFile create(SiacTFile file);

	@Override
	SiacTFile update(SiacTFile file);

}
