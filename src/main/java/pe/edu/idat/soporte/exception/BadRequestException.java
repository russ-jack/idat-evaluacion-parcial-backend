package pe.edu.idat.soporte.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}
