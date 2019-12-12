package dao;

import java.util.List;
import modelo.Estudiante;


public interface IEstudiante{
    
     void registrar (Estudiante estudiante)throws Exception;
     
     void modificar (Estudiante estudiante)throws Exception;
     
     void eliminar (Estudiante estudiante) throws Exception;
     
     List<Estudiante> listarEst() throws Exception;
             
    
}
