package com.uao.rutas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad Ruta.
 *
 * Representa una ruta turistica. Una ruta:
 *   - pertenece a una Ciudad (campo idCiudad).
 *   - es de un Tipo (campo idTipo).
 *   - tiene varias Paradas.
 *
 * Los campos idCiudad e idTipo son las llaves foraneas del modelo relacional.
 */
@Entity
public class Ruta {

    /** Clave primaria autogenerada. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la ruta (ej: Crucero Historico por el Sena). */
    @NotBlank(message = "El nombre de la ruta es obligatorio")
    private String nombre;

    /** Llave foranea: tipo de la ruta. */
    @NotNull(message = "Debe indicar el tipo (idTipo)")
    private Long idTipo;

    /** Llave foranea: ciudad a la que pertenece la ruta. */
    @NotNull(message = "Debe indicar la ciudad (idCiudad)")
    private Long idCiudad;

    /** Descripcion libre de la ruta. */
    private String descripcion;

    // ----- Getters y Setters -----

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

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
