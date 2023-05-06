package com.controller;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
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
    public ResponseEntity<List<Cuenta>> obtenerCuentasPorIdCliente(@PathVariable long id) {
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

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarClienteCompleto(@PathVariable long id, @RequestBody Cliente clienteActualizado) {
        Cliente cliente = clienteService.obtenerClientePorId(id);

        if (cliente==null) {
            return ResponseEntity.notFound().build();
        }

        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setDireccion(clienteActualizado.getDireccion());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setGenero(clienteActualizado.getGenero());
        cliente.setEdad(clienteActualizado.getEdad());
        cliente.setIdentificacion(clienteActualizado.getIdentificacion());
        cliente.setContrasena(clienteActualizado.getContrasena());
        cliente.setEstado(clienteActualizado.getEstado());

        Cliente clienteActualizadoBD = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(clienteActualizadoBD);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> actualizarClienteParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Cliente cliente = clienteService.obtenerClientePorId(id);

        if (cliente==null) {
            return ResponseEntity.notFound().build();
        } 

        campos.forEach((nombreCampo, valorCampo) -> {
            Field campo = ReflectionUtils.findField(Cliente.class, nombreCampo);
            campo.setAccessible(true);
            ReflectionUtils.setField(campo, cliente, valorCampo);
        });
            
        Cliente clienteActualizadoBD = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(clienteActualizadoBD);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente==null) {
            return ResponseEntity.notFound().build();
        } 

        clienteService.eliminarCliente(cliente.getId());
        return ResponseEntity.noContent().build();
    }

    
}
