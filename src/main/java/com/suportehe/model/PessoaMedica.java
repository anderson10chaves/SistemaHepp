package com.suportehe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.suportehe.enums.EspecialidadeMedica;

@Entity
@Table(name = "pessoa_medica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaMedica extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
    private String crm;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EspecialidadeMedica especialidadeMedica;
    
    @Column(nullable = false)
    private String cnpj;
    
    @Column(nullable = false)
    private String insc_estadual;
    
    @Column(nullable = false)
    private String insc_municipal;
    
    @Column(nullable = false)
    private String nome_fantasia;
    
    @Column(nullable = false)
    private String razao_social;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public EspecialidadeMedica getEspecialidadeMedica() {
		return especialidadeMedica;
	}

	public void setEspecialidadeMedica(EspecialidadeMedica especialidadeMedica) {
		this.especialidadeMedica = especialidadeMedica;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInsc_estadual() {
		return insc_estadual;
	}

	public void setInsc_estadual(String insc_estadual) {
		this.insc_estadual = insc_estadual;
	}

	public String getInsc_municipal() {
		return insc_municipal;
	}

	public void setInsc_municipal(String insc_municipal) {
		this.insc_municipal = insc_municipal;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	
}
