package dao;

import java.util.List;
import modelo.Curso_Aula;

public interface ICurso_Aula {
    
    public void insertar (Curso_Aula curso_aula) throws Exception;
    
    public void modificar (Curso_Aula curso_aula) throws Exception;
    
    public void eliminar (Curso_Aula curso_aula) throws Exception;
        
    List<Curso_Aula> listCurAul () throws Exception;
}
