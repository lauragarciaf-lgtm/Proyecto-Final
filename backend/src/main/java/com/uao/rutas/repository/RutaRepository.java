package com.uao.rutas.repository;

import com.uao.rutas.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio de Ruta.
 *
 * Ademas de los metodos CRUD heredados, define una consulta personalizada.
 * Spring Data JPA crea la consulta automaticamente a partir del NOMBRE del
 * metodo: "findByIdCiudad" se traduce a "buscar rutas donde idCiudad = ?".
 */
public interface RutaRepository extends JpaRepository<Ruta, Long> {

    /** Devuelve todas las rutas que pertenecen a una ciudad. */
    List<Ruta> findByIdCiudad(Long idCiudad);
}
