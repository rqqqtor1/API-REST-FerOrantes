package com.example.demo.repositories;
import com.example.demo.models.NUEVO_ALUMNOS;
import com.example.demo.models.NUEVO_CURSOS;
import com.example.demo.models.NUEVO_PROFESORES;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepo extends JpaRepository<NUEVO_CURSOS, String> {
    // El método debe aceptar un objeto NUEVO_PROFESORES
    List<NUEVO_CURSOS> findByProfesor(NUEVO_PROFESORES profesor);
}