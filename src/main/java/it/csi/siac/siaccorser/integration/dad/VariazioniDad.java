/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccorser.integration.dao.VariazioneDao;
import it.csi.siac.siaccorser.integration.entity.SiacDVariazioneStato;
import it.csi.siac.siaccorser.integration.entity.SiacDVariazioneTipo;
import it.csi.siac.siaccorser.integration.entity.SiacTVariazione;
import it.csi.siac.siaccorser.integration.repository.SiacDVariazioneStatoRepository;
import it.csi.siac.siaccorser.integration.repository.SiacTVariazioneRepository;
import it.csi.siac.siaccorser.model.AttivitaPendente;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.Variazione;
import it.csi.siac.siaccorser.model.paginazione.ListaPaginata;
import it.csi.siac.siaccorser.model.paginazione.ListaPaginataImpl;
import it.csi.siac.siaccorser.model.paginazione.ParametriPaginazione;

@Component
@Scope("prototype")
@Transactional
public class VariazioniDad extends BaseCoreDad {

	
	@Autowired
	private VariazioneDao variazioneDao;
	
	@Autowired
	private SiacTVariazioneRepository siacTVariazioneRepository;
	
	@Autowired
	private SiacDVariazioneStatoRepository siacDVariazioneStatoRepository;
	
//	@Autowired
//	private SiacTVariazioneRepository siacTVariazioneRepository;

	public Variazione findVariazioneByNumeroVar(Integer numeroVar){
		
		List<SiacTVariazione> siacTVariazioneList = variazioneDao.findVariazioneByNumeroVariazione(numeroVar);
		Variazione variazione = new Variazione();
		if(siacTVariazioneList!= null && !siacTVariazioneList.isEmpty()){
			variazione.setUid(siacTVariazioneList.get(0).getUid());
			variazione.setDescrizione(siacTVariazioneList.get(0).getVariazioneDesc());
		}
		return variazione;
		
	}
	
	




	public List<Variazione> findVariazioniBySiac(List<Integer> uidSac, int first, int maxresult,Integer annoBilancio, int  enteProprietarioId){
	
		List<Variazione> variazioni = new ArrayList<Variazione>();
		
		List<SiacTVariazione> siacTVariazioneList = variazioneDao.findVariazioneBySac( uidSac, first, maxresult, annoBilancio,   enteProprietarioId);
		
		if(siacTVariazioneList!= null && !siacTVariazioneList.isEmpty()){
			for(int ll =0; ll<siacTVariazioneList.size();ll++){
				Variazione variazione = new Variazione();
				buildVariazione( siacTVariazioneList.get(ll),  variazione);
				variazioni.add(variazione);
				
			}
		}
		return variazioni;
	}


	public Long getTotaleVariazione(List<Integer> uidSac,Integer annoBilancio, int  enteProprietarioId){
		
		Long count  = variazioneDao.getTotaleVariazione( uidSac, annoBilancio,   enteProprietarioId);
		
		return count;
	}



	
	public Variazione findVariazioneByUid(Integer uid){
		SiacTVariazione siacTVariazione = variazioneDao.findVariazioneByUid(uid);
		Variazione variazione = new Variazione();
		buildVariazione( siacTVariazione,  variazione);
		return variazione;
	}
	
	public Long caricaTotaleGruppoAttivitaPendenti(String statoCode, boolean verificaSAC, List<Integer> uidSACDirezioneCollegate, String annoBilancio) {
		return variazioneDao.caricaTotaleVariazioniByStatoAndEventuallySAC(statoCode, verificaSAC,uidSACDirezioneCollegate, getEnte().getUid(), annoBilancio);
	}
	
