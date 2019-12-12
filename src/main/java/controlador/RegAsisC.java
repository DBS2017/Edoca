package controlador;

import dao.Impl.PromedioAsisImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import modelo.PromedioAsis;


@Named(value = "promedioasisC")
@SessionScoped
public class RegAsisC implements Serializable {

    private PromedioAsis promedio = new PromedioAsis();
    private List<PromedioAsis> listaPromAsis;
    private List<PromedioAsis> listaAula;
    private List<PromedioAsis> listarEstudiante;
    PromedioAsisImpl dao;

    @PostConstruct
    public void init() {
        try {
            listarCurso();
        } catch (Exception e) {
        }

    }

    public void listarCurso() throws Exception {
        try {
            dao = new PromedioAsisImpl();
            listaPromAsis = dao.listarPorCurs();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarEstu(String codAula, String codCur) throws Exception {
        try {
            dao = new PromedioAsisImpl();
            listarEstudiante = dao.ListarporAula(codAula, codCur);
        } catch (SQLException e) {
            throw e;
        }
    }
    

    public void promediarAsis(String codAula) throws Exception {
        try {
            dao = new PromedioAsisImpl();
            listaPromAsis = dao.PromediarAsis(codAula);
        } catch (SQLException e) {
            throw e;
        }
    }

    public PromedioAsis getPromedio() {
        return promedio;
    }

    public void setPromedio(PromedioAsis promedio) {
        this.promedio = promedio;
    }

    public List<PromedioAsis> getListaPromAsis() {
        return listaPromAsis;
    }

    public void setListaPromAsis(List<PromedioAsis> listaPromAsis) {
        this.listaPromAsis = listaPromAsis;
    }

    public List<PromedioAsis> getListaAula() {
        return listaAula;
    }

    public void setListaAula(List<PromedioAsis> listaAula) {
        this.listaAula = listaAula;
    }

    public List<PromedioAsis> getListarEstudiante() {
        return listarEstudiante;
    }

    public void setListarEstudiante(List<PromedioAsis> listarEstudiante) {
        this.listarEstudiante = listarEstudiante;
    }

    public PromedioAsisImpl getDao() {
        return dao;
    }

    public void setDao(PromedioAsisImpl dao) {
        this.dao = dao;
    }
    
  
    
}
