/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * The persistent class for the siac_t_cassa_econ database table.
 * 
 */
@Entity
@Table(name="siac_t_cassa_econ")
public class SiacTCassaEcon extends SiacTEnteBase {

	private static final long serialVersionUID = 8331574350116052033L;

	@Id
	@SequenceGenerator(name = "SEQ_T_CASSA_ECON", allocationSize=1, sequenceName = "siac_t_cassa_econ_cassaecon_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_T_CASSA_ECON")
	@Column(name="cassaecon_id")
	private Integer uid;

	@Column(name="cassa_tipo_id")
	private Integer cassaTipoId;

	@Column(name="cassaecon_cc")
	private String cassaeconCc;

	@Column(name="cassaecon_code")
	private String cassaeconCode;

	@Column(name="cassaecon_desc")
	private String cassaeconDesc;

	@Column(name="cassaecon_limiteimporto")
	private BigDecimal cassaeconLimiteimporto;

	@Column(name="cassaecon_resp")
	private String cassaeconResp;

	@OneToMany(mappedBy="cassaEconomale")
	private List<SiacRAccountCassaEcon> siacRAccountCassaEcons;

	public Integer getCassaTipoId() {
		return this.cassaTipoId;
	}

	public void setCassaTipoId(Integer cassaTipoId) {
		this.cassaTipoId = cassaTipoId;
	}

	public String getCassaeconCc() {
		return this.cassaeconCc;
	}

	public void setCassaeconCc(String cassaeconCc) {
		this.cassaeconCc = cassaeconCc;
	}

	public String getCassaeconCode() {
		return this.cassaeconCode;
	}

	public void setCassaeconCode(String cassaeconCode) {
		this.cassaeconCode = cassaeconCode;
	}

	public String getCassaeconDesc() {
		return this.cassaeconDesc;
	}

	public void setCassaeconDesc(String cassaeconDesc) {
		this.cassaeconDesc = cassaeconDesc;
	}

	public BigDecimal getCassaeconLimiteimporto() {
		return this.cassaeconLimiteimporto;
	}

	public void setCassaeconLimiteimporto(BigDecimal cassaeconLimiteimporto) {
		this.cassaeconLimiteimporto = cassaeconLimiteimporto;
	}

	public String getCassaeconResp() {
		return this.cassaeconResp;
	}

	public void setCassaeconResp(String cassaeconResp) {
		this.cassaeconResp = cassaeconResp;
	}

	

	public List<SiacRAccountCassaEcon> getSiacRAccountCassaEcons() {
		return siacRAccountCassaEcons;
	}

	public void setSiacRAccountCassaEcons(List<SiacRAccountCassaEcon> siacRAccountCassaEcons) {
		this.siacRAccountCassaEcons = siacRAccountCassaEcons;
	}

	@Override
	public Integer getUid() {
		return uid;
	}
	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}
}