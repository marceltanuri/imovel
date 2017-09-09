package com.tanuri.imovel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tanuri.imovel.dominio.Produto;
import com.tanuri.imovel.service.ProdutoService;
import com.tanuri.imovel.service.TipoDeComponenteService;

@Controller
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@Autowired
	TipoDeComponenteService tipoDeComponenteService;

	@GetMapping("/produtos")
	public String listar(Model model) {
		model.addAttribute(new Produto());
		model.addAttribute("componentes", tipoDeComponenteService.listar());
		model.addAttribute("produtos", produtoService.listar());
		return "produtos/lista";
	}

	@GetMapping("/produto/editar")
	public String editar(Model model, Long id) {
		model.addAttribute("produto", produtoService.buscar(id));
		model.addAttribute("componentes", tipoDeComponenteService.listar());
		return "produtos/edicao";
	}

	@PostMapping("/produto/editar")
	public String alterar(Long id, Produto produto) {
		produtoService.alterar(id, produto);
		return "redirect:/produtos";
	}

	@PostMapping("/produtos")
	public String salvar(Produto produto) {
		produtoService.salvar(produto);
		return "redirect:/produtos";
	}

	@PostMapping("/produto/excluir")
	public String excluir(Long id) {
		produtoService.excluir(id);
		return "redirect:/produtos";
	}

}
