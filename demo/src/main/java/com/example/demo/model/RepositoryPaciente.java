package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

// Interfaz que conecta la entidad Paciente con la base de datos H2
public interface RepositoryPaciente extends JpaRepository<Paciente, Long> {
}
