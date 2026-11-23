package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Esta clase ha sido creada siguiendo el modelo que tiene el profesor de clase cuenta en la parte de construcción de software
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // para ignorar el serializador al devolver un objeto paciente
public class Paciente {

    // Clave primaria autogenerada
    // @Id marca la clave primaria y @GeneratedValue hace que H2 genere el número automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // Se usa 'long' en vez de 'int' por recomendación de JPA/Spring para evitar quedarse sin rango de IDs, Cristián hace lo mismo con el ID de cuenta

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // @Column personaliza el nombre de la columna en la tabla SQL
    @Column(name = "pais_origen")
    private String paisOrigen;

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    @Column(name = "ruta_transito")
    private String rutaTransito;

    public String getRutaTransito() {
        return rutaTransito;
    }

    public void setRutaTransito(String rutaTransito) {
        this.rutaTransito = rutaTransito;
    }

    @Column(name = "tiempo_estancia")
    private int tiempoEstancia;
    // Se usa 'int' porque los meses de estancia y la edad son números enteros pequeños
    public int getTiempoEstancia() {
        return tiempoEstancia;
    }

    public void setTiempoEstancia(int tiempoEstancia) {
        this.tiempoEstancia = tiempoEstancia;
    }

    @Column(name = "trabajo_actual")
    private String trabajoActual;

    public String getTrabajoActual() {
        return trabajoActual;
    }

    public void setTrabajoActual(String trabajoActual) {
        this.trabajoActual = trabajoActual;
    }

    @Column(name = "edad")
    private int edad;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Constructor sin parámetros requerido por JPA para crear objetos al consultar la base de datos
    public Paciente() {
    }

    // Constructor con parámetros: útil para crear pacientes rápidamente desde código Java
    public Paciente(String paisOrigen, String rutaTransito, int tiempoEstancia, String trabajoActual, int edad) {
        this.paisOrigen = paisOrigen;
        this.rutaTransito = rutaTransito;
        this.tiempoEstancia = tiempoEstancia;
        this.trabajoActual = trabajoActual;
        this.edad = edad;
    }

    //Los métodos con anotaciones override se han hecho igual que en la clase cuenta de Cristián
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Paciente) && ((Paciente) obj).getId() == this.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "Paciente " + this.id + ": " + this.paisOrigen + " (" + this.edad + " años)";
    }
}
