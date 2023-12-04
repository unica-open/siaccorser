/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetVariazioni;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetVariazioniResponse;
import it.csi.siac.siaccorser.integration.dad.VariazioniDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.Variazione;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope("prototype")
public class GetVariazioniService extends BaseService<GetVariazioni, GetVariazioniResponse> {


	@Autowired
	protected VariazioniDad variazioniDad;
	
	@Transactional
	@Override
	public GetVariazioniResponse executeService(GetVariazioni serviceRequest){
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		int uid = req.getRichiedente().getAccount().getUid();
		List<Variazione> variazioni = variazioniDad.findVariazioniBySiac(req.getSacIdList(), req.getFirst(), req.getMaxresult(),
				req.getAnnoBilancio(),req.getRichiedente().getAccount().getEnte().getUid());
		Long totale = variazioniDad.getTotaleVariazione(req.getSacIdList(),req.getAnnoBilancio(),req.getRichiedente().getAccount().getEnte().getUid());
		if (variazioni != null) {
			res.setTotale(totale);
			res.setVariazioni(variazioni);
		}else {
			res.addErrore(ErroreCore.ENTITA_NON_TROVATA.getErrore("operatore by ",
					String.format("UID: %s",uid)));
			res.setEsito(Esito.FALLIMENTO);
			return;
		}

	}

}
