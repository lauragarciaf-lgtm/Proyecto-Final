package com.uao.rutas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicacion.
 *
 * La anotacion @SpringBootApplication le dice a Spring Boot que esta es
 * la clase de arranque. Al ejecutar el metodo main, Spring:
 *   1. Levanta un servidor web (Tomcat) en el puerto 8080.
 *   2. Busca automaticamente los controladores, servicios y repositorios.
 *   3. Crea la base de datos en memoria (H2) y carga los datos de prueba.
 *
 * Para ejecutar el proyecto:  mvn spring-boot:run
 */
@SpringBootApplication
public class RutasTuristicasApplication {

    public static void main(String[] args) {
        SpringApplication.run(RutasTuristicasApplication.class, args);
    }
}
