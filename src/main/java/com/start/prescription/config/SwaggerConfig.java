package com.start.prescription.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("처방전 프로젝트")
                .version("v0.0.1")
                .description("처방전 프로젝트 API 명세서입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}