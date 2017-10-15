package com.tanuri.imovel.service;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tanuri.imovel.dominio.ComponenteDoProduto;
import com.tanuri.imovel.dominio.Foto;
import com.tanuri.imovel.dominio.Produto;
import com.tanuri.imovel.repositorio.Produtos;

@Component
public class ProdutoService {

	@Autowired
	Produtos produtos;

	@Transactional
	public Produto salvar(Produto produto) {
		removeComponentesNulos(produto);
		return produtos.save(produto);
	}

	private void removeComponentesNulos(Produto produto) {
		if (produto == null || produto.getComponentes() == null) {
			return;
		}
		Predicate<? super ComponenteDoProduto> filter = c -> c.getTipo() == null || c.getQuantidade() == null
				|| c.getQuantidade() == 0L;
		produto.getComponentes().removeIf(filter);
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
		removeComponentesNulos(p);
		p.setId(produto.getId());
		p.setFotos(produto.getFotos());
		produtos.save(p);
	}

	@Transactional
	public void alterarFotos(Long id, Integer[] fotosExcluidas, Integer fotoPrincipal,
			Collection<? extends Foto> fotosNovas) {
		Produto produto = this.buscar(id);
		if (fotosExcluidas != null) {
			int exclusoes = 0;
			for (int i = 0; i < fotosExcluidas.length; i++) {
				Integer index = fotosExcluidas[i];
				if (index == null) {
					continue;
				}
				produto.getFotos().remove(index - exclusoes++);
			}
		}
		if (fotosNovas != null && !fotosNovas.isEmpty()) {
			produto.getFotos().addAll(fotosNovas);
		}
		if (produto.getFotos() != null && !produto.getFotos().isEmpty()) {
			if (fotoPrincipal != null) {
				produto.setFotoPrincipal(fotoPrincipal);
			}
		}
		produtos.save(produto);
	}
}