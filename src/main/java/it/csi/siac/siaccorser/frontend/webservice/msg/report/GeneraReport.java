/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg.report;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.frontend.webservice.msg.AsyncServiceRequest;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GeneraReport extends AsyncServiceRequest {
	private String objectXml;
	private String codiceTemplate;
	
	// SIAC-6110
	private boolean cleanReportContent = false;
	// SIAC-6232
	private Integer xmlFileUid;

	public String getObjectXml() {
		return objectXml;
	}

	public void setObjectXml(String objectXml) {
		this.objectXml = objectXml;
	}

	public String getCodiceTemplate() {
		return codiceTemplate;
	}

	public void setCodiceTemplate(String codiceTemplate) {
		this.codiceTemplate = codiceTemplate;
	}

	/**
	 * @return the cleanReportContent
	 */
	public boolean isCleanReportContent() {
		return this.cleanReportContent;
	}

	/**
	 * @param cleanReportContent the cleanReportContent to set
	 */
	public void setCleanReportContent(boolean cleanReportContent) {
		this.cleanReportContent = cleanReportContent;
	}

	/**
	 * @return the xmlFileUid
	 */
	public Integer getXmlFileUid() {
		return this.xmlFileUid;
	}

	/**
	 * @param xmlFileUid the xmlFileUid to set
	 */
	public void setXmlFileUid(Integer xmlFileUid) {
		this.xmlFileUid = xmlFileUid;
	}

}
