/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_periodo")
public class SiacTPeriodo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -869452023988840492L;

	@Id
	@Column(name = "periodo_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERIODO")
	@SequenceGenerator(name = "SEQ_PERIODO", allocationSize=1, sequenceName = "siac_t_periodo_periodo_id_seq")
	private Integer uid;

	@Basic
	@Column(name = "data_inizio")
	private Date dataInizio;
	
	@Basic
	@Column(name = "data_fine")
	private Date dataFine;
	
	@Basic
	@Column(name = "anno")
	private String anno;
	
	@OneToOne
	@JoinColumn(name = "periodo_tipo_id")
	private SiacDPeriodoTipo tipoPeriodo;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setTipoPeriodo(SiacDPeriodoTipo tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public SiacDPeriodoTipo getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataFine() {
		return dataFine;
	}

	/**
	 * @return the anno
	 */
	public String getAnno() {
		return anno;
	}

	/**
	 * @param anno the anno to set
	 */
	public void setAnno(String anno) {
		this.anno = anno;
	}
}
