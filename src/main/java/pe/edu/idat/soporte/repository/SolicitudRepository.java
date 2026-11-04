package pe.edu.idat.soporte.repository;

import pe.edu.idat.soporte.model.Solicitud;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de repositorio para desacoplar el acceso a datos.
 */
public interface SolicitudRepository {
    Solicitud guardar(Solicitud solicitud);
    List<Solicitud> listarTodas();
    Optional<Solicitud> buscarPorId(Long id);
    Solicitud actualizar(Solicitud solicitud);
    boolean eliminarPorId(Long id);
    boolean existePorId(Long id);
}
