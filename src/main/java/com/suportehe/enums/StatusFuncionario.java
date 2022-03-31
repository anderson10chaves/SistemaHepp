package com.suportehe.enums;

public enum StatusFuncionario {
	
	Ativo("Ativo"),
	Inativo("Inativo"),
	Inss("Inss"),
	Afastamento("Afastamento"),
	retorno("Retorno");
	
	private final String descricao;
	
	StatusFuncionario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
	

}
