/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/

package it.csi.siac.siaccorser.integration.dad;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommon.util.collections.CollectionUtil;
import it.csi.siac.siaccommon.util.collections.Function;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccommonser.util.entity.EntityUtil;
import it.csi.siac.siaccorser.integration.dao.SiacDGestioneLivelloDao;
import it.csi.siac.siaccorser.integration.dao.SiacTAccountDao;
import it.csi.siac.siaccorser.integration.dao.SiacTAzioneDao;
import it.csi.siac.siaccorser.integration.dao.SiacTAzioneRichiestaDao;
import it.csi.siac.siaccorser.integration.dao.SiacTBilDao;
import it.csi.siac.siaccorser.integration.dao.SiacTClassDao;
import it.csi.siac.siaccorser.integration.dao.SiacTParametroAzioneRichiestaDao;
import it.csi.siac.siaccorser.integration.dao.SiacTSoggettoDao;
import it.csi.siac.siaccorser.integration.entity.SiacDGestioneLivello;
import it.csi.siac.siaccorser.integration.entity.SiacDGruppoAzioni;
import it.csi.siac.siaccorser.integration.entity.SiacDRuoloOp;
import it.csi.siac.siaccorser.integration.entity.SiacRAccountCassaEcon;
import it.csi.siac.siaccorser.integration.entity.SiacRAccountClass;
import it.csi.siac.siaccorser.integration.entity.SiacRAccountRuoloOp;
import it.csi.siac.siaccorser.integration.entity.SiacRGruppoRuoloOp;
import it.csi.siac.siaccorser.integration.entity.SiacRRuoloOpBil;
import it.csi.siac.siaccorser.integration.entity.SiacRSoggettoRuolo;
import it.csi.siac.siaccorser.integration.entity.SiacTAccount;
import it.csi.siac.siaccorser.integration.entity.SiacTAzione;
import it.csi.siac.siaccorser.integration.entity.SiacTAzioneRichiesta;
import it.csi.siac.siaccorser.integration.entity.SiacTBil;
import it.csi.siac.siaccorser.integration.entity.SiacTClass;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siaccorser.integration.entity.SiacTGruppo;
import it.csi.siac.siaccorser.integration.entity.SiacTParametroAzioneRichiesta;
import it.csi.siac.siaccorser.integration.entity.SiacTParametroConfigEnte;
import it.csi.siac.siaccorser.integration.entity.SiacTSoggetto;
import it.csi.siac.siaccorser.integration.repository.SiacDGestioneLivelloRepository;
import it.csi.siac.siaccorser.integration.repository.SiacRGruppoRuoloOpRepository;
import it.csi.siac.siaccorser.integration.repository.SiacTAccountRepository;
import it.csi.siac.siaccorser.integration.repository.SiacTBilRepository;
import it.csi.siac.siaccorser.integration.repository.SiacTEnteProprietarioRepository;
import it.csi.siac.siaccorser.integration.repository.SiacTGruppoRepository;
import it.csi.siac.siaccorser.integration.repository.SiacTOperazioneAsincronaRepository;
import it.csi.siac.siaccorser.integration.repository.SiacTParametroConfigEnteRepository;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.AnnoBilancio;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.Bilancio;
import it.csi.siac.siaccorser.model.CassaEconomaleCruscotto;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Gruppo;
import it.csi.siac.siaccorser.model.GruppoAzioni;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.ParametroAzioneRichiesta;
import it.csi.siac.siaccorser.model.ParametroConfigurazioneEnteEnum;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.Ruolo;
import it.csi.siac.siaccorser.model.RuoloAccount;
import it.csi.siac.siaccorser.model.RuoloGruppo;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;
import it.csi.siac.siaccorser.model.TipoAzione;
import it.csi.siac.siaccorser.model.TipoClassificatore;
import it.csi.siac.siaccorser.model.TipologiaGestioneLivelli;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope("prototype")
@Transactional
public class CoreDad extends BaseCoreDad {

	@Autowired private SiacTBilRepository siacTBilRepository;

	@Autowired private SiacTParametroConfigEnteRepository siacTParametroConfigEnteRepository;

	@Autowired private SiacTAccountRepository siacTAccountRepository;

	@Autowired private SiacDGestioneLivelloRepository siacDGestioneLivelloRepository;

	@Autowired private SiacTEnteProprietarioRepository siacTEnteProprietarioRepository;


	@Autowired private SiacTSoggettoDao soggettoDao;

	@Autowired private SiacTAccountDao accountDao;

	@Autowired private SiacTAzioneDao azioneDao;

	@Autowired private SiacTAzioneRichiestaDao azioneRichiestaDao;

	@Autowired private SiacTParametroAzioneRichiestaDao parametroAzioneRichiestaDao;

	@Autowired private SiacTBilDao annoBilancioDao;

	@Autowired private SiacDGestioneLivelloDao gestioneLivelloDao;

	@Autowired private SiacTOperazioneAsincronaRepository siacTOperazioneAsincronaRepository;

	@Autowired private SiacTGruppoRepository siacTGruppoRepository;

