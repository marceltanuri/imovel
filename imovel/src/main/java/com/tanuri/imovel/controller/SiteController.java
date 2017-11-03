package com.tanuri.imovel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tanuri.imovel.service.ProdutoService;

@Controller
public class SiteController {
	@Autowired
	ProdutoService produtoService;

	@GetMapping("/produtos")
	public String listar(Model model) {
		model.addAttribute("produtos", produtoService.listar());
		return "site/produtos";
	}
}
