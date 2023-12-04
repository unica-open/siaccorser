/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.siac.siaccorser.integration.entity.SiacTVariazione;

public interface SiacTVariazioneRepository extends JpaRepository<SiacTVariazione, Integer> {

}
