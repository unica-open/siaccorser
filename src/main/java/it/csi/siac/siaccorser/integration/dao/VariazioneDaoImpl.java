/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTVariazione;

@Component
@Transactional
public class VariazioneDaoImpl extends JpaDao<SiacTVariazione, Integer> implements VariazioneDao{

	

	@Override
	public SiacTVariazione findVariazioneByUid(int uuid) {
		return entityManager.find(SiacTVariazione.class, uuid);
	}

	@Override
	public List<SiacTVariazione> findVariazioneByNumeroVariazione(Integer numeroVariazione) {
		TypedQuery<SiacTVariazione> query = entityManager
				.createQuery("SELECT variazione FROM SiacTVariazione variazione "
						+ " WHERE variazione.variazioneNum =:numeroVariazione AND variazione.dataCancellazione is null", SiacTVariazione.class);
				query.setParameter("numeroVariazione",numeroVariazione);
				List<SiacTVariazione> variazioni = query.getResultList(); 
				return variazioni;
	}
	
	
	
	
	@Override
	public List<SiacTVariazione> findVariazioneBySac(List<Integer> uidSac, int first, int maxresult, Integer annoBilancio, int  enteProprietarioId) {
		TypedQuery<SiacTVariazione> query = entityManager
				.createQuery("SELECT variazione FROM SiacTVariazione variazione "
						+ "WHERE  variazione.ente.enteProprietarioId = :enteProprietarioId "
						+ "AND variazione.siacTBil.periodo.anno = :annoBilancio "
						+ "AND variazione.siacTClass.uid  IN :uidSac "
						+ "AND variazione.dataAperturaProposta is not null "
						+ "AND (EXISTS ( FROM variazione.siacRVariazioneStatos rvarstato WHERE rvarstato.dataCancellazione IS NULL AND rvarstato.siacDVariazioneStato.variazioneStatoTipoCode = 'B'))"
						+ " order by variazione.variazioneId desc", SiacTVariazione.class);
				query.setParameter("uidSac",uidSac);
				query.setParameter("enteProprietarioId", enteProprietarioId);
				query.setParameter("annoBilancio", annoBilancio.toString());
				query.setFirstResult(first);
				query.setMaxResults(maxresult);
				List<SiacTVariazione> variazioni = query.getResultList(); 
				return variazioni;
	}
	
	
	
	@Override
	public Long getTotaleVariazione(List<Integer> uidSac, Integer annoBilancio, int  enteProprietarioId) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("uidSac", uidSac);
				param.put("enteProprietarioId", enteProprietarioId);
				param.put("annoBilancio", annoBilancio.toString());
				Query query = createQuery("SELECT COUNT(variazione) FROM SiacTVariazione variazione "
						+ "WHERE  variazione.ente.enteProprietarioId = :enteProprietarioId "
						+ "AND variazione.siacTBil.periodo.anno = :annoBilancio "
						+ "AND variazione.siacTClass.uid  IN :uidSac "
						+ "AND variazione.dataAperturaProposta is not null "
						+ "AND (EXISTS ( FROM variazione.siacRVariazioneStatos rvarstato WHERE rvarstato.dataCancellazione IS NULL AND rvarstato.siacDVariazioneStato.variazioneStatoTipoCode = 'B'))"
						+ "", param);
				Long result = (Long) query.getSingleResult();
				return result;
				
				
	}
	
	//SIAC-8332
	@Override
	public Long caricaTotaleVariazioniByStatoAndEventuallySAC(String codice,boolean statoConSacLimitate, List<Integer> uidSACDirezioneCollegate,  Integer uidEnte,String annoBilancio) {
		if(statoConSacLimitate && (uidSACDirezioneCollegate == null || uidSACDirezioneCollegate.isEmpty())) {
			//devo filtrare  le variazioni collegate alle SAC dell'account ma all'account non e' collegata nessuna SAC.
			//saranno sempre 0
			return Long.valueOf(0);
		}
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		jpql.append("SELECT COALESCE(COUNT(*),0) ");
		componiQueryRicercaAttivitaPendentiVariazioni(codice, statoConSacLimitate, uidSACDirezioneCollegate, uidEnte, annoBilancio,jpql, params);
		Query query = createQuery(jpql.toString(), params);
		return (Long) query.getSingleResult();
	}

	protected void componiQueryRicercaAttivitaPendentiVariazioni(String codice, boolean statoConSacLimitate, List<Integer> uidSACDirezioneCollegate,
			Integer uidEnte, String annoBilancio, StringBuilder jpql, Map<String, Object> params) {
		jpql.append("FROM   SiacTVariazione var ");
		jpql.append("WHERE  var.ente.enteProprietarioId = :enteProprietarioId ");
		jpql.append("       AND var.siacTBil.periodo.anno = :annoBilancio ");
		jpql.append("       AND var.dataCancellazione is NULL ");
		if(StringUtils.isNotBlank(codice)){
			jpql.append("       AND (EXISTS ( FROM var.siacRVariazioneStatos rvarstato WHERE rvarstato.siacDVariazioneStato.variazioneStatoTipoCode = :variazioneStatoTipoCode ");
			jpql.append(" 					AND rvarstato.dataCancellazione is NULL )) ");
			params.put("variazioneStatoTipoCode", codice);
		}
		if(statoConSacLimitate) {
			jpql.append("       AND  var.siacTClass.uid IN ( :classifUids )");
			params.put("classifUids", uidSACDirezioneCollegate);
		}
		params.put("enteProprietarioId", uidEnte);
		params.put("annoBilancio", annoBilancio);
	}
	
	@Override
	public Page<SiacTVariazione> caricaVariazioniByStatoAndEventuallySAC(String codice,boolean statoConSacLimitate, List<Integer> uidSACDirezioneCollegate,  Integer uidEnte,String annoBilancio, Pageable pageable){
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
  
		jpql.append("SELECT var ");
		componiQueryRicercaAttivitaPendentiVariazioni(codice, statoConSacLimitate,uidSACDirezioneCollegate, uidEnte, annoBilancio,jpql, params);
		jpql.append(" ORDER BY variazioneNum ");

		return getPagedList(jpql.toString(), params, pageable);
	}
	

}
