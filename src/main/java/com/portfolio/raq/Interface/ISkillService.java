package com.portfolio.raq.Interface;

import com.portfolio.raq.Entity.Skill;
import java.util.List;

public interface ISkillService {
       
    public List<Skill> getSkill();

  //Guardar un Objeto de tipo Skill
    public void saveSkill(Skill skill);

    //Eliminar un objeto buscado por Id
    public void deleteSkill(Long id);

    //Buscar una skill
    public Skill findSkill(Long id);

}
