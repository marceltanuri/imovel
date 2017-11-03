package com.tanuri.imovel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tanuri.imovel.dominio.TipoDeComponente;
import com.tanuri.imovel.service.TipoDeComponenteService;

@Controller
@RequestMapping("/admin")
public class TipoDeComponenteController {

	@Autowired
	TipoDeComponenteService tipoDeComponenteService;

	@GetMapping("/componentes")
	public String listar(Model model) {
		model.addAttribute(new TipoDeComponente());
		model.addAttribute("componentes", tipoDeComponenteService.listar());
		return "componentes/lista";
	}

	@GetMapping("/componente/editar")
	public String editar(Model model, Long id) {
		model.addAttribute("componente", tipoDeComponenteService.buscar(id));
		return "componentes/edicao";
	}

	@PostMapping("/componente/editar")
	public String alterar(Long id, TipoDeComponente tipoDeComponente) {
		tipoDeComponenteService.alterar(id, tipoDeComponente);
		return "redirect:/componentes";
	}

	@PostMapping("/componentes")
	public String salvar(TipoDeComponente tipoDeComponente) {
		tipoDeComponenteService.salvar(tipoDeComponente);
		return "redirect:/componentes";
	}

	@PostMapping("/componente/excluir")
	public String excluir(Long id) {
		tipoDeComponenteService.excluir(id);
		return "redirect:/componentes";
	}

}
