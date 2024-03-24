package com.invoice.invoicesystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.invoice.invoicesystem.controller"})
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo()
    {
        return new OpenAPI()

                .info(new Info().title("INVOICE SYSTEM API")
                        .description("")
                        .version("v1"));
    }
}