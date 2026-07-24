package Modelo;

public class Factura {
    private String nroFactura;
    private String codigoReparacion;
    private double subtotal;
    private double total;

    public Factura(String nroFactura, String codigoReparacion, double subtotal) {
        this.nroFactura = nroFactura;
        this.codigoReparacion = codigoReparacion;
        this.subtotal = subtotal;
        this.total = subtotal * 1.15; // Aplicación de IVA ejemplo del 15%
    }

    public String getNroFactura() { return nroFactura; }
    public void setNroFactura(String nroFactura) { this.nroFactura = nroFactura; }
    public String getCodigoReparacion() { return codigoReparacion; }
    public void setCodigoReparacion(String codigoReparacion) { this.codigoReparacion = codigoReparacion; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
