package com.example.demo.repositories;

import com.example.demo.models.NUEVO_PROFESORES;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepo extends JpaRepository<NUEVO_PROFESORES, String> {
}

