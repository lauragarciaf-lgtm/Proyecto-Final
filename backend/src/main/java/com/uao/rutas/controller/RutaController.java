package com.uao.rutas.controller;

import com.uao.rutas.model.Ruta;
import com.uao.rutas.service.RutaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST de Ruta. Expone el CRUD bajo la ruta /api/rutas y, ademas,
 * un endpoint especial para listar las rutas de una ciudad.
 */
@RestController
@RequestMapping("/api/rutas")
public class RutaController {

    private final RutaService servicio;

    public RutaController(RutaService servicio) {
        this.servicio = servicio;
    }

    /** GET /api/rutas/ -> lista todas las rutas. */
    @GetMapping("/")
    public List<Ruta> listar() {
        return servicio.listar();
    }

    /** GET /api/rutas/{id} -> una ruta o 404. */
    @GetMapping("/{id}")
    public ResponseEntity<Ruta> buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /api/rutas/ciudad/{idCiudad}
     * Endpoint especial: lista solo las rutas que pertenecen a una ciudad.
     */
    @GetMapping("/ciudad/{idCiudad}")
    public List<Ruta> listarPorCiudad(@PathVariable Long idCiudad) {
        return servicio.listarPorCiudad(idCiudad);
    }

    /** POST /api/rutas/ -> crea una ruta. */
    @PostMapping("/")
    public ResponseEntity<Ruta> crear(@Valid @RequestBody Ruta ruta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(ruta));
    }

    /** PUT /api/rutas/ -> actualiza una ruta (id en el cuerpo). */
    @PutMapping("/")
    public ResponseEntity<Ruta> actualizar(@Valid @RequestBody Ruta ruta) {
        if (ruta.getId() == null || !servicio.existe(ruta.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicio.actualizar(ruta));
    }

    /** DELETE /api/rutas/{id} -> elimina una ruta. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!servicio.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
