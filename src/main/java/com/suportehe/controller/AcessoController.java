package com.suportehe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suportehe.exception.ExceptionSistemaHepp;
import com.suportehe.model.Acesso;
import com.suportehe.repository.AcessoRepository;
import com.suportehe.service.AcessoService;

@Controller
@RestController
public class AcessoController {

	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoRepository acessoRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarAcesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) throws ExceptionSistemaHepp {

		if (acesso.getId() == null) {

			List<Acesso> acessoSalvo = acessoRepository.buscarAcessoDesc(acesso.getDescricao().toUpperCase());
			
			if(!acessoSalvo.isEmpty()) {
				throw new ExceptionSistemaHepp("Existe um cadastro de acesso com esta descricão: " + acesso.getDescricao());
			}
		}

		Acesso acessoSalvo = acessoService.save(acesso);

		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "**/deleteAcesso")
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {

		acessoRepository.deleteById(acesso.getId());

		return new ResponseEntity<>("Acesso excluído com sucesso!", HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteAcessoPorIdAcesso/{id}")
	public ResponseEntity<?> deleteAcessoPorIdAcesso(@PathVariable("id") Long id) {

		acessoRepository.deleteById(id);

		return new ResponseEntity<>("Acesso excluído com sucesso!", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarAcessoPorId/{id}")
	public ResponseEntity<Acesso> getAcessoPorId(@PathVariable("id") Long id) {

		Acesso acesso = acessoRepository.findById(id).get();

		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarAcessoPorDesc/{desc}")
	public ResponseEntity<List<Acesso>> getAcessoPorId(@PathVariable("desc") String desc) {

		List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc.toUpperCase());

		return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);
	}
}
