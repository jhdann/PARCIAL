package com.saberpro.controller;

import com.saberpro.model.Estudiante.TipoPrograma;
import com.saberpro.service.BeneficioService;
import com.saberpro.service.EstudianteService;
import com.saberpro.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/docente")
public class DocenteController {

    @Autowired private EstudianteService estudianteService;
    @Autowired private ResultadoService resultadoService;
    @Autowired private BeneficioService beneficioService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalTecnologia", estudianteService.contarTecnologia());
        model.addAttribute("totalProfesional", estudianteService.contarProfesional());
        model.addAttribute("promedioTec", resultadoService.promedioTecnologia());
        model.addAttribute("promedioProf", resultadoService.promedioProfesional());
        model.addAttribute("seccion", "dashboard");
        return "docente/dashboard";
    }

    @GetMapping("/estudiantes")
    public String estudiantes(@RequestParam(value = "cedula", required = false) String cedula,
                              @RequestParam(value = "facultad", required = false) String facultad,
                              Model model) {
        if (cedula != null && !cedula.isBlank()) {
            estudianteService.buscarPorDocumento(cedula).ifPresent(e -> model.addAttribute("estudiante", e));
        } else if (facultad != null && !facultad.isBlank()) {
            model.addAttribute("estudiantes", estudianteService.listarPorTipoPrograma(TipoPrograma.valueOf(facultad)));
        } else {
            model.addAttribute("estudiantes", estudianteService.listarTodos());
        }
        model.addAttribute("seccion", "estudiantes");
        return "docente/estudiantes";
    }

    @GetMapping("/informe")
    public String informe(Model model) {
        model.addAttribute("resultados", resultadoService.listarOrdenados());
        model.addAttribute("promedioTec", resultadoService.promedioTecnologia());
        model.addAttribute("promedioProf", resultadoService.promedioProfesional());
        model.addAttribute("seccion", "informe");
        return "docente/informe";
    }

    @GetMapping("/beneficios")
    public String beneficios(Model model) {
        model.addAttribute("beneficios", beneficioService.listarActivos());
        model.addAttribute("seccion", "beneficios");
        return "docente/beneficios";
    }
}
