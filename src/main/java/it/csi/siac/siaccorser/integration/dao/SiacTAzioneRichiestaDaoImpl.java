/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTAzioneRichiesta;
import it.csi.siac.siaccorser.integration.entity.SiacTParametroAzioneRichiesta;
import it.csi.siac.siaccorser.model.ParametroAzioneRichiesta;

/**
 * Implementazione del DAO per SiacTAzioneRichiesta. ATTENZIONE i Dao sollevano
 * della unchecked exceptions: per catturarle occorre catturare le
 * RuntimeException
 * 
 * @author 
 */
@Component
@Transactional
public class SiacTAzioneRichiestaDaoImpl extends JpaDao<SiacTAzioneRichiesta, Integer> implements SiacTAzioneRichiestaDao {

	@Override
	public SiacTAzioneRichiesta saveAzioneRichiesta(SiacTAzioneRichiesta siacTAzioneRichiesta) {
		siacTAzioneRichiesta.setDataModifica(new Date());
		if (siacTAzioneRichiesta.getDataCreazione()==null) {
			siacTAzioneRichiesta.setDataCreazione(new Date());
		}
		if (siacTAzioneRichiesta.getDataInizioValidita()==null) {
			siacTAzioneRichiesta.setDataInizioValidita(new Date());
		}
		if (siacTAzioneRichiesta.getLoginOperazione()==null) {
			siacTAzioneRichiesta.setLoginOperazione("ADMIN");
		}
		siacTAzioneRichiesta.setUid(null);
		save(siacTAzioneRichiesta);
		
		return siacTAzioneRichiesta;
	}

	@Override
	public SiacTAzioneRichiesta deleteAzioneRichiesta(SiacTAzioneRichiesta siacTAzioneRichiesta) {
		Date now = new Date();
		siacTAzioneRichiesta.setDataModifica(now);
		siacTAzioneRichiesta.setDataFineValidita(now);
		siacTAzioneRichiesta.setDataCancellazione(now);
		
		entityManager.persist(siacTAzioneRichiesta);
		
		return siacTAzioneRichiesta;
	}
	
	

	@Override
	public void delete(SiacTAzioneRichiesta siacTAzioneRichiesta) {
		
		if (siacTAzioneRichiesta.getParametri() != null) {
			for (SiacTParametroAzioneRichiesta siacTParametroAzioneRichiesta : siacTAzioneRichiesta.getParametri()) {
				entityManager.remove(siacTParametroAzioneRichiesta);
			}
		}
		
		super.delete(siacTAzioneRichiesta);

		flushAndClear();
	}

	@Override
	public SiacTAzioneRichiesta findAzioneRichiestaById(int uuid) {
		return entityManager.find(SiacTAzioneRichiesta.class, uuid);
	}

	@Override
	public List<SiacTAzioneRichiesta> findAzioniFrequenti(int idAccount, int numeroGiorni, int numeroMaxAzioni) {

		List<SiacTAzioneRichiesta> azioniFrequenti = new ArrayList<SiacTAzioneRichiesta>();
		
		Query query = entityManager
				.createQuery("SELECT a, COUNT(ar), MAX(ar) FROM SiacTAzioneRichiesta ar, SiacTAzione a "
						+ " WHERE ar.account.uid = :idAccount "
						+ " AND ar.data > :dataInizio "
						+ " AND a.uid = ar.azione.uid "
						+ " AND a.gruppo IS NOT NULL "
						//SIAC-7776 nelle azioni frequenti filtriamo solo per azioni presenti nei menu del cruscotto
						+ " AND a.tipo.codice IN ('AVVIO_PROCESSO', 'ATTIVITA_SINGOLA') "
						+ " GROUP BY a.uid "
						+ " ORDER BY COUNT(ar) DESC "
						+ "" );
		query.setParameter("idAccount",idAccount);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, numeroGiorni*-1);
		query.setParameter("dataInizio",cal.getTime());
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		for(Object[] result:results) {
			azioniFrequenti.add((SiacTAzioneRichiesta)result[2]);
			if (azioniFrequenti.size()==numeroMaxAzioni)
				break;
		}
		return azioniFrequenti;
	}

	@Override
	public SiacTAzioneRichiesta findValidAzioneRichiestaById(int idAzioneRichiesta) {
		SiacTAzioneRichiesta siacTAzioneRichiesta = findAzioneRichiestaById(idAzioneRichiesta);
		
		return siacTAzioneRichiesta != null && siacTAzioneRichiesta.isEntitaValida() ? siacTAzioneRichiesta : null;
	}
}
