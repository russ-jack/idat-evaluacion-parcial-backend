package pe.edu.idat.soporte.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Formato estándar de respuesta para errores de la API.
 */
@Schema(description = "Estructura estándar de errores retornados por la API")
public class ErrorResponseDTO {

    @Schema(description = "Fecha y hora en que ocurrió el error")
    private LocalDateTime timestamp;

    @Schema(description = "Código de estado HTTP", example = "400")
    private int status;

    @Schema(description = "Nombre del error HTTP", example = "Bad Request")
    private String error;

    @Schema(description = "Mensaje explicativo del error", example = "Error de validación en los datos enviados")
    private String mensaje;

    @Schema(description = "Lista detallada de validaciones fallidas")
    private List<String> detalles;

    public ErrorResponseDTO() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDTO(int status, String error, String mensaje, List<String> detalles) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<String> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<String> detalles) {
        this.detalles = detalles;
    }
}
