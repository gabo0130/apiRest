package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Persona;
import com.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/")
    public List<Persona> obtenerTodasLasPersonas() throws Exception {
        return personaService.obtenerTodasLasPersonas();
    }
    
    @PostMapping("/")
    public ResponseEntity<?> crearPersona(@RequestBody Persona persona) throws Exception {
        Persona personaCreada = personaService.crearPersona(persona);
        return new ResponseEntity<>(personaCreada, HttpStatus.CREATED);
    }


}