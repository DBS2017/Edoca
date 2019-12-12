package controlador;

import dao.Impl.AulaImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Aula;

@Named(value = "aulaC")
@SessionScoped
public class AulaC implements Serializable {

    private Aula aula;
    private AulaImpl dao;
    private List<Aula> listadoAul;
    private String acctionAul;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("Error init aula " + e.getMessage());
        }
    }

    public AulaC() {
        dao = new AulaImpl();
        aula = new Aula();
        listadoAul = new ArrayList();

    }

    public void operadorAula() throws Exception {
        switch (acctionAul) {
            case "Asignar":
                registrar();
                break;
            case "Modificar":
                modificar();
                break;
        }
    }

    public void limpiar()  {
        aula = new Aula();

    }

    public void registrar() throws Exception {
        
        try {
            dao = new AulaImpl();
            aula.setCodtut(dao.obtenerCodigoTutor(aula.getAutTut()));
            dao.registrar(aula);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aula", "Registrado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public  void leerAula(String codAula) throws Exception{
        try {
            dao = new AulaImpl();
            aula = dao.leerAula(codAula);
            acctionAul = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificar() throws Exception {
        
        try {
            dao = new AulaImpl();
            aula.setCodtut(dao.obtenerCodigoTutor(aula.getAutTut()));
            dao.modificar(aula);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aula", "Modificada"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Aula aul) throws Exception {
        try {
            dao.eliminar(aul);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aula", " Eliminada de tu vida:'v"));
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            listadoAul = dao.listarAul();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeTextTutor(String query) throws SQLException, Exception {
        AulaImpl Conexion = new AulaImpl();
        return Conexion.autocompleteTutor(query);
    }
    //codigo generado

    public String getAcctionAul() {
        return acctionAul;
    }

    public void setAcctionAul(String acctionAul) {
        limpiar();
        this.acctionAul = acctionAul;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public AulaImpl getDao() {
        return dao;
    }

    public void setDao(AulaImpl dao) {
        this.dao = dao;
    }

    public List<Aula> getListadoAul() {
        return listadoAul;
    }

    public void setListadoAul(List<Aula> listadoAul) {
        this.listadoAul = listadoAul;
    }

}
