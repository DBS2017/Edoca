package controlador;

import dao.Conexion;
import dao.Impl.PromedioImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Promedio;

@Named(value = "promedioC")
@SessionScoped
public class PromedioC implements Serializable {

    private Promedio promedio = new Promedio();
    private List<Promedio> listaCurso;
    private List<Promedio> listaAula;
    private List<Promedio> listarEstudiante;
    private PromedioImpl dao;

    @PostConstruct
    public void init() {
        try {
            listarCurso();
            
        } catch (Exception e) {
        }

    }

    public void listarCurso() throws Exception {
        try {
            dao = new PromedioImpl();
            listaCurso = dao.listarPorCurs();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarEstu(String codAula, String codCur) throws Exception {
        try {
            dao = new PromedioImpl();
            listarEstudiante = dao.ListarporAula(codAula, codCur);
        } catch (Exception e) {
            throw e;
        }
    }

    public Promedio getPromedio() {
        return promedio;
    }

    public void setPromedio(Promedio promedio) {
        this.promedio = promedio;
    }

    public List<Promedio> getListaCurso() {
        return listaCurso;
    }

    public void setListaCurso(List<Promedio> listaCurso) {
        this.listaCurso = listaCurso;
    }

    public List<Promedio> getListaAula() {
        return listaAula;
    }

    public void setListaAula(List<Promedio> listaAula) {
        this.listaAula = listaAula;
    }

    public List<Promedio> getListarEstudiante() {
        return listarEstudiante;
    }

    public void setListarEstudiante(List<Promedio> listarEstudiante) {
        this.listarEstudiante = listarEstudiante;
    }

    public PromedioImpl getDao() {
        return dao;
    }

    public void setDao(PromedioImpl dao) {
        this.dao = dao;
    }

    
}
