package br.com.camtwo.cafe.model;

public enum DiaSemana {

	Domingo("Domingo"), 
	Segunda("Segunda"),
	Terca("Terça"), 
	Quarta("Quarta"), 
	Quinta("Quinta"), 
	Sexta("Sexta"), 
	Sabado("Sábado");
	
	private DiaSemana(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
}
