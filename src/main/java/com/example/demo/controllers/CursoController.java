package com.example.demo.controllers;

import com.example.demo.models.NUEVO_ALUMNOS;
import com.example.demo.models.NUEVO_CURSOS;
import com.example.demo.models.NUEVO_INSCRIPCIONES;
import com.example.demo.models.NUEVO_PROFESORES;
import com.example.demo.repositories.AlumnoRepo;
import com.example.demo.repositories.Alumno_CursoRepo;
import com.example.demo.repositories.CursoRepo;
import com.example.demo.repositories.ProfesorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private AlumnoRepo alumnoRepo;

    @Autowired
    private Alumno_CursoRepo alumnoCursoRepo;

    @Autowired
    private ProfesorRepo profesorRepo;

    @PostMapping("/{cursoId}/inscribir/{alumnoId}")
    public ResponseEntity<String> inscribirAlumno(@PathVariable String cursoId, @PathVariable Long alumnoId) {
        Optional<NUEVO_CURSOS> cursoOpt = cursoRepo.findById(cursoId);
        Optional<NUEVO_ALUMNOS> alumnoOpt = alumnoRepo.findById(alumnoId);

        if (cursoOpt.isPresent() && alumnoOpt.isPresent()) {
            NUEVO_INSCRIPCIONES inscripcion = new NUEVO_INSCRIPCIONES();
            inscripcion.setCurso(cursoOpt.get());
            inscripcion.setAlumno(alumnoOpt.get());
            alumnoCursoRepo.save(inscripcion);
            return ResponseEntity.ok("Alumno inscrito correctamente al curso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{cursoId}/alumnos")
    public List<NUEVO_ALUMNOS> getAlumnosPorCurso(@PathVariable String cursoId) {
        return alumnoCursoRepo.findByCurso_Nombre(cursoId).stream() // O utiliza findByCurso_Id si estás buscando por ID del curso
                .map(NUEVO_INSCRIPCIONES::getAlumno)
                .collect(Collectors.toList());
    }

    @PutMapping("/{cursoId}/asignarProfesor/{profesorId}")
    public ResponseEntity<String> asignarProfesor(@PathVariable String cursoId, @PathVariable String profesorId) {
        Optional<NUEVO_CURSOS> cursoOpt = cursoRepo.findById(cursoId);
        Optional<NUEVO_PROFESORES> profesorOpt = profesorRepo.findById(profesorId);

        if (cursoOpt.isPresent() && profesorOpt.isPresent()) {
            NUEVO_CURSOS curso = cursoOpt.get();
            curso.setProfesor(profesorOpt.get());
            cursoRepo.save(curso);
            return ResponseEntity.ok("Profesor asignado al curso correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<List<NUEVO_CURSOS>> getCursosPorProfesor(@PathVariable String profesorId) {
        Optional<NUEVO_PROFESORES> profesorOpt = profesorRepo.findById(profesorId);
        if (profesorOpt.isPresent()) {
            List<NUEVO_CURSOS> cursos = cursoRepo.findByProfesor(profesorOpt.get());
            return ResponseEntity.ok(cursos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}