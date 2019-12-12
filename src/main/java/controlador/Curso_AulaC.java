package controlador;

import dao.Impl.Curso_AulaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Curso_Aula;

@Named(value = "curso_AulaC")
@SessionScoped
public class Curso_AulaC implements Serializable {

    private Curso_Aula curso_aula = new Curso_Aula();
    private Curso_AulaImpl dao;
    private List<Curso_Aula> listCursoAula;
    private String acctionCur;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
    }

    public Curso_AulaC() {
        dao = new Curso_AulaImpl();
        curso_aula = new Curso_Aula();
        listCursoAula = new ArrayList();

    }

    public void operadorCurso() throws Exception {
        switch (acctionCur) {
            case "Asignar":
                registrar();
                break;
            case "Modificar":
                modificar();
                break;
        }
    }

    public void limpiar() {
        curso_aula = new Curso_Aula();
    }

    public void modificar() throws Exception {
        try {
            dao = new Curso_AulaImpl();
            curso_aula.setIdPer(dao.obtenerCodigoTutor(curso_aula.getAutPer()));
            curso_aula.setCodCur(dao.obtCurso(curso_aula.getAutCur_Aul()));
            curso_aula.setCodAul(dao.obtAula(curso_aula.getCheAul()));
            dao.modificar(curso_aula);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Modificado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void registrar() throws Exception {
        try {
            dao = new Curso_AulaImpl();
            curso_aula.setIdPer(dao.obtenerCodigoTutor(curso_aula.getAutPer()));
            curso_aula.setCodCur(dao.obtCurso(curso_aula.getAutCur_Aul()));
            curso_aula.setCodAul(dao.obtAula(curso_aula.getCheAul()));
            dao.insertar(curso_aula);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado"));
        } catch (Exception e) {
            System.out.println("error en registar " + e.getMessage());
            throw e;
        }
    }

    public void eliminar(Curso_Aula curAul) throws Exception {        
        try {
            dao = new Curso_AulaImpl();
            dao.eliminar(curAul);
            listar();  
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino", "Eliminado"));
        } catch (Exception e) {
            System.out.println("erroe en C el "+e.getMessage());
            throw e;
        }
    }
    
    public void leerCurso(String codCurAul) throws Exception{
        try {
            dao = new Curso_AulaImpl();
            curso_aula = dao.leerCurso(codCurAul);
            acctionCur = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            dao = new Curso_AulaImpl();
            listCursoAula = dao.listCurAul();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> compleCurAul(String query) throws SQLException, Exception {
        Curso_AulaImpl Conexion = new Curso_AulaImpl();
        return Conexion.autoCurso(query);
    }

    public List<String> complAul(String query) throws SQLException, Exception {
        Curso_AulaImpl Conexion = new Curso_AulaImpl();
        return Conexion.autoAul(query);
    }
    public List<String>complePer(String query) throws SQLException, Exception{
        Curso_AulaImpl Conexion = new Curso_AulaImpl();
        return Conexion.autocomplPer(query);
    }

    //generado 
    public String getAcctionCur() {
        return acctionCur;
    }

    public void setAcctionCur(String acctionCur) {
        limpiar();
        this.acctionCur = acctionCur;
    }

    public Curso_Aula getCurso_aula() {
        return curso_aula;
    }

    public void setCurso_aula(Curso_Aula curso_aula) {
        this.curso_aula = curso_aula;
    }

    public Curso_AulaImpl getDao() {
        return dao;
    }

    public void setDao(Curso_AulaImpl dao) {
        this.dao = dao;
    }

    public List<Curso_Aula> getListCursoAula() {
        return listCursoAula;
    }

    public void setListCursoAula(List<Curso_Aula> listCursoAula) {
        this.listCursoAula = listCursoAula;
    }
}
