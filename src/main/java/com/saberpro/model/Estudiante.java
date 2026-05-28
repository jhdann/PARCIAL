package com.saberpro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipoDocumento;

    @Column(nullable = false, unique = true)
    private String numeroDocumento;

    @Column(nullable = false)
    private String primerApellido;

    private String segundoApellido;

    @Column(nullable = false)
    private String primerNombre;

    private String segundoNombre;

    @Column(nullable = false, unique = true)
    private String correoElectronico;

    private String numeroTelefonico;

    @Column(nullable = false, unique = true)
    private String numeroRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPrograma tipoPrograma;

    @Column(nullable = false)
    private String programa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSaberPro estado = EstadoSaberPro.PENDIENTE;

    @Column
    private String comprobantePago;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ResultadoSaberPro resultado;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public enum TipoPrograma {
        TECNOLOGIA, PROFESIONAL
    }

    public enum EstadoSaberPro {
        PENDIENTE, APROBADO, RECHAZADO, ANULADO
    }

    public Estudiante() {}

    public String getNombreCompleto() {
        return primerNombre
            + (segundoNombre != null ? " " + segundoNombre : "")
            + " " + primerApellido
            + (segundoApellido != null ? " " + segundoApellido : "");
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public String getPrimerNombre() { return primerNombre; }
    public void setPrimerNombre(String primerNombre) { this.primerNombre = primerNombre; }

    public String getSegundoNombre() { return segundoNombre; }
    public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getNumeroTelefonico() { return numeroTelefonico; }
    public void setNumeroTelefonico(String numeroTelefonico) { this.numeroTelefonico = numeroTelefonico; }

    public String getNumeroRegistro() { return numeroRegistro; }
    public void setNumeroRegistro(String numeroRegistro) { this.numeroRegistro = numeroRegistro; }

    public TipoPrograma getTipoPrograma() { return tipoPrograma; }
    public void setTipoPrograma(TipoPrograma tipoPrograma) { this.tipoPrograma = tipoPrograma; }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    public EstadoSaberPro getEstado() { return estado; }
    public void setEstado(EstadoSaberPro estado) { this.estado = estado; }

    public String getComprobantePago() { return comprobantePago; }
    public void setComprobantePago(String comprobantePago) { this.comprobantePago = comprobantePago; }

    public ResultadoSaberPro getResultado() { return resultado; }
    public void setResultado(ResultadoSaberPro resultado) { this.resultado = resultado; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
