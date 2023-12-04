/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.csi.siac.siaccorser.integration.entity.SiacTVariazione;

public interface VariazioneDao {

	
	SiacTVariazione findVariazioneByUid(int uid);
	
	List<SiacTVariazione> findVariazioneByNumeroVariazione(Integer numeroVariazione);

	List<SiacTVariazione> findVariazioneBySac(List<Integer> uidSac, int first, int maxresult, Integer annoBilancio, int  enteProprietarioId);

	Long getTotaleVariazione(List<Integer> uidSac, Integer annoBilancio, int  enteProprietarioId);
	//SIAC-8332
	public Long caricaTotaleVariazioniByStatoAndEventuallySAC(String codice,boolean statoConSacLimitate,List<Integer> uidSACDirezioneCollegate,  Integer uidEnte,String annoBilancio);
	
	public Page<SiacTVariazione> caricaVariazioniByStatoAndEventuallySAC(String codice,boolean statoConSacLimitate,  List<Integer> uidSACDirezioneCollegate, Integer uidEnte,String annoBilancio, Pageable pageable);
	
}
