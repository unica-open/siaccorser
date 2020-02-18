/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg.file;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.CORDataDictionary;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.ServiceRequest;
import it.csi.siac.siaccorser.model.paginazione.ParametriPaginazione;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class RicercaFile extends ServiceRequest {
	private Ente ente;
	private CriteriRicercaFile criteri;
	private ParametriPaginazione parametriPaginazione = new ParametriPaginazione();

	public static RicercaFile buildRicercaFileRequest(CriteriRicercaFile criteri, int pagina, Richiedente richiedente, Ente ente) {
		RicercaFile req = new RicercaFile();

		req.setCriteri(criteri);
		req.getParametriPaginazione().setElementiPerPagina(3);
		req.getParametriPaginazione().setNumeroPagina(pagina);
		req.setRichiedente(richiedente);
		req.setEnte(ente);
		
		return req;
	}

	
	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public CriteriRicercaFile getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaFile criteri) {
		this.criteri = criteri;
	}

	public ParametriPaginazione getParametriPaginazione() {
		return parametriPaginazione;
	}

	public void setParametriPaginazione(ParametriPaginazione parametriPaginazione) {
		this.parametriPaginazione = parametriPaginazione;
	}

	@XmlType(namespace = CORDataDictionary.NAMESPACE)
	public static class CriteriRicercaFile implements Serializable {
		private static final long serialVersionUID = -7969819219736225200L;

		private Integer uid;
		private String codice;
		private String dataUpload;
		private Integer idTipo;
		private String stato;
		private String nome;

	

		public String getDataUpload() {
			return dataUpload;
		}

		public void setDataUpload(String dataUpload) {
			this.dataUpload = dataUpload;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Integer getIdTipo() {
			return idTipo;
		}

		public void setIdTipo(Integer idTipo) {
			this.idTipo = idTipo;
		}

		public String getCodice() {
			return codice;
		}

		public void setCodice(String codice) {
			this.codice = codice;
		}

		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

		public String getStato() {
			return stato;
		}

		public void setStato(String stato) {
			this.stato = stato;
		}

	

	}
}
