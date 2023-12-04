/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.classificatori;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.business.service.BaseCoreService;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabile;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabileResponse;
import it.csi.siac.siaccorser.integration.dad.ClassificatoreDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LeggiStrutturaAmminstrativoContabileService
		extends
		BaseCoreService<LeggiStrutturaAmminstrativoContabile, LeggiStrutturaAmminstrativoContabileResponse> {

	@Autowired
	private ClassificatoreDad classificatoreDad;

	@Transactional(readOnly=true)
	@Override
	public LeggiStrutturaAmminstrativoContabileResponse executeService(LeggiStrutturaAmminstrativoContabile serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		checkNotNull(req.getAnno()!=0,
				ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("anno"));
		checkNotNull(req.getIdEnteProprietario()!=0,
				ErroreCore.PARAMETRO_NON_INIZIALIZZATO
						.getErrore("id ente proprietario"));
	}

	@Override
	protected void execute() {

		List<StrutturaAmministrativoContabile> strutture = classificatoreDad
				.leggiStrutturaAmministrativoContabile(req.getAnno(),
						req.getIdEnteProprietario());

		if (strutture.isEmpty()) {
			res.addErrore(ErroreCore.ENTITA_NON_TROVATA
					.getErrore("struttura amministrativo contabile"));
			res.setEsito(Esito.FALLIMENTO);
			return;
		}

		res.setListaStrutturaAmmContabile(strutture);
	}

}
