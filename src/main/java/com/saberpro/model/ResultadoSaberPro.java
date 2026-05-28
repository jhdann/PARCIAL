package com.saberpro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resultados_saber_pro")
public class ResultadoSaberPro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    private Integer puntajeTotal;
    private String nivelTotal;

    private Integer comunicacionEscrita;
    private String nivelComunicacionEscrita;

    private Integer razonamientoCuantitativo;
    private String nivelRazonamientoCuantitativo;

    private Integer lecturaCritica;
    private String nivelLecturaCritica;

    private Integer competenciasCiudadanas;
    private String nivelCompetenciasCiudadanas;

    private Integer ingles;
    private String nivelIngles;
    private String nivelInglesLetra;

    private Integer formulacionProyectosIngenieria;
    private String nivelFormulacionProyectos;

    private Integer pensamientoCientificoMatematicas;
    private String nivelPensamientoCientifico;

    private Integer disenoSoftware;
    private String nivelDisenoSoftware;

    @Column(name = "anio_aplicacion")
    private Integer anioAplicacion;

    @Column(name = "periodo_aplicacion")
    private Integer periodoAplicacion;

    public ResultadoSaberPro() {}

    public static String calcularNivel(Integer puntaje) {
        if (puntaje == null) return "N/A";
        if (puntaje >= 180) return "Nivel 4";
        if (puntaje >= 155) return "Nivel 3";
        if (puntaje >= 120) return "Nivel 2";
        return "Nivel 1";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Integer getPuntajeTotal() { return puntajeTotal; }
    public void setPuntajeTotal(Integer puntajeTotal) { this.puntajeTotal = puntajeTotal; }

    public String getNivelTotal() { return nivelTotal; }
    public void setNivelTotal(String nivelTotal) { this.nivelTotal = nivelTotal; }

    public Integer getComunicacionEscrita() { return comunicacionEscrita; }
    public void setComunicacionEscrita(Integer comunicacionEscrita) { this.comunicacionEscrita = comunicacionEscrita; }

    public String getNivelComunicacionEscrita() { return nivelComunicacionEscrita; }
    public void setNivelComunicacionEscrita(String nivelComunicacionEscrita) { this.nivelComunicacionEscrita = nivelComunicacionEscrita; }

    public Integer getRazonamientoCuantitativo() { return razonamientoCuantitativo; }
    public void setRazonamientoCuantitativo(Integer razonamientoCuantitativo) { this.razonamientoCuantitativo = razonamientoCuantitativo; }

    public String getNivelRazonamientoCuantitativo() { return nivelRazonamientoCuantitativo; }
    public void setNivelRazonamientoCuantitativo(String nivelRazonamientoCuantitativo) { this.nivelRazonamientoCuantitativo = nivelRazonamientoCuantitativo; }

    public Integer getLecturaCritica() { return lecturaCritica; }
    public void setLecturaCritica(Integer lecturaCritica) { this.lecturaCritica = lecturaCritica; }

    public String getNivelLecturaCritica() { return nivelLecturaCritica; }
    public void setNivelLecturaCritica(String nivelLecturaCritica) { this.nivelLecturaCritica = nivelLecturaCritica; }

    public Integer getCompetenciasCiudadanas() { return competenciasCiudadanas; }
    public void setCompetenciasCiudadanas(Integer competenciasCiudadanas) { this.competenciasCiudadanas = competenciasCiudadanas; }

    public String getNivelCompetenciasCiudadanas() { return nivelCompetenciasCiudadanas; }
    public void setNivelCompetenciasCiudadanas(String nivelCompetenciasCiudadanas) { this.nivelCompetenciasCiudadanas = nivelCompetenciasCiudadanas; }

    public Integer getIngles() { return ingles; }
    public void setIngles(Integer ingles) { this.ingles = ingles; }

    public String getNivelIngles() { return nivelIngles; }
    public void setNivelIngles(String nivelIngles) { this.nivelIngles = nivelIngles; }

    public String getNivelInglesLetra() { return nivelInglesLetra; }
    public void setNivelInglesLetra(String nivelInglesLetra) { this.nivelInglesLetra = nivelInglesLetra; }

    public Integer getFormulacionProyectosIngenieria() { return formulacionProyectosIngenieria; }
    public void setFormulacionProyectosIngenieria(Integer formulacionProyectosIngenieria) { this.formulacionProyectosIngenieria = formulacionProyectosIngenieria; }

    public String getNivelFormulacionProyectos() { return nivelFormulacionProyectos; }
    public void setNivelFormulacionProyectos(String nivelFormulacionProyectos) { this.nivelFormulacionProyectos = nivelFormulacionProyectos; }

    public Integer getPensamientoCientificoMatematicas() { return pensamientoCientificoMatematicas; }
    public void setPensamientoCientificoMatematicas(Integer pensamientoCientificoMatematicas) { this.pensamientoCientificoMatematicas = pensamientoCientificoMatematicas; }

    public String getNivelPensamientoCientifico() { return nivelPensamientoCientifico; }
    public void setNivelPensamientoCientifico(String nivelPensamientoCientifico) { this.nivelPensamientoCientifico = nivelPensamientoCientifico; }

    public Integer getDisenoSoftware() { return disenoSoftware; }
    public void setDisenoSoftware(Integer disenoSoftware) { this.disenoSoftware = disenoSoftware; }

    public String getNivelDisenoSoftware() { return nivelDisenoSoftware; }
    public void setNivelDisenoSoftware(String nivelDisenoSoftware) { this.nivelDisenoSoftware = nivelDisenoSoftware; }

    public Integer getAnioAplicacion() { return anioAplicacion; }
    public void setAnioAplicacion(Integer anioAplicacion) { this.anioAplicacion = anioAplicacion; }

    public Integer getPeriodoAplicacion() { return periodoAplicacion; }
    public void setPeriodoAplicacion(Integer periodoAplicacion) { this.periodoAplicacion = periodoAplicacion; }
}
