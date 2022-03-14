package com.suportehe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suportehe.model.Acesso;
import com.suportehe.repository.AcessoRepository;

@Service
public class AcessoService {
	
	@Autowired
	private AcessoRepository acessoRepository;
		
	public Acesso save(Acesso acesso) {
		
		return acessoRepository.save(acesso);
	}

}
