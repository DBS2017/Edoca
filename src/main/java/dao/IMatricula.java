package dao;

import java.util.List;
import modelo.Matricula;

public interface IMatricula {

    void registrar(Matricula matricula) throws Exception;

    void modificar(Matricula matricula) throws Exception;

    void eliminar(Matricula matricula) throws Exception;

    List<Matricula> listarMat() throws Exception;

}
