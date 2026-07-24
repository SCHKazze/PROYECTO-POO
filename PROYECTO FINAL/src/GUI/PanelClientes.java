package GUI;

import DAO.ClienteDAO;
import Modelo.Cliente;
import Utilidades.Mensajes;
import Utilidades.Validaciones;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelClientes extends JPanel {
    private JTextField txtId, txtNombre, txtTelefono, txtCorreo;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnCrear, btnActualizar, btnEliminar, btnLimpiar;
    private ClienteDAO dao;

    public PanelClientes() {
        dao = new ClienteDAO();
        setLayout(new BorderLayout(10, 10));

        // Formulario izquierdo (GridLayout)
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createTitledBorder("Información de Clientes"));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();

        panelForm.add(new JLabel("Cédula / RUC ID:")); panelForm.add(txtId);
        panelForm.add(new JLabel("Nombre Completo:")); panelForm.add(txtNombre);
        panelForm.add(new JLabel("Teléfono de Contacto:")); panelForm.add(txtTelefono);
        panelForm.add(new JLabel("Correo Electrónico:")); panelForm.add(txtCorreo);

        // Control de botones inferior (FlowLayout)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnCrear = new JButton("Registrar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnCrear); panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);

        // Agrupar controles de edición (BoxLayout)
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.add(panelForm);
        panelIzquierdo.add(panelBotones);
        add(panelIzquierdo, BorderLayout.WEST);

        // Tabla de datos a la derecha (JTable)
        String[] columnas = {"ID Identificación", "Nombre Completo", "Teléfono", "Correo"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        configurarEventos();
        refrescarTabla();
    }

    private void refrescarTabla() {
        modelo.setRowCount(0);
        try {
            ArrayList<Cliente> lista = dao.leerTodo();
            for (Cliente c : lista) {
                modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getTelefono(), c.getCorreo()});
            }
        } catch (Exception e) {
            Mensajes.error(this, "Fallo al leer datos: " + e.getMessage());
        }
    }

    private void configurarEventos() {
        btnCrear.addActionListener(e -> {
            try {
                if(txtId.getText().isEmpty() || txtNombre.getText().isEmpty()) {
                    throw new IllegalArgumentException("Campos de llave ID y Nombre son obligatorios.");
                }
                if(!Validaciones.validarCorreo(txtCorreo.getText())) {
                    throw new IllegalArgumentException("Estructura de correo inválida.");
                }

                Cliente c = new Cliente(txtId.getText(), txtNombre.getText(), txtTelefono.getText(), txtCorreo.getText());
                dao.crear(c);
                Mensajes.exito(this, "Cliente ingresado correctamente.");
                refrescarTabla();
                limpiar();
            } catch (Exception ex) {
                Mensajes.error(this, ex.getMessage());
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                Cliente c = new Cliente(txtId.getText(), txtNombre.getText(), txtTelefono.getText(), txtCorreo.getText());
                dao.actualizar(txtId.getText(), c);
                Mensajes.exito(this, "Datos del cliente modificados.");
                refrescarTabla();
                limpiar();
            } catch (Exception ex) {
                Mensajes.error(this, ex.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                if(Mensajes.confirmar(this, "¿Dar de baja este cliente permanentemente?")) {
                    dao.eliminar(txtId.getText());
                    refrescarTabla();
                    limpiar();
                }
            } catch (Exception ex) {
                Mensajes.error(this, ex.getMessage());
            }
        });

        btnLimpiar.addActionListener(e -> limpiar());

        // Evento de Selección de Tabla (MouseListener)
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if(fila >= 0) {
                    txtId.setText(modelo.getValueAt(fila, 0).toString());
                    txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                    txtTelefono.setText(modelo.getValueAt(fila, 2).toString());
                    txtCorreo.setText(modelo.getValueAt(fila, 3).toString());
                }
            }
        });

        // Evento Restrictivo de escritura (KeyListener)
        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c)) e.consume();
            }
        });
    }

    private void limpiar() {
        txtId.setText(""); txtNombre.setText(""); txtTelefono.setText(""); txtCorreo.setText("");
    }
}
