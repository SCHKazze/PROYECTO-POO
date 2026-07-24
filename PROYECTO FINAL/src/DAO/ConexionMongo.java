package DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {
    private static ConexionMongo instancia;
    private MongoClient mongoClient;
    private MongoDatabase baseDatos;

    private ConexionMongo() {
        try {
            // Se conecta de forma segura a tu servidor local de MongoDB en el puerto estándar
            MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
            this.mongoClient = new MongoClient(uri);
            this.baseDatos = mongoClient.getDatabase("TallerMecanicoDB");
            System.out.println("¡Conexión establecida exitosamente con MongoDB!");
        } catch (Exception e) {
            System.err.println("Error crítico al enlazar con MongoDB: " + e.getMessage());
        }
    }

    public static synchronized ConexionMongo getInstancia() {
        if (instancia == null) {
            instancia = new ConexionMongo();
        }
        return instancia;
    }

    public MongoDatabase getBaseDatos() {
        return baseDatos;
    }
}