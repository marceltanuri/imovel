package com.tanuri.imovel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tanuri.imovel.dominio.Produto;
import com.tanuri.imovel.repositorio.Produtos;

@Component
public class ProdutoService {

	@Autowired
	Produtos produtos;

	@Transactional
	public Produto salvar(Produto produto) {
		return produtos.save(produto);
	}

	public Produto buscar(Long id) {
		return produtos.findOne(id);
	}

	public List<Produto> listar() {
		return produtos.findAll();
	}

	@Transactional
	public void excluir(Long id) {
		produtos.delete(id);
	}

	@Transactional
	public void alterar(Long id, Produto p) {
		Produto produto = this.buscar(id);
		produto.setNome(p.getNome());
		produto.getLocalizacao().setCidade(p.getLocalizacao().getCidade());
		produtos.save(produto);
	}

}