	@Autowired private SiacRGruppoRuoloOpRepository siacRGruppoRuoloOpRepository;

	@Autowired private SiacTClassDao siacTClassDao;
	
	
	
	
	
	private int numeroGiorni = 15;
	private int numeroMaxAzioni = 10;

	private List<Azione> cacheAzioni;

	/**
	 * @param numeroGiorni
	 *            the numeroGiorni to set
	 */
	public void setNumeroGiorni(int numeroGiorni) {
		this.numeroGiorni = numeroGiorni;
	}

	/**
	 * @param numeroMaxAzioni
	 *            the numeroMaxAzioni to set
	 */
	public void setNumeroMaxAzioni(int numeroMaxAzioni) {
		this.numeroMaxAzioni = numeroMaxAzioni;
	}

	public Operatore findOperatoreByCodiceFiscale(String codiceFiscale) {
		Operatore operatore = new Operatore();
		operatore.setCodiceFiscale(codiceFiscale);
		try {

			List<SiacTSoggetto> soggettiDto = soggettoDao
					.findSoggettiByCodiceFiscale(codiceFiscale);

			for (SiacTSoggetto soggettoDto : soggettiDto)
				for (SiacRSoggettoRuolo ruoloSoggettoSoggettoDto : soggettoDto.getRuoli())
					for (SiacTAccount accountDto : ruoloSoggettoSoggettoDto.getAccounts()) {
						if (accountDto.isEntitaValida()) {
							Account account = new Account();
							map(accountDto, account);
	
							/*
							 * RM 19-11-2014 Sull'account non bisogna settare l'ente
							 * del soggetto ma il proprio perche' un soggetto si lega
							 * a piu account
							 */
							// account.setEnte(ente);
							operatore.getAccounts().add(account);
						}
					}
		} catch (Throwable t) {
			log.error("", t);
		} 
		return operatore;
	}

	public Account findAccountById(int uid, Integer anno) {
		Account account = null;

		try {

			SiacTAccount siacTAccount = siacTAccountRepository.findAccountById(uid);

			if (siacTAccount != null) {
				account = new Account();

				map(siacTAccount, account);

				account.setOperatore(mapOperatore(siacTAccount));
				account.setEnte(mapEnte(siacTAccount.getEnte()));
				
				popolaRuoliGruppiAccount(account, anno, siacTAccount);
			}
		} catch (Throwable t) {
			log.error("findAccountById", t);
		} 

		return account;
	}
	
	public Account findAccountByIdNoRuoliGruppi(int uid) {
		Account account = null;

		try {
			SiacTAccount siacTAccount = siacTAccountRepository.findAccountById(uid);

			if (siacTAccount != null) {
				account = new Account();

				map(siacTAccount, account);

				account.setOperatore(mapOperatore(siacTAccount));
				account.setEnte(mapEnte(siacTAccount.getEnte()));
			}
		} catch (Throwable t) {
			log.error("findAccountByIdNoRuoliGruppi", t);
		} 

		return account;
	}	public Account getAccountWithRuoliGruppi(int uid, Integer anno) {
		Account account = getAccount(uid);

		if (account != null) {
			popolaRuoliGruppiAccount(account, anno, siacTAccountRepository.findOne(uid));
		}

		return account;
	}
	
	public Account getAccount(int uid) {
		SiacTAccount siacTAccount = EntityUtil.getValid(siacTAccountRepository.findOne(uid));

		if (siacTAccount == null) {
			return null;
		}
		
		Account account = new Account();

		map(siacTAccount, account);

		account.setOperatore(mapOperatore(siacTAccount));
		account.setEnte(mapEnte(siacTAccount.getEnte()));

		return account;
	}
	

	
	public void popolaRuoliGruppi(Account account, Integer anno) {
		SiacTAccount siacTAccount = siacTAccountRepository.findAccountById(account.getUid());
		popolaRuoliGruppiAccount(account, anno, siacTAccount);
		
	}
	
	private void popolaRuoliGruppiAccount(Account account, Integer anno, SiacTAccount siacTAccount) {
		cacheAzioni = new ArrayList<Azione>();
		account.setRuoli(mapRuoli(siacTAccount.getRuoli(), anno));
		
		//SIAC-8332
		//si risolve con questa segnalazione un problema pre-esistente: la mancanza di filtro su data_cancellazione e validita_fine sul collegamento account-sac
		List<SiacRAccountClass> siacRAccoutAccountClasses = siacTAccountRepository.findSiacRAccountClassesValidi(account.getUid(), anno);
		if(siacRAccoutAccountClasses != null && !siacRAccoutAccountClasses.isEmpty()) {
			account.setStruttureAmministrativeContabili(mapStruttureAmministrativeContabili(
					System.getProperty("account.sac.max") != null && siacRAccoutAccountClasses.size() > Integer.parseInt(System.getProperty("account.sac.max")) ? 
					siacRAccoutAccountClasses.subList(0, Integer.parseInt(System.getProperty("account.sac.max"))) :
						siacRAccoutAccountClasses, anno));
		}
		

		account.setCasseEconomali(mapCasseEconomali(siacTAccount
				.getSiacRAccountCassaEcons()));

//		account.setGruppi(mapGruppi(account.getUid(), account.getEnte().getUid(),
//				account.getRuoli()));
		
		account.setGruppi(mapGruppi(account.getUid(), account.getEnte().getUid(),
				account.getRuoli(), anno));
	}

