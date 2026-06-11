package com.uao.rutas.controller;

import com.uao.rutas.model.Ciudad;
import com.uao.rutas.service.CiudadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST de Ciudad. Expone el CRUD bajo la ruta /api/ciudades.
 */
@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    private final CiudadService servicio;

    public CiudadController(CiudadService servicio) {
        this.servicio = servicio;
    }

    /** GET /api/ciudades/ -> lista todas las ciudades. */
    @GetMapping("/")
    public List<Ciudad> listar() {
        return servicio.listar();
    }

    /** GET /api/ciudades/{id} -> una ciudad o 404. */
    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** POST /api/ciudades/ -> crea una ciudad. */
    @PostMapping("/")
    public ResponseEntity<Ciudad> crear(@Valid @RequestBody Ciudad ciudad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(ciudad));
    }

    /** PUT /api/ciudades/ -> actualiza una ciudad (id en el cuerpo). */
    @PutMapping("/")
    public ResponseEntity<Ciudad> actualizar(@Valid @RequestBody Ciudad ciudad) {
        if (ciudad.getId() == null || !servicio.existe(ciudad.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicio.actualizar(ciudad));
    }

    /** DELETE /api/ciudades/{id} -> elimina una ciudad. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!servicio.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
