package com.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entity.Cuenta;
import com.entity.Movimiento;
import com.repository.CuentaRepository;
import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> obtenerTodasLasCuentas() throws Exception {
        try {
            return cuentaRepository.findAll();
        } catch(Exception ex) {
            throw new Exception("Error al obtener las cuentas", ex);
        }
    }


    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta actualizarCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cuenta no encontrada"));
    }


    public Cuenta buscarPorNumero(int numeroCuenta) {
        return cuentaRepository.buscarPorNumero(numeroCuenta);
    }


    public List<Movimiento> buscarMovimientosPorNumeroCuenta(Long numeroCuenta) {
        return null;
    }

    
}