	private List<StrutturaAmministrativoContabile> mapStruttureAmministrativeContabili(
			List<SiacRAccountClass> siacRAccountClasses, Integer anno) {
		List<StrutturaAmministrativoContabile> struttureAmministrativeContabili = new ArrayList<StrutturaAmministrativoContabile>();

		for (SiacRAccountClass siacRAccountClass : siacRAccountClasses)
			struttureAmministrativeContabili.add(map(siacRAccountClass
					.getStrutturaAmministrativoContabile(), anno));

		return struttureAmministrativeContabili;
	}

	private List<Gruppo> mapGruppi(Integer idAccount, Integer idEnte, List<RuoloAccount> ruoli) {
		List<Gruppo> gruppi = new ArrayList<Gruppo>();

		// RM 03-12-2014: modifica per impostare il filtro ente anche
		// sul gruppo
		List<SiacTGruppo> listSiacTGruppo = siacTGruppoRepository.findGruppiByIdAccount(idAccount);

		if (listSiacTGruppo != null) {
			for (SiacTGruppo siacTGruppo : listSiacTGruppo) {
				Gruppo gruppo = new Gruppo();
				map(gruppo, siacTGruppo);

				List<SiacRGruppoRuoloOp> relGruppoRuoloOp = siacRGruppoRuoloOpRepository
						.findRelGruppoRuoliOpByIdGruppo(siacTGruppo.getUid(), idEnte);

				if (relGruppoRuoloOp != null) {
					for (SiacRGruppoRuoloOp ruoloGruppoDto : relGruppoRuoloOp) {
						RuoloGruppo ruoloGruppo = new RuoloGruppo();
						map(ruoloGruppoDto, ruoloGruppo);

						SiacDRuoloOp siacDRuoloOp = ruoloGruppoDto.getRuolo();

						if (!checkRuoloPresente(siacDRuoloOp, ruoli)) {
							Ruolo ruolo = mapRuolo(siacDRuoloOp);
							ruoloGruppo.setRuolo(ruolo);
							gruppo.getRuoli().add(ruoloGruppo);

						}
					}
				}

				gruppi.add(gruppo);
			}
		}

		return gruppi;

	}

	
	private List<Gruppo> mapGruppi(Integer idAccount, Integer idEnte, List<RuoloAccount> ruoli, Integer anno) {
		List<Gruppo> gruppi = new ArrayList<Gruppo>();

		// RM 03-12-2014: modifica per impostare il filtro ente anche sul gruppo
		List<SiacTGruppo> listSiacTGruppo = siacTGruppoRepository.findGruppiByIdAccount(idAccount);

		if (listSiacTGruppo != null) {
			for (SiacTGruppo siacTGruppo : listSiacTGruppo) {
				Gruppo gruppo = new Gruppo();
				map(gruppo, siacTGruppo);

				List<SiacRGruppoRuoloOp> relGruppoRuoloOp = siacRGruppoRuoloOpRepository
						.findRelGruppoRuoliOpByIdGruppo(siacTGruppo.getUid(), idEnte);

				if (relGruppoRuoloOp != null) {
					for (SiacRGruppoRuoloOp ruoloGruppoDto : relGruppoRuoloOp) {
						
						RuoloGruppo ruoloGruppo = new RuoloGruppo();
						map(ruoloGruppoDto, ruoloGruppo);

						SiacDRuoloOp siacDRuoloOp = ruoloGruppoDto.getRuolo();
						
						boolean escludiRuolo = false; 
						
						// se non c'è la relazione in SiacRRuoloOpBil vuol dire che il ruolo non ha legami con l'anno, può essere mappato
						// altrimenti devo controllare l'anno di bilancio selezionato dall'utente
						if(siacDRuoloOp.getSiacRRuoloOpBils()!=null && !siacDRuoloOp.getSiacRRuoloOpBils().isEmpty()){
							
							for (SiacRRuoloOpBil siacRRuoloOpBils : siacDRuoloOp.getSiacRRuoloOpBils()) {
						
								Integer annodb = Integer.valueOf(siacRRuoloOpBils.getBilancio().getPeriodo().getAnno());
								if(anno.equals(annodb)){
									escludiRuolo =false;
									break;
								} escludiRuolo = true;
								
							}
						}
						
						if(!escludiRuolo){
							
							if (!checkRuoloPresente(siacDRuoloOp, ruoli)) {
								Ruolo ruolo = mapRuolo(siacDRuoloOp);
								ruoloGruppo.setRuolo(ruolo);
								gruppo.getRuoli().add(ruoloGruppo);
	
							}
						}
					}
				}

				gruppi.add(gruppo);
			}
		}

		return gruppi;

	}
	
