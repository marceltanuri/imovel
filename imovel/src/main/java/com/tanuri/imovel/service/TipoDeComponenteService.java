package com.tanuri.imovel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tanuri.imovel.dominio.TipoDeComponente;
import com.tanuri.imovel.repositorio.TiposDeComponentes;

@Component
public class TipoDeComponenteService {

	@Autowired
	TiposDeComponentes tiposDeComponentes;

	@Transactional
	public TipoDeComponente salvar(TipoDeComponente tipoDeComponente) {
		return tiposDeComponentes.save(tipoDeComponente);
	}

	public TipoDeComponente buscar(Long id) {
		return tiposDeComponentes.findOne(id);
	}

	public List<TipoDeComponente> listar() {
		return tiposDeComponentes.findAll();
	}

	@Transactional
	public void excluir(Long id) {
		tiposDeComponentes.delete(id);
	}

	@Transactional
	public void alterar(Long id, TipoDeComponente t) {
		TipoDeComponente tipoDeComponente = this.buscar(id);
		tipoDeComponente.setNome(t.getNome());
		tiposDeComponentes.save(tipoDeComponente);
	}

}
