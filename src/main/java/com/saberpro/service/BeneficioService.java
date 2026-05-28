package com.saberpro.service;

import com.saberpro.model.Beneficio;
import com.saberpro.model.Beneficio.TipoPrograma;
import com.saberpro.repository.BeneficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BeneficioService {

    @Autowired
    private BeneficioRepository beneficioRepo;

    public List<Beneficio> listarActivos() {
        return beneficioRepo.findByActivoTrue();
    }

    public List<Beneficio> listarPorPrograma(TipoPrograma tipo) {
        List<Beneficio> lista = beneficioRepo.findByProgramaAplicableAndActivoTrue(tipo);
        lista.addAll(beneficioRepo.findByProgramaAplicableAndActivoTrue(TipoPrograma.AMBOS));
        return lista;
    }

    public Beneficio guardar(Beneficio beneficio) {
        return beneficioRepo.save(beneficio);
    }

    public void eliminar(Long id) {
        beneficioRepo.findById(id).ifPresent(b -> {
            b.setActivo(false);
            beneficioRepo.save(b);
        });
    }
}