	private List<CassaEconomaleCruscotto> mapCasseEconomali(
			List<SiacRAccountCassaEcon> siacRAccountCassaEcons) {
		List<CassaEconomaleCruscotto> casseEconomali = new ArrayList<CassaEconomaleCruscotto>();

		if (siacRAccountCassaEcons != null)
			for (SiacRAccountCassaEcon siacRAccountCassaEcon : siacRAccountCassaEcons)
				casseEconomali.add(map(siacRAccountCassaEcon));

		return casseEconomali;
	}

	private CassaEconomaleCruscotto map(SiacRAccountCassaEcon siacRAccountCassaEcon) {
		CassaEconomaleCruscotto ruoloCassaEconomale = new CassaEconomaleCruscotto();
		ruoloCassaEconomale.setUid(siacRAccountCassaEcon.getCassaEconomale().getUid());
		ruoloCassaEconomale.setCodice(siacRAccountCassaEcon.getCassaEconomale().getCassaeconCode());
		ruoloCassaEconomale.setDescrizione(siacRAccountCassaEcon.getCassaEconomale()
				.getCassaeconDesc());

		return ruoloCassaEconomale;
	}

	private boolean checkRuoloPresente(SiacDRuoloOp siacDRuoloOp, List<RuoloAccount> ruoliAccount) {
		for (RuoloAccount ruoloAccount : ruoliAccount)
			if (siacDRuoloOp.getUid().equals(ruoloAccount.getRuolo().getUid()))
				return true;

		return false;
	}

	private List<RuoloAccount> mapRuoli(List<SiacRAccountRuoloOp> ruoli) {
		List<RuoloAccount> ruoliAccount = new ArrayList<RuoloAccount>();

		if (ruoli != null)
			for (SiacRAccountRuoloOp siacRAccountRuoloOp : ruoli) {
				RuoloAccount ruoloAccount = new RuoloAccount();
				map(siacRAccountRuoloOp, ruoloAccount);

				SiacDRuoloOp siacDRuoloOp = siacRAccountRuoloOp.getRuolo();

				ruoloAccount.setRuolo(mapRuolo(siacDRuoloOp));

				ruoliAccount.add(ruoloAccount);
			}

		return ruoliAccount;
	}
	
	
	private List<RuoloAccount> mapRuoli(List<SiacRAccountRuoloOp> ruoli, Integer anno) {
		List<RuoloAccount> ruoliAccount = new ArrayList<RuoloAccount>();

		if (ruoli != null)
			
			for (SiacRAccountRuoloOp siacRAccountRuoloOp : ruoli) {
				
				SiacDRuoloOp siacDRuoloOp = siacRAccountRuoloOp.getRuolo();
				
				boolean escludiRuolo = false; 
				
				// se non c'è la relazione in SiacRRuoloOpBil vuol dire che il ruolo non ha legami con l'anno, può essere mappato
				// altrimenti devo controllare l'anno di bilancio selezionato dall'utente
				if(siacDRuoloOp.getSiacRRuoloOpBils()!=null && !siacDRuoloOp.getSiacRRuoloOpBils().isEmpty()){
					
					for (SiacRRuoloOpBil siacRRuoloOpBils : siacDRuoloOp.getSiacRRuoloOpBils()) {
				
						Integer annodb = Integer.valueOf(siacRRuoloOpBils.getBilancio().getPeriodo().getAnno());
						if(anno.equals(annodb)){
							escludiRuolo = false;
							break;
						} escludiRuolo = true;
						
					}
				}
				
				if(!escludiRuolo){
					
					RuoloAccount ruoloAccount = new RuoloAccount();
					map(siacRAccountRuoloOp, ruoloAccount);
					
					ruoloAccount.setRuolo(mapRuolo(siacDRuoloOp));
	
					ruoliAccount.add(ruoloAccount);
				}
			}

		return ruoliAccount;
	}


	private Ente mapEnte(SiacTEnteProprietario siacTEnte) {
		Ente ente = new Ente();
		map(siacTEnte, ente);
		return ente;
	}

	private Operatore mapOperatore(SiacTAccount accountDto) {
		SiacTSoggetto soggettoDto = accountDto.getRuoloSoggetto().getSoggetto();
		Operatore operatore = new Operatore();
		operatore.setCodiceFiscale(soggettoDto.getCodiceFiscale());
		operatore.setNome(soggettoDto.getNome());
		return operatore;
	}


