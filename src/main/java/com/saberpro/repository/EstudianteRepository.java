package com.saberpro.repository;

import com.saberpro.model.Estudiante;
import com.saberpro.model.Estudiante.TipoPrograma;
import com.saberpro.model.Estudiante.EstadoSaberPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByNumeroDocumento(String numeroDocumento);
    Optional<Estudiante> findByNumeroRegistro(String numeroRegistro);
    Optional<Estudiante> findByUsuarioId(Long usuarioId);
    Optional<Estudiante> findByCorreoElectronico(String correo);

    List<Estudiante> findByTipoPrograma(TipoPrograma tipoPrograma);
    List<Estudiante> findByEstado(EstadoSaberPro estado);
    List<Estudiante> findByTipoProgramaAndEstado(TipoPrograma tipoPrograma, EstadoSaberPro estado);

    List<Estudiante> findByPrimerApellidoContainingIgnoreCaseOrPrimerNombreContainingIgnoreCase(
            String apellido, String nombre);

    long countByTipoPrograma(TipoPrograma tipoPrograma);
    long countByEstado(EstadoSaberPro estado);

    @Query("SELECT e FROM Estudiante e WHERE e.resultado IS NOT NULL ORDER BY e.resultado.puntajeTotal DESC")
    List<Estudiante> findAllConResultadoOrdenados();
}
