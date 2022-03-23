package com.suportehe;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.suportehe.controller.PessoaController;
import com.suportehe.enums.EspecialidadeMedica;
import com.suportehe.exception.ExceptionSistemaHepp;
import com.suportehe.model.Endereco;
import com.suportehe.model.PessoaMedica;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = SistemaHeppApplication.class)
public class TestePessoaUsuario extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	
	
	@Test
	public void testCadPessoaMedica() throws ExceptionSistemaHepp {
		
		PessoaMedica pessoaMedica = new PessoaMedica();
		
		pessoaMedica.setNome("Anderson Chaves PM");
		pessoaMedica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoaMedica.setEmail("achaves@gmail.com");
		pessoaMedica.setCrm("11111111111-SP");
		pessoaMedica.setTelefone("1898989666767");
		pessoaMedica.setInsc_estadual("3551551515");
		pessoaMedica.setInsc_municipal("1551548751565");
		pessoaMedica.setNome_fantasia("HEPP");
		pessoaMedica.setRazao_social("MED");
		pessoaMedica.setTipoPessoa("MEDICO");
		pessoaMedica.setEspecialidadeMedica(EspecialidadeMedica.ANESTESISTA);
		
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
		
		Endereco endereco2 = new Endereco();
		endereco2.setBairro("Primavera");
		endereco2.setCep("19274-000");
		endereco2.setComplemento("Quadra 100");
		endereco2.setCidade("Rosana");
		endereco2.setEmpresa(pessoaMedica);
		endereco2.setNumero("100");
		endereco2.setPessoa(pessoaMedica);
		endereco2.setRua("Rua Comércio");
		endereco2.setUf("SP");
		
		pessoaMedica.getEnderecos().add(endereco2);
		pessoaMedica.getEnderecos().add(endereco1);
		
		pessoaMedica = pessoaController.salvarPMedica(pessoaMedica).getBody();
		
		assertEquals(true, pessoaMedica.getId() > 0);
		
		for (Endereco endereco : pessoaMedica.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}
		
		assertEquals(2, pessoaMedica.getEnderecos().size());
		
		/*PessoaPac pessoaPac = new PessoaPac();
		
		pessoaPac.setNome("Denise Chaves");
		pessoaPac.setCid("M-750");
		pessoaPac.setEmail("denise@gmail.com");
		pessoaPac.setProntuario("5157455457877");
		pessoaPac.setTelefone("1898989666767");
		pessoaPac.setIdade("35");
		pessoaPac.setEmpresa(pessoaPac); */
		
		
	}

}
