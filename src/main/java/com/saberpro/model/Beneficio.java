package com.saberpro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "beneficios")
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoBeneficio tipo;

    @Column(nullable = false)
    private String nivelRequerido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPrograma programaAplicable;

    private String resolucion;

    @Column(nullable = false)
    private boolean activo = true;

    public enum TipoBeneficio {
        BECA, DESCUENTO, RECONOCIMIENTO, EXENCION
    }

    public enum TipoPrograma {
        TECNOLOGIA, PROFESIONAL, AMBOS
    }

    public Beneficio() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public TipoBeneficio getTipo() { return tipo; }
    public void setTipo(TipoBeneficio tipo) { this.tipo = tipo; }

    public String getNivelRequerido() { return nivelRequerido; }
    public void setNivelRequerido(String nivelRequerido) { this.nivelRequerido = nivelRequerido; }

    public TipoPrograma getProgramaAplicable() { return programaAplicable; }
    public void setProgramaAplicable(TipoPrograma programaAplicable) { this.programaAplicable = programaAplicable; }

    public String getResolucion() { return resolucion; }
    public void setResolucion(String resolucion) { this.resolucion = resolucion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
