package com.example.demo.controllers;

import com.example.demo.models.NUEVO_INSCRIPCIONES;
import com.example.demo.services.CursoAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
public class AlumnoCursoController {

    @Autowired
    private CursoAlumnoService cursoAlumnoService;

    @PutMapping("/{alumnoId}/curso/{cursoId}/actualizar")
    public ResponseEntity<String> actualizarCalificacion(@PathVariable Long alumnoId, @PathVariable String cursoId, @RequestBody double calificacion) {
        return cursoAlumnoService.actualizarCalificacion(alumnoId, cursoId, calificacion)
                .map(inscripcion -> ResponseEntity.ok("Calificación actualizada correctamente."))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{alumnoId}/cursos")
    public List<NUEVO_INSCRIPCIONES> getCalificacionesPorAlumno(@PathVariable Long alumnoId) {
        return cursoAlumnoService.getCalificacionesPorAlumno(alumnoId);
    }

    @GetMapping("/{alumnoId}/promedio")
    public double getPromedioCalificaciones(@PathVariable Long alumnoId) {
        return cursoAlumnoService.getPromedioCalificaciones(alumnoId);
    }
}