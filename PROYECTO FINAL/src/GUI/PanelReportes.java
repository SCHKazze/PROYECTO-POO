package GUI;
import DAO.FacturaDAO;
import Modelo.Factura;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelReportes extends JPanel {
    private JLabel lblCajaTotal;

    public PanelReportes() {
        setLayout(new BorderLayout());
        JPanel central = new JPanel(new FlowLayout());

        JButton btnCalcular = new JButton("Calcular Arqueo de Caja del Mes");
        lblCajaTotal = new JLabel("Ingresos Totales Brutos: $0.00");
        lblCajaTotal.setFont(new Font("Arial", Font.BOLD, 18));

        central.add(btnCalcular);
        central.add(lblCajaTotal);
        add(central, BorderLayout.CENTER);

        btnCalcular.addActionListener(e -> {
            try {
                FacturaDAO dao = new FacturaDAO();
                ArrayList<Factura> facturas = dao.leerTodo();
                double acum = 0;
                for(Factura f : facturas) {
                    acum += f.getTotal();
                }
                lblCajaTotal.setText("Ingresos Totales Brutos (Base Mongo): $" + String.format("%.2f", acum));
            } catch(Exception ex) {
                lblCajaTotal.setText("Error al conectar.");
            }
        });
    }
}