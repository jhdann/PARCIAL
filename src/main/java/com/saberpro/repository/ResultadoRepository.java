package com.saberpro.repository;

import com.saberpro.model.ResultadoSaberPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoSaberPro, Long> {

    Optional<ResultadoSaberPro> findByEstudianteId(Long estudianteId);
    Optional<ResultadoSaberPro> findByEstudianteNumeroRegistro(String numeroRegistro);

    @Query("SELECT AVG(r.puntajeTotal) FROM ResultadoSaberPro r WHERE r.estudiante.tipoPrograma = 'TECNOLOGIA'")
    Double promedioTecnologia();

    @Query("SELECT AVG(r.puntajeTotal) FROM ResultadoSaberPro r WHERE r.estudiante.tipoPrograma = 'PROFESIONAL'")
    Double promedioProfesional();

    @Query("SELECT r FROM ResultadoSaberPro r ORDER BY r.puntajeTotal DESC")
    List<ResultadoSaberPro> findAllOrderByPuntajeDesc();
}
