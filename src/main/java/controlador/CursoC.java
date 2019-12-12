package controlador;

import dao.Impl.CursoImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Curso;

@Named(value = "cursoCon")
@SessionScoped
public class CursoC implements Serializable {

    private Curso curso;
    private CursoImpl dao;
    private List<Curso> listadoCur;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("Error init aula " + e.getMessage());
        }
    }

    public CursoC() {
        dao = new CursoImpl();
        curso = new Curso();
        listadoCur = new ArrayList();
    }

    public void registrar() throws Exception {
        CursoImpl dao;
        try {
            dao = new CursoImpl();
            dao.registrar(curso);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Completado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(curso);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Modificado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(curso);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado...", null));
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            listadoCur = dao.listarCur();
        } catch (Exception e) {
            throw e;
        }
    }

    //DESCARGAR REPORTE DE ALUMNOS
    public void REPORTE_PDF_CURSO(String CodigoCurso) throws Exception {
        CursoImpl reportCur = new CursoImpl();
        try {
            Map<String, Object> parameters = new HashMap(); // Libro de parametros
            parameters.put(null, CodigoCurso); //Insertamos un parametro
            reportCur.REPORTE_PDF_CURSO(parameters); //Pido exportar Reporte con los parametros
//            report.exportarPDF2(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        curso = new Curso();
    }
//codigo generado

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getListadoCur() {
        return listadoCur;
    }

    public void setListadoCur(List<Curso> listadoCur) {
        this.listadoCur = listadoCur;
    }

}
