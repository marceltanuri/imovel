package com.tanuri.imovel.controller;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tanuri.imovel.dominio.Chat;
import com.tanuri.imovel.dominio.MensagemChat;
import com.tanuri.imovel.dominio.MensagemChat.TiposDeEmissor;
import com.tanuri.imovel.repositorio.Chats;
import com.tanuri.imovel.repositorio.MensagensChat;

@RestController
public class ChatRestController {

	@Autowired
	MensagensChat mensagensChat;

	@Autowired
	Chats chats;

	@PostMapping("/mensagensChat")
	@Transactional
	@CrossOrigin
	public ResponseEntity<Void> enviaMensagemChat(@RequestBody MensagemChat mensagemChat, HttpSession session) {
		Chat chat = chats.findOne(Sessao.getChat(session) != null ? Sessao.getChat(session) : -1L);
		mensagemChat.setEmissor(TiposDeEmissor.CLIENTE);
		mensagemChat.setChat(chat != null ? chat : new Chat());
		mensagensChat.save(mensagemChat);
		Sessao.setChat(session, mensagemChat.getChat().getId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/a/mensagensChat/{chat_id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<Void> enviaMensagemChat(@PathVariable("chat_id") Long chatId, @RequestBody MensagemChat mensagemChat,
			HttpSession session) {
		Chat chat = chats.findOne(chatId);
		mensagemChat.setEmissor(TiposDeEmissor.SERVIDOR);
		mensagemChat.setChat(chat);
		mensagensChat.save(mensagemChat);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/a/mensagensChat/{chat_id}")
	@CrossOrigin
	public ResponseEntity<Collection<MensagemChat>> buscaMensagemChat(@PathVariable("chat_id") Long chatId,
			HttpSession session) {
		Collection<MensagemChat> lista = mensagensChat.findByChatIdOrderByData(chatId);
		return new ResponseEntity<Collection<MensagemChat>>(lista, HttpStatus.OK);
	}

	@GetMapping("/mensagensChat")
	@CrossOrigin
	public ResponseEntity<Collection<MensagemChat>> buscaMensagemChat(HttpSession session) {
		if (Sessao.getChat(session) == null) {
			return new ResponseEntity<Collection<MensagemChat>>(Arrays.asList(), HttpStatus.OK);
		}
		Collection<MensagemChat> lista = mensagensChat.findByChatIdOrderByData(Sessao.getChat(session));
		return new ResponseEntity<Collection<MensagemChat>>(lista, HttpStatus.OK);
	}
}