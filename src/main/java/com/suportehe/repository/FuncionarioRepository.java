package com.suportehe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suportehe.model.Funcao_Funcionario;
import com.suportehe.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query(value = "select f from Funcionario f where f.email = ?1")
	public Funcionario existeEmailCadastrado(String email);
	
}
