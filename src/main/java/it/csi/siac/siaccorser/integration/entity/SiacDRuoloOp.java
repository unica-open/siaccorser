/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_d_ruolo_op")
public class SiacDRuoloOp extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6195576166697639829L;

	@Id
	@Column(name = "ruolo_op_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_RUOLO_OP")
	@SequenceGenerator(name = "SEQ_T_RUOLO_OP", allocationSize=1, sequenceName = "siac_d_ruolo_op_ruolo_op_id_seq")
	private Integer uid;
	
	@Column(name = "ruolo_op_code")
	private String nome;
	
	@Column(name = "ruolo_op_desc")
	private String descrizione;
	
	@OneToMany(mappedBy = "ruolo")
	private List<SiacRAccountRuoloOp> accounts = new ArrayList<SiacRAccountRuoloOp>();
		
	@OneToMany(mappedBy = "ruolo")
	private List<SiacRGruppoRuoloOp> gruppi = new ArrayList<SiacRGruppoRuoloOp>();
	
	@OneToMany(mappedBy="siacDRuoloOp")
	private List<SiacRRuoloOpAzione> siacRRuoloOpAziones = new ArrayList<SiacRRuoloOpAzione>();
		
	@OneToMany(mappedBy="siacDRuoloOp")
	private List<SiacRRuoloOpBil> siacRRuoloOpBils = new ArrayList<SiacRRuoloOpBil>();
		
	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<SiacRAccountRuoloOp> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<SiacRAccountRuoloOp> accounts) {
		this.accounts = accounts;
	}

	public List<SiacRGruppoRuoloOp> getGruppi() {
		return gruppi;
	}

	public void setGruppi(List<SiacRGruppoRuoloOp> gruppi) {
		this.gruppi = gruppi;
	}
	

	public List<SiacRRuoloOpAzione> getSiacRRuoloOpAziones() {
		return siacRRuoloOpAziones;
	}

	public void setSiacRRuoloOpAziones(List<SiacRRuoloOpAzione> siacRRuoloOpAziones) {
		this.siacRRuoloOpAziones = siacRRuoloOpAziones;
	}

	public List<SiacRRuoloOpBil> getSiacRRuoloOpBils() {
		return siacRRuoloOpBils;
	}

	public void setSiacRRuoloOpBils(List<SiacRRuoloOpBil> siacRRuoloOpBils) {
		this.siacRRuoloOpBils = siacRRuoloOpBils;
	}

}
