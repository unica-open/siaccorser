/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siaccommonser.business.service.base.BaseService;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccounts;
import it.csi.siac.siaccorser.frontend.webservice.msg.GetAccountsResponse;
import it.csi.siac.siaccorser.integration.dad.CoreDad;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.Esito;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Service
@Scope("prototype")
public class GetAccountService extends BaseService<GetAccounts, GetAccountsResponse> {


	@Autowired
	protected CoreDad coreDad;
	
	@Transactional
	@Override
	public GetAccountsResponse executeService(GetAccounts serviceRequest){
		return super.executeService(serviceRequest);
	}

	@Override
	protected void execute() {
		int uid = req.getRichiedente().getAccount().getUid();
		Account account = coreDad.findAccountById(uid, req.getAnnoBilancio());
		if (account != null) {
			List<Account> accounts = new ArrayList<Account>();
			accounts.add(account);
			res.setAccounts(accounts);
		}else {
			res.addErrore(ErroreCore.ENTITA_NON_TROVATA.getErrore("operatore by ",
					String.format("UID: %s",uid)));
			res.setEsito(Esito.FALLIMENTO);
			return;
		}

	}

}
