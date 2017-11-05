package com.tanuri.imovel.controller;

import javax.servlet.http.HttpSession;

public class Sessao {

	public static Long getChat(HttpSession session) {
		return session.getAttribute("chat_id") != null ? (Long) session.getAttribute("chat_id") : null;
	}

	public static void setChat(HttpSession session, Long chat) {
		session.setAttribute("chat_id", chat);
	}
}
