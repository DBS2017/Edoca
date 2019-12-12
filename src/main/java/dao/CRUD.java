package dao;

import java.util.List;

public interface CRUD<Modelo> {

//    intefaces para implementar en todas las maestras
    public void Registrar(Modelo modelo) throws Exception;

    public void Modificar(Modelo modelo) throws Exception;

    public void Eliminar(Modelo modelo) throws Exception;

}
