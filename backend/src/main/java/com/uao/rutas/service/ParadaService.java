package com.uao.rutas.service;

import com.uao.rutas.model.Parada;
import com.uao.rutas.repository.ParadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de Parada.
 *
 * Ademas del CRUD basico, ofrece el metodo para listar las paradas de una
 * ruta ordenadas por el campo "orden" (requerimiento del enunciado).
 */
@Service
public class ParadaService {

    private final ParadaRepository repositorio;

    public ParadaService(ParadaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Parada> listar() {
        return repositorio.findAll();
    }

    public Optional<Parada> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    /** Lista las paradas de una ruta, ordenadas por el campo orden. */
    public List<Parada> listarPorRuta(Long idRuta) {
        return repositorio.findByIdRutaOrderByOrdenAsc(idRuta);
    }

    public Parada crear(Parada parada) {
        parada.setId(null);
        return repositorio.save(parada);
    }

    public Parada actualizar(Parada parada) {
        return repositorio.save(parada);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    public boolean existe(Long id) {
        return repositorio.existsById(id);
    }
}
