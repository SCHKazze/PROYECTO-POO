import GUI.Principal; //
import javax.swing.SwingUtilities; //
import javax.swing.UIManager; //
import java.awt.Color;

public class Main { //
    public static void main(String[] args) { //
        try { //
            // Definición de la paleta de colores
            Color azulMarino = new Color(15, 34, 64);
            Color blanco = Color.WHITE;
            Color negro = Color.BLACK;

            // Colores de fondo de las ventanas y paneles
            UIManager.put("Panel.background", azulMarino);
            UIManager.put("OptionPane.background", azulMarino);
            UIManager.put("Window.background", azulMarino);

            // Colores de los botones
            UIManager.put("Button.background", blanco);
            UIManager.put("Button.foreground", negro);

            // --- CORRECCIÓN: NOMBRES E INDICADORES (LABELS) EN BLANCO Y SIN FONDO ---
            UIManager.put("Label.background", azulMarino); // Se integra al fondo
            UIManager.put("Label.foreground", blanco);     // Texto completamente blanco y legible
            UIManager.put("Label.opaque", false);          // Mantiene la transparencia original
            // ------------------------------------------------------------------------

            // TEXTO DE PESTAÑAS (TabbedPane) EN BLANCO
            UIManager.put("TabbedPane.foreground", blanco);
            UIManager.put("TabbedPane.selectedForeground", blanco);
            UIManager.put("TabbedPane.background", azulMarino);

            // TÍTULOS DE BORDES EN BLANCO
            UIManager.put("TitledBorder.titleColor", blanco);

            // Colores de los campos de entrada de texto
            UIManager.put("TextField.background", blanco);
            UIManager.put("TextField.foreground", negro);
            UIManager.put("PasswordField.background", blanco);
            UIManager.put("PasswordField.foreground", negro);
            UIManager.put("TextArea.background", blanco);
            UIManager.put("TextArea.foreground", negro);

            // Colores de las tablas
            UIManager.put("Table.background", blanco);
            UIManager.put("Table.foreground", negro);
            UIManager.put("TableHeader.background", azulMarino);
            UIManager.put("TableHeader.foreground", blanco);

            // Colores de los componentes de selección (ComboBox, CheckBox)
            UIManager.put("ComboBox.background", blanco);
            UIManager.put("ComboBox.foreground", negro);
            UIManager.put("CheckBox.background", azulMarino);
            UIManager.put("CheckBox.foreground", blanco);

        } catch (Exception e) { //
            System.err.println("No se pudo establecer el tema de colores: " + e.getMessage()); //
        } //

        // Ejecutar la interfaz gráfica
        SwingUtilities.invokeLater(() -> { //
            Principal app = new Principal(); //
            app.setVisible(true); //
        }); //
    } //
} //