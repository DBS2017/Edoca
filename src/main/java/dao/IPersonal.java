package dao;

import java.util.List;
import modelo.Personal;


public interface IPersonal{
    
     void registrar (Personal personal)throws Exception;
     
     void modificar (Personal personal)throws Exception;
     
     void eliminar (Personal personal) throws Exception;
     
     List<Personal> listarPer() throws Exception;
             
    
}
