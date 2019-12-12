package controlador;

import dao.Impl.PersonalImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Personal;

@Named(value = "personalCon")
@SessionScoped
public class PersonalC implements Serializable {

    private Personal personal = new Personal();
    private PersonalImpl dao;
    private List<Personal> filterPers;
    private List<Personal> listadoPer;
    private String accionPer;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }

    }

    public PersonalC() {
        dao = new PersonalImpl();
        personal = new Personal();
        listadoPer = new ArrayList();

    }

    public void operarPersonal() throws Exception {
        switch (accionPer) {
            case "Registrar":
                registrar();
                break;
            case "Modificar":
                modificarPersonal();
                break;
        }
    }

    public void limpiar() {
        personal = new Personal();
    }

    public void registrar() throws Exception {
        try {
            dao = new PersonalImpl();
            dao.registrar(personal);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarPersonal() throws Exception {
        try {
            dao = new PersonalImpl();
            dao.modificar(personal);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", null));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Personal per) throws Exception {
        try {
            dao = new PersonalImpl();
            dao.eliminar(per);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Eliminado..."));
            listar();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerPersonal(String codigoPersonal) throws Exception {

        try {
            dao = new PersonalImpl();
            personal = dao.leerPersonal(codigoPersonal);
            accionPer = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        PersonalImpl Conexion;
        try {
            Conexion = new PersonalImpl();
            listadoPer = Conexion.listarPer();
        } catch (Exception e) {
            throw e;
        }
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public PersonalImpl getDao() {
        return dao;
    }

    public void setDao(PersonalImpl dao) {
        this.dao = dao;
    }

    public List<Personal> getFilterPers() {
        return filterPers;
    }

    public void setFilterPers(List<Personal> filterPers) {
        this.filterPers = filterPers;
    }

    public List<Personal> getListadoPer() {
        return listadoPer;
    }

    public void setListadoPer(List<Personal> listadoPer) {
        this.listadoPer = listadoPer;
    }

    public String getAccionPer() {
        return accionPer;
    }

    public void setAccionPer(String accionPer) {
        
        this.accionPer = accionPer;
    }

}
