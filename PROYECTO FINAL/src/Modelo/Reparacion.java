package Modelo;

public class Reparacion {
    private String codigo;
    private String placaVehiculo;
    private String idMecanico;
    private String descripcion;
    private double costoEstimado;
    private EstadoReparacion estado; // Tu Enum

    public Reparacion(String codigo, String placaVehiculo, String idMecanico, String descripcion, double costoEstimado, EstadoReparacion estado) {
        this.codigo = codigo;
        this.placaVehiculo = placaVehiculo;
        this.idMecanico = idMecanico;
        this.descripcion = descripcion;
        this.costoEstimado = costoEstimado;
        this.estado = estado;
    }

    // Getters y Setters corregidos
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getPlacaVehiculo() { return placaVehiculo; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }

    public String getIdMecanico() { return idMecanico; }
    public void setIdMecanico(String idMecanico) { this.idMecanico = idMecanico; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getCostoEstimado() { return costoEstimado; }
    public void setCostoEstimado(double costoEstimado) { this.costoEstimado = costoEstimado; }

    // AQUÍ ESTABA EL ERROR: Debe retornar EstadoReparacion, NO String
    public EstadoReparacion getEstado() { return estado; }
    public void setEstado(EstadoReparacion estado) { this.estado = estado; }
}