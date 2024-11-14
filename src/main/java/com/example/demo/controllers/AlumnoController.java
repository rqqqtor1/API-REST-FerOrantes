package com.example.demo.controllers;

import com.example.demo.models.NUEVO_ALUMNOS;
import com.example.demo.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<NUEVO_ALUMNOS> getAllAlumnos() {
        return alumnoService.getAllAlumnos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NUEVO_ALUMNOS> getAlumnoById(@PathVariable Long id) {
        Optional<NUEVO_ALUMNOS> alumno = alumnoService.getAlumnoById(id);
        return alumno.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public NUEVO_ALUMNOS createAlumno(@RequestBody NUEVO_ALUMNOS alumno) {
        return alumnoService.createAlumno(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NUEVO_ALUMNOS> updateAlumno(@PathVariable Long id, @RequestBody NUEVO_ALUMNOS alumnoDetails) {
        Optional<NUEVO_ALUMNOS> updatedAlumno = alumnoService.updateAlumno(id, alumnoDetails);
        return updatedAlumno.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        boolean deleted = alumnoService.deleteAlumno(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/grado/{grado}")
    public List<NUEVO_ALUMNOS> getAlumnosPorGrado(@PathVariable String grado) {
        return alumnoService.getAlumnosPorGrado(grado);
    }
}