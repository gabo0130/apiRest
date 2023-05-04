package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
