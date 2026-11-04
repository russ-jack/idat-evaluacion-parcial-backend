package pe.edu.idat.soporte.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad principal del sistema que almacena los detalles de una solicitud de soporte técnico.
 */
public class Solicitud {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private EstadoSolicitud estado;
    private PrioridadSolicitud prioridad;
    private Cliente cliente;
    private Tecnico tecnicoAsignado;

    public Solicitud() {
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoSolicitud.PENDIENTE;
        this.prioridad = PrioridadSolicitud.MEDIA;
    }

    public Solicitud(Long id, String titulo, String descripcion, Cliente cliente, PrioridadSolicitud prioridad) {
        this();
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.cliente = cliente;
        if (prioridad != null) {
            this.prioridad = prioridad;
        }
    }

    // Getters y Setters
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void setTecnicoAsignado(Tecnico tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solicitud that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", estado=" + estado +
                ", prioridad=" + prioridad +
                ", cliente=" + (cliente != null ? cliente.getNombre() : "null") +
                ", tecnico=" + (tecnicoAsignado != null ? tecnicoAsignado.getNombre() : "Sin asignar") +
                '}';
    }
}
