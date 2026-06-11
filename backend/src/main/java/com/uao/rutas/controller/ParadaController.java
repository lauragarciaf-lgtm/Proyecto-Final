package com.uao.rutas.controller;

import com.uao.rutas.model.Parada;
import com.uao.rutas.service.ParadaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST de Parada. Expone el CRUD bajo la ruta /api/paradas y un
 * endpoint especial para listar las paradas de una ruta (ordenadas por orden).
 */
@RestController
@RequestMapping("/api/paradas")
public class ParadaController {

    private final ParadaService servicio;

    public ParadaController(ParadaService servicio) {
        this.servicio = servicio;
    }

    /** GET /api/paradas/ -> lista todas las paradas. */
    @GetMapping("/")
    public List<Parada> listar() {
        return servicio.listar();
    }

    /** GET /api/paradas/{id} -> una parada o 404. */
    @GetMapping("/{id}")
    public ResponseEntity<Parada> buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /api/paradas/ruta/{idRuta}
     * Endpoint especial: lista las paradas de una ruta ordenadas por orden.
     */
    @GetMapping("/ruta/{idRuta}")
    public List<Parada> listarPorRuta(@PathVariable Long idRuta) {
        return servicio.listarPorRuta(idRuta);
    }

    /** POST /api/paradas/ -> crea una parada. */
    @PostMapping("/")
    public ResponseEntity<Parada> crear(@Valid @RequestBody Parada parada) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(parada));
    }

    /** PUT /api/paradas/ -> actualiza una parada (id en el cuerpo). */
    @PutMapping("/")
    public ResponseEntity<Parada> actualizar(@Valid @RequestBody Parada parada) {
        if (parada.getId() == null || !servicio.existe(parada.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicio.actualizar(parada));
    }

    /** DELETE /api/paradas/{id} -> elimina una parada. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!servicio.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
