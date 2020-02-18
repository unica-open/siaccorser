/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.file;

import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccommon.util.CoreUtils;
import it.csi.siac.siaccorser.model.CORDataDictionary;


/**
 * TipoFileEnum: enum che raccoglie i codici dei tipi file
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public enum TipoFileEnum {
	
	
	ORDINATIVO_SPESA, ORDINATIVO_ENTRATA, 
	PREDOCUMENTI_SPESA, PREDOCUMENTI_ENTRATA,
	DOCUMENTO_SPESA, DOCUMENTO_ENTRATA;
	
	private static final Map<String, TipoFileEnum> REVERSE_MAP = CoreUtils
			.getEnumMap(TipoFileEnum.class);


	public static TipoFileEnum fromCodice(String codice) {
		return REVERSE_MAP.get(codice);
	}

}
