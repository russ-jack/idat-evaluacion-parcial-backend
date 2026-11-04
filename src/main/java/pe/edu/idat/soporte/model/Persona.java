package pe.edu.idat.soporte.model;

import java.util.Objects;

/**
 * Superclase abstracta que encapsula los atributos comunes para personas en el sistema.
 * Aplica los principios de Abstracción y Herencia en POO.
 */
public abstract class Persona {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;

    public Persona() {
    }

    public Persona(Long id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * Método polimórfico que cada subclase concreta debe implementar.
     * @return El nombre del rol de la persona.
     */
    public abstract String obtenerRol();

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(id, persona.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
