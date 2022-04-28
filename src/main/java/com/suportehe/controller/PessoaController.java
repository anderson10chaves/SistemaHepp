package com.suportehe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suportehe.exception.ExceptionSistemaHepp;
import com.suportehe.model.Funcao_Funcionario;
import com.suportehe.model.Funcionario;
import com.suportehe.model.Pessoa;
import com.suportehe.model.PessoaMedica;
import com.suportehe.model.Setores;
import com.suportehe.repository.Funcao_FuncionarioRepository;
import com.suportehe.repository.FuncionarioRepository;
import com.suportehe.repository.PessoaRepository;
import com.suportehe.repository.SetoresRepository;
import com.suportehe.service.PessoaUserService;

@RestController
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaUserService pessoaUserService;

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private Funcao_FuncionarioRepository funcao_FuncionarioRepository;
	
	@Autowired
	private SetoresRepository setoresRepository;
	
	@ResponseBody
	@PostMapping(value = "**/salvarPMedica")
	public ResponseEntity<PessoaMedica> salvarPMedica(@RequestBody @Valid PessoaMedica pessoaMedica)
			throws ExceptionSistemaHepp {

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

	@ResponseBody
	@PostMapping(value = "**/salvarFunc")
	public ResponseEntity<Funcionario> salvarFunc(@RequestBody @Valid Funcionario funcionario, Funcao_Funcionario funcao_Funcionario, Setores setores) throws ExceptionSistemaHepp {

		if (funcionario == null) {
			throw new ExceptionSistemaHepp("Funcionário não pode ser nulo");
		}

		if (funcionario.getId() == null && funcionarioRepository.existeEmailCadastrado(funcionario.getEmail()) != null) {
			throw new ExceptionSistemaHepp("Já existe Email cadastrado: " + funcionario.getEmail());
		}
		
		/*
		 * if(funcao_Funcionario.getId() == null &&
		 * funcao_FuncionarioRepository.existeFuncaoCadastrado(funcao_Funcionario.
		 * getDescricao()) != null) { throw new
		 * ExceptionSistemaHepp("Já existe Função cadastrado: " +
		 * funcao_Funcionario.getDescricao()); }
		 * 
		 * if(setores.getId() == null &&
		 * setoresRepository.existeDescricaoCadastrado(setores.getDescricao()) != null)
		 * { throw new ExceptionSistemaHepp("Já existe Setor cadastrado: " +
		 * setores.getDescricao()); }
		 */

		funcionario = pessoaUserService.salvarFunc(funcionario);

		return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
	}

}
