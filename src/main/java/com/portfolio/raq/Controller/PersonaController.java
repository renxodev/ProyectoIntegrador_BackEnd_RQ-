/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.raq.Controller;

import com.portfolio.raq.Entity.Persona;
import com.portfolio.raq.Security.Controller.Mensaje;
import com.portfolio.raq.Service.ImpPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author renxoproduxer
 */
@RestController
@RequestMapping("/personas")
@CrossOrigin (origins = "*")
public class PersonaController {
    
    @Autowired
    ImpPersonaService imppersonaService;

    @GetMapping("/list")
    public List<Persona> getPersona() {
        return imppersonaService.getPersona();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createPersona(@RequestBody Persona persona) {
        imppersonaService.savePersona(persona);
        return "La persona fue creada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deletePersona(@PathVariable Long id) {
        imppersonaService.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public Persona editPersona(@PathVariable("id")Long id,
            @RequestBody Persona persona) {

         persona.setId(id);
        imppersonaService.savePersona(persona);
        return persona;
    }

      @GetMapping(("/detail/{id}"))
    public ResponseEntity<Persona> getById(@PathVariable("id")long id){
        if(!imppersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = imppersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
}
