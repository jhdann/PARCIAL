package com.saberpro.controller;

import com.saberpro.model.Estudiante;
import com.saberpro.model.ResultadoSaberPro;
import com.saberpro.service.BeneficioService;
import com.saberpro.service.EstudianteService;
import com.saberpro.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.saberpro.repository.UsuarioRepository;
import com.saberpro.model.Estudiante.TipoPrograma;

import java.util.Optional;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired private EstudianteService estudianteService;
    @Autowired private ResultadoService resultadoService;
    @Autowired private BeneficioService beneficioService;
    @Autowired private UsuarioRepository usuarioRepo;

    private Estudiante getEstudianteActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return usuarioRepo.findByUsername(username)
            .flatMap(u -> estudianteService.buscarPorUsuarioId(u.getId()))
            .orElse(null);
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Estudiante est = getEstudianteActual();
        if (est == null) return "redirect:/auth/login";
        model.addAttribute("estudiante", est);
        resultadoService.buscarPorEstudianteId(est.getId())
            .ifPresent(r -> model.addAttribute("resultado", r));
        model.addAttribute("seccion", "dashboard");
        return "estudiante/dashboard";
    }

    @GetMapping("/datos")
    public String datos(Model model) {
        Estudiante est = getEstudianteActual();
        if (est == null) return "redirect:/auth/login";
        model.addAttribute("estudiante", est);
        model.addAttribute("seccion", "datos");
        return "estudiante/datos";
    }

    @GetMapping("/resultado")
    public String resultado(Model model) {
        Estudiante est = getEstudianteActual();
        if (est == null) return "redirect:/auth/login";
        model.addAttribute("estudiante", est);
        Optional<ResultadoSaberPro> r = resultadoService.buscarPorEstudianteId(est.getId());
        r.ifPresent(res -> model.addAttribute("resultado", res));
        model.addAttribute("tieneResultado", r.isPresent());
        model.addAttribute("seccion", "resultado");
        return "estudiante/resultado";
    }

    @GetMapping("/beneficios")
    public String beneficios(Model model) {
        Estudiante est = getEstudianteActual();
        if (est == null) return "redirect:/auth/login";
        model.addAttribute("estudiante", est);
        TipoPrograma tipo = est.getTipoPrograma();
        model.addAttribute("beneficios", beneficioService.listarPorPrograma(
            tipo == TipoPrograma.TECNOLOGIA ?
            com.saberpro.model.Beneficio.TipoPrograma.TECNOLOGIA :
            com.saberpro.model.Beneficio.TipoPrograma.PROFESIONAL));
        model.addAttribute("seccion", "beneficios");
        return "estudiante/beneficios";
    }
}
