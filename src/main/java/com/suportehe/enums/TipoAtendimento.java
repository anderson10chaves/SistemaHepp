package com.suportehe.enums;

public enum TipoAtendimento {

	CONSULTA("Consulta"),
    EXAME("EXAME"),
    CIRURGICO("CIRURGICO"),
    RETORNO_CONSULTA("Retorno_Consulta"),
    RETORNO_EXAME("Retorno_Exame"),
    RETORNO_PRÉ_CIRURGICO("Retorno_Pre_Cirurgico"),
    RETORNO_PÓS_CIRURGICO("Retorno_Pos_Cirurgico");

    private final String descricao;

    TipoAtendimento(String descricao) {
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
