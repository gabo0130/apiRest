package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}