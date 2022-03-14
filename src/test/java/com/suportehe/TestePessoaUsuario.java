package com.suportehe;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.suportehe.controller.PessoaController;
import com.suportehe.enums.EspecialidadeMedica;
import com.suportehe.exception.ExceptionSistemaHepp;
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
		pessoaMedica.setCrm("5157455457877");
		pessoaMedica.setTelefone("1898989666767");
		pessoaMedica.setInsc_estadual("3551551515");
		pessoaMedica.setInsc_municipal("1551548751565");
		pessoaMedica.setNome_fantasia("HEPP");
		pessoaMedica.setRazao_social("MED");
		pessoaMedica.setTipoPessoa("MEDICO");
		pessoaMedica.setEspecialidadeMedica(EspecialidadeMedica.ANESTESISTA);
		
		pessoaController.salvarPMedica(pessoaMedica);
		
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
