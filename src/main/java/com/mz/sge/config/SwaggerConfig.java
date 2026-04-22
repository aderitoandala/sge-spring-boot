package com.mz.sge.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("SGE — Sistema de Gestão Escolar")
                .description("API REST para gestão de dados do ensino básico no Sistema Nacional de Ensino de Moçambique.")
                .version("v1.0")
                .contact(new Contact()
                    .name("Adérito Andala")
                    .url("https://github.com/aderitoandala")));
    }




}
