package com.tanuri.imovel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class SecurityConfig {
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.inMemoryAuthentication().withUser("admin").password("tanuri@1561").roles("USER");
	}
}