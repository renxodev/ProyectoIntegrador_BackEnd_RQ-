/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.raq.Service;

import com.portfolio.raq.Entity.Skill;
import com.portfolio.raq.Repository.ISkillRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author renxoproduxer
 */
@Service
public class ImpSkillService{
    @Autowired
  
    ISkillRepository iskillRepository;

      public Optional<Skill> getByNombre(String nombre){
        return iskillRepository.findByNombre(nombre);
    }
      
    
    public List<Skill> list() {
        return iskillRepository.findAll();
    }


    public void save(Skill skill) {
        iskillRepository.save(skill);
    }

    public void delete(long id) {
        iskillRepository.deleteById(id);
    }
    
    public Skill find(long id) {
        Skill skill = iskillRepository.findById(id).orElse(null);
        return skill;
    }
    public boolean existsById(long id){
        return iskillRepository.existsById(id);
    }
     public Optional<Skill> getOne(long id){
        return iskillRepository.findById(id);
    }
         public boolean existsByNombre(String nombre){
        return iskillRepository.existsByNombre(nombre);
    }
}
