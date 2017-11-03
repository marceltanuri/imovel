package com.tanuri.imovel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tanuri.imovel.dominio.SolicitacaoDeContato;
import com.tanuri.imovel.repositorio.SolicitacoesDeContato;
import com.tanuri.imovel.service.ProdutoService;

@Controller
public class SiteController {
	@Autowired
	ProdutoService produtoService;

	@Autowired
	SolicitacoesDeContato solicitacoesDeContato;

	@GetMapping("/produtos")
	public String listar(Model model) {
		model.addAttribute(new SolicitacaoDeContato());
		model.addAttribute("produtos", produtoService.listar());
		return "site/produtos";
	}

	@PostMapping("/solicitacaoDeContato")
	public String registraSolicitacaoDeContato(SolicitacaoDeContato solicitacaoDeContato, RedirectAttributes redirect) {
		redirect.addFlashAttribute("mensagemSucesso", "Sua solicitação foi enviada, aguarde nosso contato.");
		solicitacoesDeContato.save(solicitacaoDeContato);
		return "redirect:/produtos";
	}
}
