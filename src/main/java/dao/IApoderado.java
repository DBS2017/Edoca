package dao;

import java.util.List;
import modelo.Apoderado;

public interface IApoderado {

    void registrar(Apoderado apoderado) throws Exception;

    void modificar(Apoderado apoderado) throws Exception;

    void eliminar(Apoderado apoderado) throws Exception;

    List<Apoderado> listarApo() throws Exception;

}
