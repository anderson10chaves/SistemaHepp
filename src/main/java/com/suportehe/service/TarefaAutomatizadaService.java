package com.suportehe.service;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.suportehe.model.Usuario;
import com.suportehe.repository.UsuarioRepository;

@Service
public class TarefaAutomatizadaService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Scheduled(initialDelay = 2000, fixedDelay = 86400000) /*Roda cada 24hrs*/
	//@Scheduled(cron = "0 0 11 * * *", zone = "America/Sao_Paulo") /*Roda todos os dias as 11hrs*/
	public void notificarUserTrocaSenha() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		
		List<Usuario> usuarios = usuarioRepository.usuarioSenhaVencida();
		
		for (Usuario usuario : usuarios) {
			
			StringBuilder msg = new StringBuilder();
			msg.append("<b>Olá, </b>").append("<br/>").append(usuario.getPessoa().getNome()).append("<br/>");
			msg.append("Seu acesso ao sistema está maior que 90 dias, por segurança troque a sua senha!").append("<br/>");
			msg.append("<b>Obrigado!</b>").append("<br/>");
			msg.append("<b>Atenciosamente Departamento de TI-HEPP</b>");
			
			sendEmailService.enviarEmailHtml("Troca de Senha", msg.toString(), usuario.getLogin());
			
			Thread.sleep(3000);
		}
	}

}
