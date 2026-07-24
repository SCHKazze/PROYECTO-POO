package DAO;

import Modelo.Cliente;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;

public class ClienteDAO implements CrudDAO<Cliente> {
    private MongoCollection<Document> coleccion;

    public ClienteDAO() {
        this.coleccion = ConexionMongo.getInstancia().getBaseDatos().getCollection("clientes");
    }

    @Override
    public void crear(Cliente c) throws Exception {
        Document doc = new Document("_id", c.getId())
                .append("nombre", c.getNombre())
                .append("telefono", c.getTelefono())
                .append("correo", c.getCorreo());
        coleccion.insertOne(doc);
    }

    @Override
    public ArrayList<Cliente> leerTodo() throws Exception {
        ArrayList<Cliente> lista = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            lista.add(new Cliente(
                    doc.getString("_id"),
                    doc.getString("nombre"),
                    doc.getString("telefono"),
                    doc.getString("correo")
            ));
        }
        return lista;
    }

    @Override
    public void actualizar(String id, Cliente c) throws Exception {
        Document doc = new Document("nombre", c.getNombre())
                .append("telefono", c.getTelefono())
                .append("correo", c.getCorreo());
        coleccion.updateOne(Filters.eq("_id", id), new Document("$set", doc));
    }

    @Override
    public void eliminar(String id) throws Exception {
        coleccion.deleteOne(Filters.eq("_id", id));
    }
}
