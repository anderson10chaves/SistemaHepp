package com.suportehe.enums;

public enum Procedimento_Cirurgico {

//  PROCEDIMENTOS CIRURGICOS

	CIRURGIA_MULTIPLAS("Cirurgia Multiplas"),
	ATENDIMENTO_EM_CLINICA_CIRURGICA("1º Atendimento em Clinica Cirurgica"),
	COLECISTECTOMIA("Colecistectomia"),
	APENDICECTOMIA("Apendicectomia"),
	FISTULECTOMIA("Fistulectomia"),
	DRENAGEM_DE_ABSCESSO_ANU_RETAL("Drenagem de Abscesso Anu-Retal"),
	HERNIORRAFIA_INGUINAL_UNILATERAL("Herniorrafia Inguinal Unilateral"),
	HERNIORRAFIA_INGUINAL_BILATERAL("Herniorrafia Inguinal Bilateral"),
	HERNIORRAFIA_UMBILICAL("Herniorrafia Umilical"),
	EXCISAO_DE_LESAO_TUMOR_ANU_RETAL("Excisão de Lesão Tumor Anu-Retal"),
	LAPAROTOMIA_EXPLORADORA("Laparotomia Exploradora"),
	HEMORROIDECTOMIA("Hemorroidectomia"),
	DEBRIDAMENTO_DE_FASCEITE_NECROTIZANTE("Debridamento de Fasceite Necrozitante"),
	EXTIPARÇÃO_E_SUSPRESSÃO_DE_LESÃO_DE_PELE("Extirpação e Supressão de Lesão de Pele"),
	EXERESE_DE_CISTO_SACRO_COCCIGEO("Exerese de Cisto Sacro Coccigeo"),
	TRATAMENTO_DE_PEQUENO_QUEIMADO("Tratamento de Pequeno Queimado"),
	EXERESE_DE_GANGLIOS_LINFÁTICOS("Exerese de Ganglios Linfáticos"),
	CISTO_DERMÓIDE("Cisto Dermóide"),
	ESPLENECTOMIA("Esplenectomia"),
	AMIGALECTOMIA_COM_ADENÓIDE("Amigalectomia com adenóide"),
	AMIGALECTOMIA_SEM_ADENÓIDE("Amigalectomia sem adenóide"),
	ENTEROTOMIA_ENTERORRAFIAC_SUTURA_RESSECÇÃO("Enterotomia, Enterorrafiac, sutura ressecção"),
	HEPATORRAFIA("Hepatorrafia"),
	LIBERAÇÃO_DE_ADERÊNCIA_INTESTINAIS("Liberação de Aderência Intestinais"),
	HEMICOLECTOMIA("Hemicolectomia"),
	DRENAGEM_HEMATOMA("Drenagem Hematoma"),
	PLÁSTICA_ANAL_EXTERNA("Plástica Anal Externa"),
	TRAT_CIRURGICO_LESÕES_EXTENSAS_PERDAS_SUBSTANCIAS_CUTANEA(
			"Trat Cirurgico Lesões Extensas Perdas Substancias Cutanea"),
	EXCISÃO_SUTURA("Excisão Sutura"),
	CISTOSTOMIA("Cistostomia"),
	TORACOSTOMIA_DRENAGEM_PNEUMOTÓRAX_TRAUMÁTICO("Toracostomia Drenagem Pneumotórax Traumático"),
	TORACOSTOMIA_DRENAGEM_OUTROS_TIPOS("Toracostomia Drenagem Outros Tipos "),
	TORACOSTOMIA_DRENAGEM_PNEUMOTÓRAX_ESPONTÂNEO("Toracostomia Drenagem Pneumotórax Expontâneo"),
	COLOSTOMIA("Colostomia"),
	TRAT_CIRURGICO_ARTRITE_INFECCIOSA_GRANDES_ARTICULAÇÕES(
			"Tratamento Cirurrgico Artrite Infecciosa Grandes Articulaçôes"),
	CISTO_TEREOGLOSSO("Cisto Tereoglosso"),
	CORREÇÃO_RETRATAÇÃO_CICATRICIAL_VÁRIOS_ESTÁGIOS("Correção Retratação Cicatricial Vários Estágios"),
	GASTRORRAFIA("Gastrorrafia"),
	RESSUTURA_DE_PAREDE_ABDOMINAL("Ressultura de Parede Abdominal"),
	ENTERECTOMIA("Enterectomia"),
	TRATAMENTO_CIRURGICO_EM_POLITRAUMATIZADOS("Tratamento Cirurgico em Politraumatizados"),
	DEBRIDAMENTO_DE_ULCERA_DE_TECIDOS_DESVITALIZADOS("Debridamento de Ulcera de Tecidos Desvitalizados"),

//  CIRURGIAS MASCULINAS

	TRATAMENTO_CIRURGICO_DE_HIDROCELE("Tratamento Cirurgico de Hidrocele"),
	EXERESE_DE_CISTO_DE_EPIDIDEMO("Exerese de Cisto de Epididemo"),
	ORQUIECTOMIA_UNILATERAL("Orquiectomia Unilateral");
	
	private final String descricao;
	
	Procedimento_Cirurgico(String descricao) {
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
