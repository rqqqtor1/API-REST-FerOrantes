package com.example.demo.repositories;

import com.example.demo.models.NUEVO_ALUMNOS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepo extends JpaRepository<NUEVO_ALUMNOS, Long> {
    List<NUEVO_ALUMNOS> findByGrado(String grado);
}
