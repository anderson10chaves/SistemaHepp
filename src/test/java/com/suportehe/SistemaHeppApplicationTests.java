package com.suportehe;


import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportehe.controller.AcessoController;
import com.suportehe.exception.ExceptionSistemaHepp;
import com.suportehe.model.Acesso;
import com.suportehe.repository.AcessoRepository;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = SistemaHeppApplication.class)
class SistemaHeppApplicationTests extends TestCase {

	@Autowired
	private AcessoController acessoController;

	@Autowired
	private AcessoRepository acessoRepository;

	@Autowired
	private WebApplicationContext wac;

	/* Teste do end-point /salvarAcesso do cadastro de acessos */ 

	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		Acesso acesso = new Acesso();

		acesso.setDescricao("ROLE_USER" + Calendar.getInstance().getTimeInMillis());

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.post("/salvarAcesso").content(objectMapper.writeValueAsString(acesso))
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		System.out.println("Retorno da API" + retornoApi.andReturn().getResponse().getContentAsString());

//		 Converter o retorno da API para o objeto acesso 

		Acesso objetoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(),
				Acesso.class);

		assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
	}

	@Test
	public void testRestApiDeleteAcesso() throws JsonProcessingException, Exception {

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		Acesso acesso = new Acesso();

		acesso.setDescricao("ROLE_TESTE_DELETE");

		acesso = acessoRepository.save(acesso);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.post("/deleteAcesso").content(objectMapper.writeValueAsString(acesso))
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		System.out.println("Retorno da API" + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Status de retorno: " + retornoApi.andReturn().getResponse().getStatus());

		//assertEquals("Acesso removido com sucesso", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

	}

	@Test
	public void testRestApiDeletePorIdAcesso() throws JsonProcessingException, Exception {

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		Acesso acesso = new Acesso();

		acesso.setDescricao("ROLE_TESTE_DELETE");

		acesso = acessoRepository.save(acesso);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders
				.delete("/deleteAcessoPorIdAcesso/" + acesso.getId()).content(objectMapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		System.out.println("Retorno da API" + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Status de retorno: " + retornoApi.andReturn().getResponse().getStatus());

		//assertEquals("Acesso removido com sucesso", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

	}

	@Test
	public void testRestApibuscarAcessoPorId() throws JsonProcessingException, Exception {

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		Acesso acesso = new Acesso();

		acesso.setDescricao("ROLE_PESQUISAR");

		acesso = acessoRepository.save(acesso);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/buscarAcessoPorId/" + acesso.getId())
				.content(objectMapper.writeValueAsString(acesso)).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));

		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

		Acesso acessoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(),
				Acesso.class);

		assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());

		assertEquals(acesso.getId(), acessoRetorno.getId());
	}

	@Test
	public void testRestApibuscarAcessoPorDesc() throws JsonProcessingException, Exception {

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		Acesso acesso = new Acesso();

		acesso.setDescricao("ROLE_PESQUISAR_DESC");

		acesso = acessoRepository.save(acesso);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/buscarAcessoPorDesc/PESQUISAR_DESC")
				.content(objectMapper.writeValueAsString(acesso)).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));

		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

		List<Acesso> retornoApiList = objectMapper.
				readValue(retornoApi.andReturn().getResponse().getContentAsString(),
				new TypeReference<List<Acesso>>() {
				});
		
		assertEquals(1, retornoApiList.size());
		
		assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());

		acessoRepository.deleteById(acesso.getId());
	}

	@Test
	public void testCadastroAcesso() throws ExceptionSistemaHepp {

		String desacesso = "ROLE_ADMIN" + Calendar.getInstance().getTimeInMillis();
		
		Acesso acesso = new Acesso();

		acesso.setDescricao(desacesso);

		assertEquals(true, acesso.getId() == null);

		/* Grava no banco de dados */
		acesso = acessoController.salvarAcesso(acesso).getBody();

		assertEquals(true, acesso.getId() > 0);
		/* Valida os dados */
		assertEquals(desacesso, acesso.getDescricao());

		/* Teste de Carregamento de dependência */

		Acesso acessoSalvo = acessoRepository.findById(acesso.getId()).get();

		assertEquals(acesso.getId(), acessoSalvo.getId());

		/* Teste de Delete */

		acessoRepository.deleteById(acessoSalvo.getId());

		acessoRepository.flush(); /* Roda esse SQL de delete no banco de dados */

		Acesso acesso3 = acessoRepository.findById(acessoSalvo.getId()).orElse(null);

		assertEquals(true, acesso3 == null);

		/* Teste de Query */

		acesso = new Acesso();

		acesso.setDescricao("ROLE_VENDEDOR");

		acesso = acessoController.salvarAcesso(acesso).getBody();

		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("VENDEDOR".trim().toUpperCase());

		assertEquals(1, acessos.size());

		acessoRepository.deleteById(acesso.getId());
	}

}
