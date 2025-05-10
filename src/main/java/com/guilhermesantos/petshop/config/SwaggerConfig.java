package com.guilhermesantos.petshop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("PetShopManagerAPI").version("1.0").contact(new Contact().name("Portfólio Guilherme").url("https://guilherme-santos-alves.github.io/portfolio/")));
    }

}
