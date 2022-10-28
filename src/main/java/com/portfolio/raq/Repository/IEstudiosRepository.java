package com.portfolio.raq.Repository;

import com.portfolio.raq.Entity.Estudios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudiosRepository extends JpaRepository<Estudios,Long>{
public Optional<Estudios> findByNombreE(String nombreE);
public boolean existsByNombreE(String nombreE);
}
