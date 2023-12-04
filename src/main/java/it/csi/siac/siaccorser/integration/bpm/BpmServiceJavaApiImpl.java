/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm;

import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.ow2.bonita.facade.ManagementAPI;
import org.ow2.bonita.facade.QueryRuntimeAPI;
import org.ow2.bonita.facade.RuntimeAPI;
import org.ow2.bonita.facade.def.majorElement.ProcessDefinition;
import org.ow2.bonita.facade.runtime.ActivityInstance;
import org.ow2.bonita.util.AccessorUtil;
import org.ow2.bonita.util.BonitaConstants;
import org.ow2.bonita.util.SimpleCallbackHandler;

import it.csi.siac.siaccommon.util.log.LogUtil;
import it.csi.siac.siaccorser.frontend.webservice.exception.SystemException;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcesso;
import it.csi.siac.siaccorser.integration.bpm.msg.AvviaProcessoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTask;
import it.csi.siac.siaccorser.integration.bpm.msg.EseguiTaskResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.GetTaskInfo;
import it.csi.siac.siaccorser.integration.bpm.msg.GetTaskInfoResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendenti;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaAttivitaPendentiResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaNotifichePendenti;
import it.csi.siac.siaccorser.integration.bpm.msg.RicercaNotifichePendentiResponse;
import it.csi.siac.siaccorser.integration.bpm.msg.TerminaProcesso;
import it.csi.siac.siaccorser.integration.bpm.msg.TerminaProcessoResponse;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.VariabileProcesso;

/**
 * Implementazione del servizio che permette di accedere al BPM server bonita.
 * che utilizza le chiamate rest offerte dalle API bonita, ma che non funziona
 * probabilmente perchè JBOSS delega ad un thread diverso da quello che fa il login
 * la chiamata http e l'header della chiamata non contiene la basic autentication.
 * 
 * Esempio di connessione con le api di bonita:
 * @see http://www.bonitasoft.org/blog/tutorial/how-to-use-the-bonita-http-api/
 * 
 * Il server va configurato come segue
 * @see http://www.bonitasoft.com/resources/documentation/bos-59/system-administration/installation/configure-bos-rest/bos-rest-jboss

 * @deprecated by SIAC-8332-REGP
 * @author alagna
 * @version $Id: $
 */
@Deprecated
public class BpmServiceJavaApiImpl implements BpmService {

	private static final LogUtil LOGGER = new LogUtil(BpmServiceJavaApiImpl.class);

	

	private static final String LOGIN = "restuser";
	private static final String PSSWD = "restbpm";
	private static final String REST_SERVER_ADDRESS = "http://localhost:8080/bonita-server-rest/";
	private static final String JAAS_FILE = "jaas-standard.cfg";

	private ManagementAPI managementAPI;
	private RuntimeAPI runtimeAPI;
	private QueryRuntimeAPI queryRuntimeAPI;

	/** Utilizzata per verificare se l'inizializzazione è già stata fatta */
	private boolean giaInizializzato = false;

	@Override
	public RicercaAttivitaPendentiResponse ricercaAttivitaPendenti(
			RicercaAttivitaPendenti request) {
		 init();
		return null;
	}

	@Override
	public RicercaNotifichePendentiResponse ricercaNotifichePendenti(
			RicercaNotifichePendenti request) {
		 init();
		return null;
	}
	
	/**
	 * Inizializza la connessione al server BPM
	 * 
	 * @throws SystemException
	 */
	private void init() {
		final String methodName = "init";
		// il servizio è già stato inizializzato => non fare nulla
		if (giaInizializzato)
			return;

		LOGGER.debug(methodName, "================================>");
		LOGGER.debug(methodName, "inizializzazione della connessione al server BPM");
		// set system properties
		System.setProperty(BonitaConstants.API_TYPE_PROPERTY, "REST");
		System.setProperty(BonitaConstants.REST_SERVER_ADDRESS_PROPERTY,
				REST_SERVER_ADDRESS);
		System.setProperty(BonitaConstants.JAAS_PROPERTY, JAAS_FILE);

		try {

			
			//verify the user exists 
			LoginContext loginContext = new LoginContext(
				"BonitaAuth", new SimpleCallbackHandler(LOGIN,PSSWD)); 
			loginContext.login();
			loginContext.logout();

			// propagate the user credentials
			loginContext = new LoginContext("BonitaStore",
				new SimpleCallbackHandler(LOGIN, PSSWD));
			loginContext.login();
			LOGGER.debug(methodName, "autenticazione ottenuta con successo");

			// get the APIs
			managementAPI = AccessorUtil.getManagementAPI();
			runtimeAPI = AccessorUtil.getRuntimeAPI();
			queryRuntimeAPI = AccessorUtil.getQueryRuntimeAPI();

			LOGGER.debug(methodName, "istanze di processo: "
					+ queryRuntimeAPI.getLightProcessInstances().size());

			LOGGER.debug(methodName, "connessione al server BPM inizializzata con successo");
			LOGGER.debug(methodName, "<================================");

			giaInizializzato = true;

		} catch (LoginException e) {
			LOGGER.error(methodName, "problema sull'autenticazione:", e);
		}
	}
	@Override
	public AvviaProcessoResponse avviaProcesso(AvviaProcesso request) {
		return null;
	}

	@Override
	public EseguiTaskResponse eseguiTask(EseguiTask request) {
		return null;
	}

	@Override
	public TerminaProcessoResponse terminaProcesso(TerminaProcesso request) {
		return null;
	}

	@Override
	public ProcessDefinition getUltimaVersione(String nomeProcesso) {
		return null;
	}

	@Override
	public List<ActivityInstance> getProssimaActivity(String idProcesso, boolean isTask) {
		return null;
	}

	@Override
	public ServiceResponse setVariabiliDiProcesso(String string, String string2,
			List<VariabileProcesso> variabiliDiProcesso) {
		return null;
	}

	@Override
	public AvviaProcessoResponse startProcesso(AvviaProcesso request) {
		return null;
	}

	@Override
	public Map<String, VariabileProcesso> getVariabiliDiProcesso(String idIstanzaProcesso,
			List<String> nomiVariabili) {
		return null;
	}

	@Override
	public String getIdIstanzaProcesso(String idAttivita) {
		return null;
	}

	@Override
	public GetTaskInfoResponse getTaskInfo(GetTaskInfo getTaskInfoReq) {
		return null;
	}

	@Override
	public void copiaVarIstanzaDaVarProcesso(
			Map<String, VariabileProcesso> mapVars,
			List<ActivityInstance> tasks, String nomeVarIstanza,
			String nomeVarProcesso) {
		// Non faccio alcunche'
	}

	@Override
	public Map<String, VariabileProcesso> getVariabiliDiProcesso(String idIstanzaProcesso) {
		return null;
	}
	
}
