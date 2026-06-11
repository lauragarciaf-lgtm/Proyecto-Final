package com.uao.rutas.repository;

import com.uao.rutas.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio de Parada.
 *
 * Incluye una consulta personalizada que devuelve las paradas de una ruta
 * ya ORDENADAS por el campo "orden" (ascendente), tal como pide el enunciado.
 * El nombre "findByIdRutaOrderByOrdenAsc" le indica a Spring que ordene.
 */
public interface ParadaRepository extends JpaRepository<Parada, Long> {

    /** Devuelve las paradas de una ruta ordenadas por el campo orden. */
    List<Parada> findByIdRutaOrderByOrdenAsc(Long idRuta);
}
