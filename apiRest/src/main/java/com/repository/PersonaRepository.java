package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
}

