package com.uao.rutas.repository;

import com.uao.rutas.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Tipo.
 *
 * Al extender JpaRepository, Spring Data JPA crea AUTOMATICAMENTE los metodos
 * basicos para trabajar con la base de datos, sin escribir SQL:
 *   - findAll()      -> traer todos
 *   - findById(id)   -> traer uno por id
 *   - save(objeto)   -> insertar o actualizar
 *   - deleteById(id) -> eliminar
 *
 * El <Tipo, Long> indica: entidad = Tipo, tipo de la clave primaria = Long.
 */
public interface TipoRepository extends JpaRepository<Tipo, Long> {
}
