package com.tanuri.imovel.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanuri.imovel.dominio.MensagemChat;

public interface MensagensChat extends JpaRepository<MensagemChat, Long> {

	public Collection<MensagemChat> findByChatIdOrderByData(Long chatId);

}