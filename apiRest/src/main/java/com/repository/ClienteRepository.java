package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT p.id FROM Persona p WHERE p.nombre = :nombre")
    String buscarIdPorNombre(@Param("nombre") String nombre);
}
