package com.uao.rutas.repository;

import com.uao.rutas.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Ciudad. Hereda los metodos CRUD basicos de JpaRepository.
 */
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
