package com.uao.rutas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad Ciudad.
 *
 * Representa una ciudad que pertenece a un pais. La relacion con Pais se
 * guarda con el campo idPais (la llave foranea segun el modelo relacional).
 * Tambien guarda las coordenadas geograficas (longitud y latitud).
 */
@Entity
public class Ciudad {

    /** Clave primaria autogenerada. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la ciudad (ej: Paris). */
    @NotBlank(message = "El nombre de la ciudad es obligatorio")
    private String nombre;

    /** Llave foranea: id del pais al que pertenece la ciudad. */
    @NotNull(message = "Debe indicar el pais (idPais)")
    private Long idPais;

    /** Coordenada de longitud de la ciudad. */
    private Double longitud;

    /** Coordenada de latitud de la ciudad. */
    private Double latitud;

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

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
}
