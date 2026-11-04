package pe.edu.idat.soporte.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.soporte.dto.SolicitudRequestDTO;
import pe.edu.idat.soporte.dto.SolicitudResponseDTO;
import pe.edu.idat.soporte.model.EstadoSolicitud;
import pe.edu.idat.soporte.service.SolicitudService;

import java.util.List;

/**
 * Controlador RESTful para gestionar el ciclo de vida de las solicitudes de soporte técnico.
 * Cumple con estándares de nomenclatura, verbos HTTP y códigos de estado contextuales.
 */
@RestController
@RequestMapping("/api/v1/solicitudes")
@CrossOrigin(origins = "*")
@Tag(name = "Solicitudes de Soporte", description = "Endpoints para registro, consulta, actualización y eliminación de solicitudes de soporte técnico")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    @Operation(summary = "Crear nueva solicitud de soporte", description = "Registra una nueva solicitud con validación Bean Validation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o incompletos"),
            @ApiResponse(responseCode = "404", description = "Cliente o técnico no encontrado")
    })
    public ResponseEntity<SolicitudResponseDTO> crear(@Valid @RequestBody SolicitudRequestDTO dto) {
        SolicitudResponseDTO creada = solicitudService.crearSolicitud(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todas las solicitudes", description = "Retorna la colección completa de solicitudes, opcionalmente filtrada por estado")
    @ApiResponse(responseCode = "200", description = "Lista de solicitudes obtenida con éxito")
    public ResponseEntity<List<SolicitudResponseDTO>> listar(
            @Parameter(description = "Filtro opcional por estado: PENDIENTE, EN_PROCESO, RESUELTO, CANCELADO")
            @RequestParam(required = false) EstadoSolicitud estado) {
        if (estado != null) {
            return ResponseEntity.ok(solicitudService.filtrarPorEstado(estado));
        }
        return ResponseEntity.ok(solicitudService.listarSolicitudes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar solicitud por ID", description = "Retorna el detalle completo de una solicitud")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada con el ID provisto")
    })
    public ResponseEntity<SolicitudResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(solicitudService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar solicitud", description = "Modifica los atributos, estado o técnico de una solicitud existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
            @ApiResponse(responseCode = "404", description = "Solicitud o técnico no encontrado")
    })
    public ResponseEntity<SolicitudResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SolicitudRequestDTO dto) {
        return ResponseEntity.ok(solicitudService.actualizarSolicitud(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar solicitud", description = "Elimina permanentemente una solicitud del almacenamiento en memoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Solicitud eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
