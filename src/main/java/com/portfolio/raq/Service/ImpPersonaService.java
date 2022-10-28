package com.portfolio.raq.Service;

import com.portfolio.raq.Entity.Persona;
import com.portfolio.raq.Interface.IPersonaService;
import com.portfolio.raq.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPersonaService implements IPersonaService{
    
    @Autowired IPersonaRepository ipersonaRepository;
    @Override
    public List<Persona> getPersona() {
    List<Persona> persona = ipersonaRepository.findAll();
    return persona;
    }

    @Override
    public void savePersona(Persona persona) {
        ipersonaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        ipersonaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
          Persona persona = ipersonaRepository.findById(id).orElse(null);
          return persona;
}
    public boolean existsById(Long id){
        return ipersonaRepository.existsById(id);
    }
     public Optional<Persona> getOne(long id){
        return ipersonaRepository.findById(id);
    }
}