	public AzioneRichiesta saveAzioneRichiesta(AzioneRichiesta azioneRichiesta, Ente ente, String codiceFiscale) {
		try {
			SiacTAzioneRichiesta azioneRichiestaDto = new SiacTAzioneRichiesta();
			map(azioneRichiesta, azioneRichiestaDto);
			SiacTAccount accountDto = accountDao.findAccountById(azioneRichiesta.getAccount()
					.getUid());
			azioneRichiestaDto.setAccount(accountDto);
			SiacTAzione azioneDto = azioneDao.findAzioneById(azioneRichiesta.getAzione().getUid());
			azioneRichiestaDto.setLoginOperazione(codiceFiscale);
			azioneRichiestaDto.setAzione(azioneDto);
			azioneRichiestaDto = azioneRichiestaDao.saveAzioneRichiesta(azioneRichiestaDto);

			for (ParametroAzioneRichiesta parametro : azioneRichiesta.getParametri()) {
				if (parametro.getValore() == null) {
					continue;
				}
				
				parametro.setEnte(ente);
				SiacTParametroAzioneRichiesta parametroDto = new SiacTParametroAzioneRichiesta();
				map(parametro, parametroDto);
				parametroDto.setAzioneRichiesta(azioneRichiestaDto);
				
				parametroAzioneRichiestaDao.saveParametroAzioneRichiesta(parametroDto);
			}

			azioneRichiesta.setUid(azioneRichiestaDto.getUid());
		} catch (Throwable t) {
			log.error("saveAzioneRichiesta", t);
		} 

		return azioneRichiesta;
	}

	public AzioneRichiesta findAzioneRichiestaById(int idAzioneRichiesta) {
		AzioneRichiesta azioneRichiesta = new AzioneRichiesta();
		try {
			SiacTAzioneRichiesta siacTAzioneRichiesta = azioneRichiestaDao.findValidAzioneRichiestaById(idAzioneRichiesta);
			
			if (siacTAzioneRichiesta == null) {
				return null;
			}
			
			map(siacTAzioneRichiesta, azioneRichiesta);

			SiacTAccount accountDto = siacTAzioneRichiesta.getAccount();
			Account account = new Account();
			map(accountDto, account);

			SiacTEnteProprietario enteDto = accountDto.getEnte();
			Ente ente = new Ente();
			map(enteDto, ente);
			account.setEnte(ente);

			Operatore operatore = mapOperatore(accountDto);
			account.setOperatore(operatore);
			azioneRichiesta.setAccount(account);

			SiacTAzione azioneDto = siacTAzioneRichiesta.getAzione();
			Azione azione = new Azione();
			map(azioneDto, azione);
			azioneRichiesta.setAzione(azione);
			for (SiacTParametroAzioneRichiesta parametroDto : siacTAzioneRichiesta.getParametri()) {
				ParametroAzioneRichiesta parametro = new ParametroAzioneRichiesta();
				map(parametroDto, parametro);
				azioneRichiesta.getParametri().add(parametro);
			}
			
			azioneRichiestaDao.deleteAzioneRichiesta(siacTAzioneRichiesta);
			
		} catch (Throwable t) {
			log.error("findAzioneRichiestaById", t);
		} 

		return azioneRichiesta;
	}

	 /**
     * @deprecated
     * This method is no longer used.
     * <p> See {@link it.csi.siac.siaccorser.model.ParametroConfigurazioneEnteEnum}.
     */	
	@Deprecated
	public Map<TipologiaGestioneLivelli, String> findGestioneLivelloByIdEnte(int idEnte) {
		Map<TipologiaGestioneLivelli, String> map = new HashMap<TipologiaGestioneLivelli, String>();
		try {
			List<SiacDGestioneLivello> dtos = gestioneLivelloDao
					.findGestioneLivelloByIdEnte(idEnte);
			if (dtos != null && !dtos.isEmpty())
				for (SiacDGestioneLivello gestioneLivelloDto : dtos)
					if (gestioneLivelloDto.getGestioneTipo() != null
							&& StringUtils.isNotEmpty(gestioneLivelloDto.getGestioneTipo().getCodice())) {
						String codiceTipoGestione = gestioneLivelloDto.getGestioneTipo().getCodice();
						TipologiaGestioneLivelli tipo = TipologiaGestioneLivelli.fromCodice(codiceTipoGestione);
						if (tipo != null)
							map.put(tipo, gestioneLivelloDto.getCodice());
					}
		} catch (Throwable t) {
			log.error("findGestioneLivelloByIdEnte", t);
		} 

		return map;
	}

	public List<AzioneRichiesta> findAzioniFrequenti(int idAccount) {
		List<AzioneRichiesta> azioniFrequenti = new ArrayList<AzioneRichiesta>();
		try {
			List<SiacTAzioneRichiesta> azioniFrequentiDto = azioneRichiestaDao.findAzioniFrequenti(
					idAccount, numeroGiorni, numeroMaxAzioni);
			for (SiacTAzioneRichiesta azioneRichiestaDto : azioniFrequentiDto) {
				AzioneRichiesta azioneRichiesta = new AzioneRichiesta();
				map(azioneRichiestaDto, azioneRichiesta);
				Azione azione = new Azione();
				map(azioneRichiestaDto.getAzione(), azione);
				azioneRichiesta.setAzione(azione);
				azioniFrequenti.add(azioneRichiesta);
			}
		} catch (Throwable t) {
			log.error("findAzioniFrequenti", t);
		} 

		return azioniFrequenti;
	}

