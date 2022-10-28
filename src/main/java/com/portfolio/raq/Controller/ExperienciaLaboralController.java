/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.raq.Controller;


import org.apache.commons.lang3.StringUtils;
import com.portfolio.raq.Entity.ExperienciaLaboral;
import com.portfolio.raq.Security.Controller.Mensaje;
import com.portfolio.raq.Security.Dto.dtoExperiencia;
import com.portfolio.raq.Service.ImpExperienciaLaboralService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author renxoproduxer
 */
@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = "*")

public class ExperienciaLaboralController {

    @Autowired
    ImpExperienciaLaboralService impExperienciaLaboralService;
    
    @GetMapping("/list")
    public ResponseEntity <List<ExperienciaLaboral>> list() {
        List<ExperienciaLaboral> list = impExperienciaLaboralService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity <?> create(@RequestBody dtoExperiencia dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreEx()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        if(impExperienciaLaboralService.existsByNombreEx(dtoexp.getNombreEx()))
            return new ResponseEntity(new Mensaje("Esa Experiencia existe"), HttpStatus.BAD_REQUEST);
        ExperienciaLaboral experiencia = new ExperienciaLaboral (dtoexp.getNombreEx(), dtoexp.getDescripcionEx());
        impExperienciaLaboralService.save(experiencia);
        return new ResponseEntity(new Mensaje("ExperienciaAgregada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id")Long id){
        if (!impExperienciaLaboralService.existById(id))
        return new ResponseEntity(new Mensaje("La experiencia no existe"), 
                    HttpStatus.BAD_REQUEST);
                    
            impExperienciaLaboralService.delete(id);
            return new ResponseEntity(new Mensaje("La experiencia fue borrada"),HttpStatus.OK);
    }
  
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id")Long id,
            @RequestBody dtoExperiencia dtoexp) {
        if(!impExperienciaLaboralService.existById(id))
            return new ResponseEntity(new Mensaje("La experiencia no existe"), 
                    HttpStatus.BAD_REQUEST);
        if(impExperienciaLaboralService.existsByNombreEx(dtoexp.getNombreEx())&& impExperienciaLaboralService.getByNombreEx(dtoexp.getNombreEx()).get().getId() != id)
        return new ResponseEntity
        (new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(dtoexp.getNombreEx()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),
                    HttpStatus.BAD_REQUEST);
        ExperienciaLaboral experiencia = impExperienciaLaboralService.getOne(id).get();
        experiencia.setNombreEx(dtoexp.getNombreEx());
        experiencia.setDescripcionEx(dtoexp.getDescripcionEx());
        
        impExperienciaLaboralService.save(experiencia);
        return new ResponseEntity(new Mensaje("La experiencia fue guardada con exito"),HttpStatus.OK);       
    }
      @GetMapping(("/detail/{id}"))
    public ResponseEntity<ExperienciaLaboral> getById(@PathVariable("id")long id){
        if(!impExperienciaLaboralService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        ExperienciaLaboral experiencia = impExperienciaLaboralService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
}
