package br.com.kaju.model;

import java.io.Serializable;

public class CondicaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}