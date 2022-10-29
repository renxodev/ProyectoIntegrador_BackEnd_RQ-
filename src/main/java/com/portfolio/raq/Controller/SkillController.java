/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.raq.Controller;

import com.portfolio.raq.Entity.Skill;
import com.portfolio.raq.Security.Controller.Mensaje;
import com.portfolio.raq.Security.Dto.dtoSkill;
import com.portfolio.raq.Service.ImpSkillService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/skills")
@CrossOrigin (origins = "*")
public class SkillController {
    
    @Autowired
    ImpSkillService impskillService;

    @GetMapping("/list")
    public List<Skill> getSkill() {
        return impskillService.list();
    }

    
     @PostMapping("/create")
     public ResponseEntity<?> create(@RequestBody dtoSkill dtoskill){
        if(StringUtils.isBlank(dtoskill.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (impskillService.existsByNombre(dtoskill.getNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Skill skill = new Skill(
        dtoskill.getNombre(), dtoskill.getPorcentaje(), dtoskill.getImg()
        );
        impskillService.save(skill);
        return new ResponseEntity(new Mensaje("Skill creada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Long id) {
        impskillService.delete(id);
        return "Skill fue eliminada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkill dtoskill) {
        //Validamos si existe el ID
        if (!impskillService.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de skills
        if (impskillService.existsByNombre(dtoskill.getNombre()) && impskillService.getByNombre(dtoskill.getNombre()).get()
                .getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoskill.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Skill skill = impskillService.getOne(id).get();
        skill.setNombre(dtoskill.getNombre());
        skill.setPorcentaje(dtoskill.getPorcentaje());

        impskillService.save(skill);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);

    }

          @GetMapping(("/detail/{id}"))
    public ResponseEntity<Skill> getById(@PathVariable("id")long id){
        if(!impskillService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        Skill skill = impskillService.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    } 
}
