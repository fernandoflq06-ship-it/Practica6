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

import com.example.demo.model.Prueba;
import com.example.demo.model.PruebaService;

@RestController
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    // Devuelve todas las pruebas propuestas (RF2, RF9, RF10)
    @GetMapping("/pruebas")
    public List<Prueba> getPruebas() {
        return pruebaService.getAllPruebas();
    }

    // Devuelve una prueba por ID
    @GetMapping("/prueba/{id}")
    public Prueba getPrueba(@PathVariable("id") Long id) {
        return pruebaService.getPrueba(id);
    }

    // Añade una prueba propuesta en formato JSON
    @PostMapping(value = "/prueba", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> savePrueba(@RequestBody Prueba prueba) {
        try {
            pruebaService.addPrueba(prueba);
            return ResponseEntity.ok().body("Una nueva prueba se ha añadido al protocolo");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("La prueba ya existe o ha ocurrido un error");
        }
    }

    // Añade una prueba propuesta en formato HTML Form
    @PostMapping(value = "/prueba", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> saveHTML(Prueba prueba) {
        try {
            pruebaService.addPrueba(prueba);
            return ResponseEntity.ok().body("Una nueva prueba se ha añadido al protocolo");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("La prueba ya existe o ha ocurrido un error");
        }
    }

    // Modifica o valida una prueba propuesta 
    @PutMapping("/prueba")
    public ResponseEntity<?> updatePrueba(@RequestBody Prueba prueba) {
        try {
            pruebaService.updatePrueba(prueba);
            return ResponseEntity.ok().body("La prueba se ha actualizado/validado correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar la prueba");
        }
    }

    // Descarta una prueba pasando el objeto Prueba 
    @DeleteMapping("/prueba")
    public ResponseEntity<?> deletePrueba(@RequestBody Prueba prueba) {
        try {
            pruebaService.removePrueba(prueba);
            return ResponseEntity.ok().body("La prueba se ha descartado del cribado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al descartar la prueba");
        }
    }

    // Descarta una prueba por ID 
    @DeleteMapping("/prueba/{id}")
    public ResponseEntity<?> deletePrueba(@PathVariable("id") Long id) {
        try {
            pruebaService.removePruebaID(id);
            return ResponseEntity.ok().body("La prueba se ha descartado del cribado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al descartar la prueba");
        }
    }
}
