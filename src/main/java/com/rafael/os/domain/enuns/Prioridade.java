package com.rafael.os.domain.enuns;

public enum Prioridade {
	
	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

	private Integer cod;
	private String descriçao;

	private Prioridade(Integer cod, String descrição) {
		this.cod = cod;
		this.descriçao = descrição;
	}


	public Integer getCod() {
		return cod;
	}


	public void setCod(Integer cod) {
		this.cod = cod;
	}


	public String getDescriçao() {
		return descriçao;
	}


	public void setDescriçao(String descriçao) {
		this.descriçao = descriçao;
	}


	public static Prioridade toEnum(Object prioridade) {
		if (prioridade == null) {
			return null;
		}

		for (Prioridade x : Prioridade.values()) {
			if (prioridade.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Prioridade inválida!" + prioridade);

	}

}


