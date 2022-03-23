package com.suportehe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suportehe.exception.ExceptionSistemaHepp;
import com.suportehe.model.PessoaMedica;
import com.suportehe.repository.PessoaRepository;
import com.suportehe.service.PessoaUserService;

@RestController
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaUserService pessoaUserService;

	@ResponseBody
	@PostMapping(value = "**/salvarPMedica")
	public ResponseEntity<PessoaMedica> salvarPMedica(@RequestBody PessoaMedica pessoaMedica) throws ExceptionSistemaHepp {
		
		if (pessoaMedica == null) {
			throw new ExceptionSistemaHepp("Médico não pode ser nulo");
		}
		
		if (pessoaMedica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoaMedica.getCnpj()) != null) {
			throw new ExceptionSistemaHepp("Já existe CNPJ cadastrado: " + pessoaMedica.getCnpj());
		}
		
		
	    if (pessoaMedica.getId() == null && pessoaRepository.existeCrmCadastrado(pessoaMedica.getCrm()) != null) {
		  throw new ExceptionSistemaHepp("Existe um CRM já cadastrado: " + pessoaMedica.getCrm()); 
		  }
		 		
		pessoaMedica = pessoaUserService.salvarPessoaMedica(pessoaMedica);
		
		
		return new ResponseEntity<PessoaMedica>(pessoaMedica, HttpStatus.OK);
	}
}
