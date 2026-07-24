package DAO;

import Modelo.Factura;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;

public class FacturaDAO implements CrudDAO<Factura> {
    private MongoCollection<Document> coleccion;

    public FacturaDAO() {
        this.coleccion = ConexionMongo.getInstancia().getBaseDatos().getCollection("facturas");
    }

    @Override
    public void crear(Factura f) throws Exception {
        Document doc = new Document("_id", f.getNroFactura())
                .append("codigoReparacion", f.getCodigoReparacion())
                .append("subtotal", f.getSubtotal())
                .append("total", f.getTotal());
        coleccion.insertOne(doc);
    }

    @Override
    public ArrayList<Factura> leerTodo() throws Exception {
        ArrayList<Factura> lista = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            Factura f = new Factura(
                    doc.getString("_id"),
                    doc.getString("codigoReparacion"),
                    doc.getDouble("subtotal")
            );
            f.setTotal(doc.getDouble("total"));
            lista.add(f);
        }
        return lista;
    }

    @Override
    public void actualizar(String nroFactura, Factura f) throws Exception {
        Document doc = new Document("codigoReparacion", f.getCodigoReparacion())
                .append("subtotal", f.getSubtotal())
                .append("total", f.getTotal());
        coleccion.updateOne(Filters.eq("_id", nroFactura), new Document("$set", doc));
    }

    @Override
    public void eliminar(String nroFactura) throws Exception {
        coleccion.deleteOne(Filters.eq("_id", nroFactura));
    }
}