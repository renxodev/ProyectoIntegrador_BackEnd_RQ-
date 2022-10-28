package com.portfolio.raq.Interface;

import com.portfolio.raq.Entity.Persona;
import java.util.List;

public interface IPersonaService {
   
    public List<Persona> getPersona();

  //Guardar un Objeto de tipo Persona
    public void savePersona(Persona persona);

    //Eliminar un objeto buscado por Id
    public void deletePersona(Long id);

    //Buscar una persona
    public Persona findPersona(Long id);
}
