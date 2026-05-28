package com.saberpro.controller;

import com.saberpro.model.Estudiante;
import com.saberpro.model.Estudiante.TipoPrograma;
import com.saberpro.model.ResultadoSaberPro;
import com.saberpro.model.Usuario;
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

import java.util.Optional;

@Controller
@RequestMapping("/coordinacion")
public class CoordinacionController {

    @Autowired private EstudianteService estudianteService;
    @Autowired private ResultadoService resultadoService;
    @Autowired private BeneficioService beneficioService;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private PasswordEncoder encoder;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalTecnologia", estudianteService.contarTecnologia());
        model.addAttribute("totalProfesional", estudianteService.contarProfesional());
        model.addAttribute("aprobados", estudianteService.contarAprobados());
        model.addAttribute("pendientes", estudianteService.contarPendientes());
        model.addAttribute("promedioTec", resultadoService.promedioTecnologia());
        model.addAttribute("promedioProf", resultadoService.promedioProfesional());
        model.addAttribute("seccion", "dashboard");
        return "coordinacion/dashboard";
    }

    
    @GetMapping("/estudiantes")
    public String estudiantes(@RequestParam(value = "q", required = false) String q,
                              @RequestParam(value = "tipo", required = false) String tipo,
                              Model model) {
        if (q != null && !q.isBlank()) {
            model.addAttribute("estudiantes", estudianteService.buscar(q));
        } else if (tipo != null && !tipo.isBlank()) {
            model.addAttribute("estudiantes", estudianteService.listarPorTipoPrograma(TipoPrograma.valueOf(tipo)));
        } else {
            model.addAttribute("estudiantes", estudianteService.listarTodos());
        }
        model.addAttribute("nuevoEstudiante", new Estudiante());
        model.addAttribute("seccion", "estudiantes");
        return "coordinacion/estudiantes";
    }

    @GetMapping("/estudiantes/{id}")
    public String verEstudiante(@PathVariable Long id, Model model) {
        Optional<Estudiante> est = estudianteService.buscarPorId(id);
        if (est.isEmpty()) return "redirect:/coordinacion/estudiantes";
        model.addAttribute("estudiante", est.get());
        Optional<ResultadoSaberPro> resultado = resultadoService.buscarPorEstudianteId(id);
        resultado.ifPresent(r -> model.addAttribute("resultado", r));
        model.addAttribute("seccion", "estudiantes");
        return "coordinacion/detalle-estudiante";
    }

    @PostMapping("/estudiantes/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante, RedirectAttributes ra) {
        
        String username = estudiante.getPrimerNombre().toLowerCase() + "." + estudiante.getPrimerApellido().toLowerCase();
        Usuario u = new Usuario();
        u.setUsername(username.replace(" ", "").replace("ó","o").replace("á","a").replace("é","e").replace("í","i").replace("ú","u"));
        u.setPassword(encoder.encode("est123"));
        u.setNombre(estudiante.getPrimerNombre());
        u.setApellido(estudiante.getPrimerApellido());
        u.setEmail(estudiante.getCorreoElectronico());
        u.setRol(Usuario.Rol.ESTUDIANTE);
        usuarioRepo.save(u);
        estudiante.setUsuario(u);
        estudianteService.guardar(estudiante);
        ra.addFlashAttribute("success", "Estudiante registrado correctamente.");
        return "redirect:/coordinacion/estudiantes";
    }

    @PostMapping("/estudiantes/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, @ModelAttribute Estudiante datos, RedirectAttributes ra) {
        estudianteService.buscarPorId(id).ifPresent(est -> {
            est.setPrimerNombre(datos.getPrimerNombre());
            est.setSegundoNombre(datos.getSegundoNombre());
            est.setPrimerApellido(datos.getPrimerApellido());
            est.setSegundoApellido(datos.getSegundoApellido());
            est.setCorreoElectronico(datos.getCorreoElectronico());
            est.setNumeroTelefonico(datos.getNumeroTelefonico());
            est.setTipoPrograma(datos.getTipoPrograma());
            est.setPrograma(datos.getPrograma());
            estudianteService.guardar(est);
        });
        ra.addFlashAttribute("success", "Estudiante actualizado.");
        return "redirect:/coordinacion/estudiantes";
    }

    @GetMapping("/estudiantes/aprobar/{id}")
    public String aprobar(@PathVariable Long id, RedirectAttributes ra) {
        estudianteService.aprobar(id);
        ra.addFlashAttribute("success", "Estudiante aprobado para Saber Pro.");
        return "redirect:/coordinacion/estudiantes";
    }

    @GetMapping("/estudiantes/rechazar/{id}")
    public String rechazar(@PathVariable Long id, RedirectAttributes ra) {
        estudianteService.rechazar(id);
        ra.addFlashAttribute("warning", "Estudiante rechazado.");
        return "redirect:/coordinacion/estudiantes";
    }

    
    @GetMapping("/informe/general")
    public String informeGeneral(Model model) {
        model.addAttribute("tecnologia", estudianteService.listarPorTipoPrograma(TipoPrograma.TECNOLOGIA));
        model.addAttribute("profesional", estudianteService.listarPorTipoPrograma(TipoPrograma.PROFESIONAL));
        model.addAttribute("seccion", "informe");
        return "coordinacion/informe-general";
    }

    @GetMapping("/informe/detallado")
    public String informeDetallado(Model model) {
        model.addAttribute("resultados", resultadoService.listarOrdenados());
        model.addAttribute("promedioTec", resultadoService.promedioTecnologia());
        model.addAttribute("promedioProf", resultadoService.promedioProfesional());
        model.addAttribute("seccion", "informe");
        return "coordinacion/informe-detallado";
    }

    @GetMapping("/beneficios")
    public String beneficios(Model model) {
        model.addAttribute("beneficios", beneficioService.listarActivos());
        model.addAttribute("seccion", "beneficios");
        return "coordinacion/beneficios";
    }
}
