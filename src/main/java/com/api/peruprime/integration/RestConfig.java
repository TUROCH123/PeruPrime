package com.api.peruprime.integration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	@Bean(name = "restTemplateUsuario")
	public RestTemplate restTemplateUsuario(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.setConnectTimeout(null).setReadTimeout(null).build();
	}

	@Bean(name = "restTemplatePago")
	public RestTemplate restTemplatePago(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.setConnectTimeout(null).setReadTimeout(null).build();
	}

	@Bean(name = "restTemplatePlanes")
	public RestTemplate restTemplatePlanes(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.setConnectTimeout(null).setReadTimeout(null).build();
	}
	@Bean(name = "restTemplatePerfiles")
	public RestTemplate restTemplatePerfiles(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.setConnectTimeout(null).setReadTimeout(null).build();
	}
}