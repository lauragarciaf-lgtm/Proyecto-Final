package com.uao.rutas.controller;

import com.uao.rutas.model.Tipo;
import com.uao.rutas.service.TipoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST de Tipo.
 *
 * Un controlador recibe las peticiones HTTP del cliente y devuelve respuestas.
 * Las anotaciones indican que metodo HTTP y que ruta atiende cada funcion:
 *   @GetMapping    -> GET    (consultar)
 *   @PostMapping   -> POST   (crear)
 *   @PutMapping    -> PUT    (actualizar)
 *   @DeleteMapping -> DELETE (eliminar)
 *
 * @RestController + @RequestMapping("/api/tipos") = todas las rutas empiezan
 * con /api/tipos y las respuestas se devuelven en formato JSON.
 *
 * ResponseEntity nos permite devolver, ademas del dato, el codigo de estado
 * HTTP correcto (200 OK, 201 Creado, 404 No encontrado...).
 */
@RestController
@RequestMapping("/api/tipos")
public class TipoController {

    private final TipoService servicio;

    // Spring inyecta el servicio automaticamente.
    public TipoController(TipoService servicio) {
        this.servicio = servicio;
    }

    /** GET /api/tipos/  -> lista todos los tipos. */
    @GetMapping("/")
    public List<Tipo> listar() {
        return servicio.listar();
    }

    /** GET /api/tipos/{id}  -> devuelve un tipo, o 404 si no existe. */
    @GetMapping("/{id}")
    public ResponseEntity<Tipo> buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)                       // si existe -> 200 con el dato
                .orElse(ResponseEntity.notFound().build());    // si no existe -> 404
    }

    /** POST /api/tipos/  -> crea un tipo nuevo y responde 201 (Creado). */
    @PostMapping("/")
    public ResponseEntity<Tipo> crear(@Valid @RequestBody Tipo tipo) {
        Tipo creado = servicio.crear(tipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /** PUT /api/tipos/  -> actualiza un tipo existente (el id viene en el cuerpo). */
    @PutMapping("/")
    public ResponseEntity<Tipo> actualizar(@Valid @RequestBody Tipo tipo) {
        if (tipo.getId() == null || !servicio.existe(tipo.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicio.actualizar(tipo));
    }

    /** DELETE /api/tipos/{id}  -> elimina un tipo; 404 si no existe. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!servicio.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();             // 204 (sin contenido)
    }
}
