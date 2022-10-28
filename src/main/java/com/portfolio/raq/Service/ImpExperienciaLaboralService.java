/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.raq.Service;


import com.portfolio.raq.Entity.ExperienciaLaboral;
import com.portfolio.raq.Repository.IExperienciaLaboralRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author renxoproduxer
 */
@Transactional
@Service
public class ImpExperienciaLaboralService{
    
    @Autowired
    IExperienciaLaboralRepository iexperienciaLaboralRepository;

    public List<ExperienciaLaboral> list() {
     return iexperienciaLaboralRepository.findAll();
    }

    public Optional<ExperienciaLaboral> getOne(long id){
        return iexperienciaLaboralRepository.findById(id);
    }

    public Optional<ExperienciaLaboral> getByNombreEx(String nombreEx){
        return iexperienciaLaboralRepository.findByNombreEx(nombreEx);
    }

    public void save(ExperienciaLaboral expe) {
        iexperienciaLaboralRepository.save(expe);
    }

    public void delete(Long id) {
        iexperienciaLaboralRepository.deleteById(id);
    }

    public boolean  existById(long id){
        return iexperienciaLaboralRepository.existsById(id);
    }

    public boolean  existsByNombreEx(String nombreEx){
        return iexperienciaLaboralRepository.existsByNombreEx(nombreEx);
    }
}
