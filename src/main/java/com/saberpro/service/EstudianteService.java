package com.saberpro.service;

import com.saberpro.model.Estudiante;
import com.saberpro.model.Estudiante.EstadoSaberPro;
import com.saberpro.model.Estudiante.TipoPrograma;
import com.saberpro.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepo;

    public List<Estudiante> listarTodos() {
        return estudianteRepo.findAll();
    }

    public List<Estudiante> listarPorTipoPrograma(TipoPrograma tipo) {
        return estudianteRepo.findByTipoPrograma(tipo);
    }

    public Optional<Estudiante> buscarPorId(Long id) {
        return estudianteRepo.findById(id);
    }

    public Optional<Estudiante> buscarPorDocumento(String documento) {
        return estudianteRepo.findByNumeroDocumento(documento);
    }

    public Optional<Estudiante> buscarPorUsuarioId(Long usuarioId) {
        return estudianteRepo.findByUsuarioId(usuarioId);
    }

    public List<Estudiante> buscar(String termino) {
        return estudianteRepo
            .findByPrimerApellidoContainingIgnoreCaseOrPrimerNombreContainingIgnoreCase(termino, termino);
    }

    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepo.save(estudiante);
    }

    public void eliminar(Long id) {
        estudianteRepo.deleteById(id);
    }

    public void aprobar(Long id) {
        estudianteRepo.findById(id).ifPresent(e -> {
            e.setEstado(EstadoSaberPro.APROBADO);
            estudianteRepo.save(e);
        });
    }

    public void rechazar(Long id) {
        estudianteRepo.findById(id).ifPresent(e -> {
            e.setEstado(EstadoSaberPro.RECHAZADO);
            estudianteRepo.save(e);
        });
    }

    public long contarTecnologia() {
        return estudianteRepo.countByTipoPrograma(TipoPrograma.TECNOLOGIA);
    }

    public long contarProfesional() {
        return estudianteRepo.countByTipoPrograma(TipoPrograma.PROFESIONAL);
    }

    public long contarAprobados() {
        return estudianteRepo.countByEstado(EstadoSaberPro.APROBADO);
    }

    public long contarPendientes() {
        return estudianteRepo.countByEstado(EstadoSaberPro.PENDIENTE);
    }

    public List<Estudiante> conResultadosOrdenados() {
        return estudianteRepo.findAllConResultadoOrdenados();
    }
}
