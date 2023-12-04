/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import it.csi.siac.siaccorser.integration.entity.bpm.VariabileProcesso;

@Entity
@Table(name = "siac_t_azione_richiesta")
public class SiacTAzioneRichiesta extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3053252174369757597L;
	
	@Id
	@Column(name = "azione_richiesta_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_AZIONE_RICHIESTA")
	@SequenceGenerator(name = "SEQ_T_AZIONE_RICHIESTA", allocationSize=1, sequenceName = "siac_t_azione_richiesta_azione_richiesta_id_seq")
	private Integer uid;
	
	@Column(name = "attivita_id")
	private String idAttivita;
	
	@Column(name = "da_cruscotto")
	private boolean daCruscotto = false;
	
	@Column(name = "data")
	private Date data;
    
	@ManyToOne
    @JoinColumn(name = "azione_id")
	private SiacTAzione azione;
    
	@ManyToOne
    @JoinColumn(name = "account_id")
	private SiacTAccount account;
	
	@OneToMany(mappedBy = "azioneRichiesta")
	private List<SiacTParametroAzioneRichiesta> parametri = new ArrayList<SiacTParametroAzioneRichiesta>();
	
	@Transient
	private List<VariabileProcesso> variabiliProcesso = new ArrayList<VariabileProcesso>();
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
	 * @return the idAttivita
	 */
	public String getIdAttivita() {
		return idAttivita;
	}
	/**
	 * @param idAttivita the idAttivita to set
	 */
	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}
	/**
	 * @return the daCruscotto
	 */
	public boolean isDaCruscotto() {
		return daCruscotto;
	}
	/**
	 * @param daCruscotto the daCruscotto to set
	 */
	public void setDaCruscotto(boolean daCruscotto) {
		this.daCruscotto = daCruscotto;
	}
	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * @return the azione
	 */
	public SiacTAzione getAzione() {
		return azione;
	}
	/**
	 * @param azione the azione to set
	 */
	public void setAzione(SiacTAzione azione) {
		this.azione = azione;
	}
	/**
	 * @return the account
	 */
	public SiacTAccount getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(SiacTAccount account) {
		this.account = account;
	}
	/**
	 * @return the parametri
	 */
	public List<SiacTParametroAzioneRichiesta> getParametri() {
		return parametri;
	}
	/**
	 * @param parametri the parametri to set
	 */
	public void setParametri(List<SiacTParametroAzioneRichiesta> parametri) {
		this.parametri = parametri;
	}
	/**
	 * @return the variabiliProcesso
	 */
	public List<VariabileProcesso> getVariabiliProcesso() {
		return variabiliProcesso;
	}
	/**
	 * @param variabiliProcesso the variabiliProcesso to set
	 */
	public void setVariabiliProcesso(List<VariabileProcesso> variabiliProcesso) {
		this.variabiliProcesso = variabiliProcesso;
	}
}
