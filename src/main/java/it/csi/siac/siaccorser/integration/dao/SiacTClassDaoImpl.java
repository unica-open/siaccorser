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

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccorser.integration.entity.SiacTClass;
import it.csi.siac.siaccorser.integration.entity.enumeration.SiacDClassFamEnum;

/**
 * Implementazione del DAO per le codifche. ATTENZIONE i Dao sollevano della
 * unchecked exceptions: per catturarle occorre catturare le RuntimeException
 * 
 * @author rmontuori

 */
@Component
@Transactional
public class SiacTClassDaoImpl extends JpaDao<SiacTClass, Integer> implements SiacTClassDao {

	@Override
	public List<SiacTClass> findCodifiche(int anno, int enteProprietarioId) {
		final String methodName = "findCodifiche";
		log.debug(methodName, "init");

		TypedQuery<SiacTClass> query = entityManager
				.createQuery("select c from SiacTClass c where c.ente.enteProprietarioId = :enteProprietarioId ", SiacTClass.class);
		query.setParameter("enteProprietarioId", enteProprietarioId);

		List<SiacTClass> dtos = query.getResultList();

		return dtos;
	}


	@Override
	public SiacTClass findById(int id) {
		log.debug("findById", "findById(int)");
		return entityManager.find(SiacTClass.class, id);
	}

	@Override
	public List<SiacTClass> findTreeByCodiceFamiglia(int anno,
			int enteProprietarioId) {
		
		return findTreeByCodiceFamiglia("IS NULL ", anno, enteProprietarioId);
	}
	
	@Override
	public List<SiacTClass> findTreeByCodiceFamigliaAndParentId(Integer anno,
			int enteProprietarioId, int parentId) {
		
		return findTreeByCodiceFamiglia("= " + parentId, anno, enteProprietarioId);
	}
	
	private List<SiacTClass> findTreeByCodiceFamiglia(String parentClause, Integer anno,
			int enteProprietarioId) {
		log.trace("findTreeByCodiceFamiglia", "findTreeByCodiceFamiglia(String, Integer, int)");

		TypedQuery<SiacTClass> query = entityManager
				.createQuery("SELECT DISTINCT c "
					+ " FROM SiacTClass c "
					+ " WHERE c.ente.enteProprietarioId = :enteProprietarioId "
					+ " AND c.padre.uid " + parentClause
					+ " AND c.codificaFamiglia.codiceCodificaFamiglia.codice = :codicefamigliaTree " 
					+ " AND c.codificaFamiglia.codiceCodificaFamiglia.ente.enteProprietarioId = :enteProprietarioId "
					+ " AND c.dataCancellazione IS NULL "
					+ " AND c.codificaFamiglia.dataCancellazione IS NULL "
					+ getSiacTClassDataValiditaSql("c", "anno")
					+ " ORDER BY c.uid", SiacTClass.class);

		
		query.setParameter("anno", anno);
		query.setParameter("enteProprietarioId", enteProprietarioId);
		query.setParameter("codicefamigliaTree", SiacDClassFamEnum.StrutturaAmministrativaContabile.getCodice());

		List<SiacTClass> dtos =query.getResultList();

		return dtos;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> findSACCollegatoAccountDirettamenteByClassifTipo(Integer anno, Integer accountId, String classifTipoCode){
		
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("anno", anno);
		param.put("uidAccount", accountId);
		param.put("classifTipoCode", classifTipoCode);
		param.put("codicefamigliaTree", SiacDClassFamEnum.StrutturaAmministrativaContabile.getCodice());
		
		queryString.append("SELECT DISTINCT c.uid ")
				   .append("FROM SiacRAccountClass rac, SiacTClass c ")
				   .append("WHERE rac.strutturaAmministrativoContabile = c ")
				   .append("AND rac.account.uid = :uidAccount ")
				   .append("AND c.codificaFamiglia.codiceCodificaFamiglia.codice = :codicefamigliaTree ")
				   .append("AND c.tipoClassificatore.codice = :classifTipoCode ")
				   .append(getSiacTClassDataValiditaSql("c", "anno"))
				   .append(getSiacTClassDataValiditaSql("rac", "anno"))				   
				   ;
		Query query = createQuery(queryString.toString(), param);
		
		return query.getResultList();
	}
	
	/**
	 * @param anno
	 * @param accountId
	 * @return
	 */
	@Override
	public List<Object[]> findPadriSACCDCCollegateAdAccount(Integer anno, Integer accountId, List<Integer> uidsDaEscludere){
		
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("anno", anno);
		param.put("uidAccount", accountId);
		param.put("classifTipoCode", "CDC");
		param.put("codicefamigliaTree", SiacDClassFamEnum.StrutturaAmministrativaContabile.getCodice());
		
		queryString.append("SELECT DISTINCT cl.tipoClassificatore.codice, cl.uid ")
				   .append("FROM SiacTClass cl ")
				   .append("WHERE cl.codificaFamiglia.codiceCodificaFamiglia.codice = :codicefamigliaTree ")
				   .append("AND EXISTS ( ")
				   .append("   FROM SiacRAccountClass rac, SiacRClassFamTree fam  ")
				   .append("   WHERE rac.strutturaAmministrativoContabile.tipoClassificatore.codice = :classifTipoCode ")
				   .append("   AND rac.strutturaAmministrativoContabile = fam.codifica2 ")
				   .append("   AND fam.siacTClassPadre = cl ")
				   .append("   AND rac.account.uid = :uidAccount ")
				   .append(getSiacTClassDataValiditaSql("rac", "anno"))	
				   .append(getSiacTClassDataValiditaSql("fam", "anno"))	
				   .append(" ) ")
				   .append(getSiacTClassDataValiditaSql("cl", "anno"));
		if(uidsDaEscludere != null && !uidsDaEscludere.isEmpty()) {
			queryString.append(" AND cl.uid NOT IN ( :uidsDaEscludere )");
			param.put("uidsDaEscludere", uidsDaEscludere);
		}
		
		Query query = createQuery(queryString.toString(), param);
		
		return query.getResultList();
	}
	
	@Override
	public List<Integer> findfigliClassificatoriSAC(Integer anno, List<Integer> uidsPadri){
		
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("anno", anno);
		param.put("codicefamigliaTree", SiacDClassFamEnum.StrutturaAmministrativaContabile.getCodice());
		
		queryString.append("SELECT DISTINCT cl.uid ")
				   .append("FROM SiacRClassFamTree fam , SiacTClass cl ")
				   .append("WHERE fam.codifica2.codificaFamiglia.codiceCodificaFamiglia.codice = :codicefamigliaTree ")
				   .append("AND fam.codifica2 = cl ")
				   .append(getSiacTClassDataValiditaSql("cl", "anno"))	
				   .append(getSiacTClassDataValiditaSql("fam", "anno"));
		if(uidsPadri != null && !uidsPadri.isEmpty()) {
			queryString.append(" AND fam.siacTClassPadre.uid IN ( :uidsPadri) ");
			param.put("uidsPadri", uidsPadri);
		}
		
		Query query = createQuery(queryString.toString(), param);
		
		return query.getResultList();
	}
	
}
