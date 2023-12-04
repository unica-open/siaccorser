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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_parametro_config_ente")
public class SiacTParametroConfigEnte extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4972888656733684604L;

	@Id
	@Column(name = "parametro_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_PARAMETRO_CONFIG_ENTE")
	@SequenceGenerator(name = "SEQ_T_PARAMETRO_CONFIG_ENTE", allocationSize = 1, sequenceName = "siac_t_parametro_config_ente_parametro_id_seq")
	private Integer uid;

	@Column(name = "parametro_nome")
	private String parametroNome;

	@Column(name = "parametro_valore")
	private String parametroValore;

	@Column(name = "parametro_abilitato")
	private Boolean parametroAbilitato;

	@Override
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getParametroNome() {
		return parametroNome;
	}

	public void setParametroNome(String parametroNome) {
		this.parametroNome = parametroNome;
	}

	public String getParametroValore() {
		return parametroValore;
	}

	public void setParametroValore(String parametroValore) {
		this.parametroValore = parametroValore;
	}

	public Boolean getParametroAbilitato() {
		return parametroAbilitato;
	}

	public void setParametroAbilitato(Boolean parametroAbilitato) {
		this.parametroAbilitato = parametroAbilitato;
	}

}
