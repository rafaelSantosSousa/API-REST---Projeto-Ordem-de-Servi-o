package com.rafael.os.domain.enuns;

public enum Status {

	ABERTO(0, "BAIXA"), ANDAMENTO(1, "MEDIA"), ENCERRADO(2, "ALTA");

	private Integer cod;
	private String descricao;

	private Status(Integer cod, String descrição) {
		this.cod = cod;
		this.descricao = descrição;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Status x : Status.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status inválida!" + cod);

	}

}
