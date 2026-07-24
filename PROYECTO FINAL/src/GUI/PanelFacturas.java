package GUI;

import DAO.FacturaDAO;
import Modelo.Factura;
import Utilidades.GeneradorCodigo;
import Utilidades.Mensajes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelFacturas extends JPanel {
    private JTextField txtNro, txtCodReparacion, txtSubtotal;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnCrear, btnGen;
    private FacturaDAO dao;

    public PanelFacturas() {
        dao = new FacturaDAO();
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Cierre de Caja y Facturación"));

        txtNro = new JTextField(); txtNro.setEditable(false);
        txtCodReparacion = new JTextField();
        txtSubtotal = new JTextField();
        btnGen = new JButton("Nro Aut.");

        panelForm.add(btnGen); panelForm.add(txtNro);
        panelForm.add(new JLabel("Código de Reparación:")); panelForm.add(txtCodReparacion);
        panelForm.add(new JLabel("Subtotal ($):")); panelForm.add(txtSubtotal);

        btnCrear = new JButton("Emitir Factura Legal");
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.add(panelForm); wrapper.add(btnCrear);
        add(wrapper, BorderLayout.WEST);

        modelo = new DefaultTableModel(new String[]{"Nro Factura", "Cod Reparación", "Subtotal", "Total (c/IVA)"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnGen.addActionListener(e -> txtNro.setText(GeneradorCodigo.generarIdCorto("FAC")));

        btnCrear.addActionListener(e -> {
            try {
                Factura f = new Factura(txtNro.getText(), txtCodReparacion.getText(), Double.parseDouble(txtSubtotal.getText()));
                dao.crear(f);
                refrescar();
            } catch (Exception ex) { Mensajes.error(this, ex.getMessage()); }
        });

        refrescar();
    }

    private void refrescar() {
        modelo.setRowCount(0);
        try {
            ArrayList<Factura> lista = dao.leerTodo();
            for(Factura f : lista) {
                modelo.addRow(new Object[]{f.getNroFactura(), f.getCodigoReparacion(), f.getSubtotal(), f.getTotal()});
            }
        } catch (Exception e){}
    }
}
