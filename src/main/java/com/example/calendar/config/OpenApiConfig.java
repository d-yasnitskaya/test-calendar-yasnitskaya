package com.example.calendar.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация документации OpenAPI.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Создаёт общее описание API приложения
     *
     * @return конфигурация OpenAPI
     */
    @Bean
    public OpenAPI calendarOpenApi() {
        return new OpenAPI().info(new Info().title("Calendar API").version("1.0").description(
                "API для получения календаря года, месяца и отдельного календарного дня"));
    }
}