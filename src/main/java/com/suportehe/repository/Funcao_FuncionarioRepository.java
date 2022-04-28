package com.suportehe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suportehe.model.Funcao_Funcionario;

@Repository
public interface Funcao_FuncionarioRepository extends JpaRepository<Funcao_Funcionario, Long> {

}
