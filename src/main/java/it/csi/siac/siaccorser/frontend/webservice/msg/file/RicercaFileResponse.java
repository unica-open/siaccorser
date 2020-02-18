/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg.file;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.PagedResponse;
import it.csi.siac.siaccorser.model.file.File;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class RicercaFileResponse extends PagedResponse<File> {

	/** Per la serializzazione */
	private static final long serialVersionUID = -2539859558957437281L;
	

}
