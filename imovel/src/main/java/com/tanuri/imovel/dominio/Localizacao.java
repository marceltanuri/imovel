package com.tanuri.imovel.dominio;

import javax.persistence.Embeddable;

@Embeddable
public class Localizacao {

	private String bairro;

	private String zona;

	private String cidade;

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

}
