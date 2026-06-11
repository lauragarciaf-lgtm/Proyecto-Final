package com.uao.rutas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de la documentacion Swagger / OpenAPI.
 *
 * Define el titulo, la version y la descripcion que aparecen en la pagina
 * de documentacion automatica disponible en:
 *      http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiRutasTuristicas() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Rutas Turisticas")
                        .version("1.0.0")
                        .description("API para gestionar Rutas Turisticas"));
    }
}
