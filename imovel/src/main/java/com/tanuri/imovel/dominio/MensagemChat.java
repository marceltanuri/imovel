package com.tanuri.imovel.dominio;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MensagemChat {

	@Id
	@GeneratedValue
	private Long id;

	private Calendar data;

	private String texto;

	private TiposDeEmissor emissor;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "chat_id")
	@JsonIgnore
	private Chat chat;

	public MensagemChat() {
		this.data = Calendar.getInstance();
	}

	public String getTexto() {
		return texto;
	}

	public Long getId() {
		return id;
	}

	public Calendar getData() {
		return data;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public TiposDeEmissor getEmissor() {
		return emissor;
	}

	public void setEmissor(TiposDeEmissor emissor) {
		this.emissor = emissor;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public enum TiposDeEmissor {
		CLIENTE, SERVIDOR
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MensagemChat other = (MensagemChat) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
