package GUI;

import DAO.ReparacionDAO;
import Modelo.Reparacion;
import Modelo.EstadoReparacion;
import Utilidades.GeneradorCodigo;
import Utilidades.Mensajes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelReparaciones extends JPanel {
    private JTextField txtCodigo, txtPlaca, txtIdMecanico, txtDescripcion, txtCosto;
    private JComboBox<EstadoReparacion> cbEstado;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnCrear, btnActualizar, btnGenId;
    private ReparacionDAO dao;

    public PanelReparaciones() {
        dao = new ReparacionDAO();
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(6, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Orden de Servicio Técnico"));

        txtCodigo = new JTextField(); txtCodigo.setEditable(false);
        txtPlaca = new JTextField();
        txtIdMecanico = new JTextField();
        txtDescripcion = new JTextField();
        txtCosto = new JTextField();
        cbEstado = new JComboBox<>(EstadoReparacion.values());

        btnGenId = new JButton("Asignar Código Aut.");
        panelForm.add(btnGenId); panelForm.add(txtCodigo);
        panelForm.add(new JLabel("Placa de Vehículo:")); panelForm.add(txtPlaca);
        panelForm.add(new JLabel("ID Mecánico a Cargo:")); panelForm.add(txtIdMecanico);
        panelForm.add(new JLabel("Falla / Diagnóstico:")); panelForm.add(txtDescripcion);
        panelForm.add(new JLabel("Costo Estimado ($):")); panelForm.add(txtCosto);
        panelForm.add(new JLabel("Estado Operación:")); panelForm.add(cbEstado);

        JPanel pBotonera = new JPanel(new FlowLayout());
        btnCrear = new JButton("Abrir Orden");
        btnActualizar = new JButton("Actualizar Estado");
        pBotonera.add(btnCrear); pBotonera.add(btnActualizar);

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(panelForm); left.add(pBotonera);
        add(left, BorderLayout.WEST);

        modelo = new DefaultTableModel(new String[]{"Cod Orden", "Placa", "ID Mec", "Detalle", "Costo", "Estado"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnGenId.addActionListener(e -> txtCodigo.setText(GeneradorCodigo.generarIdCorto("REP")));

        btnCrear.addActionListener(e -> {
            try {
                Reparacion r = new Reparacion(txtCodigo.getText(), txtPlaca.getText(), txtIdMecanico.getText(), txtDescripcion.getText(), Double.parseDouble(txtCosto.getText()), (EstadoReparacion) cbEstado.getSelectedItem());
                dao.crear(r);
                refrescar();
            } catch (Exception ex) { Mensajes.error(this, ex.getMessage()); }
        });

        btnActualizar.addActionListener(e -> {
            try {
                Reparacion r = new Reparacion(txtCodigo.getText(), txtPlaca.getText(), txtIdMecanico.getText(), txtDescripcion.getText(), Double.parseDouble(txtCosto.getText()), (EstadoReparacion) cbEstado.getSelectedItem());
                dao.actualizar(txtCodigo.getText(), r);
                refrescar();
            } catch (Exception ex) { Mensajes.error(this, ex.getMessage()); }
        });

        refrescar();
    }

    private void refrescar() {
        modelo.setRowCount(0);
        try {
            ArrayList<Reparacion> lista = dao.leerTodo();
            for(Reparacion r : lista) {
                modelo.addRow(new Object[]{r.getCodigo(), r.getPlacaVehiculo(), r.getIdMecanico(), r.getDescripcion(), r.getCostoEstimado(), r.getEstado()});
            }
        } catch(Exception e){}
    }
}
