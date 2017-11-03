package com.tanuri.imovel.dominio;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.transaction.annotation.Transactional;

import com.tanuri.imovel.repositorio.SolicitacoesDeContato;

@Entity
public class SolicitacaoDeContato {

	@Id
	@GeneratedValue
	private Long id;

	private Calendar data = Calendar.getInstance();

	private String email;

	private String nome;

	private Telefone telefone;

	private boolean lida;

	@OneToOne
	private Produto produto;

	public Long getId() {
		return id;
	}

	public Calendar getData() {
		return data;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public boolean isLida() {
		return lida;
	}

	public void setLida(boolean jaFoiLida) {
		this.lida = jaFoiLida;
	}

	@Transactional
	public void marcaDesmarcaComoLida(SolicitacoesDeContato solicitacoesDeContato) {
		this.lida = !this.lida;
		solicitacoesDeContato.save(this);
	}
	
	@Transactional
	public void desmarcaComoLida(SolicitacoesDeContato solicitacoesDeContato) {
		this.lida = false;
		solicitacoesDeContato.save(this);
	}

}