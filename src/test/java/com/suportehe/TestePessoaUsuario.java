package com.suportehe;

import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.suportehe.controller.PessoaController;
import com.suportehe.enums.EspecialidadeMedica;
import com.suportehe.enums.Sexo;
import com.suportehe.enums.StatusFuncionario;
import com.suportehe.exception.ExceptionSistemaHepp;
import com.suportehe.model.Endereco;
import com.suportehe.model.Funcao_Funcionario;
import com.suportehe.model.Funcionario;
import com.suportehe.model.Pessoa;
import com.suportehe.model.PessoaMedica;
import com.suportehe.model.Setores;
import com.suportehe.model.Usuario;
import com.suportehe.repository.FuncionarioRepository;
import com.suportehe.repository.PessoaRepository;
import com.suportehe.repository.SetoresRepository;
import com.suportehe.repository.UsuarioRepository;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = SistemaHeppApplication.class)
public class TestePessoaUsuario extends TestCase {

	@Autowired
	private PessoaController pessoaController;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private SetoresRepository setoresRepository;

	@Test
	public void testCadPessoaMedica() throws ExceptionSistemaHepp {

		PessoaMedica pessoaMedica = new PessoaMedica();

		pessoaMedica.setNome("Denise dos Santos Mendonça MEI");
		pessoaMedica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoaMedica.setEmail("dettaat@mgmaiiil.com");
		pessoaMedica.setCrm("" + Calendar.getInstance().getTimeInMillis());
		pessoaMedica.setTelefone("1898989666767");
		pessoaMedica.setInsc_estadual("3551551515");
		pessoaMedica.setInsc_municipal("1551548751565");
		pessoaMedica.setNome_fantasia("HEPP");
		pessoaMedica.setRazao_social("MED");
		pessoaMedica.setTipoPessoa("MEDICO");
		pessoaMedica.setEspecialidadeMedica(EspecialidadeMedica.ANESTESISTA);
		pessoaMedica.setEmpresa(pessoaMedica);
		pessoaMedica.setFuncao_Funcionario(pessoaMedica);
		pessoaMedica.setSetores(pessoaMedica);

		Endereco endereco1 = new Endereco();
		endereco1.setBairro("Primavera");
		endereco1.setCep("19274-000");
		endereco1.setComplemento("Quadra 150");
		endereco1.setCidade("Rosana");
		endereco1.setEmpresa(pessoaMedica);
		endereco1.setNumero("84");
		endereco1.setPessoa(pessoaMedica);
		endereco1.setRua("Rua Diamanatina");
		endereco1.setUf("SP");

		pessoaMedica.getEnderecos().add(endereco1);
		
		Funcao_Funcionario funcao_Funcionario = new Funcao_Funcionario();
		  
		  funcao_Funcionario.setDescricao("Anestesista");
		  funcao_Funcionario.setPessoa(pessoaMedica);
		  funcao_Funcionario.setEmpresa(pessoaMedica);
		  
		  funcao_Funcionario.getDescricao();
		  
		  pessoaMedica.getFuncao_Funcionarios().add(funcao_Funcionario);
		  
		  Setores setores = new Setores();
		  
		  setores.setDescricao("Médicos");
		  setores.setPessoa(pessoaMedica);
		  setores.setEmpresa(pessoaMedica);
		  
		  setores.getDescricao();
		  
		  pessoaMedica.getSetores2().add(setores);
		  
		pessoaMedica = pessoaController.salvarPMedica(pessoaMedica).getBody();

		assertEquals(true, pessoaMedica.getId() > 0);

		for (Endereco endereco : pessoaMedica.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}

		assertEquals(1, pessoaMedica.getEnderecos().size());

	}
	
	@Test public void testCadPessoaFuncionario() throws ExceptionSistemaHepp {
		  
		 PessoaMedica pessoaMedica = pessoaRepository.existeCnpjCadastrado("1648781379276");
		  
		  Funcionario funcionario = new Funcionario();
		  
		  funcionario.setNome("Anderson Chaves Func");
		  funcionario.setSobrenome("Teste");
		  funcionario.setEmail("anskji@gmail.com");
		  funcionario.setCbo("157844");
		  funcionario.setTelefone("1898989666767");
		  funcionario.setCpf("498.495.470-14");
		  funcionario.setCtps("1551548751565");
		  funcionario.setDataContratacao(null);
		  funcionario.setDataDemissao(null);
		  funcionario.setTipoPessoa("FUNCIONÁRIO");
		  funcionario.setDependente("2");
		  funcionario.setDataNascimento(null);
		  funcionario.setEmpresa(funcionario);
		  funcionario.setIdade("35");
		  funcionario.setImagemMiniatura(null);
		  funcionario.setImagemOriginal(null);
		  funcionario.setPis("4545745457");
		  funcionario.setRg("99.999.999-9");
		  funcionario.setSalBase("R$ 1200,00");
		  funcionario.setFuncao_Funcionario(funcionario);
		  funcionario.setSetores(funcionario);
		  funcionario.setSexo(Sexo.M);
		  funcionario.setStatus(StatusFuncionario.Afastamento);
		  
		  Endereco endereco1 = new Endereco();
		  endereco1.setBairro("Primavera");
		  endereco1.setCep("19274-000");
		  endereco1.setComplemento("Quadra 150");
		  endereco1.setCidade("Rosana");
		  endereco1.setEmpresa(funcionario);
		  endereco1.setNumero("84");
		  endereco1.setPessoa(funcionario);
		  endereco1.setRua("Rua Diamanatina");
		  endereco1.setUf("SP");
		  
		  funcionario.getEnderecos().add(endereco1);
		   
		  Funcao_Funcionario funcao_Funcionario = new Funcao_Funcionario();
		  
		  funcao_Funcionario.setDescricao("Administrador");
		  funcao_Funcionario.setPessoa(funcionario);
		  funcao_Funcionario.setEmpresa(funcionario);
		  
		  funcao_Funcionario.getDescricao();
		  
		  funcionario.getFuncao_Funcionarios().add(funcao_Funcionario);
		  
		  Setores setores = new Setores();
		  
		  setores.setDescricao("ADMINISTRACÃO");
		  setores.setPessoa(funcionario);
		  setores.setEmpresa(funcionario);
		  
		  setores.getDescricao();
		  
		  funcionario.getSetores2().add(setores);
		  
		  funcionario = pessoaController.salvarFunc(funcionario,funcao_Funcionario, setores).getBody();
		  
		  assertEquals(true, funcao_Funcionario.getId() > 0);
		  
		  assertEquals(true, setores.getId() > 0);
		  	  
		  assertEquals(true, funcionario.getId() > 0);
		  
		  for (Endereco endereco : funcionario.getEnderecos()) {
			  assertEquals(true, endereco.getId() > 0); }
		  
		  assertEquals(1, funcionario.getEnderecos().size());
		  
		  }
	  
}
