/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.raq.Service;

import com.portfolio.raq.Entity.Estudios;
import com.portfolio.raq.Repository.IEstudiosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author renxoproduxer
 */
@Service
public class ImpEstudiosService{

    @Autowired
    IEstudiosRepository iEstudiosRepository;

    public List<Estudios> list() {
        return iEstudiosRepository.findAll();

    }

    public Optional<Estudios> getOne(long id){
        return iEstudiosRepository.findById(id);
    }

    public Optional<Estudios>getByNombreE(String nombreE){
        return iEstudiosRepository.findByNombreE(nombreE);
    }


    public void saveEstudios(Estudios estudios) {
        iEstudiosRepository.save(estudios);
    }

    public void deleteEstudios(Long id) {
        iEstudiosRepository.deleteById(id);
    }

    public boolean existById(Long id) {
        return iEstudiosRepository.existsById(id);
    }

    public boolean existsByNombreE(String nombreE){
     return iEstudiosRepository.existsByNombreE(nombreE);
    }
}
