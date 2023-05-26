package br.com.planilha.leitorapp.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI leitorPlanilhaApi() {
        return new OpenAPI().info(
                (new Info()).title("Leitor")
                        .description("serviço responsável por ler a planilha de cidades")
                        .version("v0.0.1")
        );
    }
}
