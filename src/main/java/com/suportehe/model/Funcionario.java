package com.suportehe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.suportehe.enums.Sexo;
import com.suportehe.enums.StatusFuncionario;

@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "text", nullable = true)
	private String imagemOriginal;

	@Column(columnDefinition = "text", nullable = true)
	private String imagemMiniatura;

	@Column(nullable = false)
	private String sobrenome;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataContratacao;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataDemissao;

	@Column(nullable = false)
	private String rg;

	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String ctps;

	@Column(nullable = false)
	private String salBase;

	@Column(nullable = false)
	private String dependente;

	@Column(nullable = true)
	private String cbo;

	@Column(nullable = false)
	private String pis;

	@Column(nullable = false)
	private String idade;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusFuncionario status;

	@OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private final List<Endereco> enderecos = new ArrayList<Endereco>();
	
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private Pessoa empresa;

	public String getImagemOriginal() {
		return imagemOriginal;
	}

	public void setImagemOriginal(String imagemOriginal) {
		this.imagemOriginal = imagemOriginal;
	}

	public String getImagemMiniatura() {
		return imagemMiniatura;
	}

	public void setImagemMiniatura(String imagemMiniatura) {
		this.imagemMiniatura = imagemMiniatura;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getSalBase() {
		return salBase;
	}

	public void setSalBase(String salBase) {
		this.salBase = salBase;
	}

	public String getDependente() {
		return dependente;
	}

	public void setDependente(String dependente) {
		this.dependente = dependente;
	}

	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public StatusFuncionario getStatus() {
		return status;
	}

	public void setStatus(StatusFuncionario status) {
		this.status = status;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Pessoa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}

}