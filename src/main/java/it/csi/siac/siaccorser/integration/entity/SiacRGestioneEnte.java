/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_r_gestione_ente")
public class SiacRGestioneEnte extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8081498448686979206L;

	@Id
	@Column(name = "gestione_ente_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_R_GESTIONE_ENTE")
	@SequenceGenerator(name = "SEQ_R_GESTIONE_ENTE", allocationSize=1,sequenceName = "siac_r_gestione_ente_gestione_ente_id_seq")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "gestione_livello_id", nullable = false)
	private SiacDGestioneLivello gestioneLivelloDto;
	
	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid= uid;
	}

	public SiacDGestioneLivello getGestioneLivelloDto() {
		return gestioneLivelloDto;
	}

	public void setGestioneLivelloDto(SiacDGestioneLivello gestioneLivelloDto) {
		this.gestioneLivelloDto = gestioneLivelloDto;
	}
	
	

}
