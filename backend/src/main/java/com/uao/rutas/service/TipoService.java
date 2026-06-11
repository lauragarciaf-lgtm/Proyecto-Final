package com.uao.rutas.service;

import com.uao.rutas.model.Tipo;
import com.uao.rutas.repository.TipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de Tipo.
 *
 * La capa de servicio contiene la "logica de negocio". Sirve de intermediaria
 * entre el controlador (que recibe las peticiones web) y el repositorio (que
 * habla con la base de datos). La anotacion @Service la registra en Spring.
 */
@Service
public class TipoService {

    private final TipoRepository repositorio;

    // Spring inyecta automaticamente el repositorio por el constructor.
    public TipoService(TipoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /** Lista todos los tipos. */
    public List<Tipo> listar() {
        return repositorio.findAll();
    }

    /** Busca un tipo por su id (puede no existir, por eso Optional). */
    public Optional<Tipo> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    /** Crea un nuevo tipo. Se pone id en null para que sea insercion. */
    public Tipo crear(Tipo tipo) {
        tipo.setId(null);
        return repositorio.save(tipo);
    }

    /** Actualiza un tipo existente (el objeto debe traer su id). */
    public Tipo actualizar(Tipo tipo) {
        return repositorio.save(tipo);
    }

    /** Elimina un tipo por su id. */
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    /** Indica si existe un tipo con ese id. */
    public boolean existe(Long id) {
        return repositorio.existsById(id);
    }
}
