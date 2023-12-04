/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTAzione;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;

/**
 * Implementazione del DAO per Azione. ATTENZIONE i Dao sollevano
 * della unchecked exceptions: per catturarle occorre catturare le
 * RuntimeException
 * 
 * @author 
 */
@Component
@Transactional
public class SiacTAzioneDaoImpl extends JpaDao<SiacTAzione, Integer> implements SiacTAzioneDao {

	@Override
	public SiacTAzione saveAzione(SiacTAzione siacTAzione) {
		entityManager.persist(siacTAzione);
		return siacTAzione;
	}

	@Override
	public SiacTAzione deleteAzione(SiacTAzione siacTAzione) {
		siacTAzione.setDataFineValidita(new Date());
		entityManager.persist(siacTAzione);
		return siacTAzione;
	}

	@Override
	public SiacTAzione findAzioneById(int uuid) {
		return entityManager.find(SiacTAzione.class, uuid);
	}
	
	@Override
	public List<SiacTAzione> findAzioneByNome(String nomeAzione, Integer idEnte, Integer accountId) {
		TypedQuery<SiacTAzione> query = entityManager
		.createQuery("SELECT a FROM SiacTAzione a "
				+ " WHERE a.nome=:nomeAzione "
				+ " AND a.ente.enteProprietarioId=:idEnte"
				+ " AND a.dataCancellazione IS NULL "
				+ " AND EXISTS (FROM a.siacRRuoloOpAziones ra "
				+ "				 WHERE ra.dataCancellazione IS NULL"
				+ "			     AND EXISTS ( FROM ra.siacDRuoloOp sdro "
				+ "							 WHERE sdro.dataCancellazione IS NULL "
				+ "                          AND ( "
				+ "									EXISTS ( FROM sdro.accounts sraro"
				+ " 									  	WHERE sraro.dataCancellazione IS NULL "	
				+ "										  	AND sraro.account.uid = :idAccount "
				+ "											)"
				+ "			     					OR EXISTS ( FROM sdro.gruppi srgro"
				+ "										        WHERE srgro.dataCancellazione IS NULL "
				+ " 									   		AND EXISTS ( FROM srgro.gruppo stg"
				+ "													   		 WHERE stg.dataCancellazione IS NULL"
				+ "															 AND EXISTS ( FROM stg.siacRGruppoAccounts srga"
				+ "																		 WHERE srga.dataCancellazione IS NULL "
				+ "																		 AND srga.siacTAccount.uid = :idAccount "
				+ "																    	)"
				+ "													  		)"	
				+ "											)"	
				+ "								) "
				+ " 						)"
				+ "             )", SiacTAzione.class);
		query.setParameter("nomeAzione", nomeAzione);
		query.setParameter("idEnte", idEnte);
		query.setParameter("idAccount", accountId);

		return query.getResultList();
	}
	
	
	@Override
	public List<SiacTAzione> findAzioniByRuoloOp(Integer idRuoloOp, SiacTEnteProprietario ente) {
		TypedQuery<SiacTAzione> query = entityManager
		.createQuery("SELECT DISTINCT azione FROM SiacTAzione azione, IN(azione.siacRRuoloOpAziones) rRuoloAzione "
				+ " LEFT OUTER JOIN azione.gruppo gruppo "
				+ " WHERE azione.ente=:ente AND azione.dataCancellazione IS NULL "
				+ " AND azione.tipo.dataCancellazione IS NULL " 
				+ " AND gruppo.dataCancellazione IS NULL "
				+ " AND rRuoloAzione.siacDRuoloOp.uid=:idRuoloOp  AND rRuoloAzione.dataCancellazione IS NULL", SiacTAzione.class);
		query.setParameter("ente",ente);
		query.setParameter("idRuoloOp",idRuoloOp);

		List<SiacTAzione> azioni = query.getResultList(); 
		
		return azioni;
	}
}
