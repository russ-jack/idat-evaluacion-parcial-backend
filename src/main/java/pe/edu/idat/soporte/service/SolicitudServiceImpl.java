package pe.edu.idat.soporte.service;

import org.springframework.stereotype.Service;
import pe.edu.idat.soporte.dto.SolicitudRequestDTO;
import pe.edu.idat.soporte.dto.SolicitudResponseDTO;
import pe.edu.idat.soporte.exception.ResourceNotFoundException;
import pe.edu.idat.soporte.model.Cliente;
import pe.edu.idat.soporte.model.EstadoSolicitud;
import pe.edu.idat.soporte.model.PrioridadSolicitud;
import pe.edu.idat.soporte.model.Solicitud;
import pe.edu.idat.soporte.model.Tecnico;
import pe.edu.idat.soporte.repository.SolicitudRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Implementación de la lógica de negocio.
 * Mantiene catálogos simulados de Clientes y Técnicos en memoria, e interactúa con el Repositorio.
 */
@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;

    // Colecciones simuladas en memoria para clientes y técnicos
    private final Map<Long, Cliente> clientesEnMemoria = new ConcurrentHashMap<>();
    private final Map<Long, Tecnico> tecnicosEnMemoria = new ConcurrentHashMap<>();

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
        inicializarDatosSemilla();
    }

    private void inicializarDatosSemilla() {
        // Clientes iniciales
        clientesEnMemoria.put(1L, new Cliente(1L, "Carlos Mendoza", "carlos@empresa.com", "987654321", "TechSolutions S.A.C."));
        clientesEnMemoria.put(2L, new Cliente(2L, "Lucía Ramírez", "lucia@innovatech.pe", "912345678", "InnovaTech Perú"));

        // Técnicos iniciales
        tecnicosEnMemoria.put(1L, new Tecnico(1L, "Miguel Ángel Ruiz", "miguel.ruiz@soporte.com", "999888777", "Redes y Conectividad"));
        tecnicosEnMemoria.put(2L, new Tecnico(2L, "Sofía Castro", "sofia.castro@soporte.com", "955443322", "Servidores y BD"));
    }

    @Override
    public SolicitudResponseDTO crearSolicitud(SolicitudRequestDTO dto) {
        Cliente cliente = clientesEnMemoria.get(dto.getClienteId());
        if (cliente == null) {
            throw new ResourceNotFoundException("No se encontró ningún cliente registrado con el ID: " + dto.getClienteId());
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setTitulo(dto.getTitulo());
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setCliente(cliente);
        solicitud.setPrioridad(dto.getPrioridad() != null ? dto.getPrioridad() : PrioridadSolicitud.MEDIA);
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);

        if (dto.getTecnicoId() != null) {
            Tecnico tecnico = tecnicosEnMemoria.get(dto.getTecnicoId());
            if (tecnico == null) {
                throw new ResourceNotFoundException("No se encontró ningún técnico registrado con el ID: " + dto.getTecnicoId());
            }
            solicitud.setTecnicoAsignado(tecnico);
            solicitud.setEstado(EstadoSolicitud.EN_PROCESO);
        }

        Solicitud guardada = solicitudRepository.guardar(solicitud);
        return mapearADTO(guardada);
    }

    @Override
    public List<SolicitudResponseDTO> listarSolicitudes() {
        return solicitudRepository.listarTodas().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public SolicitudResponseDTO obtenerPorId(Long id) {
        Solicitud solicitud = solicitudRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con el ID: " + id));
        return mapearADTO(solicitud);
    }

    @Override
    public List<SolicitudResponseDTO> filtrarPorEstado(EstadoSolicitud estado) {
        return solicitudRepository.listarTodas().stream()
                .filter(s -> s.getEstado() == estado)
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public SolicitudResponseDTO actualizarSolicitud(Long id, SolicitudRequestDTO dto) {
        Solicitud solicitud = solicitudRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. Solicitud no encontrada con ID: " + id));

        solicitud.setTitulo(dto.getTitulo());
        solicitud.setDescripcion(dto.getDescripcion());

        if (dto.getPrioridad() != null) {
            solicitud.setPrioridad(dto.getPrioridad());
        }

        if (dto.getEstado() != null) {
            solicitud.setEstado(dto.getEstado());
        }

        if (dto.getTecnicoId() != null) {
            Tecnico tecnico = tecnicosEnMemoria.get(dto.getTecnicoId());
            if (tecnico == null) {
                throw new ResourceNotFoundException("No se encontró el técnico con ID: " + dto.getTecnicoId());
            }
            solicitud.setTecnicoAsignado(tecnico);
        }

        Solicitud actualizada = solicitudRepository.actualizar(solicitud);
        return mapearADTO(actualizada);
    }

    @Override
    public void eliminarSolicitud(Long id) {
        if (!solicitudRepository.existePorId(id)) {
            throw new ResourceNotFoundException("No existe la solicitud con ID: " + id + " para ser eliminada");
        }
        solicitudRepository.eliminarPorId(id);
    }

    private SolicitudResponseDTO mapearADTO(Solicitud s) {
        SolicitudResponseDTO dto = new SolicitudResponseDTO();
        dto.setId(s.getId());
        dto.setTitulo(s.getTitulo());
        dto.setDescripcion(s.getDescripcion());
        dto.setFechaCreacion(s.getFechaCreacion());
        dto.setEstado(s.getEstado());
        dto.setPrioridad(s.getPrioridad());

        if (s.getCliente() != null) {
            dto.setClienteNombre(s.getCliente().getNombre());
            dto.setClienteEmpresa(s.getCliente().getEmpresa());
        }

        if (s.getTecnicoAsignado() != null) {
            dto.setTecnicoNombre(s.getTecnicoAsignado().getNombre());
        } else {
            dto.setTecnicoNombre("Sin asignar");
        }

        return dto;
    }
}
