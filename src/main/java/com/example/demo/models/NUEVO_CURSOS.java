package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "NUEVO_CURSOS")
public class NUEVO_CURSOS {

    @Id
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_profesor") // Cambia 'id_profesor' si el nombre de la columna es diferente
    private NUEVO_PROFESORES profesor;

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NUEVO_PROFESORES getProfesor() {
        return profesor;
    }

    public void setProfesor(NUEVO_PROFESORES profesor) {
        this.profesor = profesor;
    }
}
