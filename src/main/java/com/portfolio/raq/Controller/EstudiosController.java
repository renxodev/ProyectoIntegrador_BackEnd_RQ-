/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.raq.Controller;

import com.portfolio.raq.Entity.Estudios;
import com.portfolio.raq.Security.Controller.Mensaje;
import com.portfolio.raq.Security.Dto.dtoEstudios;
import com.portfolio.raq.Service.ImpEstudiosService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/estudios")
@CrossOrigin (origins = "*")
public class EstudiosController {
    
  @Autowired
    ImpEstudiosService impEstudiosService;

    @GetMapping(("/list"))
    public ResponseEntity<List<Estudios>> list(){
        List<Estudios> list = impEstudiosService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping(("/detail/{id}"))
    public ResponseEntity<Estudios> getById(@PathVariable("id")long id){
        if(!impEstudiosService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        Estudios estudios = impEstudiosService.getOne(id).get();
        return new ResponseEntity(estudios, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long id){
        if (!impEstudiosService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"),HttpStatus.NOT_FOUND);
        }
        impEstudiosService.deleteEstudios(id);
        return new ResponseEntity(new Mensaje("Fue borrado correctamente."), HttpStatus.OK);
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEstudios dtoestudios){
        if(StringUtils.isBlank(dtoestudios.getNombreE())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (impEstudiosService.existsByNombreE(dtoestudios.getNombreE())){
            return new ResponseEntity(new Mensaje("EseNombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Estudios estudios = new Estudios(
        dtoestudios.getNombreE(), dtoestudios.getDescripcionE()
        );
        impEstudiosService.saveEstudios(estudios);
        return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") long id, @RequestBody dtoEstudios dtoestudios){
        if(!impEstudiosService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        }
        if(impEstudiosService.existsByNombreE(dtoestudios.getNombreE())&& impEstudiosService.getByNombreE(dtoestudios.getNombreE()).get().getId() != id){
         return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);   
        }
        if (StringUtils.isBlank(dtoestudios.getNombreE())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        Estudios estudios= impEstudiosService.getOne(id).get();
        
        estudios.setNombreE(dtoestudios.getNombreE());
        estudios.setDescripcionE(dtoestudios.getDescripcionE());
        
        impEstudiosService.saveEstudios(estudios);
        
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }
}
