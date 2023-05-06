package com.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Cuenta;
import com.entity.Movimiento;
import com.repository.MovimientoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<Movimiento> obtenerTodosLosMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento crearMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public Movimiento actualizarMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public void eliminarMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    public Movimiento obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Movimiento no encontrado"));
    }

    public List<Movimiento> buscarPorCuentaYRangoDeFechas(long cuentaId, LocalDate fechaInicio, LocalDate fechaFin){
        return movimientoRepository.buscarPorCuentaYRangoDeFechas(cuentaId,  fechaInicio,  fechaFin);
    }

    public long obtenerTotalDebitosDelDia(long cuenta, LocalDate fechaHoy){
        return movimientoRepository.obtenerTotalDebitosDelDia(cuenta, fechaHoy, fechaHoy);
    }

    @Transactional
    public void eliminarMovimientosPorNumeroCuenta(Long idCuenta) {
        movimientoRepository.eliminarMovimientosPorNumeroCuenta(idCuenta);
    }


}
