package com.uao.rutas.service;

import com.uao.rutas.model.Ruta;
import com.uao.rutas.repository.RutaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de Ruta.
 *
 * Ademas del CRUD basico, ofrece el metodo para listar las rutas de una
 * ciudad especifica (requerimiento del enunciado).
 */
@Service
public class RutaService {

    private final RutaRepository repositorio;

    public RutaService(RutaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Ruta> listar() {
        return repositorio.findAll();
    }

    public Optional<Ruta> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    /** Lista las rutas que pertenecen a una ciudad. */
    public List<Ruta> listarPorCiudad(Long idCiudad) {
        return repositorio.findByIdCiudad(idCiudad);
    }

    public Ruta crear(Ruta ruta) {
        ruta.setId(null);
        return repositorio.save(ruta);
    }

    public Ruta actualizar(Ruta ruta) {
        return repositorio.save(ruta);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    public boolean existe(Long id) {
        return repositorio.existsById(id);
    }
}
