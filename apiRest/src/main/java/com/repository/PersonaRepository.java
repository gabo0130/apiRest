package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}

