package Utilidades;
import java.util.UUID;

public class GeneradorCodigo {
    public static String generarIdCorto(String prefijo) {
        return prefijo + "-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}