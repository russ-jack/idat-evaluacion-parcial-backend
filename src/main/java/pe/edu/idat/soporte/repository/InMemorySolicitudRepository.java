package pe.edu.idat.soporte.repository;

import org.springframework.stereotype.Repository;
import pe.edu.idat.soporte.model.Solicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simulación de persistencia local en memoria según los requerimientos del examen.
 * Utiliza estructuras Java Collections (Map, List) y AtomicLong para generar IDs secuenciales.
 */
@Repository
public class InMemorySolicitudRepository implements SolicitudRepository {

    // Almacenamiento en memoria usando una colección Map concurrente
    private final Map<Long, Solicitud> almacenamiento = new ConcurrentHashMap<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    @Override
    public Solicitud guardar(Solicitud solicitud) {
        if (solicitud.getId() == null) {
            solicitud.setId(contadorId.getAndIncrement());
        }
        almacenamiento.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    @Override
    public List<Solicitud> listarTodas() {
        return new ArrayList<>(almacenamiento.values());
    }

    @Override
    public Optional<Solicitud> buscarPorId(Long id) {
        return Optional.ofNullable(almacenamiento.get(id));
    }

    @Override
    public Solicitud actualizar(Solicitud solicitud) {
        almacenamiento.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    @Override
    public boolean eliminarPorId(Long id) {
        return almacenamiento.remove(id) != null;
    }

    @Override
    public boolean existePorId(Long id) {
        return almacenamiento.containsKey(id);
    }
}
