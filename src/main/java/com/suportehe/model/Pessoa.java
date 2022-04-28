package com.suportehe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", initialValue = 1, allocationSize = 1)
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    private Long id;

    @NotNull(message = "Campo nome não pode ser vazio ou nulo ")
    @NotBlank(message = "Campo nome não pode ser vazio ou nulo")
    @Size(min = 4, message = "Campo nome deverá conter no minimo 4 caracteres")
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column
    private String tipoPessoa;

    @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Endereco> enderecos = new ArrayList<Endereco>();
    
    @ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id", nullable = true,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private Pessoa empresa;
    
    @ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "funcao_funcionario_id", nullable = true,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "funcao_funcionario_id_fk"))
	private Pessoa funcao_Funcionario;

	@ManyToOne
	@JoinColumn(name = "setores_id", nullable = true,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "setores_id_fk"))
	private Pessoa setores;
	
	@OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private final List<Funcao_Funcionario> funcao_Funcionarios = new ArrayList<Funcao_Funcionario>();
	
	@OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private final List<Setores> setores2 = new ArrayList<Setores>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Pessoa getFuncao_Funcionario() {
		return funcao_Funcionario;
	}

	public void setFuncao_Funcionario(Pessoa funcao_Funcionario) {
		this.funcao_Funcionario = funcao_Funcionario;
	}

	public Pessoa getSetores() {
		return setores;
	}

	public void setSetores(Pessoa setores) {
		this.setores = setores;
	}

	public List<Funcao_Funcionario> getFuncao_Funcionarios() {
		return funcao_Funcionarios;
	}

	public List<Setores> getSetores2() {
		return setores2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
}
