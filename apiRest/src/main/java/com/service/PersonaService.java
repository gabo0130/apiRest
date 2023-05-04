package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entity.Persona;
import com.repository.PersonaRepository;
import java.util.List;

@Service
public class PersonaService {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    public List<Persona> obtenerTodasLasPersonas() throws Exception {
        try {
            return personaRepository.findAll();
        } catch(Exception ex) {
            throw new Exception("Error al obtener las personas", ex);
        }
    }
    
    public Persona crearPersona(Persona persona) throws Exception {
        try {
            return personaRepository.save(persona);
        } catch(Exception ex) {
            throw new Exception("Error al crear la persona", ex);
        }
    }
}