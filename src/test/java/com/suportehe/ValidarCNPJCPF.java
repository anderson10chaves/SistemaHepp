package com.suportehe;

import com.suportehe.utils.ValidaCNPJ;
import com.suportehe.utils.ValidaCPF;

public class ValidarCNPJCPF {
	
	public static void main(String[] args) {
		boolean isCnpj = ValidaCNPJ.isCNPJ("95.970.046/0001-40");
		
		System.out.println("Cnpj Valido : " + isCnpj);
		
		boolean isCpf = ValidaCPF.isCPF("058.312.430-52");
		
		System.out.println("CPF Válido : " + isCpf);
	}

}
