/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.dad;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.integration.dad.base.BaseDadImpl;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteBase;
import it.csi.siac.siaccorser.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Entita;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public abstract class BaseCoreDad extends BaseDadImpl {

	private Ente ente;
	private String loginOperazione;

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public String getLoginOperazione() {
		return loginOperazione;
	}

	public void setLoginOperazione(String loginOperazione) {
		this.loginOperazione = loginOperazione;
	}

	protected <T extends SiacTEnteBase> T toEnteBase(Entita entita, Class<T> c) {
		T dest = super.map(entita, c);

		dest.setEnte(super.map(getEnte(), SiacTEnteProprietario.class));
		dest.setLoginOperazione(loginOperazione);

		return dest;
	}
}
