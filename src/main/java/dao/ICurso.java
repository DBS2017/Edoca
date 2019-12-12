package dao;

import java.util.List;
import modelo.Curso;


public interface ICurso{
    
     void registrar (Curso curso)throws Exception;
     
     void modificar (Curso curso)throws Exception;
     
     void eliminar (Curso curso) throws Exception;
     
     List<Curso> listarCur() throws Exception;
     
}