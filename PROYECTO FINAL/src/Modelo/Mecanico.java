package Modelo;

public class Mecanico extends Persona {
    private String especialidad;

    public Mecanico(String id, String nombre, String telefono, String especialidad) {
        super(id, nombre, telefono);
        this.especialidad = especialidad;
    }

    @Override
    public String getDetallesRol() {
        return "Mecánico - ID: " + getId() + ", Especialidad: " + especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}