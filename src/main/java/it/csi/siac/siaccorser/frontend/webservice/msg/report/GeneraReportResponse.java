/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg.report;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.file.File;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GeneraReportResponse extends ServiceResponse {
	private Integer numeroPagineGenerate;
	
	private File report;

	public Integer getNumeroPagineGenerate() {
		return numeroPagineGenerate;
	}

	public void setNumeroPagineGenerate(Integer numeroPagineGenerate) {
		this.numeroPagineGenerate = numeroPagineGenerate;
	}

	public File getReport() {
		return report;
	}

	public void setReport(File report) {
		this.report = report;
	}

	
	
	
	
}
