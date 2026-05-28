package com.saberpro.service;

import com.saberpro.model.Beneficio;
import com.saberpro.model.Beneficio.TipoPrograma;
import com.saberpro.model.ResultadoSaberPro;
import com.saberpro.repository.BeneficioRepository;
import com.saberpro.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepo;

    public Optional<ResultadoSaberPro> buscarPorEstudianteId(Long id) {
        return resultadoRepo.findByEstudianteId(id);
    }

    public Optional<ResultadoSaberPro> buscarPorRegistro(String registro) {
        return resultadoRepo.findByEstudianteNumeroRegistro(registro);
    }

    public List<ResultadoSaberPro> listarOrdenados() {
        return resultadoRepo.findAllOrderByPuntajeDesc();
    }

    public ResultadoSaberPro guardar(ResultadoSaberPro resultado) {
        return resultadoRepo.save(resultado);
    }

    public Double promedioTecnologia() {
        Double avg = resultadoRepo.promedioTecnologia();
        return avg != null ? Math.round(avg * 100.0) / 100.0 : 0.0;
    }

    public Double promedioProfesional() {
        Double avg = resultadoRepo.promedioProfesional();
        return avg != null ? Math.round(avg * 100.0) / 100.0 : 0.0;
    }
}
