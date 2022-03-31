package com.suportehe.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suportehe.model.PessoaMedica;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<PessoaMedica, Long> {

	@Query(value = "select pm from PessoaMedica pm where pm.cnpj = ?1")
	public PessoaMedica existeCnpjCadastrado(String cnpj);

	@Query(value = "select pm from PessoaMedica pm where pm.crm = ?1")
	public PessoaMedica existeCrmCadastrado(String crm);

}
