package pe.edu.idat.soporte.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pe.edu.idat.soporte.model.EstadoSolicitud;
import pe.edu.idat.soporte.model.PrioridadSolicitud;

/**
 * DTO para la creación y actualización de solicitudes de soporte.
 * Contiene anotaciones Bean Validation (@Valid, @NotNull, @NotBlank, @Size).
 */
@Schema(description = "Datos para registrar o modificar una solicitud de soporte técnico")
public class SolicitudRequestDTO {

    @Schema(description = "Título descriptivo del problema", example = "Falla de conectividad en switches del piso 3")
    @NotBlank(message = "El título de la solicitud es obligatorio y no puede estar vacío")
    @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
    private String titulo;

    @Schema(description = "Detalle exhaustivo de la incidencia técnica", example = "Los equipos no obtienen dirección IP dinámica vía DHCP desde las 09:00 hrs")
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    private String descripcion;

    @Schema(description = "Identificador único del cliente que solicita el servicio", example = "1")
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @Schema(description = "Identificador opcional del técnico asignado", example = "1")
    private Long tecnicoId;

    @Schema(description = "Prioridad de la solicitud", example = "ALTA")
    private PrioridadSolicitud prioridad;

    @Schema(description = "Estado de la solicitud (utilizado principalmente al actualizar)", example = "EN_PROCESO")
    private EstadoSolicitud estado;

    public SolicitudRequestDTO() {
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Long tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public PrioridadSolicitud getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadSolicitud prioridad) {
        this.prioridad = prioridad;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }
}
