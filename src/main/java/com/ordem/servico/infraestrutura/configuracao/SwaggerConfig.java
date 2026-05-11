package com.ordem.servico.infraestrutura.configuracao;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OrderFlow - API de Ordens de Serviço")
                        .description("Sistema de Gestão de Ordens de Serviço - SENAI")
                        .version("1.0.0"));
    }
}