	public ListaPaginata<AttivitaPendente> caricaAttivitaPendenti(String statoCode, Azione azione, List<Integer> uidSACDirezioneCollegate, String annoBilancio, Integer uidAccount, ParametriPaginazione pp){
		Page<SiacTVariazione> variaziones = variazioneDao.caricaVariazioniByStatoAndEventuallySAC(statoCode, Boolean.TRUE.equals(azione.getFlagVerificaSac()),uidSACDirezioneCollegate, getEnte().getUid(), annoBilancio, toPageable(pp));
		List<AttivitaPendente> aps = new ArrayList<AttivitaPendente>();
		for (SiacTVariazione vv : variaziones.getContent()) {
			AttivitaPendente ap = buildAttivitaPendente(vv, azione);
			aps.add(ap);
		}
		ListaPaginataImpl<AttivitaPendente> result = new ListaPaginataImpl<AttivitaPendente>(aps);

		result.setTotaleElementi((int) variaziones.getTotalElements());
		result.setTotalePagine(variaziones.getTotalPages());
		result.setPaginaCorrente(variaziones.getNumber());

		return result;
	}






	protected AttivitaPendente buildAttivitaPendente(SiacTVariazione vv, Azione azione) {
		SiacDVariazioneTipo dv = vv.getSiacDVariazioneTipo();
		
		AttivitaPendente ap = new AttivitaPendente();
		ap.setDataAperturaProposta(vv.getDataAperturaProposta());
		ap.setDataChiusuraProposta(vv.getDataChiusuraProposta());
		ap.setDescrizione(caricaDescrizioneAttivitaPendente(vv, dv));
		ap.setDescrizioneBreve(vv.getVariazioneDesc());
		ap.setUidEntita(vv.getUid());
		ap.setDirezioneProponente(caricaDirezioneProponente(vv));

		ap.setAzione(azione);
		ap.setTipologia(dv != null? dv.getTipologia() : "");
		return ap;
	}
		
	private String caricaDescrizioneAttivitaPendente(SiacTVariazione vv, SiacDVariazioneTipo dv) {
		StringBuilder d = new StringBuilder();
		d.append(vv.getVariazioneNum())
			.append(" - ")
			.append(vv.getVariazioneDesc())
			.append(" - ");
		
		if(dv != null) {
			d.append(dv.getVariazioneTipoDesc());
		}
		return d.toString();
	}






	private String caricaDirezioneProponente(SiacTVariazione vv) {
		
		if(vv.getSiacTClass()== null){
			return null;
		}
		StringBuilder sb = new StringBuilder();	
		if(vv.getSiacTClass().getCodice()!=null){
			sb.append(vv.getSiacTClass().getCodice());
		}
		if(vv.getSiacTClass().getDescrizione()!=null){
			sb.append(" - ");
			sb.append(vv.getSiacTClass().getDescrizione());
		}
		return sb.toString();
	}

	public String caricaStatoCodeByAzionePendente(Azione azione) {
		final String methodName = "caricaStatoByAzionePendente";
		List<SiacDVariazioneStato> founds = siacDVariazioneStatoRepository.findByAzionePendenteUid(azione.getUid(), getEnte().getUid());
		if(founds == null || founds.isEmpty() || founds.get(0) == null) {
			return null;
		}
		if(founds.size() > 1 ) {
			log.warn(methodName, "Trovati piu stati di variazione per azione pendente" + azione.getUid());
		}
		 return founds.get(0).getVariazioneStatoTipoCode();
	}


private void buildVariazione(SiacTVariazione siacTVariazione, Variazione variazione){
	
	if(siacTVariazione!= null ){
		variazione.setUid(siacTVariazione.getUid());
		variazione.setDescrizione(siacTVariazione.getVariazioneDesc());
		variazione.setDataApertura(siacTVariazione.getDataAperturaProposta());
		variazione.setDataChiusura(siacTVariazione.getDataChiusuraProposta());
		variazione.setNumeroVariazione(siacTVariazione.getVariazioneNum());
		if(siacTVariazione.getSiacTClass()!= null){
			StringBuilder sb = new StringBuilder();
			if(siacTVariazione.getSiacTClass().getCodice()!=null){
				sb.append(siacTVariazione.getSiacTClass().getCodice());
			}
			if(siacTVariazione.getSiacTClass().getDescrizione()!=null){
				sb.append(" - ");
				sb.append(siacTVariazione.getSiacTClass().getDescrizione());
			}
			variazione.setDirezione(sb.toString());
		}
	}
	
}


	
}
