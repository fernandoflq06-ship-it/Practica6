package com.example.demo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PruebaService {

    @Autowired
    RepositoryPrueba repositoryPrueba;

    // Devuelve todas las pruebas
    public List<Prueba> getAllPruebas() {
        return repositoryPrueba.findAll();
    }

    // Devuelve una prueba por ID
    public Prueba getPrueba(Long id) {
        return repositoryPrueba.getReferenceById(id);
    }

    // Devuelve una prueba por su código (ej: AN-01)
    public Prueba getPruebaByCodigo(String codigo) {
        return repositoryPrueba.findByCodigo(codigo);
    }

    // Crea una prueba
    public Prueba addPrueba(Prueba p) {
        return repositoryPrueba.saveAndFlush(p);
    }

    // Actualiza o valida una prueba (RF2: modificar o validar propuesta)
    public void updatePrueba(Prueba p) {
        Prueba prueba = repositoryPrueba.getReferenceById(p.getId());
        prueba.setCodigo(p.getCodigo());
        prueba.setNombre(p.getNombre());
        prueba.setDescripcion(p.getDescripcion());
        prueba.setEsRiesgoAgudo(p.isEsRiesgoAgudo());
        prueba.setRequiereAyuno(p.isRequiereAyuno());
        prueba.setValidada(p.isValidada());
        prueba.setPaciente(p.getPaciente());
        repositoryPrueba.save(prueba);
    }

    // Elimina una prueba 
    public void removePrueba(Prueba p) {
        repositoryPrueba.delete(p);
    }

    // Elimina una prueba por ID
    public void removePruebaID(Long id) {
        repositoryPrueba.deleteById(id);
    }
}
