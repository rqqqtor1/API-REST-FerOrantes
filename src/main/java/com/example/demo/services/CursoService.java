package com.example.demo.services;

import com.example.demo.models.NUEVO_CURSOS;
import com.example.demo.models.NUEVO_PROFESORES;
import com.example.demo.repositories.CursoRepo;
import com.example.demo.repositories.ProfesorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private ProfesorRepo profesorRepo;

    public Optional<NUEVO_CURSOS> getCursoById(String cursoId) {
        return cursoRepo.findById(cursoId);
    }

    public List<NUEVO_CURSOS> getCursosPorProfesor(String profesorId) {
        Optional<NUEVO_PROFESORES> profesorOpt = profesorRepo.findById(profesorId);
        if (profesorOpt.isPresent()) {
            return cursoRepo.findByProfesor(profesorOpt.get()); // Pasa el objeto NUEVO_PROFESORES en lugar del String
        } else {
            // Retorna una lista vacía o lanza una excepción si es necesario
            return List.of();
        }
    }

    public Optional<NUEVO_CURSOS> asignarProfesor(String cursoId, String profesorId) {
        Optional<NUEVO_CURSOS> cursoOpt = cursoRepo.findById(cursoId);
        Optional<NUEVO_PROFESORES> profesorOpt = profesorRepo.findById(profesorId);

        if (cursoOpt.isPresent() && profesorOpt.isPresent()) {
            NUEVO_CURSOS curso = cursoOpt.get();
            curso.setProfesor(profesorOpt.get());
            return Optional.of(cursoRepo.save(curso));
        } else {
            return Optional.empty();
        }
    }
}