package com.portfolio.raq.Interface;

import com.portfolio.raq.Entity.ExperienciaLaboral;
import java.util.List;

public interface IExperienciaLaboralService {
       
    public List<ExperienciaLaboral> getExperienciaLaboral();

  //Guardar un Objeto de tipo ExperienciaLaboral
    public void saveExperienciaLaboral(ExperienciaLaboral experiencialaboral);

    //Eliminar un objeto buscado por Id
    public void deleteExperienciaLaboral(Long id);

    //Buscar una ExperienciaLaboral
    public ExperienciaLaboral findExperienciaLaboral(Long id);
}
