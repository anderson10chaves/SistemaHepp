package com.suportehe.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "segtrab")
@SequenceGenerator(name = "seq_setrab", sequenceName = "seq_setrab", allocationSize = 1, initialValue = 1)
public class SegTrab implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_setrab")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date periodico;

	@Column(nullable = true)
	private boolean realizado;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date proxExData;

	@ManyToOne
	@JoinColumn(name = "funcionario_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "funcionario_fk"))
	private Funcionario funcionario;
	
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private Pessoa empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPeriodico() {
		return periodico;
	}

	public void setPeriodico(Date periodico) {
		this.periodico = periodico;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public Date getProxExData() {
		return proxExData;
	}

	public void setProxExData(Date proxExData) {
		this.proxExData = proxExData;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Pessoa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
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
		SegTrab other = (SegTrab) obj;
		return Objects.equals(id, other.id);
	}

}
