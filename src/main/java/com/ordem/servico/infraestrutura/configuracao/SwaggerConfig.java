package com.ordem.servico.infraestrutura.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	 @Bean
	    public OpenAPI openApi() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("API Ordem de Serviço")
	                        .version("1.0")
	                        .description("Documentação da API de Ordens de Serviço"));
	    }
}
