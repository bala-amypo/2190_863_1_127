package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Development Server");

        Server deploymentServer = new Server();
        deploymentServer.setUrl("https://9081.32procr.amypo.ai");
        deploymentServer.setDescription("Deployment Server");

        Server productionServer = new Server();
        productionServer.setUrl("https://api.example.com");
        productionServer.setDescription("Production Server");

        Contact contact = new Contact();
        contact.setEmail("support@example.com");
        contact.setName("API Support Team");
        contact.setUrl("https://www.example.com");

        return new OpenAPI()
                .servers(List.of(localServer, deploymentServer, productionServer))
                .info(new Info()
                        .title("Complaint Prioritization Engine API")
                        .version("1.0")
                        .description("API for managing customer complaints with priority scoring")
                        .contact(contact))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}