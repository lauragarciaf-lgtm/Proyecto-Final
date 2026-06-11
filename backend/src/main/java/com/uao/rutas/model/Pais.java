package com.uao.rutas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidad Pais.
 *
 * Representa un pais. Un pais tiene muchas ciudades (relacion 1 a muchos),
 * pero para mantener el modelo simple guardamos la referencia desde Ciudad
 * mediante el campo idPais.
 */
@Entity
public class Pais {

    /** Clave primaria autogenerada. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre del pais (ej: Colombia). */
    @NotBlank(message = "El nombre del pais es obligatorio")
    private String nombre;

    /** Codigo ISO de 2 letras del pais (ej: CO, ES, FR). */
    private String codigoAlfa2;

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

    public String getCodigoAlfa2() {
        return codigoAlfa2;
    }

    public void setCodigoAlfa2(String codigoAlfa2) {
        this.codigoAlfa2 = codigoAlfa2;
    }
}
