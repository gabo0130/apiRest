package com.service;

import java.util.NoSuchElementException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Cliente;
import com.entity.Cuenta;
import com.repository.ClienteRepository;
import com.repository.CuentaRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
    }

    public String buscarIdPorNombre(String nombre) {
        return clienteRepository.buscarIdPorNombre(nombre);
    }
    public List<Cuenta> buscarCuentasPorIdCliente(int id) {
        return cuentaRepository.buscarPorClienteId(id);
    }

    

   
}