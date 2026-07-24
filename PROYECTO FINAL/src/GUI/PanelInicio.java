package GUI;
import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel {
    public PanelInicio() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 244, 248));

        JLabel lblTitulo = new JLabel("Bienvenido al Sistema Operativo del Taller", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(33, 37, 41));

        JLabel lblSub = new JLabel("Utiliza la barra de menú superior para interactuar con las colecciones.", JLabel.CENTER);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 14));

        add(lblTitulo, BorderLayout.CENTER);
        add(lblSub, BorderLayout.SOUTH);

        // --- APLICAR PALETA DE COLORES GLOBAL AL PANEL INICIO ---
        java.awt.Color azulMarino = new java.awt.Color(15, 34, 64);
        java.awt.Color blanco = java.awt.Color.WHITE;
        java.awt.Color negro = java.awt.Color.BLACK;

        // Cambiar el fondo del panel principal
        this.setBackground(azulMarino);

        // Buscar y pintar todos los subcomponentes dentro de este panel de forma automática
        for (java.awt.Component comp : this.getComponents()) {
            // Si es un panel interno o un contenedor
            if (comp instanceof javax.swing.JPanel) {
                comp.setBackground(azulMarino);
            }
            // Si es un botón
            else if (comp instanceof javax.swing.JButton) {
                comp.setBackground(blanco);
                comp.setForeground(negro);
            }
            // Si es un texto indicativo (Label)
            else if (comp instanceof javax.swing.JLabel) {
                comp.setForeground(blanco);
                ((javax.swing.JLabel) comp).setOpaque(false);
            }
            // Si es una tabla
            else if (comp instanceof javax.swing.JTable) {
                comp.setBackground(blanco);
                comp.setForeground(negro);
            }
        }
        // --------------------------------------------------------
    }
}