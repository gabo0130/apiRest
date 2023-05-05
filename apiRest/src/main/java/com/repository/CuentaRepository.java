package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    @Query("SELECT c FROM Cuenta c WHERE c.numeroCuenta = :numeroCuenta")
    Cuenta buscarPorNumero(@Param("numeroCuenta") int numeroCuenta);

    @Query("SELECT c FROM Cuenta c WHERE c.cliente.id = :clienteId")
    List<Cuenta> buscarPorClienteId(@Param("clienteId") int clienteId);
}
