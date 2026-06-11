package com.uao.rutas.controller;

import com.uao.rutas.model.Pais;
import com.uao.rutas.service.PaisService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST de Pais. Expone el CRUD bajo la ruta /api/paises.
 * (El funcionamiento es el mismo explicado en TipoController.)
 */
@RestController
@RequestMapping("/api/paises")
public class PaisController {

    private final PaisService servicio;

    public PaisController(PaisService servicio) {
        this.servicio = servicio;
    }

    /** GET /api/paises/ -> lista todos los paises. */
    @GetMapping("/")
    public List<Pais> listar() {
        return servicio.listar();
    }

    /** GET /api/paises/{id} -> un pais o 404. */
    @GetMapping("/{id}")
    public ResponseEntity<Pais> buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** POST /api/paises/ -> crea un pais. */
    @PostMapping("/")
    public ResponseEntity<Pais> crear(@Valid @RequestBody Pais pais) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(pais));
    }

    /** PUT /api/paises/ -> actualiza un pais (id en el cuerpo). */
    @PutMapping("/")
    public ResponseEntity<Pais> actualizar(@Valid @RequestBody Pais pais) {
        if (pais.getId() == null || !servicio.existe(pais.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicio.actualizar(pais));
    }

    /** DELETE /api/paises/{id} -> elimina un pais. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!servicio.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
