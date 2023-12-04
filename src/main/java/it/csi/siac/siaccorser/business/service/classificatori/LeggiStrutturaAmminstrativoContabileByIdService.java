/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service.classificatori;

import it.csi.siac.siaccommonser.business.service.base.exception.ServiceParamError;
import it.csi.siac.siaccorser.business.service.BaseCoreService;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabile;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabileResponse;
import it.csi.siac.siaccorser.integration.dad.ClassificatoreDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("prototype")
public class LeggiStrutturaAmminstrativoContabileByIdService extends BaseCoreService<LeggiStrutturaAmminstrativoContabile, LeggiStrutturaAmminstrativoContabileResponse> {

	//SIAC-6884: implementata ricerca SAC by Id, poi diventata superflua (questa classe al momento non viene usata, ma potrebbe tornare utile)
	
	@Autowired
	private ClassificatoreDad classificatoreDad;

	@Transactional(readOnly=true)
	@Override
	public LeggiStrutturaAmminstrativoContabileResponse executeService(LeggiStrutturaAmminstrativoContabile serviceRequest) {
		return super.executeService(serviceRequest);
	}

	@Override
	protected void checkServiceParam() throws ServiceParamError {
		//checkNotNull(req.getSacUid() !=0, ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("sacUid"));
	}

	@Override
	protected void execute() {
		StrutturaAmministrativoContabile struttura = null;//classificatoreDad.leggiStrutturaAmministrativoContabileById(req.getSacUid());
		//if (struttura == null) {
		//	res.addErrore(ErroreCore.ENTITA_NON_TROVATA.getErrore("struttura amministrativo contabile"));
		//	res.setEsito(Esito.FALLIMENTO);
			return;
		//}
		//res.setStrutturaAmmContabile(struttura);
	}

}
