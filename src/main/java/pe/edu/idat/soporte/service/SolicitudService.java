package pe.edu.idat.soporte.service;

import pe.edu.idat.soporte.dto.SolicitudRequestDTO;
import pe.edu.idat.soporte.dto.SolicitudResponseDTO;
import pe.edu.idat.soporte.model.EstadoSolicitud;

import java.util.List;

/**
 * Interfaz del servicio que define las operaciones de negocio para la gestión de solicitudes.
 */
public interface SolicitudService {
    SolicitudResponseDTO crearSolicitud(SolicitudRequestDTO dto);
    List<SolicitudResponseDTO> listarSolicitudes();
    SolicitudResponseDTO obtenerPorId(Long id);
    List<SolicitudResponseDTO> filtrarPorEstado(EstadoSolicitud estado);
    SolicitudResponseDTO actualizarSolicitud(Long id, SolicitudRequestDTO dto);
    void eliminarSolicitud(Long id);
}
