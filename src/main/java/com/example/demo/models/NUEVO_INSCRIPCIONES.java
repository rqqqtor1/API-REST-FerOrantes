package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "NUEVO_INSCRIPCIONES")
public class NUEVO_INSCRIPCIONES {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private NUEVO_ALUMNOS alumno;

    @ManyToOne
    @JoinColumn(name = "curso")
    private NUEVO_CURSOS curso;

    private double calificacion;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NUEVO_ALUMNOS getAlumno() {
        return alumno;
    }

    public void setAlumno(NUEVO_ALUMNOS alumno) {
        this.alumno = alumno;
    }

    public NUEVO_CURSOS getCurso() {
        return curso;
    }

    public void setCurso(NUEVO_CURSOS curso) {
        this.curso = curso;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
}