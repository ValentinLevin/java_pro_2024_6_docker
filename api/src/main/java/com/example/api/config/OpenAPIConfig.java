package com.example.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                              .title("Домашнее задание по курсу Java Pro 2024")
                              .description(
                                      "<p><i>Студент: Валентин Левин</i></p>" +
                                              "<h2>5. Системы обмена сообщениями </h2> ")
                );
    }
}
