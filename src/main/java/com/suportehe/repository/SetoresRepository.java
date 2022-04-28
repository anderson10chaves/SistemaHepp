package com.suportehe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suportehe.model.Setores;

@Repository
public interface SetoresRepository extends JpaRepository<Setores, Long> {

}
