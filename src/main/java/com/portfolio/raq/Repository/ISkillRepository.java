package com.portfolio.raq.Repository;

import com.portfolio.raq.Entity.Skill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISkillRepository extends JpaRepository<Skill,Long>{
public Optional<Skill> findByNombre(String nombre);
public boolean existsByNombre(String nombre);
public Optional<Skill> findById(Long id);
}
