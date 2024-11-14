package com.example.demo.services;
import com.example.demo.models.NUEVO_ALUMNOS;
import com.example.demo.repositories.AlumnoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepo alumnoRepo;

    public List<NUEVO_ALUMNOS> getAllAlumnos() {
        return alumnoRepo.findAll();
    }

    public Optional<NUEVO_ALUMNOS> getAlumnoById(Long id) {
        return alumnoRepo.findById(id);
    }

    public NUEVO_ALUMNOS createAlumno(NUEVO_ALUMNOS alumno) {
        return alumnoRepo.save(alumno);
    }

    public Optional<NUEVO_ALUMNOS> updateAlumno(Long id, NUEVO_ALUMNOS alumnoDetails) {
        return alumnoRepo.findById(id).map(alumno -> {
            alumno.setNombre(alumnoDetails.getNombre());
            alumno.setApellido(alumnoDetails.getApellido());
            alumno.setEdad(alumnoDetails.getEdad());
            alumno.setGrado(alumnoDetails.getGrado());
            return alumnoRepo.save(alumno);
        });
    }

    public boolean deleteAlumno(Long id) {
        return alumnoRepo.findById(id).map(alumno -> {
            alumnoRepo.delete(alumno);
            return true;
        }).orElse(false);
    }

    public List<NUEVO_ALUMNOS> getAlumnosPorGrado(String grado) {
        return alumnoRepo.findByGrado(grado);
    }
}
