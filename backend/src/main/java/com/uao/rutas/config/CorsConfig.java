package com.uao.rutas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuracion de CORS (Cross-Origin Resource Sharing).
 *
 * Por seguridad, los navegadores bloquean por defecto las peticiones entre
 * dominios/puertos distintos. Como el frontend (AngularJS) se ejecuta en un
 * puerto diferente al backend (8080), debemos permitir explicitamente esas
 * llamadas. Aqui autorizamos cualquier origen y los metodos REST que usamos.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")                 // aplica a todas las rutas /api
                .allowedOrigins("*")                   // permite peticiones desde cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // metodos permitidos
                .allowedHeaders("*");                  // permite cualquier cabecera
    }
}
