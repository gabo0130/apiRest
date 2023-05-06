package com.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    
    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.id = :cuentaId AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> buscarPorCuentaYRangoDeFechas(@Param("cuentaId") long cuentaId, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
    
    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM Movimiento m WHERE m.cuenta.cliente.id = :clienteId AND m.fecha BETWEEN :fechaInicio AND :fechaFin AND m.tipoMovimiento = 'DEBITO'")
    long obtenerTotalDebitosDelDia(@Param("clienteId") Long clienteId, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
    
    @Modifying
    @Query("DELETE FROM Movimiento m WHERE m.cuenta.id = :cuentaId")
    void eliminarMovimientosPorNumeroCuenta(@Param("cuentaId") long cuentaId);
}