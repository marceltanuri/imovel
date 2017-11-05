package com.tanuri.imovel.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanuri.imovel.dominio.Chat;

public interface Chats extends JpaRepository<Chat, Long> {
}