	public Integer getCountOperazioneAsincronaDaNotificare(Integer idAccount, Integer idAzione,
			Integer idEnte) {

		Integer totali = siacTOperazioneAsincronaRepository
				.getCountOperazioneAsincronaDaNotificare(idAccount, idAzione, idEnte).intValue();
		return totali;
	}

	public List<AnnoBilancio> findAnniBilancio(int idEnte) {
		List<AnnoBilancio> anniBilancio = new ArrayList<AnnoBilancio>();
		try {
			List<SiacTBil> anniBilancioDto = annoBilancioDao.findAnniBilancioByEnte(idEnte);
			for (SiacTBil annoBilancioDto : anniBilancioDto) {
				AnnoBilancio annoBilancio = new AnnoBilancio();
				map(annoBilancioDto, annoBilancio);
				anniBilancio.add(annoBilancio);
			}
		} catch (Throwable t) {
			log.error("getCountOperazioneAsincronaDaNotificare", t);
		} 

		return anniBilancio;
	}

	/**
	 * Dato il ruolo mappa le azioni e il gruppo su ci sono configurate (es:
	 * inserisci capitolo UG per gruppo Capitoli di Gestione)
	 * 
	 * @param siacDRuoloOp
	 * @param ruolo
	 * @param cacheAzioni
	 */
	private Ruolo mapRuolo(SiacDRuoloOp siacDRuoloOp) {
		final String methodName = "mapRuolo";
		Ruolo ruolo = new Ruolo();

		map(siacDRuoloOp, ruolo);
		mapAnniBilancio(siacDRuoloOp.getSiacRRuoloOpBils(), ruolo.getAnniBilancio());

		// FIXME: Eseguuo una query che dato il ruolo mi tira su le azioni
		// per ottenere il getAzioni by ente e dataCancellazione is null
		List<SiacTAzione> azioni = azioneDao.findAzioniByRuoloOp(siacDRuoloOp.getUid(),
				siacDRuoloOp.getEnte());

		if (azioni != null) {
			for (SiacTAzione siacTAzione : azioni) {
				if (!azioneInCache(siacTAzione)) {
					Azione azione = new Azione();
					map(siacTAzione, azione);

					cacheAzioni.add(azione);

					if (siacTAzione.getGruppo() != null) {
						GruppoAzioni gruppoAzioni = new GruppoAzioni();
						map(siacTAzione.getGruppo(), gruppoAzioni);
						azione.setGruppo(gruppoAzioni);
					}
					
					
					ruolo.getAzioni().add(azione);
				}
			}
		}

		if(log.isTraceEnabled()) {
			log.trace(methodName, "Ho mappato per il ruolo " + ruolo.getUid() + " - nome: " + ruolo.getNome() + ", numero azioni: " + ruolo.getAzioni().size() + ". Le azioni sono: " );
			for (Azione azione : ruolo.getAzioni()) {
				log.trace(methodName, "[" + azione.getNome() + "]");
			}
		}
		
		return ruolo;
	}

	private void mapAnniBilancio(List<SiacRRuoloOpBil> siacRRuoloOpBils, Set<Integer> anniBilancio) {
		
		for (SiacRRuoloOpBil rob : siacRRuoloOpBils) {
			Integer anno = Integer.valueOf(rob.getBilancio().getPeriodo().getAnno());
			anniBilancio.add(anno);
		}
	}

	private boolean azioneInCache(SiacTAzione azioneDto) {
		for (Azione azione : cacheAzioni) {
			// RM 19-12-2014
			// se sono su un'attività di avvio o esecuzione di un
			// processo aggiungo al controllo per codice anche nome
			// task
			// nome processo (ci sono task come l'aggiornamento
			// variazione che hanno lo stesso codice)

			String codiceTipoAzione = azioneDto.getTipo().getCodice();

			
			if (codiceTipoAzione.equalsIgnoreCase(TipoAzione.AVVIO_PROCESSO.toString())
					|| codiceTipoAzione.equalsIgnoreCase(TipoAzione.ATTIVITA_PROCESSO.toString())){
				
					if (azione.getNome().equals(azioneDto.getNome())
						&& azione.getNomeProcesso().equals(azioneDto.getNomeProcesso())
						&& azione.getNomeTask().equals(azioneDto.getNomeTask()))
					return true;
			}else{

				if (azione.getNome().equals(azioneDto.getNome()))
					return true;
			}

		}

		return false;
	}

	private void map(SiacTEnteProprietario enteDto, Ente ente) {
		ente.setUid(enteDto.getUid());
		ente.setNome(enteDto.getEnteDenominazione());
	}

	private void map(SiacTAccount siacTAccount, Account account) {
		account.setUid(siacTAccount.getUid());
		account.setCodice(siacTAccount.getCodice());
		account.setDescrizione(siacTAccount.getDescrizione());
		account.setIndirizzoMail(siacTAccount.getIndirizzoMail());
		account.setNome(siacTAccount.getNome());
		Ente ente = mapEnte(siacTAccount.getEnte());
		account.setEnte(ente);
	}

	private void map(SiacRAccountRuoloOp ruoloAccountDto, RuoloAccount ruoloAccount) {
		ruoloAccount.setUid(ruoloAccountDto.getUid());
	}

