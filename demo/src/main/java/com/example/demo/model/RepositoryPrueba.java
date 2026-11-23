package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

// Interfaz de acceso a datos para la entidad Prueba
public interface RepositoryPrueba extends JpaRepository<Prueba, Long> {
    Prueba findByCodigo(String codigo);
    // Consulta personalizada siguiendo el ejemplo de clase (findByCcc): Spring genera la búsqueda por código automáticamente

}
