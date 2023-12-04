/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccounts;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccountsResponse;
import it.csi.siac.siaccorser.integration.dad.CoreDad;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GetAccountsService extends BaseService<GetAccounts, GetAccountsResponse> {


	@Autowired
	protected CoreDad coreDad;
	
	@Transactional
	@Override
	public GetAccountsResponse executeService(GetAccounts serviceRequest){
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		String codiceFiscale = req.getRichiedente().getOperatore().getCodiceFiscale();
		Operatore operatore = coreDad.findOperatoreByCodiceFiscale(codiceFiscale);
		if (operatore != null) {
			res.setAccounts(operatore.getAccounts());
		}else {
			res.addErrore(ErroreCore.ENTITA_NON_TROVATA.getErrore("operatore by ",
					String.format("Codice fiscale: %s",codiceFiscale)));
			res.setEsito(Esito.FALLIMENTO);
			return;
		}

	}

}
