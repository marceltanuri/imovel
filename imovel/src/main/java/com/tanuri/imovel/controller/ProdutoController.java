package com.tanuri.imovel.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tanuri.imovel.dominio.ComponenteDoProduto;
import com.tanuri.imovel.dominio.Produto;
import com.tanuri.imovel.dominio.TipoDeComponente;
import com.tanuri.imovel.service.ProdutoService;
import com.tanuri.imovel.service.TipoDeComponenteService;

@Controller
@RequestMapping("/admin")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@Autowired
	TipoDeComponenteService tipoDeComponenteService;

	@GetMapping("/produtos")
	public String listar(Model model) {
		model.addAttribute(new Produto());
		model.addAttribute("componentes", montaListaDeComponentes(tipoDeComponenteService.listar(), null));
		model.addAttribute("produtos", produtoService.listar());
		return "produtos/lista";
	}

	@GetMapping("/produto/editar")
	public String editar(Model model, Long id) {
		Produto produto = produtoService.buscar(id);
		model.addAttribute("produto", produto);
		model.addAttribute("componentes", montaListaDeComponentes(tipoDeComponenteService.listar(), produto));
		return "produtos/edicao";
	}

	@GetMapping("/produto/fotos")
	public String fotos(Model model, Long id) {
		Produto produto = produtoService.buscar(id);
		model.addAttribute("produto", produto);
		return "produtos/fotos";
	}

	@PostMapping("/produto/fotos")
	public String salvarFotos(Long id, Integer[] fotosExcluidas, Integer fotoPrincipal, Produto produto,
			RedirectAttributes redirect) {
		produtoService.alterarFotos(id, fotosExcluidas, fotoPrincipal, produto.getFotos());
		redirect.addFlashAttribute("mensagemSucesso", "As fotos do produto foram atualizadas.");
		return "redirect:/produto/fotos?id=" + id;
	}

	private Set<ComponenteDoProduto> montaListaDeComponentes(List<TipoDeComponente> tiposDeComponente,
			Produto produto) {
		Set<ComponenteDoProduto> componentes = new HashSet<>();
		if (produto != null && produto.getComponentes() != null && !produto.getComponentes().isEmpty()) {
			componentes.addAll(produto.getComponentes());
		}
		for (TipoDeComponente tipoDeComponente : tiposDeComponente) {
			componentes.add(new ComponenteDoProduto(tipoDeComponente));
		}
		return componentes;
	}

	@PostMapping("/produto/editar")
	public String alterar(Long id, Produto produto, RedirectAttributes redirect) {
		produtoService.alterar(id, produto);
		redirect.addFlashAttribute("mensagemSucesso", "Os dados do produto foram atualizados.");
		return "redirect:/produtos";
	}

	@PostMapping("/produtos")
	public String salvar(Produto produto, RedirectAttributes redirect) {
		produtoService.salvar(produto);
		redirect.addFlashAttribute("mensagemSucesso", "O produto foi inserido.");
		return "redirect:/produtos";
	}

	@PostMapping("/produto/excluir")
	public String excluir(Long id, RedirectAttributes redirect) {
		produtoService.excluir(id);
		redirect.addFlashAttribute("mensagemSucesso", "O produto foi excluído.");
		return "redirect:/produtos";
	}

}
