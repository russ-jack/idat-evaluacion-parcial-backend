package pe.edu.idat.soporte.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import pe.edu.idat.soporte.model.EstadoSolicitud;
import pe.edu.idat.soporte.model.PrioridadSolicitud;

import java.time.LocalDateTime;

/**
 * DTO de salida para entregar respuestas estructuradas al cliente sin exponer entidades internas.
 */
@Schema(description = "Datos retornados de una solicitud de soporte")
public class SolicitudResponseDTO {

    @Schema(description = "ID único de la solicitud", example = "1")
    private Long id;

    @Schema(description = "Título de la solicitud", example = "Falla de conectividad en switches del piso 3")
    private String titulo;

    @Schema(description = "Descripción detallada", example = "Los equipos no obtienen dirección IP dinámica vía DHCP")
    private String descripcion;

    @Schema(description = "Fecha y hora de registro")
    private LocalDateTime fechaCreacion;

    @Schema(description = "Estado actual", example = "PENDIENTE")
    private EstadoSolicitud estado;

    @Schema(description = "Nivel de prioridad", example = "ALTA")
    private PrioridadSolicitud prioridad;

    @Schema(description = "Nombre completo del cliente", example = "Carlos Mendoza")
    private String clienteNombre;

    @Schema(description = "Empresa a la que pertenece el cliente", example = "TechSolutions S.A.C.")
    private String clienteEmpresa;

    @Schema(description = "Nombre del técnico asignado", example = "Miguel Ángel Ruiz")
    private String tecnicoNombre;

    public SolicitudResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public PrioridadSolicitud getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadSolicitud prioridad) {
        this.prioridad = prioridad;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteEmpresa() {
        return clienteEmpresa;
    }

    public void setClienteEmpresa(String clienteEmpresa) {
        this.clienteEmpresa = clienteEmpresa;
    }

    public String getTecnicoNombre() {
        return tecnicoNombre;
    }

    public void setTecnicoNombre(String tecnicoNombre) {
        this.tecnicoNombre = tecnicoNombre;
    }
}
