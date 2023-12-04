/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccorser.integration.entity.SiacDFileStato;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siaccorser.integration.repository.SiacDFileStatoRepository;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional(propagation = Propagation.SUPPORTS)
public class SiacDFileStatoCache {
	@Autowired
	private SiacDFileStatoRepository statoFileRepository;

	private final Map<String, Integer> statoIdByCodiceCache = new HashMap<String, Integer>();

	private final Map<Integer, List<SiacDFileStato>> elencoStatoFileCache = new HashMap<Integer, List<SiacDFileStato>>();

	public Integer getStatoIdByCodice(SiacTEnteProprietario ente, String codice) {
		String key = String.format("%s:%d", codice, ente.getUid());

		if (!statoIdByCodiceCache.containsKey(key))
			statoIdByCodiceCache.put(key, statoFileRepository.getStatoByCodice(ente, codice)
					.getUid());

		return statoIdByCodiceCache.get(key);

	}

	public List<SiacDFileStato> getElencoStatoFile(SiacTEnteProprietario ente) {
		Integer key = ente.getUid();

		if (!elencoStatoFileCache.containsKey(key))
			elencoStatoFileCache.put(key, statoFileRepository.getElencoStatoFile(ente));

		return elencoStatoFileCache.get(key);
	}
}
