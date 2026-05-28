package com.saberpro.controller;

import com.saberpro.model.Docente;
import com.saberpro.model.Usuario;
import com.saberpro.repository.DocenteRepository;
import com.saberpro.repository.UsuarioRepository;
import com.saberpro.service.BeneficioService;
import com.saberpro.service.EstudianteService;
import com.saberpro.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private EstudianteService estudianteService;
    @Autowired private ResultadoService resultadoService;
    @Autowired private BeneficioService beneficioService;
    @Autowired private DocenteRepository docenteRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private PasswordEncoder encoder;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalTecnologia", estudianteService.contarTecnologia());
        model.addAttribute("totalProfesional", estudianteService.contarProfesional());
        model.addAttribute("totalAprobados", estudianteService.contarAprobados());
        model.addAttribute("totalPendientes", estudianteService.contarPendientes());
        model.addAttribute("promedioTec", resultadoService.promedioTecnologia());
        model.addAttribute("promedioProf", resultadoService.promedioProfesional());
        model.addAttribute("topEstudiantes", estudianteService.conResultadosOrdenados().stream().limit(5).toList());
        model.addAttribute("seccion", "dashboard");
        return "admin/dashboard";
    }

    @GetMapping("/docentes")
    public String docentes(Model model) {
        model.addAttribute("docentes", docenteRepo.findAll());
        model.addAttribute("nuevoDocente", new Docente());
        model.addAttribute("seccion", "docentes");
        return "admin/docentes";
    }

    @PostMapping("/docentes/guardar")
    public String guardarDocente(@ModelAttribute Docente docente, RedirectAttributes ra) {
        
        Usuario u = new Usuario();
        u.setUsername(docente.getNombre().toLowerCase() + "." + docente.getApellido().toLowerCase());
        u.setPassword(encoder.encode("docente123"));
        u.setNombre(docente.getNombre());
        u.setApellido(docente.getApellido());
        u.setEmail(docente.getEmail());
        u.setRol(Usuario.Rol.DOCENTE);
        usuarioRepo.save(u);
        docente.setUsuario(u);
        docenteRepo.save(docente);
        ra.addFlashAttribute("success", "Docente registrado correctamente.");
        return "redirect:/admin/docentes";
    }

    @GetMapping("/docentes/eliminar/{id}")
    public String eliminarDocente(@PathVariable Long id, RedirectAttributes ra) {
        docenteRepo.findById(id).ifPresent(d -> {
            d.setActivo(false);
            docenteRepo.save(d);
        });
        ra.addFlashAttribute("success", "Docente desactivado.");
        return "redirect:/admin/docentes";
    }

  
    @GetMapping("/beneficios")
    public String beneficios(Model model) {
        model.addAttribute("beneficios", beneficioService.listarActivos());
        model.addAttribute("seccion", "beneficios");
        return "admin/beneficios";
    }

   
    @GetMapping("/informe")
    public String informe(Model model) {
        model.addAttribute("resultados", resultadoService.listarOrdenados());
        model.addAttribute("promedioTec", resultadoService.promedioTecnologia());
        model.addAttribute("promedioProf", resultadoService.promedioProfesional());
        model.addAttribute("seccion", "informe");
        return "admin/informe";
    }
}
