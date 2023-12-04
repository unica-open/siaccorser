/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/

package it.csi.siac.siaccorser.integration.dad;

import it.csi.siac.siaccorser.integration.dao.SiacTClassDao;
import it.csi.siac.siaccorser.integration.entity.SiacTClass;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;
import it.csi.siac.siaccorser.model.TipoClassificatore;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope("prototype")
@Transactional
public class ClassificatoreDad extends BaseCoreDad {

		
	@Autowired
	private SiacTClassDao siacTClassDao;
	

	public List<StrutturaAmministrativoContabile> leggiStrutturaAmministrativoContabile(
			int anno, int idEnteProprietario) {
		List<SiacTClass> dtos = siacTClassDao.findTreeByCodiceFamiglia(anno, idEnteProprietario);
		List<StrutturaAmministrativoContabile> result = new ArrayList<StrutturaAmministrativoContabile>() ;
		return convertTreeDto(dtos, result, new StrutturaAmministrativoContabile(), anno);
	}
	
	//SIAC-6884: implementata ricerca SAC by Id, poi diventata superflua
	/*public StrutturaAmministrativoContabile leggiStrutturaAmministrativoContabileById(int uidSac){
		SiacTClass entity = siacTClassDao.findById(uidSac);
		if(entity != null){
			StrutturaAmministrativoContabile obj = new StrutturaAmministrativoContabile();
			obj.setUid(entity.getUid());
			obj.setCodice(entity.getCodice());
			obj.setDescrizione(entity.getDescrizione());
			TipoClassificatore tipoClassif = map(entity.getTipoClassificatore(), TipoClassificatore.class);
			obj.setTipoClassificatore(tipoClassif);
			if (entity.getCodificaAttributo() != null && !StringUtils.isEmpty(entity.getCodificaAttributo().getDescrizione())) {
				obj.setAssessorato(entity.getCodificaAttributo().getDescrizione());
			}
			return obj;
		}else{
			return null;
		}
	}*/

	
	
	/**
	 * Converte un tree di CodificaDto con famiglia StrutturaAmministrativoContabile
	 * in un tree di StrutturaAmministrativoContabile
	 * @param dtos
	 * @param list
	 * @param padre
	 * @param anno 
	 * @return
	 */
	public List<StrutturaAmministrativoContabile> convertTreeDto(
			List<SiacTClass> classificatori,
			List<StrutturaAmministrativoContabile> list,
			StrutturaAmministrativoContabile padre, int anno) {

		for (SiacTClass classificatore : classificatori) {

			StrutturaAmministrativoContabile obj = new StrutturaAmministrativoContabile();

			obj.setUid(classificatore.getUid());
			obj.setCodice(classificatore.getCodice());
			obj.setDescrizione(classificatore.getDescrizione());
			
			TipoClassificatore tipoClassif = map(classificatore.getTipoClassificatore(), TipoClassificatore.class);
			obj.setTipoClassificatore(tipoClassif);

			if (classificatore.getCodificaAttributo() != null
					&& !StringUtils.isEmpty(classificatore.getCodificaAttributo()
							.getDescrizione())) {
				obj.setAssessorato(classificatore.getCodificaAttributo().getDescrizione());
			}

			List<SiacTClass> figli = siacTClassDao.findTreeByCodiceFamigliaAndParentId(anno, classificatore.getEnte().getUid(), classificatore.getUid());
			
			if (!figli.isEmpty()) {
				List<StrutturaAmministrativoContabile> substrutture = new ArrayList<StrutturaAmministrativoContabile>();
				substrutture = convertTreeDto(figli, substrutture, obj, anno);
				obj.setSubStrutture(substrutture);
			}
			list.add(obj);

		}
		return list;
	}

	public void popolaListaConCDRDirettamenteCollegateAdAccount(Account account, Integer anno, List<Integer> listaDaPopolare) {
		List<Integer> CDRCollegate = siacTClassDao.findSACCollegatoAccountDirettamenteByClassifTipo(anno, account.getUid(), "CDR");
		if(CDRCollegate == null || CDRCollegate.isEmpty()) {
			return;
		}
		listaDaPopolare.addAll(CDRCollegate);
	}
	
	public void popolaListaCDRConCDCFigliCollegatiAdAccount(Account account, Integer anno, List<Integer> listaDaPopolare, List<Integer> listaUidDaEscludere ) {
		List<Object[]> CDRCollegate = siacTClassDao.findPadriSACCDCCollegateAdAccount(anno, account.getUid(), listaUidDaEscludere);
		if(CDRCollegate == null) {
			return;
		}
//		uidsCD
		for (Object[] o : CDRCollegate) {
			if(o == null || o.length != 2) {
				continue;
			}
			String tipoSAC = (String) o[0];
			Integer uidSAC = (Integer) o[1];
			if("CDR".equals(tipoSAC)) {
				listaDaPopolare.add(uidSAC);
				continue;
			}
			listaDaPopolare.add(getPadreClassificatore(uidSAC, "CDR"));
		}
	}

	
	private Integer getPadreClassificatore(Integer uidSAC, String classifTipoCodePadre) {
		boolean controllaTipoClassificatore = StringUtils.isNotBlank(classifTipoCodePadre);
		SiacTClass siacTClass = siacTClassDao.findById(uidSAC);
		SiacTClass padre = siacTClass.getPadre();
		if(padre == null || (controllaTipoClassificatore && padre.getTipoClassificatore() == null)) {
			return null;
		}
		Integer idPadre = padre.getUid();
		if(!controllaTipoClassificatore) {
			return idPadre;
		}
		
		return classifTipoCodePadre.equals(padre.getTipoClassificatore().getCodice()) ? 
				idPadre :
					getPadreClassificatore(idPadre, classifTipoCodePadre);
	}

}

