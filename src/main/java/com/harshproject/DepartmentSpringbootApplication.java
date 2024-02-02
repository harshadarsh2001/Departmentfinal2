package com.harshproject;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springdoc.core.customizers.OpenApiCustomizer;

@SpringBootApplication
@EnableScheduling
@EnableRabbit
@EnableCaching
@EnableSpringHttpSession
public class DepartmentSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartmentSpringbootApplication.class, args);
    }

    @Configuration
    @SecurityScheme(
            name = "bearer-key",
            type = SecuritySchemeType.HTTP,
            scheme = "bearer",
            bearerFormat = "JWT"
    )    
    public class SwaggerConfig {

        @Bean
        public OpenApiCustomizer openApiCustomizer() {
            return openApi -> openApi.info(
                    new io.swagger.v3.oas.models.info.Info()
                            .title("Department API")
                            .version("1.0")
                            .description("Documentation for Department API"))
                    .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearer-key"));
        }
    }
}
