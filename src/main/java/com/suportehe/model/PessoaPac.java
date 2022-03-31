package com.suportehe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_pac")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaPac extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String idade;

	@Column(nullable = false)
	private String prontuario;

	@Column(nullable = false)
	private String cid;

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getProntuario() {
		return prontuario;
	}

	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
