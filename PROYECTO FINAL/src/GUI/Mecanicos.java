package GUI;

import DAO.MecanicoDAO;
import Modelo.Mecanico;
import Utilidades.Mensajes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Mecanicos extends JPanel {
    private JTextField txtId, txtNombre, txtTelefono, txtEspecialidad;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnCrear, btnEliminar;
    private MecanicoDAO dao;

    public Mecanicos() {
        dao = new MecanicoDAO();
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Personal Técnico"));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtEspecialidad = new JTextField();

        panelForm.add(new JLabel("ID Mecánico:")); panelForm.add(txtId);
        panelForm.add(new JLabel("Nombre Completo:")); panelForm.add(txtNombre);
        panelForm.add(new JLabel("Teléfono:")); panelForm.add(txtTelefono);
        panelForm.add(new JLabel("Especialidad Técnica:")); panelForm.add(txtEspecialidad);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnCrear = new JButton("Añadir Técnico");
        btnEliminar = new JButton("Remover Fila");
        panelBotones.add(btnCrear); panelBotones.add(btnEliminar);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.add(panelForm); wrapper.add(panelBotones);
        add(wrapper, BorderLayout.WEST);

        modelo = new DefaultTableModel(new String[]{"ID Técnico", "Nombre", "Teléfono", "Especialidad"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnCrear.addActionListener(e -> {
            try {
                Mecanico m = new Mecanico(txtId.getText(), txtNombre.getText(), txtTelefono.getText(), txtEspecialidad.getText());
                dao.crear(m);
                refrescar();
            } catch (Exception ex) { Mensajes.error(this, ex.getMessage()); }
        });

        btnEliminar.addActionListener(e -> {
            try {
                dao.eliminar(txtId.getText());
                refrescar();
            } catch (Exception ex) { Mensajes.error(this, ex.getMessage()); }
        });

        refrescar();
    }

    private void refrescar() {
        modelo.setRowCount(0);
        try {
            ArrayList<Mecanico> lista = dao.leerTodo();
            for(Mecanico m : lista) {
                modelo.addRow(new Object[]{m.getId(), m.getNombre(), m.getTelefono(), m.getEspecialidad()});
            }
        } catch(Exception e) {}
    }
}