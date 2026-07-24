package GUI;

import DAO.VehiculoDAO;
import Modelo.Vehiculo;
import Modelo.TipoVehiculo;
import Utilidades.Mensajes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelVehiculos extends JPanel {
    private JTextField txtPlaca, txtMarca, txtModelo, txtIdCliente;
    private JComboBox<TipoVehiculo> cbTipo;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnCrear, btnActualizar, btnEliminar;
    private VehiculoDAO dao;

    public PanelVehiculos() {
        dao = new VehiculoDAO();
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Registro Automotor"));

        txtPlaca = new JTextField();
        txtMarca = new JTextField();
        txtModelo = new JTextField();
        txtIdCliente = new JTextField();
        cbTipo = new JComboBox<>(TipoVehiculo.values());

        panelForm.add(new JLabel("Nro Placa:")); panelForm.add(txtPlaca);
        panelForm.add(new JLabel("Marca:")); panelForm.add(txtMarca);
        panelForm.add(new JLabel("Modelo Año:")); panelForm.add(txtModelo);
        panelForm.add(new JLabel("Categoría:")); panelForm.add(cbTipo);
        panelForm.add(new JLabel("ID Dueño (Cliente):")); panelForm.add(txtIdCliente);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnCrear = new JButton("Registrar Vehículo");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Remover");
        panelBotones.add(btnCrear); panelBotones.add(btnActualizar); panelBotones.add(btnEliminar);

        JPanel contenedorIzquierdo = new JPanel();
        contenedorIzquierdo.setLayout(new BoxLayout(contenedorIzquierdo, BoxLayout.Y_AXIS));
        contenedorIzquierdo.add(panelForm);
        contenedorIzquierdo.add(panelBotones);
        add(contenedorIzquierdo, BorderLayout.WEST);

        modelo = new DefaultTableModel(new String[]{"Placa", "Marca", "Modelo", "Tipo", "ID Cliente"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        configurarAcciones();
        refrescar();
    }

    private void refrescar() {
        modelo.setRowCount(0);
        try {
            ArrayList<Vehiculo> lista = dao.leerTodo();
            for(Vehiculo v : lista) {
                modelo.addRow(new Object[]{v.getPlaca(), v.getMarca(), v.getModelo(), v.getTipo().name(), v.getIdCliente()});
            }
        } catch (Exception e) {
            Mensajes.error(this, e.getMessage());
        }
    }

    private void configurarAcciones() {
        btnCrear.addActionListener(e -> {
            try {
                Vehiculo v = new Vehiculo(txtPlaca.getText(), txtMarca.getText(), txtModelo.getText(), (TipoVehiculo) cbTipo.getSelectedItem(), txtIdCliente.getText());
                dao.crear(v);
                refrescar();
            } catch (Exception ex) { Mensajes.error(this, ex.getMessage()); }
        });
        btnActualizar.addActionListener(e -> {
            try {
                Vehiculo v = new Vehiculo(txtPlaca.getText(), txtMarca.getText(), txtModelo.getText(), (TipoVehiculo) cbTipo.getSelectedItem(), txtIdCliente.getText());
                dao.actualizar(txtPlaca.getText(), v);
                refrescar();
            } catch (Exception ex) { Mensajes.error(this, ex.getMessage()); }
        });
        btnEliminar.addActionListener(e -> {
            try {
                dao.eliminar(txtPlaca.getText());
                refrescar();
            } catch (Exception ex) { Mensajes.error(this, ex.getMessage()); }
        });

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int f = tabla.getSelectedRow();
                if(f >= 0) {
                    txtPlaca.setText(modelo.getValueAt(f, 0).toString());
                    txtMarca.setText(modelo.getValueAt(f, 1).toString());
                    txtModelo.setText(modelo.getValueAt(f, 2).toString());
                    cbTipo.setSelectedItem(TipoVehiculo.valueOf(modelo.getValueAt(f, 3).toString()));
                    txtIdCliente.setText(modelo.getValueAt(f, 4).toString());
                }
            }
        });
    }
}