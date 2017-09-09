package com.tanuri.imovel.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanuri.imovel.dominio.Produto;

public interface Produtos extends JpaRepository<Produto, Long> {
}