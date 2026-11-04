package pe.edu.idat.soporte.model;

/**
 * Representa al personal técnico asignado para resolver incidencias.
 * Hereda de Persona y especifica el área de especialidad.
 */
public class Tecnico extends Persona {
    private String especialidad;

    public Tecnico() {
        super();
    }

    public Tecnico(Long id, String nombre, String email, String telefono, String especialidad) {
        super(id, nombre, email, telefono);
        this.especialidad = especialidad;
    }

    @Override
    public String obtenerRol() {
        return "TECNICO";
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "especialidad='" + especialidad + '\'' +
                "} " + super.toString();
    }
}
