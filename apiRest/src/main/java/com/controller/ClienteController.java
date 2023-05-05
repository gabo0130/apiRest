package com.controller;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Cliente;
import com.entity.Cuenta;
import com.entity.Movimiento;
import com.service.ClienteService;
import com.service.MovimientoService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private  MovimientoService MovimientoService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente clienteCreado = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
    }

    @GetMapping("/{id}/cuentas")
    public ResponseEntity<List<Cuenta>> obtenerCuentasPorIdCliente(@PathVariable int id) {
        List<Cuenta> cuentas = clienteService.buscarCuentasPorIdCliente(id);
        return ResponseEntity.ok(cuentas);
    }


    @GetMapping("/movimientos")
    public ResponseEntity<List<Cuenta>> obtenerMovimientosDeClientePorRangoDeFechas(
            @RequestParam String nombre,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin
    ) {

        String idCliente = clienteService.buscarIdPorNombre(nombre);
        if (idCliente == null) {
            return ResponseEntity.notFound().build();
        }
        List<Cuenta> cuentas = clienteService.buscarCuentasPorIdCliente(Integer.parseInt(idCliente));
        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = MovimientoService.buscarPorCuentaYRangoDeFechas(1, LocalDate.parse(fechaInicio), LocalDate.parse(fechaFin));
            cuenta.setMovimientos(movimientos);
        }

        return ResponseEntity.ok(cuentas);
    }

    
}
