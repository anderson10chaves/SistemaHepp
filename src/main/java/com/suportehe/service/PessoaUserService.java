package com.suportehe.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.suportehe.model.PessoaMedica;
import com.suportehe.model.Usuario;
import com.suportehe.repository.PessoaRepository;
import com.suportehe.repository.UsuarioRepository;

@Service
public class PessoaUserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public PessoaMedica salvarPessoaMedica(PessoaMedica pessoaMedica) {
		
		pessoaMedica = pessoaRepository.save(pessoaMedica);
		
		Usuario usuarioPm = usuarioRepository.findUserByPessoa(pessoaMedica.getId(), pessoaMedica.getEmail());
		
		if (usuarioPm == null) {
			
			String constraint = usuarioRepository.consultaConstraintAcesso();
			
			if(constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint + "; commit;");
			}
			
			usuarioPm = new Usuario();
			usuarioPm.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPm.setEmpresa(pessoaMedica);
			usuarioPm.setPessoa(pessoaMedica);
			usuarioPm.setLogin(pessoaMedica.getEmail());
			
			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = new BCryptPasswordEncoder().encode(senha);
			
			usuarioPm.setSenha(senhaCript);
			
			usuarioPm = usuarioRepository.save(usuarioPm);
			
			usuarioRepository.insereAcessoUserPm(usuarioPm.getId());
		}
		
		return pessoaMedica;
	}

}
