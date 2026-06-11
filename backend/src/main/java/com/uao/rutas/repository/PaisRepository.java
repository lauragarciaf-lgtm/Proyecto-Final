package com.uao.rutas.repository;

import com.uao.rutas.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Pais. Hereda los metodos CRUD basicos de JpaRepository.
 */
public interface PaisRepository extends JpaRepository<Pais, Long> {
}
