/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.errore;

import it.csi.siac.siaccorser.model.Errore;

public interface TipoErrore {

	Errore getErrore(Object... args);

}
