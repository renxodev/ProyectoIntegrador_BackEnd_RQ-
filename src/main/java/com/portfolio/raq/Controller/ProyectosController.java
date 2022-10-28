package com.portfolio.raq.Controller;

import com.portfolio.raq.Entity.Proyectos;
import com.portfolio.raq.Security.Controller.Mensaje;
import com.portfolio.raq.Service.ImpProyectosService;
import java.util.List;
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



@RestController
@RequestMapping("/proyectos")
@CrossOrigin (origins = "*")
public class ProyectosController {
    
    @Autowired
    ImpProyectosService impproyectosService;
    
    @GetMapping("/list")
    public List<Proyectos> getProyectos() {
        return impproyectosService.getProyectos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createProyectos(@RequestBody Proyectos proyectos) {
        impproyectosService.saveProyectos(proyectos);
        return "Proyectos fue creada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteProyectos(@PathVariable Long id) {
        impproyectosService.deleteProyectos(id);
        return "Proyectos fue eliminada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public Proyectos editProyectos(@PathVariable("id")Long id,
        @RequestBody Proyectos proyectos) {
        proyectos.setId(id);
        impproyectosService.saveProyectos(proyectos);
        return proyectos;
    }

          @GetMapping(("/detail/{id}"))
    public ResponseEntity<Proyectos> getById(@PathVariable("id")long id){
        if(!impproyectosService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        Proyectos proyectos = impproyectosService.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
}
