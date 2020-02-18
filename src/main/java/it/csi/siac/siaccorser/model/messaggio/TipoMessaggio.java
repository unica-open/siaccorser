/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.messaggio;

import it.csi.siac.siaccorser.model.Messaggio;

public interface TipoMessaggio {

	String getCodice();

	Messaggio getMessaggio(Object... args);

}
