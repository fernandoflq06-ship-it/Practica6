package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Prueba {

    // Clave primaria autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // unique = true impide que se guarden dos pruebas con el mismo código en la BD
    @Column(unique = true)
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "descripcion")
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Booleanos para los requisitos RF9 y RF10, decir se requiere ayuno o si es de riesgo agudo
    @Column(name = "es_riesgo_agudo")
    private boolean esRiesgoAgudo;

    public boolean isEsRiesgoAgudo() {
        return esRiesgoAgudo;
    }

    public void setEsRiesgoAgudo(boolean esRiesgoAgudo) {
        this.esRiesgoAgudo = esRiesgoAgudo;
    }

    @Column(name = "requiere_ayuno")
    private boolean requiereAyuno;

    public boolean isRequiereAyuno() {
        return requiereAyuno;
    }

    public void setRequiereAyuno(boolean requiereAyuno) {
        this.requiereAyuno = requiereAyuno;
    }

    //Atributos que nos dicen si la prueba ha sido validada por el médico
    @Column(name = "validada")
    private boolean validada;

    public boolean isValidada() {
        return validada;
    }

    public void setValidada(boolean validada) {
        this.validada = validada;
    }

    // Relación N:1 (Muchas pruebas pueden pertenecer a un mismo paciente)
    //Hemos decidido implementarlo en la clase prueba ya que el many to one es más fácil de implementar
    // que el one to many, ya que este último requiere una lista de pruebas en la clase paciente y un método para añadir pruebas a esa lista
    @ManyToOne
    private Paciente paciente;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Prueba() {
    }

    public Prueba(String codigo, String nombre, String descripcion, boolean esRiesgoAgudo, boolean requiereAyuno, boolean validada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esRiesgoAgudo = esRiesgoAgudo;
        this.requiereAyuno = requiereAyuno;
        this.validada = validada;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Prueba) && ((Prueba) obj).getCodigo() != null 
                && ((Prueba) obj).getCodigo().equalsIgnoreCase(this.codigo);
    }

    @Override
    public int hashCode() {
        return (this.codigo != null) ? this.codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Prueba " + this.codigo + ": " + this.nombre;
    }
}