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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_bil")
@SecondaryTables({
	@SecondaryTable(name = "siac_r_bil_fase_operativa", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "bil_id") }) })
public class SiacTBil extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3331741417649249995L;

	@Id
	@Column(name = "bil_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_BILANCIO")
	@SequenceGenerator(name = "SEQ_T_BILANCIO", allocationSize=1, sequenceName = "siac_t_bilancio_bil_id_seq")
	private Integer uid;
	
	@ManyToOne
	@JoinColumn(name = "fase_operativa_id", referencedColumnName = "fase_operativa_id", table = "siac_r_bil_fase_operativa", insertable = false, updatable = false)
	private SiacDFaseOperativa faseOperativa;

	@ManyToOne
    @JoinColumn(name = "periodo_id")
	private SiacTPeriodo periodo;

	
	/**
	 * @return the uid
	 */
	@Override
	public Integer getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * @return the faseOperativa
	 */
	public SiacDFaseOperativa getFaseOperativa() {
		return faseOperativa;
	}
	/**
	 * @param faseOperativa the faseOperativa to set
	 */
	public void setFaseOperativa(SiacDFaseOperativa faseOperativa) {
		this.faseOperativa = faseOperativa;
	}
	/**
	 * @return the periodo
	 */
	public SiacTPeriodo getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(SiacTPeriodo periodo) {
		this.periodo = periodo;
	}
}
