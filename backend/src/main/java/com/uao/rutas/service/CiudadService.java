package com.uao.rutas.service;

import com.uao.rutas.model.Ciudad;
import com.uao.rutas.repository.CiudadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de Ciudad. Contiene la logica de negocio del CRUD de ciudades.
 */
@Service
public class CiudadService {

    private final CiudadRepository repositorio;

    public CiudadService(CiudadRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Ciudad> listar() {
        return repositorio.findAll();
    }

    public Optional<Ciudad> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    public Ciudad crear(Ciudad ciudad) {
        ciudad.setId(null);
        return repositorio.save(ciudad);
    }

    public Ciudad actualizar(Ciudad ciudad) {
        return repositorio.save(ciudad);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    public boolean existe(Long id) {
        return repositorio.existsById(id);
    }
}
