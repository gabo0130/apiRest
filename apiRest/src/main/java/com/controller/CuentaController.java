package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Cliente;
import com.entity.Cuenta;
import com.entity.Movimiento;
import com.request.AsociarCuentaAClienteRequest;
import com.service.ClienteService;
import com.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private ClienteService clienteService;
    

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> obtenerCuentaPorNumero(@PathVariable long numeroCuenta) {
        Cuenta cuenta = cuentaService.buscarPorNumero(numeroCuenta);
        return ResponseEntity.ok(cuenta);
    }

    //@PostMapping("/")
    //public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
      //  Cuenta cuentaCreada = cuentaService.crearCuenta(cuenta);
        //return ResponseEntity.status(HttpStatus.CREATED).body(cuentaCreada);
   // }

    @GetMapping("/{numeroCuenta}/movimientos")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorNumeroCuenta(@PathVariable Long numeroCuenta) {
        List<Movimiento> movimientos = cuentaService.buscarMovimientosPorNumeroCuenta(numeroCuenta);
        return ResponseEntity.ok(movimientos);
    }

    @PostMapping("/")
    public ResponseEntity<String> asociarCuentaACliente(@RequestBody AsociarCuentaAClienteRequest request) {
        String idCliente = clienteService.buscarIdPorNombre(request.getCliente());
        System.out.println("idCliente: " + idCliente);
        if (idCliente==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
        Cliente cliente = new Cliente();
        cliente.setId(Integer.parseInt(idCliente));
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(request.getNumeroCuenta());
        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setEstado(request.getEstado());
        cuenta.setSaldo(request.getSaldo());
        cuenta.setCliente(cliente);
        cuentaService.crearCuenta(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cuenta creada al cliente " + request.getCliente() + " con éxito");
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long numeroCuenta, @RequestBody Cuenta cuenta) {
        Cuenta cuentaEncontrada = cuentaService.buscarPorNumero(numeroCuenta);
        if (cuentaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        cuenta.setId(cuentaEncontrada.getId());
        cuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuenta.setTipoCuenta(cuenta.getTipoCuenta());
        cuenta.setEstado(cuenta.getEstado());
        cuenta.setSaldo(cuenta.getSaldo());
        cuenta.setCliente(cuentaEncontrada.getCliente());

        Cuenta cuentaActualizada = cuentaService.actualizarCuenta(cuenta);
        return ResponseEntity.ok(cuentaActualizada);
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> eliminarCuenta(@PathVariable Long numeroCuenta) {
        Cuenta cuentaEncontrada = cuentaService.buscarPorNumero(numeroCuenta);
        if (cuentaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        cuentaService.eliminarCuenta(cuentaEncontrada.getId());
        return ResponseEntity.ok(cuentaEncontrada);
    }

    
}