package com.suportehe.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.suportehe.model.Funcao_Funcionario;
import com.suportehe.model.Funcionario;
import com.suportehe.model.PessoaMedica;
import com.suportehe.model.Usuario;
import com.suportehe.repository.FuncionarioRepository;
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

	@Autowired
	private SendEmailService sendEmailService;

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	

	public PessoaMedica salvarPessoaMedica(PessoaMedica pessoaMedica) {

		for (int i = 0; i < pessoaMedica.getEnderecos().size(); i++) {
			pessoaMedica.getEnderecos().get(i).setPessoa(pessoaMedica);
			pessoaMedica.getEnderecos().get(i).setEmpresa(pessoaMedica);
		}
		
		pessoaMedica = pessoaRepository.save(pessoaMedica);

		Usuario usuarioPm = usuarioRepository.findUserByPessoa(pessoaMedica.getId(), pessoaMedica.getEmail());

		if (usuarioPm == null) {

			String constraint = usuarioRepository.consultaConstraintAcesso();

			if (constraint != null) {
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

			usuarioRepository.insereAcessoUser(usuarioPm.getId(), "ROLE_USER");
			// usuarioRepository.insereAcessoUser(usuarioPm.getId());

			StringBuilder mensagemHtml = new StringBuilder();

			mensagemHtml.append("<b>Segue os dados de acesso para Sistema HEPP</b><br/><br/>");
			mensagemHtml.append("<b>Login: </b>" + pessoaMedica.getEmail() + "</b><br/>");
			mensagemHtml.append("<b>Senha: </b>" + senha + "</b><br/>");
			mensagemHtml.append("<p>Atenciosamente Equipe Tecnologia - HEPP</p>");

			try {
				sendEmailService.enviarEmailHtml("Acesso ao Sistema HEPP Liberado", mensagemHtml.toString(),
						pessoaMedica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return pessoaMedica;
	}

	public Funcionario salvarFunc(Funcionario funcionario) {

		for (int i = 0; i < funcionario.getEnderecos().size(); i++) {
			funcionario.getEnderecos().get(i).setPessoa(funcionario);
			funcionario.getEnderecos().get(i).setEmpresa(funcionario);
		}
		
		funcionario = funcionarioRepository.save(funcionario);

		Usuario usuarioFunc = usuarioRepository.findUserByPessoa(funcionario.getId(), funcionario.getEmail());

		if (usuarioFunc == null) {

			String constraint = usuarioRepository.consultaConstraintAcesso();

			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint + "; commit;");
			}

			usuarioFunc = new Usuario();
			usuarioFunc.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioFunc.setEmpresa(funcionario.getEmpresa());
			usuarioFunc.setPessoa(funcionario);
			usuarioFunc.setLogin(funcionario.getEmail());

			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = new BCryptPasswordEncoder().encode(senha);

			usuarioFunc.setSenha(senhaCript);

			usuarioFunc = usuarioRepository.save(usuarioFunc);

			usuarioRepository.insereAcessoUser(usuarioFunc.getId(), "ROLE_USER");
			//usuarioRepository.insereAcessoUser(usuarioFunc.getId());

			StringBuilder mensagemHtml = new StringBuilder();

			mensagemHtml.append("<b>Segue os dados de acesso para Sistema HEPP</b><br/><br/>");
			mensagemHtml.append("<b>Login: </b>" + funcionario.getEmail() + "</b><br/>");
			mensagemHtml.append("<b>Senha: </b>" + senha + "</b><br/>");
			mensagemHtml.append("<p>Atenciosamente Equipe Tecnologia - HEPP</p>");

			try {
				sendEmailService.enviarEmailHtml("Acesso ao Sistema HEPP Liberado", mensagemHtml.toString(),
						funcionario.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return funcionario;
	}

}
