package com.tanuri.imovel.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanuri.imovel.dominio.TipoDeComponente;

public interface TiposDeComponentes extends JpaRepository<TipoDeComponente, Long> {
}