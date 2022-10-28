
package com.portfolio.raq.Repository;

import com.portfolio.raq.Entity.ExperienciaLaboral;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral,Long>{
public Optional<ExperienciaLaboral> findByNombreEx(String nombreEx);
public boolean existsByNombreEx(String nombreEx);
}
