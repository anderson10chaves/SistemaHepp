package com.suportehe.enums;

public enum EspecialidadeMedica {

	PEDIATRIA("Pediatria"),
    CIRURGIAO("Cirurgião Geral"),
    ANESTESISTA("Anestesista"),
    CARDIOLOGIA("Cardiologia"),
    GINECOLOGIA_OBSTETRIA("Ginecologia/Obstetricia"),
    DERMATOLOGIA("Dermatologia"),
    PLANTONISTA("Plantonista"),
    ORTOPEDIA("Ortopedia"),
    ULTRASSONOGRAFIA("Ultrassonografia"),
    RADIOLOGIA("Radiologia"),
    UROLOGIA("Urologia");

	private final String descricao;

	EspecialidadeMedica(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}
}
