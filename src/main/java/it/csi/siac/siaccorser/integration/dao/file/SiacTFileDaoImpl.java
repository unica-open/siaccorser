/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao.file;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTFile;

@Component
@Transactional
public class SiacTFileDaoImpl extends JpaDao<SiacTFile, Integer> implements SiacTFileDao {

	@Override
	public SiacTFile create(SiacTFile file) {
		Date now = new Date();
		
		file.setDataCreazione(now);
		file.setDataInizioValidita(now);
		file.setDataModifica(now);
		file.setUid(null);
		file.setDatiStati();

		super.save(file);

		return file;
	}

	@Override
	public SiacTFile update(SiacTFile file) {
		file.setDataModifica(new Date());
		return super.update(file);
	}
}