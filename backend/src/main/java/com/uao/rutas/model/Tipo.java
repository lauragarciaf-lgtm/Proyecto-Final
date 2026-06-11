package com.uao.rutas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidad Tipo.
 *
 * Representa el tipo de una ruta turistica (por ejemplo: Bus, Fluvial,
 * Caminata, Bicicleta...). La anotacion @Entity le indica a JPA que esta
 * clase se debe guardar como una tabla en la base de datos.
 *
 * Cada atributo de la clase = una columna de la tabla.
 */
@Entity
public class Tipo {

    /** Identificador unico. @Id = clave primaria; se genera automaticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre del tipo. @NotBlank obliga a que no llegue vacio. */
    @NotBlank(message = "El nombre del tipo es obligatorio")
    private String nombre;

    // ----- Getters y Setters -----
    // Permiten leer (get) y modificar (set) los valores de cada atributo.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
