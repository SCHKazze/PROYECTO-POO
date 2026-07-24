package Modelo;

public class Cliente extends Persona {
    private String correo;

    public Cliente(String id, String nombre, String telefono, String correo) {
        super(id, nombre, telefono);
        this.correo = correo;
    }

    @Override
    public String getDetallesRol() {
        return "Cliente - ID: " + getId() + ", Email: " + correo;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
