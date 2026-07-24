package DAO;
import java.util.ArrayList;

public interface CrudDAO<T> {
    void crear(T entidad) throws Exception;
    ArrayList<T> leerTodo() throws Exception;
    void actualizar(String id, T entidad) throws Exception;
    void eliminar(String id) throws Exception;
}
