package com.example.demo.services;

import com.example.demo.models.NUEVO_INSCRIPCIONES;
import com.example.demo.repositories.Alumno_CursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoAlumnoService {

    @Autowired
    private Alumno_CursoRepo alumnoCursoRepo;

    public Optional<NUEVO_INSCRIPCIONES> actualizarCalificacion(Long alumnoId, String cursoId, double calificacion) {
        Optional<NUEVO_INSCRIPCIONES> inscripcionOpt = alumnoCursoRepo.findByAlumno_IdAndCurso_Nombre(alumnoId, cursoId);

        if (inscripcionOpt.isPresent()) {
            NUEVO_INSCRIPCIONES inscripcion = inscripcionOpt.get();
            inscripcion.setCalificacion(calificacion);
            return Optional.of(alumnoCursoRepo.save(inscripcion));
        }
        return Optional.empty();
    }

    public List<NUEVO_INSCRIPCIONES> getCalificacionesPorAlumno(Long alumnoId) {
        return alumnoCursoRepo.findByAlumno_Id(alumnoId); // Usa 'findByAlumno_Id' en lugar de 'findByIdAlumno'
    }

    public double getPromedioCalificaciones(Long alumnoId) {
        List<NUEVO_INSCRIPCIONES> calificaciones = alumnoCursoRepo.findByAlumno_Id(alumnoId); // Usa 'findByAlumno_Id'

        if (calificaciones.isEmpty()) {
            return 0.0;
        }

        return calificaciones.stream()
                .collect(Collectors.averagingDouble(NUEVO_INSCRIPCIONES::getCalificacion));
    }
}
