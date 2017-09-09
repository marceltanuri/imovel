package com.tanuri.imovel.dominio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Foto {

	@Id
	@GeneratedValue
	private Long id;

	@Lob
	private String conteudo;

	@Transient
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) throws IOException {
		InputStream is = file.getInputStream();
		byte[] bytes = IOUtils.toByteArray(is);
		this.conteudo = Base64.getEncoder().encodeToString(bytes);
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
