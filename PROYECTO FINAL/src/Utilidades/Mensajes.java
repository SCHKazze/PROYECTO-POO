package Utilidades;
import javax.swing.JOptionPane;
import java.awt.Component;

public class Mensajes {
    public static void exito(Component padre, String msg) {
        JOptionPane.showMessageDialog(padre, msg, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(Component padre, String msg) {
        JOptionPane.showMessageDialog(padre, msg, "Error de Aplicación", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirmar(Component padre, String msg) {
        int respuesta = JOptionPane.showConfirmDialog(padre, msg, "Confirmar Acción", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
