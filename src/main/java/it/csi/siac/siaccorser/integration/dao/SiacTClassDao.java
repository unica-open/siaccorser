/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import it.csi.siac.siaccorser.integration.entity.SiacTClass;

public interface SiacTClassDao {

	List<SiacTClass> findCodifiche(int anno, int enteProprietarioId);

	List<SiacTClass> findTreeByCodiceFamiglia(int anno,
			int enteProprietarioId);

	List<SiacTClass> findTreeByCodiceFamigliaAndParentId(Integer anno,
			int enteProprietarioId, int parentId);
	
	SiacTClass findById(int id);
	
	public List<Integer> findSACCollegatoAccountDirettamenteByClassifTipo(Integer anno, Integer accountId, String classifTipoCode);
	
	public List<Object[]> findPadriSACCDCCollegateAdAccount(Integer anno, Integer accountId, List<Integer> uidsDaEscludere);
	
	public List<Integer> findfigliClassificatoriSAC(Integer anno, List<Integer> uidsPadri);

}
