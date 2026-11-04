package pe.edu.idat.soporte.model;

/**
 * Representa al cliente que reporta las solicitudes de soporte técnico.
 * Hereda de Persona y añade información empresarial.
 */
public class Cliente extends Persona {
    private String empresa;

    public Cliente() {
        super();
    }

    public Cliente(Long id, String nombre, String email, String telefono, String empresa) {
        super(id, nombre, email, telefono);
        this.empresa = empresa;
    }

    @Override
    public String obtenerRol() {
        return "CLIENTE";
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "empresa='" + empresa + '\'' +
                "} " + super.toString();
    }
}
