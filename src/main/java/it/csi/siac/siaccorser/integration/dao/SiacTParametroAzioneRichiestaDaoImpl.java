/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTParametroAzioneRichiesta;

/**
 * Implementazione del DAO per ParametroAzioneRichiesta. ATTENZIONE i Dao sollevano
 * della unchecked exceptions: per catturarle occorre catturare le
 * RuntimeException
 * 
 * @author 
 */
@Component
@Transactional
public class SiacTParametroAzioneRichiestaDaoImpl extends JpaDao<SiacTParametroAzioneRichiesta, Integer> implements SiacTParametroAzioneRichiestaDao {

	@Override
	public SiacTParametroAzioneRichiesta saveParametroAzioneRichiesta(SiacTParametroAzioneRichiesta parametroAzioneRichiesta) {
		parametroAzioneRichiesta.setDataModifica(new Date());
		if (parametroAzioneRichiesta.getDataCreazione()==null) {
			parametroAzioneRichiesta.setDataCreazione(new Date());
		}
		if (parametroAzioneRichiesta.getDataInizioValidita()==null) {
			parametroAzioneRichiesta.setDataInizioValidita(new Date());
		}
		if (parametroAzioneRichiesta.getLoginOperazione()==null) {
			parametroAzioneRichiesta.setLoginOperazione("ADMIN");
		}
		
		parametroAzioneRichiesta.setUid(null);
		save(parametroAzioneRichiesta);
		return parametroAzioneRichiesta;
	}

	@Override
	public SiacTParametroAzioneRichiesta deleteParametroAzioneRichiesta(SiacTParametroAzioneRichiesta parametroAzioneRichiesta) {
		parametroAzioneRichiesta.setDataFineValidita(new Date());
		entityManager.persist(parametroAzioneRichiesta);
		return parametroAzioneRichiesta;
	}

	@Override
	public SiacTParametroAzioneRichiesta findParametroAzioneRichiestaById(int uuid) {
		return entityManager.find(SiacTParametroAzioneRichiesta.class, uuid);
	}

}
