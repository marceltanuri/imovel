package com.tanuri.imovel.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanuri.imovel.dominio.SolicitacaoDeContato;

public interface SolicitacoesDeContato extends JpaRepository<SolicitacaoDeContato, Long> {
}