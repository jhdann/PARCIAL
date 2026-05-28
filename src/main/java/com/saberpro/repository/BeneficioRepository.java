package com.saberpro.repository;

import com.saberpro.model.Beneficio;
import com.saberpro.model.Beneficio.TipoPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {
    List<Beneficio> findByActivoTrue();
    List<Beneficio> findByProgramaAplicableAndActivoTrue(TipoPrograma tipoPrograma);
    List<Beneficio> findByNivelRequeridoAndActivoTrue(String nivel);
}
