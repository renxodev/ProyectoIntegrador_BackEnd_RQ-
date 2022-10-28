package com.portfolio.raq.Interface;

import com.portfolio.raq.Entity.Estudios;
import java.util.List;

public interface IEstudiosService {
       
    public List<Estudios> getEstudios();

  //Guardar un Objeto de tipo Estudios
    public void saveEstudios(Estudios estudios);

    //Eliminar un objeto buscado por Id
    public void deleteEstudios(Long id);

    //Buscar una Estudios
    public Estudios findEstudios(Long id);
}
