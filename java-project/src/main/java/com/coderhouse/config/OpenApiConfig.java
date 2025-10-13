package com.coderhouse.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Full | Java")
                        .version("1.0.0")
                        .description("API REST desarrollada con Spring Boot para la gestión completa de clientes, productos y ventas. "
                                + "Permite registrar clientes, crear productos asociados a categorías y realizar ventas asignando productos a los clientes. "
                                + "El proyecto utiliza MySQL como base de datos relacional e implementa JPA/Hibernate para la persistencia de datos. "
                                + "Incluye operaciones CRUD, relaciones entre entidades (OneToMany, ManyToMany) y manejo de transacciones para mantener la integridad de los datos. "
                                + "Este sistema sirve como ejemplo educativo de cómo estructurar una API empresarial con arquitectura en capas y buenas prácticas de desarrollo backend.")
                        .contact(new Contact()
                                .name("Johnny Telles")
                                .email("johnnytelles@example.com")
                                .url("https://github.com/johnnytelles77"))
                        .license(new License()
                                .name("Licencia MIT")
                                .url("https://github.com/johnnytelles77/Java-Project.git")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor Local"),
                        new Server()
                                .url("https://java-project-production.up.railway.app")
                                .description("Servidor en Producción (Railway)")
                ));
    }
}