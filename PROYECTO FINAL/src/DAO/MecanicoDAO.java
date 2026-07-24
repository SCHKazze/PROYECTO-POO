package DAO;

import Modelo.Mecanico;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;

public class MecanicoDAO implements CrudDAO<Mecanico> {
    private MongoCollection<Document> coleccion;

    public MecanicoDAO() {
        this.coleccion = ConexionMongo.getInstancia().getBaseDatos().getCollection("mecanicos");
    }

    @Override
    public void crear(Mecanico m) throws Exception {
        Document doc = new Document("_id", m.getId())
                .append("nombre", m.getNombre())
                .append("telefono", m.getTelefono())
                .append("especialidad", m.getEspecialidad());
        coleccion.insertOne(doc);
    }

    @Override
    public ArrayList<Mecanico> leerTodo() throws Exception {
        ArrayList<Mecanico> lista = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            lista.add(new Mecanico(
                    doc.getString("_id"),
                    doc.getString("nombre"),
                    doc.getString("telefono"),
                    doc.getString("especialidad")
            ));
        }
        return lista;
    }

    @Override
    public void actualizar(String id, Mecanico m) throws Exception {
        Document doc = new Document("nombre", m.getNombre())
                .append("telefono", m.getTelefono())
                .append("especialidad", m.getEspecialidad());
        coleccion.updateOne(Filters.eq("_id", id), new Document("$set", doc));
    }

    @Override
    public void eliminar(String id) throws Exception {
        coleccion.deleteOne(Filters.eq("_id", id));
    }
}
