package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Paciente;
import com.example.demo.model.PacienteService;

// Indica a Spring que esta clase es una API REST y devuelve los datos en formato JSON
@RestController
public class PacienteController {


    @Autowired
    // Inyecta automáticamente el servicio sin usar 'new'
    private PacienteService pacienteService;

    // Devuelve todos los pacientes
    // @GetMapping atiende peticiones HTTP GET
    @GetMapping("/pacientes")
    public List<Paciente> getPacientes() {
        return pacienteService.getAllPacientes();
    }

    // Devuelve un paciente por ID
    // @PathVariable captura el valor {id} directamente de la URL
    @GetMapping("/paciente/{id}")
    public Paciente getPaciente(@PathVariable("id") Long id) {
        return pacienteService.getPaciente(id);
    }

    // Crea un paciente con el objeto Paciente recibido en formato JSON 
    // @PostMapping atiende peticiones POST para crear registros
    @PostMapping(value = "/paciente", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> savePaciente(@RequestBody Paciente paciente) { // @RequestBody convierte el JSON recibido desde Postman en un objeto Java Paciente
        try {
            pacienteService.addPaciente(paciente);
            return ResponseEntity.ok().body("Un nuevo paciente se ha registrado en el sistema");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al registrar el paciente");
        }
    }

    // Variante del POST que ahora recibe los datos desde un formulario HTML 
    //No necesita de @RequestBody
    @PostMapping(value = "/paciente", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> saveHTML(Paciente paciente) {
        try {
            pacienteService.addPaciente(paciente);
            return ResponseEntity.ok().body("Un nuevo paciente se ha registrado en el sistema");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al registrar el paciente");
        }
    }

    // @PutMapping atiende peticiones HTTP PUT para modificar un paciente ya existente en la BD
    @PutMapping("/paciente")
    public ResponseEntity<?> updatePaciente(@RequestBody Paciente paciente) {
        try {
            pacienteService.updatePaciente(paciente);
            return ResponseEntity.ok().body("Los datos del paciente se han actualizado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar el paciente");
        }
    }

    // @DeleteMapping atiende peticiones HTTP DELETE para borrar un registro
    @DeleteMapping("/paciente")
    public ResponseEntity<?> deletePaciente(@RequestBody Paciente paciente) {
        try {
            pacienteService.removePaciente(paciente);
            return ResponseEntity.ok().body("El paciente se ha eliminado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar el paciente");
        }
    }

    // Variante del DELETE que borra directamente indicando su {id} en la URL
    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable("id") Long id) {
        try {
            pacienteService.removePacienteID(id);
            return ResponseEntity.ok().body("El paciente se ha eliminado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar el paciente");
        }
    }
}
