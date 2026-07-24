package GUI;

import javax.swing.*;
import java.awt.*;

public class Principal extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelContenedor;

    public Principal() {
        setTitle("Taller Mecánico Avanzado v1.0");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout Principal
        setLayout(new BorderLayout());

        // Inicializar Menú Superior
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuModulos = new JMenu("Secciones del Sistema");

        JMenuItem itemInicio = new JMenuItem("Inicio");
        JMenuItem itemClientes = new JMenuItem("Gestión Clientes");
        JMenuItem itemVehiculos = new JMenuItem("Control Vehículos");
        JMenuItem itemMecanicos = new JMenuItem("Equipo Mecánicos");
        JMenuItem itemReparaciones = new JMenuItem("Ordenes Reparación");
        JMenuItem itemFacturas = new JMenuItem("Facturación");
        JMenuItem itemReportes = new JMenuItem("Reportes Consolidados");

        menuModulos.add(itemInicio);
        menuModulos.addSeparator();
        menuModulos.add(itemClientes);
        menuModulos.add(itemVehiculos);
        menuModulos.add(itemMecanicos);
        menuModulos.add(itemReparaciones);
        menuModulos.add(itemFacturas);
        menuModulos.add(itemReportes);
        barraMenu.add(menuModulos);
        setJMenuBar(barraMenu);

        // Configurar CardLayout para las pantallas
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Añadir paneles reales al contenedor
        panelContenedor.add(new PanelInicio(), "Inicio");
        panelContenedor.add(new PanelClientes(), "Clientes");
        panelContenedor.add(new PanelVehiculos(), "Vehiculos");
        panelContenedor.add(new Mecanicos(), "Mecanicos"); // Coincide con tu clase sin "Panel"
        panelContenedor.add(new PanelReparaciones(), "Reparaciones");
        panelContenedor.add(new PanelFacturas(), "Facturas");
        panelContenedor.add(new PanelReportes(), "Reportes");

        add(panelContenedor, BorderLayout.CENTER);

        // Control de navegación mediante eventos ActionListeners
        itemInicio.addActionListener(e -> cardLayout.show(panelContenedor, "Inicio"));
        itemClientes.addActionListener(e -> cardLayout.show(panelContenedor, "Clientes"));
        itemVehiculos.addActionListener(e -> cardLayout.show(panelContenedor, "Vehiculos"));
        itemMecanicos.addActionListener(e -> cardLayout.show(panelContenedor, "Mecanicos"));
        itemReparaciones.addActionListener(e -> cardLayout.show(panelContenedor, "Reparaciones"));
        itemFacturas.addActionListener(e -> cardLayout.show(panelContenedor, "Facturas"));
        itemReportes.addActionListener(e -> cardLayout.show(panelContenedor, "Reportes"));

        // Mostrar vista por defecto
        cardLayout.show(panelContenedor, "Inicio");
    }
}