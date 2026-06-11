package com.uao.rutas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad Parada.
 *
 * Representa una parada de una ruta turistica. Cada parada:
 *   - pertenece a una Ruta (campo idRuta).
 *   - tiene un numero de Orden que indica su posicion dentro de la ruta.
 *   - guarda coordenadas (longitud, latitud), tiempo estimado y descripcion.
 */
@Entity
public class Parada {

    /** Clave primaria autogenerada. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la parada (ej: Embarcadero Torre Eiffel). */
    @NotBlank(message = "El nombre de la parada es obligatorio")
    private String nombre;

    /** Orden de la parada dentro de la ruta (1, 2, 3...). */
    @NotNull(message = "Debe indicar el orden de la parada")
    private Integer orden;

    /** Llave foranea: ruta a la que pertenece la parada. */
    @NotNull(message = "Debe indicar la ruta (idRuta)")
    private Long idRuta;

    /** Coordenada de longitud de la parada. */
    private Double longitud;

    /** Coordenada de latitud de la parada. */
    private Double latitud;

    /** Tiempo estimado en la parada (en minutos). */
    private Integer tiempo;

    /** Descripcion libre de la parada. */
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Long getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Long idRuta) {
        this.idRuta = idRuta;
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

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
