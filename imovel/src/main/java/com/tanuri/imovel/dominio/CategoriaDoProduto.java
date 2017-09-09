package com.tanuri.imovel.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CategoriaDoProduto {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	@ManyToOne
	private CategoriaDoProduto categoriaPai;

	@OneToMany
	private List<CategoriaDoProduto> categoriasFilhas;

	@OneToMany
	private List<Produto> produtos;

	// getter and setters

	public List<Produto> getAllProdutos() {
		List<Produto> allProdutos = new ArrayList<Produto>();
		allProdutos.addAll(this.produtos);
		for (CategoriaDoProduto t : this.categoriasFilhas) {
			allProdutos.addAll(t.getProdutos());
		}
		return allProdutos;
	}

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

	public CategoriaDoProduto getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(CategoriaDoProduto categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<CategoriaDoProduto> getCategoriasFilhas() {
		return categoriasFilhas;
	}

	public void setCategoriasFilhas(List<CategoriaDoProduto> categoriasFilhas) {
		this.categoriasFilhas = categoriasFilhas;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
