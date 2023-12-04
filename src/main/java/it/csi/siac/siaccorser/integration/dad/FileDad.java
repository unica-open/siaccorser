/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dad;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFile.CriteriRicercaFile;
import it.csi.siac.siaccorser.integration.cache.SiacDFileStatoCache;
import it.csi.siac.siaccorser.integration.dao.file.SiacTFileDao;
import it.csi.siac.siaccorser.integration.entity.SiacDFileTipo;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siaccorser.integration.entity.SiacTFile;
import it.csi.siac.siaccorser.integration.repository.SiacDFileTipoRepository;
import it.csi.siac.siaccorser.integration.repository.SiacTFileRepository;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.file.File;
import it.csi.siac.siaccorser.model.file.StatoFile;
import it.csi.siac.siaccorser.model.file.StatoFile.CodiceStatoFile;
import it.csi.siac.siaccorser.model.file.TipoFile;
import it.csi.siac.siaccorser.model.paginazione.ListaPaginata;
import it.csi.siac.siaccorser.model.paginazione.ParametriPaginazione;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class FileDad extends BaseCoreDad {
	@Autowired
	private SiacTFileDao fileDao;

	@Autowired
	private SiacDFileTipoRepository tipoFileRepository;

	@Autowired
	private SiacTFileRepository siacTFileRepository;

	@Autowired
	private SiacDFileStatoCache statoFileCache;

	public File create(File file) {
		SiacTFile fileEntity = toEnteBase(file, SiacTFile.class);

		fileEntity.setStatoId(statoFileCache.getStatoIdByCodice(fileEntity.getEnte(), file
				.getStatoFile().getCodice()));

		fileDao.create(fileEntity);

		fileEntity.getStato().setCodice(file.getStatoFile().getCodice());

		return map(fileEntity, File.class);
	}

	public ListaPaginata<File> ricercaFile(CriteriRicercaFile criteri,
			ParametriPaginazione parametriPaginazione) {
		Page<SiacTFile> elencoFile = siacTFileRepository.ricercaFile(
				super.map(getEnte(), SiacTEnteProprietario.class), criteri.getUid(),
				criteri.getCodice(), StringUtils.defaultString(criteri.getNome()),
				criteri.getIdTipo(), criteri.getStato(), StringUtils.defaultString(criteri.getDataUpload()),
				toPageable(parametriPaginazione, new Sort(new Order(Direction.DESC, "dataModifica"))));

		return super.toListaPaginata(elencoFile, File.class);
	}

	public TipoFile ricercaTipoFile(String codice) {
		SiacDFileTipo siacDFileTipo = tipoFileRepository.ricercaTipoFile(codice,
				map(getEnte(), SiacTEnteProprietario.class));
		
		return siacDFileTipo == null ? null : map(siacDFileTipo, TipoFile.class);
	}

	public List<TipoFile> getElencoTipoFileByIdAccount(Account account) {
		return super.convertiLista(
				tipoFileRepository.getElencoTipoFileByIdAccount(account.getUid(),
						super.map(getEnte(), SiacTEnteProprietario.class)), TipoFile.class);
	}

	public List<StatoFile> getElencoStatoFile() {
		return super.convertiLista(statoFileCache.getElencoStatoFile(super.map(getEnte(),
				SiacTEnteProprietario.class)), StatoFile.class);
	}

	public void updateStatoFile(Integer uid, CodiceStatoFile codiceStato, String loginOperazione) {
		SiacTEnteProprietario enteEntity = super.map(getEnte(), SiacTEnteProprietario.class);
		SiacTFile fileEntity = siacTFileRepository.find(enteEntity, uid);

		fileEntity.setStatoId(statoFileCache.getStatoIdByCodice(fileEntity.getEnte(),
				codiceStato.name()));

		fileEntity.setDataModifica(new Date());
		fileEntity.setLoginOperazione(loginOperazione);

		fileDao.update(fileEntity);
	}

	public void eliminaFile(Integer uid, String codiceFiscale) {
		siacTFileRepository.eliminaFile(uid, codiceFiscale);		
	}
}
