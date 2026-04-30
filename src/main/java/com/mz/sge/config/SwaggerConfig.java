package com.mz.sge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info().title("SGE — Sistema de Gestão Escolar").description(
						"API REST para gestão de dados do ensino básico no Sistema Nacional de Ensino de Moçambique.")
						.version("v1.0")
						.contact(new Contact().name("Adérito Andala").url("https://github.com/aderitoandala")))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
				.components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme().name("bearerAuth")
						.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}

}
