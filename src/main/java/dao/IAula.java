package dao;

import java.util.List;
import modelo.Aula;

public interface IAula{
    
    void registrar(Aula aula) throws Exception;

    void modificar(Aula aula) throws Exception;

    void eliminar(Aula aula) throws Exception;

    List<Aula> listarAul() throws Exception;
    
}
