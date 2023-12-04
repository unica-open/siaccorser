/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entitymapping.beanfactory;

import org.dozer.BeanFactory;

import it.csi.siac.siaccorser.integration.entity.SiacDFileStato;
import it.csi.siac.siaccorser.model.file.StatoFile;

public class StatoFileBeanFactory implements BeanFactory {
	@Override
	public Object createBean(Object src, Class<?> srcClass, String beanFactoryId) {
		SiacDFileStato statoFileEntity = (SiacDFileStato) src;

		return new StatoFile(statoFileEntity.getCodice());
	}
}
