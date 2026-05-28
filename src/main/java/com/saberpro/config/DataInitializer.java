package com.saberpro.config;

import com.saberpro.model.*;
import com.saberpro.model.Estudiante.EstadoSaberPro;
import com.saberpro.model.Estudiante.TipoPrograma;
import com.saberpro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private EstudianteRepository estudianteRepo;
    @Autowired private ResultadoRepository resultadoRepo;
    @Autowired private DocenteRepository docenteRepo;
    @Autowired private BeneficioRepository beneficioRepo;
    @Autowired private PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (usuarioRepo.count() > 0) return; 

       
        crearUsuarioAdmin();
        crearUsuarioCoordinacion();
        crearUsuariosDocentes();
        crearEstudiantesConResultados();
        crearBeneficios();
    }

    private void crearUsuarioAdmin() {
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin123"));
        admin.setNombre("Administrador");
        admin.setApellido("Sistema");
        admin.setEmail("admin@udes.edu.co");
        admin.setRol(Usuario.Rol.ADMINISTRADOR);
        usuarioRepo.save(admin);
    }

    private void crearUsuarioCoordinacion() {
        Usuario coord = new Usuario();
        coord.setUsername("coordinacion");
        coord.setPassword(encoder.encode("coord123"));
        coord.setNombre("María");
        coord.setApellido("González");
        coord.setEmail("coordinacion@udes.edu.co");
        coord.setRol(Usuario.Rol.COORDINACION);
        usuarioRepo.save(coord);
    }

    private void crearUsuariosDocentes() {
        String[][] docentes = {
            {"Juan", "Martínez", "jmartinez", "1098765432", "Ingeniería de Sistemas", "Desarrollo de Software"},
            {"Laura", "Ospina", "lospina", "1098765433", "Ingeniería de Sistemas", "Bases de Datos"},
            {"Carlos", "Rueda", "crueda", "1098765434", "Tecnología", "Redes y Comunicaciones"}
        };

        for (String[] d : docentes) {
            Usuario u = new Usuario();
            u.setUsername(d[2]);
            u.setPassword(encoder.encode("docente123"));
            u.setNombre(d[0]);
            u.setApellido(d[1]);
            u.setEmail(d[2] + "@udes.edu.co");
            u.setRol(Usuario.Rol.DOCENTE);
            usuarioRepo.save(u);

            Docente doc = new Docente();
            doc.setNombre(d[0]);
            doc.setApellido(d[1]);
            doc.setEmail(d[2] + "@udes.edu.co");
            doc.setCedula(d[3]);
            doc.setFacultad(d[4]);
            doc.setDepartamento(d[5]);
            doc.setActivo(true);
            doc.setUsuario(u);
            docenteRepo.save(doc);
        }
    }

    private void crearEstudiantesConResultados() {
        // Dato
        Object[][] datos = {
            {"BARBOSA", "ALEJANDRO", "CARLOS", "1098700001", "EK20183007722", "carlos.barbosa@correo.udes.edu.co", "3001234501", 200, 128, 182, 202, 206, 183, "B1", 185, 160, 197, "TECNOLOGIA"},
            {"QUINTERO", "MARCELA", "ANA", "1098700002", "EK20183140703", "ana.quintero@correo.udes.edu.co", "3001234502", 165, 125, 151, 179, 163, 205, "B2", 182, 144, 136, "TECNOLOGIA"},
            {"PARRA", "JULIAN", "DIEGO", "1098700003", "EK20183040545", "diego.parra@correo.udes.edu.co", "3001234503", 164, 159, 172, 182, 142, 165, "A2", 167, 132, 148, "TECNOLOGIA"},
            {"ANAYA", "VALENTINA", "SARA", "1098700004", "EK20183025381", "sara.anaya@correo.udes.edu.co", "3001234504", 160, 146, 199, 157, 149, 147, "A2", 174, 127, 171, "TECNOLOGIA"},
            {"FLOR", "NICOLAS", "ANDRES", "1098700005", "EK20183025335", "andres.flor@correo.udes.edu.co", "3001234505", 160, 198, 153, 147, 157, 146, "A2", 168, 114, 160, "TECNOLOGIA"},
            {"GARCIA", "SOFIA", "ISABELLA", "1098700006", "EK20183122648", "isabella.garcia@correo.udes.edu.co", "3001234506", 157, 179, 172, 158, 140, 136, "A1", 128, 121, 142, "TECNOLOGIA"},
            {"MANOSALVA", "FELIPE", "JUAN", "1098700007", "EK20183064605", "juan.manosalva@correo.udes.edu.co", "3001234507", 153, 115, 152, 159, 172, 165, "A2", 142, 118, 119, "TECNOLOGIA"},
            {"MENDOZA", "LAURA", "CAMILA", "1098700008", "EK20183187351", "camila.mendoza@correo.udes.edu.co", "3001234508", 151, 132, 123, 125, 169, 204, "B2", 173, 127, 171, "TECNOLOGIA"},
            {"BELTRAN", "SEBASTIAN", "MIGUEL", "1098700009", "EK20183233820", "miguel.beltran@correo.udes.edu.co", "3001234509", 150, 86, 187, 160, 171, 148, "A2", 162, 125, 142, "TECNOLOGIA"},
            {"SANTAMARIA", "DANIELA", "PAULA", "1098700010", "EK20183030016", "paula.santamaria@correo.udes.edu.co", "3001234510", 150, 175, 149, 145, 158, 125, "A1", 162, 76, 125, "TECNOLOGIA"},
            {"SANCHEZ", "DAVID", "ESTEBAN", "1098700011", "EK20183047073", "esteban.sanchez@correo.udes.edu.co", "3001234511", 149, 209, 143, 117, 129, 147, "A2", 137, 125, 136, "TECNOLOGIA"},
            {"ROMERO", "MARIANA", "LUISA", "1098700012", "EK20183236451", "luisa.romero@correo.udes.edu.co", "3001234512", 146, 93, 183, 155, 164, 133, "A1", 174, 130, 154, "TECNOLOGIA"},
            {"LUNA", "GABRIEL", "MATEO", "1098700013", "EK20183041714", "mateo.luna@correo.udes.edu.co", "3001234513", 141, 125, 157, 138, 135, 152, "A2", 176, 128, 165, "TECNOLOGIA"},
            {"TRIANA", "ALEJANDRA", "NATALIA", "1098700014", "EK20183187801", "natalia.triana@correo.udes.edu.co", "3001234514", 141, 150, 136, 145, 150, 126, "A1", 148, 129, 131, "TECNOLOGIA"},
            {"SUAREZ", "JORGE", "SAMUEL", "1098700015", "EK20183176566", "samuel.suarez@correo.udes.edu.co", "3001234515", 140, 128, 146, 146, 132, 147, "A2", 130, 110, 125, "TECNOLOGIA"},
            {"GARCIA", "MANUELA", "VALERIA", "1098700016", "EK20183204427", "valeria.garcia2@correo.udes.edu.co", "3001234516", 139, 129, 138, 148, 146, 135, "A1", 109, 107, 131, "TECNOLOGIA"},
            {"PINZON", "TOMAS", "ALEJANDRO", "1098700017", "EK20183196280", "alejandro.pinzon@correo.udes.edu.co", "3001234517", 138, 153, 123, 127, 147, 140, "A1", 145, 143, 160, "TECNOLOGIA"},
            {"JAIMES", "CATALINA", "STEFANIA", "1098700018", "EK20183173799", "stefania.jaimes@correo.udes.edu.co", "3001234518", 137, 166, 157, 124, 100, 140, "A1", 100, 105, 113, "TECNOLOGIA"},
            {"NIÑO", "JULIAN", "PABLO", "1098700019", "EK20183009565", "pablo.nino@correo.udes.edu.co", "3001234519", 134, 165, 137, 136, 118, 116, "A0", 146, 122, 154, "TECNOLOGIA"},
            {"FABIAN", "ANDREA", "JESSICA", "1098700020", "EK20183117756", "jessica.fabian@correo.udes.edu.co", "3001234520", 133, 139, 93, 168, 150, 114, "A0", 102, 123, 94, "TECNOLOGIA"},
            {"HERNANDEZ", "CAMILO", "OSCAR", "1098700021", "EK20183044579", "oscar.hernandez@correo.udes.edu.co", "3001234521", 132, 116, 166, 136, 104, 140, "A1", 158, 125, 154, "TECNOLOGIA"},
            {"LARIOS", "DIANA", "CAROLINA", "1098700022", "EK20183045760", "carolina.larios@correo.udes.edu.co", "3001234522", 131, 149, 123, 129, 121, 131, "A1", 101, 102, 165, "TECNOLOGIA"},
            {"CALDERON", "ANDRES", "FELIPE", "1098700023", "EK20183034044", "felipe.calderon@correo.udes.edu.co", "3001234523", 130, 127, 147, 134, 111, 131, "A1", 65, 112, 94, "TECNOLOGIA"},
            {"VILLARREAL", "TATIANA", "MARIA", "1098700024", "EK20183041521", "maria.villarreal@correo.udes.edu.co", "3001234524", 129, 96, 162, 114, 131, 144, "A1", 122, 112, 131, "TECNOLOGIA"},
           
            {"RESTREPO", "LAURA", "ANA", "1098700025", "EK20183027436", "ana.restrepo@correo.udes.edu.co", "3001234525", 126, 81, 134, 126, 149, 139, "A1", 127, 136, 142, "PROFESIONAL"},
            {"CACERES", "IVAN", "LUIS", "1098700026", "EK20183031592", "luis.caceres@correo.udes.edu.co", "3001234526", 125, 124, 135, 108, 92, 165, "A2", 132, 104, 131, "PROFESIONAL"},
            {"TABARES", "MONICA", "DIANA", "1098700027", "EK20183004153", "diana.tabares@correo.udes.edu.co", "3001234527", 124, 131, 131, 107, 88, 162, "A2", 136, 112, 148, "PROFESIONAL"},
            {"NARANJO", "CARLOS", "HENRY", "1098700028", "EK20183030783", "henry.naranjo@correo.udes.edu.co", "3001234528", 122, 166, 113, 113, 112, 106, "A0", 135, 117, 119, "PROFESIONAL"},
            {"PRADA", "LILIANA", "ROSA", "1098700029", "EK20183024754", "rosa.prada@correo.udes.edu.co", "3001234529", 122, 119, 125, 137, 107, 123, "A1", 83, 104, 119, "PROFESIONAL"},
            {"VARGAS", "ANDRES", "MARIO", "1098700030", "EK20183186200", "mario.vargas@correo.udes.edu.co", "3001234530", 114, 95, 120, 151, 86, 119, "A0", 149, 103, 119, "PROFESIONAL"},
            {"TORRES", "PATRICIA", "GLORIA", "1098700031", "EK20183182410", "gloria.torres@correo.udes.edu.co", "3001234531", 113, 109, 105, 104, 103, 142, "A1", 102, 135, 80, "PROFESIONAL"},
            {"ORTIZ", "RAFAEL", "MIGUEL", "1098700032", "EK20183213735", "miguel.ortiz@correo.udes.edu.co", "3001234532", 107, 128, 81, 107, 102, 119, "A0", 130, 111, 125, "PROFESIONAL"},
            {"VILLAMIZAR", "JENNY", "ELSA", "1098700033", "EK20183065220", "elsa.villamizar@correo.udes.edu.co", "3001234533", 106, 134, 96, 92, 110, 97, "A0", 83, 107, 119, "PROFESIONAL"},
            {"RESTREPO", "EDGAR", "JOSE", "1098700034", "EK20183028123", "jose.restrepo@correo.udes.edu.co", "3001234534", 96, 0, 117, 122, 105, 137, "A1", 157, 96, 131, "PROFESIONAL"}
        };

        for (Object[] d : datos) {
            String apellido = (String) d[0];
            String nombre2 = (String) d[1];
            String nombre1 = (String) d[2];
            String cedula = (String) d[3];
            String registro = (String) d[4];
            String correo = (String) d[5];
            String tel = (String) d[6];
            int puntajeTotal = (int) d[7];
            int comEsc = (int) d[8];
            int razCuant = (int) d[9];
            int lecCrit = (int) d[10];
            int compCiu = (int) d[11];
            int ingles = (int) d[12];
            String nivelInglesLetra = (String) d[13];
            int formProy = (int) d[14];
            int pensCient = (int) d[15];
            int disenoSw = (int) d[16];
            String tipoProg = (String) d[17];

          
            String username = nombre1.toLowerCase() + "." + apellido.toLowerCase();
            username = username.replace("Ñ", "n").replace("ñ", "n")
                              .replace("á","a").replace("é","e").replace("í","i")
                              .replace("ó","o").replace("ú","u");

            Usuario u = new Usuario();
            u.setUsername(username);
            u.setPassword(encoder.encode("est123"));
            u.setNombre(nombre1);
            u.setApellido(apellido);
            u.setEmail(correo);
            u.setRol(Usuario.Rol.ESTUDIANTE);
            usuarioRepo.save(u);

            // Crear estudiante
            Estudiante est = new Estudiante();
            est.setTipoDocumento("CC");
            est.setNumeroDocumento(cedula);
            est.setPrimerApellido(apellido);
            est.setPrimerNombre(nombre1);
            est.setSegundoNombre(nombre2);
            est.setCorreoElectronico(correo);
            est.setNumeroTelefonico(tel);
            est.setNumeroRegistro(registro);
            est.setTipoPrograma(TipoPrograma.valueOf(tipoProg));
            est.setEstado(EstadoSaberPro.APROBADO);
            est.setPrograma(tipoProg.equals("TECNOLOGIA") ? "Tecnología en Sistemas" : "Ingeniería de Sistemas");
            est.setUsuario(u);
            estudianteRepo.save(est);

            ResultadoSaberPro r = new ResultadoSaberPro();
            r.setEstudiante(est);
            r.setPuntajeTotal(puntajeTotal);
            r.setNivelTotal(ResultadoSaberPro.calcularNivel(puntajeTotal));
            r.setComunicacionEscrita(comEsc);
            r.setNivelComunicacionEscrita(ResultadoSaberPro.calcularNivel(comEsc));
            r.setRazonamientoCuantitativo(razCuant);
            r.setNivelRazonamientoCuantitativo(ResultadoSaberPro.calcularNivel(razCuant));
            r.setLecturaCritica(lecCrit);
            r.setNivelLecturaCritica(ResultadoSaberPro.calcularNivel(lecCrit));
            r.setCompetenciasCiudadanas(compCiu);
            r.setNivelCompetenciasCiudadanas(ResultadoSaberPro.calcularNivel(compCiu));
            r.setIngles(ingles);
            r.setNivelIngles(ResultadoSaberPro.calcularNivel(ingles));
            r.setNivelInglesLetra(nivelInglesLetra);
            r.setFormulacionProyectosIngenieria(formProy);
            r.setNivelFormulacionProyectos(ResultadoSaberPro.calcularNivel(formProy));
            r.setPensamientoCientificoMatematicas(pensCient);
            r.setNivelPensamientoCientifico(ResultadoSaberPro.calcularNivel(pensCient));
            r.setDisenoSoftware(disenoSw);
            r.setNivelDisenoSoftware(ResultadoSaberPro.calcularNivel(disenoSw));
            r.setAnioAplicacion(2026);
            r.setPeriodoAplicacion(1);
            resultadoRepo.save(r);
        }
    }

    private void crearBeneficios() {
        List<Object[]> beneficios = Arrays.asList(
            new Object[]{"Matrícula de Honor", "Descuento del 25% en matrícula por obtener Nivel 4 en puntaje global", Beneficio.TipoBeneficio.DESCUENTO, "Nivel 4", Beneficio.TipoPrograma.AMBOS, "Res. 001-2026"},
            new Object[]{"Beca Excelencia Tecnología", "Beca completa para estudiantes de tecnología con Nivel 3 o superior", Beneficio.TipoBeneficio.BECA, "Nivel 3", Beneficio.TipoPrograma.TECNOLOGIA, "Res. 002-2026"},
            new Object[]{"Distinción Académica", "Reconocimiento especial a los mejores puntajes de la institución", Beneficio.TipoBeneficio.RECONOCIMIENTO, "Nivel 4", Beneficio.TipoPrograma.AMBOS, "Res. 003-2026"},
            new Object[]{"Exención Derechos de Grado", "Exoneración del pago de derechos de grado por buen desempeño", Beneficio.TipoBeneficio.EXENCION, "Nivel 3", Beneficio.TipoPrograma.AMBOS, "Res. 004-2026"},
            new Object[]{"Beca Ingeniería", "Apoyo económico para profesional con nivel superior en competencias específicas", Beneficio.TipoBeneficio.BECA, "Nivel 4", Beneficio.TipoPrograma.PROFESIONAL, "Res. 005-2026"}
        );

        for (Object[] b : beneficios) {
            Beneficio ben = new Beneficio();
            ben.setNombre((String) b[0]);
            ben.setDescripcion((String) b[1]);
            ben.setTipo((Beneficio.TipoBeneficio) b[2]);
            ben.setNivelRequerido((String) b[3]);
            ben.setProgramaAplicable((Beneficio.TipoPrograma) b[4]);
            ben.setResolucion((String) b[5]);
            ben.setActivo(true);
            beneficioRepo.save(ben);
        }
    }
}
