package com.uao.rutas.service;

import com.uao.rutas.model.Pais;
import com.uao.rutas.repository.PaisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de Pais. Contiene la logica de negocio del CRUD de paises.
 */
@Service
public class PaisService {

    private final PaisRepository repositorio;

    public PaisService(PaisRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Pais> listar() {
        return repositorio.findAll();
    }

    public Optional<Pais> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    public Pais crear(Pais pais) {
        pais.setId(null);
        return repositorio.save(pais);
    }

    public Pais actualizar(Pais pais) {
        return repositorio.save(pais);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    public boolean existe(Long id) {
        return repositorio.existsById(id);
    }
}
