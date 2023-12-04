/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacDGestioneLivello;

/**
 * Implementazione del DAO per GestioneLivello. ATTENZIONE i Dao sollevano
 * della unchecked exceptions: per catturarle occorre catturare le
 * RuntimeException
 * 
 * @author rmontuori
 */
@Component
@Transactional
public class SiacDGestioneLivelloDaoImpl extends JpaDao<SiacDGestioneLivello, Integer> implements
		SiacDGestioneLivelloDao {

	@Override
	public List<SiacDGestioneLivello> findGestioneLivelloByIdEnte(int idEnte) {
		StringBuilder strQuery = new StringBuilder("select gl  from SiacDGestioneLivello gl, in(gl.gestioneEnte) ge")
			.append(" where gl.ente.enteProprietarioId = :enteProprietarioId and ")
			.append(" ge.ente.enteProprietarioId = :enteProprietarioId and ")
			.append(" ge.dataCancellazione is null and ")
			.append(" (ge.dataFineValidita is null or now() between ge.dataInizioValidita and coalesce(ge.dataFineValidita,now()))");
			

		TypedQuery<SiacDGestioneLivello> query = entityManager.createQuery(strQuery.toString(), SiacDGestioneLivello.class);
		
		query.setParameter("enteProprietarioId", idEnte);
		
		return query.getResultList();
	}
	


}
