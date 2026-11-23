package com.example.demo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    RepositoryPaciente repositoryPaciente;

    // Devuelve todos los pacientes
    public List<Paciente> getAllPacientes() {
        return repositoryPaciente.findAll();
    }

    // Devuelve un paciente por ID
    public Paciente getPaciente(Long id) {
        return repositoryPaciente.getReferenceById(id);
    }

    // Crea un paciente (RF1)
    public Paciente addPaciente(Paciente p) {
        return repositoryPaciente.saveAndFlush(p);
    }

    // Actualiza un paciente
    public void updatePaciente(Paciente p) {
        Paciente paciente = repositoryPaciente.getReferenceById(p.getId());
        paciente.setPaisOrigen(p.getPaisOrigen());
        paciente.setRutaTransito(p.getRutaTransito());
        paciente.setTiempoEstancia(p.getTiempoEstancia());
        paciente.setTrabajoActual(p.getTrabajoActual());
        paciente.setEdad(p.getEdad());
        repositoryPaciente.save(paciente);
    }

    // Elimina un paciente
    public void removePaciente(Paciente p) {
        repositoryPaciente.delete(p);
    }

    // Elimina un paciente por ID
    public void removePacienteID(Long id) {
        repositoryPaciente.deleteById(id);
    }
}
