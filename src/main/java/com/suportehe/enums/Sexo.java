package com.suportehe.enums;

public enum Sexo {
	
	M("Masculino"),
	F("Feminino"),
	X("Outros");
	
	private final String descricao;

    Sexo(String descricao) {
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
