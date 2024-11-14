package com.example.demo.repositories;

import com.example.demo.models.NUEVO_INSCRIPCIONES;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Alumno_CursoRepo extends JpaRepository<NUEVO_INSCRIPCIONES, Long> {
    // Método para buscar inscripciones por ID de alumno
    List<NUEVO_INSCRIPCIONES> findByAlumno_Id(Long alumnoId);

    // Método para buscar una inscripción específica por ID de alumno y nombre del curso
    Optional<NUEVO_INSCRIPCIONES> findByAlumno_IdAndCurso_Nombre(Long alumnoId, String cursoNombre);

    // Método para buscar inscripciones por nombre del curso
    List<NUEVO_INSCRIPCIONES> findByCurso_Nombre(String cursoNombre);
}