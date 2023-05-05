package com.controller;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Cuenta;
import com.entity.Movimiento;
import com.service.CuentaService;
import com.service.MovimientoService;
import com.repository.CuentaRepository;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.obtenerMovimientoPorId(id);
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping("/")
    public ResponseEntity<Movimiento> crearMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento movimientoCreado = movimientoService.crearMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoCreado);
    }


    @PostMapping("/{numeroCuenta}")
    public ResponseEntity<String> realizarMovimiento(@PathVariable int numeroCuenta,@RequestBody Movimiento movimiento) {

        Cuenta cuenta = cuentaService.buscarPorNumero(numeroCuenta);
        if (cuenta!=null) {
            movimiento.setCuenta(cuenta);
            movimiento.setSaldoInicial(cuenta.getSaldo());
            movimiento.setFecha(LocalDate.now());

            if (movimiento.getTipoMovimiento().equals("RETIRO")) {
                if (cuenta.getSaldo() >= movimiento.getValor()) {
                    movimientoService.crearMovimiento(movimiento);
                    cuenta.setSaldo(cuenta.getSaldo() - movimiento.getValor());
                    cuentaRepository.save(cuenta);
                    return ResponseEntity.ok("Movimiento realizado exitosamente.");
                } else {
                    return ResponseEntity.badRequest().body("La cuenta no tiene suficiente saldo.");
                }
            } else {
                movimientoService.crearMovimiento(movimiento);
                cuenta.setSaldo(cuenta.getSaldo() +  movimiento.getValor());
                cuentaRepository.save(cuenta);
                return ResponseEntity.ok("Movimiento realizado exitosamente.");
            }
        } else {
            return ResponseEntity.badRequest().body("La cuenta no existe.");
        }
    }
}

