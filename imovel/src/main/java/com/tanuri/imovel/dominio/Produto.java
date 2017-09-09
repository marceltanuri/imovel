package com.tanuri.imovel.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	@Embedded
	private Localizacao localizacao = new Localizacao();

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private CategoriaDoProduto tipo;

	private Long metragem;

	@ManyToMany
	private List<Componente> componentes;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Foto> fotos = new ArrayList<Foto>();

	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public CategoriaDoProduto getTipo() {
		return tipo;
	}

	public void setTipo(CategoriaDoProduto tipo) {
		this.tipo = tipo;
	}

	public Long getMetragem() {
		return metragem;
	}

	public void setMetragem(Long metragem) {
		this.metragem = metragem;
	}

	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
