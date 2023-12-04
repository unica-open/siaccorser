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
@Table(name = "siac_t_gruppo")
public class SiacTGruppo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7981376479392390298L;
	
	@Id
	@Column(name = "gruppo_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_GRUPPO")
	@SequenceGenerator(name = "SEQ_T_GRUPPO", allocationSize=1, sequenceName = "siac_t_gruppo_gruppo_id_seq")
	private Integer uid;
	
	@Column(name = "gruppo_code")
	private String nome;
	
	@Column(name = "gruppo_desc")
	private String descrizione;

	@OneToMany(mappedBy="gruppo")
	private List<SiacRGruppoRuoloOp> ruoli = new ArrayList<SiacRGruppoRuoloOp>();
		
	@OneToMany(mappedBy="siacTGruppo")
	private List<SiacRGruppoAccount> siacRGruppoAccounts = new ArrayList<SiacRGruppoAccount>();

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

	
	public List<SiacRGruppoRuoloOp> getRuoli() {
		return ruoli;
	}
	
	public void setRuoli(List<SiacRGruppoRuoloOp> ruoli) {
		this.ruoli = ruoli;
	}
	
	public List<SiacRGruppoAccount> getSiacRGruppoAccounts() {
		return siacRGruppoAccounts;
	}
	
	public void setSiacRGruppoAccounts(List<SiacRGruppoAccount> siacRGruppoAccounts) {
		this.siacRGruppoAccounts = siacRGruppoAccounts;
	}

}
