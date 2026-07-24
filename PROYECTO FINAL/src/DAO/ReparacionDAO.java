package DAO;

import Modelo.Reparacion;
import Modelo.EstadoReparacion;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;

public class ReparacionDAO implements CrudDAO<Reparacion> {
    private MongoCollection<Document> coleccion;

    public ReparacionDAO() {
        this.coleccion = ConexionMongo.getInstancia().getBaseDatos().getCollection("reparaciones");
    }

    @Override
    public void crear(Reparacion r) throws Exception {
        Document doc = new Document("_id", r.getCodigo())
                .append("placaVehiculo", r.getPlacaVehiculo())
                .append("idMecanico", r.getIdMecanico())
                .append("descripcion", r.getDescripcion())
                .append("costoEstimado", r.getCostoEstimado())
                .append("estado", r.getEstado().name());
        coleccion.insertOne(doc);
    }

    @Override
    public ArrayList<Reparacion> leerTodo() throws Exception {
        ArrayList<Reparacion> lista = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            lista.add(new Reparacion(
                    doc.getString("_id"),
                    doc.getString("placaVehiculo"),
                    doc.getString("idMecanico"),
                    doc.getString("descripcion"),
                    doc.getDouble("costoEstimado"),
                    EstadoReparacion.valueOf(doc.getString("estado"))
            ));
        }
        return lista;
    }

    @Override
    public void actualizar(String codigo, Reparacion r) throws Exception {
        Document doc = new Document("placaVehiculo", r.getPlacaVehiculo())
                .append("idMecanico", r.getIdMecanico())
                .append("descripcion", r.getDescripcion())
                .append("costoEstimado", r.getCostoEstimado())
                .append("estado", r.getEstado().name());
        coleccion.updateOne(Filters.eq("_id", codigo), new Document("$set", doc));
    }

    @Override
    public void eliminar(String codigo) throws Exception {
        coleccion.deleteOne(Filters.eq("_id", codigo));
    }
}