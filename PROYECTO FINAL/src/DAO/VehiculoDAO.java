package DAO;

import Modelo.Vehiculo;
import Modelo.TipoVehiculo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;

public class VehiculoDAO implements CrudDAO<Vehiculo> {
    private MongoCollection<Document> coleccion;

    public VehiculoDAO() {
        this.coleccion = ConexionMongo.getInstancia().getBaseDatos().getCollection("vehiculos");
    }

    @Override
    public void crear(Vehiculo v) throws Exception {
        Document doc = new Document("_id", v.getPlaca())
                .append("marca", v.getMarca())
                .append("modelo", v.getModelo())
                .append("tipo", v.getTipo().name())
                .append("idCliente", v.getIdCliente());
        coleccion.insertOne(doc);
    }

    @Override
    public ArrayList<Vehiculo> leerTodo() throws Exception {
        ArrayList<Vehiculo> lista = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            lista.add(new Vehiculo(
                    doc.getString("_id"),
                    doc.getString("marca"),
                    doc.getString("modelo"),
                    TipoVehiculo.valueOf(doc.getString("tipo")),
                    doc.getString("idCliente")
            ));
        }
        return lista;
    }

    @Override
    public void actualizar(String placa, Vehiculo v) throws Exception {
        Document doc = new Document("marca", v.getMarca())
                .append("modelo", v.getModelo())
                .append("tipo", v.getTipo().name())
                .append("idCliente", v.getIdCliente());
        coleccion.updateOne(Filters.eq("_id", placa), new Document("$set", doc));
    }

    @Override
    public void eliminar(String placa) throws Exception {
        coleccion.deleteOne(Filters.eq("_id", placa));
    }
}
