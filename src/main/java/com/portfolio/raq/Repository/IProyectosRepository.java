package com.portfolio.raq.Repository;

import com.portfolio.raq.Entity.Proyectos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProyectosRepository extends JpaRepository<Proyectos,Long>{
public Optional<Proyectos> findByNombre(String nombre);
public boolean existsByNombre(String nombre);
}
