package com.saberpro.repository;

import com.saberpro.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findByCedula(String cedula);
    Optional<Docente> findByEmail(String email);
    List<Docente> findByFacultad(String facultad);
    List<Docente> findByActivo(boolean activo);
}
