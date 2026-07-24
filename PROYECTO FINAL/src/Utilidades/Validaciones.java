package Utilidades;

public class Validaciones {
    public static boolean esNumerico(String texto) {
        return texto != null && texto.matches("\\d+");
    }

    public static boolean esDecimal(String texto) {
        return texto != null && texto.matches("\\d+(\\.\\d+)?");
    }

    public static boolean validarCorreo(String correo) {
        return correo != null && correo.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}