	private void map(SiacDRuoloOp ruoloDto, Ruolo ruolo) {
		ruolo.setUid(ruoloDto.getUid());
		ruolo.setDescrizione(ruoloDto.getDescrizione());
		ruolo.setNome(ruoloDto.getNome());
	}

	private StrutturaAmministrativoContabile map(SiacTClass siacTClass, Integer anno) {
		StrutturaAmministrativoContabile strutturaAmministrativoContabile = new StrutturaAmministrativoContabile();

		// RM modifica fatta per il decentrato, serve restituire la porzione di
		// tree
		// partendo dalla sac associata al ruolo_op account
		strutturaAmministrativoContabile.setUid(siacTClass.getUid());
		strutturaAmministrativoContabile.setCodice(siacTClass.getCodice());
		strutturaAmministrativoContabile.setDescrizione(siacTClass.getDescrizione());
		/*
		 *SIAC-7633
		 *build del padre della componente 
		 */
		if(siacTClass.getPadre()!= null){
			strutturaAmministrativoContabile.setUidPadre(siacTClass.getPadre().getUid());
			strutturaAmministrativoContabile.setCodicePadre(siacTClass.getPadre().getCodice());
			TipoClassificatore tipoClassifPadre = map(siacTClass.getPadre().getTipoClassificatore(), TipoClassificatore.class);
			strutturaAmministrativoContabile.setTipoClassificatorePadre(tipoClassifPadre);
			strutturaAmministrativoContabile.setDescrizionePadre(siacTClass.getPadre().getDescrizione());
		}
		
		TipoClassificatore tipoClassif = map(siacTClass.getTipoClassificatore(), TipoClassificatore.class);
		strutturaAmministrativoContabile.setTipoClassificatore(tipoClassif);

		List<SiacTClass> figli = siacTClassDao.findTreeByCodiceFamigliaAndParentId(anno, siacTClass.getEnte().getUid(), siacTClass.getUid());

		if (!figli.isEmpty()) {
			List<StrutturaAmministrativoContabile> substrutture = convertiLista(figli,
					StrutturaAmministrativoContabile.class);

			strutturaAmministrativoContabile.setSubStrutture(substrutture);
		}

		return strutturaAmministrativoContabile;
	}
	
	protected List<Azione> mapAzioni(List<SiacTAzione> azioniDto) {
		List<Azione> res = new ArrayList<Azione>();
		for(SiacTAzione sta : azioniDto) {
			res.add(map(sta));
		}
		return res;
	}

	private Azione map(SiacTAzione azioneDto) {
		Azione azione = new Azione();
		
		map(azioneDto, azione);
		
		return azione;
	}

	
	private void map(SiacTAzione azioneDto, Azione azione) {
		azione.setUid(azioneDto.getUid());
		azione.setNome(azioneDto.getNome());
		azione.setNomeProcesso(azioneDto.getNomeProcesso());
		azione.setNomeTask(azioneDto.getNomeTask());
		azione.setTipo(TipoAzione.valueOf(azioneDto.getTipo().getCodice()));
		azione.setTitolo(azioneDto.getTitolo());
		azione.setUrlApplicazione(azioneDto.getUrlApplicazione());
		azione.setFlagVerificaSac(azioneDto.getVerificaSAC());
	}

	private void map(SiacDGruppoAzioni gruppoAzioniDto, GruppoAzioni gruppoAzioni) {
		gruppoAzioni.setUid(gruppoAzioniDto.getUid());
		gruppoAzioni.setDescrizione(gruppoAzioniDto.getDescrizione());
		gruppoAzioni.setNome(gruppoAzioniDto.getNome());
		gruppoAzioni.setTitolo(gruppoAzioniDto.getTitolo());
	}

	private void map(Gruppo gruppo, SiacTGruppo gruppoDto) {
		gruppo.setUid(gruppoDto.getUid());
		gruppo.setDescrizione(gruppoDto.getDescrizione());
		gruppo.setNome(gruppoDto.getNome());
	}

	private void map(SiacRGruppoRuoloOp ruoloGruppoDto, RuoloGruppo ruoloGruppo) {
		ruoloGruppo.setUid(ruoloGruppoDto.getUid());
	}

	private void map(AzioneRichiesta azioneRichiesta, SiacTAzioneRichiesta azioneRichiestaDto) {
		azioneRichiestaDto.setUid(azioneRichiesta.getUid());
		azioneRichiestaDto.setDaCruscotto(azioneRichiesta.isDaCruscotto());
		azioneRichiestaDto.setIdAttivita(azioneRichiesta.getIdAttivita());
		azioneRichiestaDto.setData(azioneRichiesta.getData());
		SiacTEnteProprietario ente = new SiacTEnteProprietario();
		ente.setUid(azioneRichiesta.getAccount().getEnte().getUid());
		azioneRichiestaDto.setEnte(ente);
	}

