/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity.enumeration;

import it.csi.siac.siaccorser.model.Codifica;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;

public enum SiacDClassFamEnum {

StrutturaAmministrativaContabile("00005", StrutturaAmministrativoContabile.class);
	
	private String codice;
	private Class<? extends Codifica> codificaClass;
		

	SiacDClassFamEnum(String codice, Class<? extends Codifica> codificaClass){		
		this.codice = codice;
		this.codificaClass = codificaClass;
	}

	

	public String getCodice() {
		return codice;
	}

	public Class<? extends Codifica> getCodificaClass() {
		return codificaClass;
	}
	
	
	public static SiacDClassFamEnum byCodice(String codice){
		for(SiacDClassFamEnum e : SiacDClassFamEnum.values()){
			if(e.getCodice().equals(codice)){
				return e;
			}
		}
		throw new IllegalArgumentException("Il codice "+ codice + " non ha un mapping corrispondente in SiacDClassFamEnum");
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Codifica> T getCodificaInstance() {
		try {
			return (T) codificaClass.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalStateException("Eccezione istanziamento TipoClassificatore."+name()+"->"+codificaClass + " ["+codice+"]",e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Impossibile accedere al costruttore vuoto di  "+codificaClass + " ["+codice+"]",e);
		}
	}



}
