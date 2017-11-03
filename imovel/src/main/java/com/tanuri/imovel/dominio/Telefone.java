package com.tanuri.imovel.dominio;

import javax.persistence.Embeddable;

@Embeddable
public class Telefone {
	private String numero;
	private boolean whatsapp;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(boolean whatsapp) {
		this.whatsapp = whatsapp;
	}

}