	private void map(SiacTAzioneRichiesta azioneRichiestaDto, AzioneRichiesta azioneRichiesta) {
		azioneRichiesta.setUid(azioneRichiestaDto.getUid());
		azioneRichiesta.setDaCruscotto(azioneRichiestaDto.isDaCruscotto());
		azioneRichiesta.setIdAttivita(azioneRichiestaDto.getIdAttivita());
		azioneRichiesta.setData(azioneRichiestaDto.getData());
	}

	private void map(ParametroAzioneRichiesta parametro, SiacTParametroAzioneRichiesta parametroDto) {
		parametroDto.setUid(null);
		parametroDto.setDataModifica(new Date());
		parametroDto.setDataCreazione(new Date());
		parametroDto.setDataInizioValidita(new Date());
		parametroDto.setUid(parametro.getUid());
		parametroDto.setNome(parametro.getNome());
		parametroDto.setValore(parametro.getValore());
		parametroDto.setLoginOperazione("ADMIN");
		SiacTEnteProprietario ente = new SiacTEnteProprietario();
		ente.setUid(parametro.getEnte().getUid());
		parametroDto.setEnte(ente);
	}

	private void map(SiacTParametroAzioneRichiesta parametroDto, ParametroAzioneRichiesta paremetro) {
		paremetro.setUid(parametroDto.getUid());
		paremetro.setNome(parametroDto.getNome());
		paremetro.setValore(parametroDto.getValore());
	}

	private void map(SiacTBil annoBilancioDto, AnnoBilancio annoBilancio) {
		annoBilancio.setIdBilancio(annoBilancioDto.getUid());
		annoBilancio.setIdPeriodo(annoBilancioDto.getPeriodo().getUid());
		Integer anno = Integer.valueOf(annoBilancioDto.getPeriodo().getAnno());
		annoBilancio.setAnno(anno);

		if (annoBilancioDto.getFaseOperativa() != null) {
			annoBilancio.setCodiceFase(annoBilancioDto.getFaseOperativa().getCodice());
			annoBilancio.setDescrizioneFase(annoBilancioDto.getFaseOperativa().getDescrizione());
		} else {
			throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("fase operativa"));
		}
	}	
	
	
	
	public Ente ricercaEnte(String codiceFiscale) {
		return map(siacTEnteProprietarioRepository.ricercaEnte(codiceFiscale), Ente.class);
	}

	
	public Bilancio ricercaBilancio(Ente ente, String anno) {
		return map(siacTBilRepository.ricercaBilancio(ente.getUid(), anno), Bilancio.class);
	}

	
	public Account ricercaAccount(String codice) {
		SiacTAccount siacTAccount = siacTAccountRepository.ricercaAccount(codice);
		
		return siacTAccount == null ? null : map(siacTAccount, Account.class);
	}

	
	public Richiedente getRichiedente(String codiceAccount) {
		Account account = ricercaAccount(codiceAccount);
		
		if (account == null) {
			return null;
		}

		Richiedente richiedente = new Richiedente();

		richiedente.setOperatore(account.getOperatore());
		richiedente.setAccount(account);

		return richiedente;
	}

	public String readMessaggioInformativo(Integer idEnte) {
		SiacDGestioneLivello siacDGestioneLivello = siacDGestioneLivelloRepository
				.getGestioneLivello(idEnte, TipologiaGestioneLivelli.MESSAGGIO_INFORMATIVO.getCodice());

		return siacDGestioneLivello.getDescrizione();
	}

	public Azione findAzioneById(Integer idAzione) {
		return map(azioneDao.findAzioneById(idAzione));
	}

	public List<Azione> findAzioneByNome(String nomeAzione, Integer idEnte, Integer accountId) {
		return mapAzioni(azioneDao.findAzioneByNome(nomeAzione, idEnte, accountId));
	}
	
	
	
	public Map<ParametroConfigurazioneEnteEnum, String> getParametriConfigurazioneEnte() {
		return getParametriConfigurazioneEnte(getEnte().getUid());
	}

	public Map<ParametroConfigurazioneEnteEnum, String> getParametriConfigurazioneEnte(Integer idEnte) {
		try {
			return CollectionUtil.convertToMap(
					siacTParametroConfigEnteRepository.findAll(idEnte, Boolean.TRUE),
					new Function<SiacTParametroConfigEnte, ParametroConfigurazioneEnteEnum>() {

						@Override
						public ParametroConfigurazioneEnteEnum map(SiacTParametroConfigEnte source) {
							try {
								return ParametroConfigurazioneEnteEnum.byNomeParametro(source.getParametroNome());
							}
							catch (IllegalArgumentException e) {
								log.warn("getParametriConfigurazioneEnte", e.getMessage());
							}
							
							return null;
						}
					}, 
					new Function<SiacTParametroConfigEnte, String>() {

						@Override
						public String map(SiacTParametroConfigEnte source) {
							return source.getParametroValore();
						}
					}			
			);
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	public String getParametroConfigurazioneEnte(String nome) {
		try {
			return siacTParametroConfigEnteRepository.find(getEnte().getUid(), nome, Boolean.TRUE).getParametroValore();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
}
