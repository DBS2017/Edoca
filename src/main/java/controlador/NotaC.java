package controlador;

import dao.Impl.NotaImpl;
import dao.Impl.ReporteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Bimestre;
import modelo.Nota;

@Named(value = "notaC")
@SessionScoped
public class NotaC implements Serializable {

    private ReporteImpl rdao;
    private Nota nota;
    private NotaImpl dao;
    private NotaImpl dao1;
    private List<Nota> listaPorCursos;
    private List<Nota> list;
    private List<Nota> listaAula;
    private List<Nota> listaEstu;
    private List<Nota> listarCriterios;
    private List<Nota> listarEstudiante;
    private List<Nota> listNotExMen;
    private List<Nota> listNotExDi;
    private List<Bimestre> promedioBim;
    private List<Nota> listNotTarDia;
    private List<Nota> listarEstud;
    DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @PostConstruct
    public void init() {
        try {
            listarPorAula();
            listarCriterio();
            listarEstu();
        } catch (Exception e) {
            System.out.println("Erro" + e);
        }

    }

    public NotaC() {
        nota = new Nota();
        dao = new NotaImpl();
        list = new ArrayList();
    }
//De la misma manera que las asistencas,  registramos las notas pasamos  las lista 
//    y luego con el for ingresamos las matriculas que vendria a ser el estudiante y las notas

    public void registrar() throws Exception {
        try {
            for (Nota agreNot : listarEstud) {
                nota.setIDMATRI(agreNot.getIDMATRI());
                nota.setREGNOT(agreNot.getREGNOT());
                dao.Registrar(nota);
            }
            limpiar();
            listarPorAula();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Correctamente"));
        } catch (Exception e) {
            throw e;
        }

    }

    public void modificar1(Nota nota) throws Exception {
        try {
            dao1.Modificar(nota);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar2(Nota nota) throws Exception {
        try {
            dao1.Modificar2(nota);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar3(Nota nota) throws Exception {
        try {
            dao1.Modificar3(nota);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar4(Nota nota) throws Exception {
        try {
            dao1.Modificar4(nota);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarCriterio() throws Exception {
        try {
            dao = new NotaImpl();
            listarCriterios = dao.ListarCriterios();
        } catch (Exception e) {
            throw e;
        }
    }

    //primer ITEM
    public void lisPorCurso(String IdProf) throws Exception {
        try {
            listaPorCursos = dao.ListarPorCurs(IdProf);
        } catch (Exception e) {
            throw e;
        }
    }

    //PRIMER AJAX
    public void listarAula() throws Exception {

        try {
            listaAula = dao.listarAulas(nota.getCODCUR());
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        nota = new Nota();
    }

    public void listarEstu() throws Exception {
        try {
            dao.traerIDAula(nota);
            listarEstud = dao.ListarporAula(nota.getCODAUL());
        } catch (Exception e) {
            throw e;
        }
    }
//lista de notas bimestrales de aula y curso

    public void Promedio() throws Exception {
        try {
            promedioBim = dao.lisPromedio(nota.getCODAUL(), nota.getCODCUR());
        } catch (Exception e) {
            throw e;
        }
    }

    //segundo ajax
    public void listarPorAula() throws Exception {
        try {
            dao.traerIDAula(nota);
            listNotTarDia = dao.TareDia(nota.getCODAUL(), nota.getCODCUR());
        } catch (Exception e) {
            throw e;
        }
    }

    //segundo ajax
    public void listarPorAula2() throws Exception {
        try {
            dao.traerIDAula(nota);
            listaEstu = dao.list(nota.getCODAUL(), nota.getCODCUR());
        } catch (Exception e) {
            throw e;
        }
    }

    //segundo ajax
    public void listarPorAula3() throws Exception {
        try {
            dao.traerIDAula(nota);
            listNotExMen = dao.ExmaMen(nota.getCODAUL(), nota.getCODCUR());
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarPorAula4() throws Exception {
        try {
            dao.traerIDAula(nota);
            listNotExDi = dao.ExmaDir(nota.getCODAUL(), nota.getCODCUR());
        } catch (Exception e) {
            throw e;
        }
    }

    public void RAULA(String CODAUL) throws Exception {

        ReporteImpl reporteImpl = new ReporteImpl();
        try {
            HashMap parameters = new HashMap(); // Libro de parametros  
            parameters.put("CODAUL", CODAUL); //Insertamos un parametro            
            reporteImpl.RAula(parameters); //Pido exportar Reporte con los parametros
        } catch (Exception e) {
            throw e;
        }
    }

    public NotaImpl getDao1() {
        return dao1;
    }

    public void setDao1(NotaImpl dao1) {
        this.dao1 = dao1;
    }

    public List<Bimestre> getPromedioBim() {
        return promedioBim;
    }

    public void setPromedioBim(List<Bimestre> promedioBim) {
        this.promedioBim = promedioBim;
    }

    public List<Nota> getListNotExMen() {
        return listNotExMen;
    }

    public void setListNotExMen(List<Nota> listNotExMen) {
        this.listNotExMen = listNotExMen;
    }

    public List<Nota> getListNotExDi() {
        return listNotExDi;
    }

    public void setListNotExDi(List<Nota> listNotExDi) {
        this.listNotExDi = listNotExDi;
    }

    public List<Nota> getListNotTarDia() {
        return listNotTarDia;
    }

    public void setListNotTarDia(List<Nota> listNotTarDia) {
        this.listNotTarDia = listNotTarDia;
    }

    public List<Nota> getListarCriterios() {
        return listarCriterios;
    }

    public void setListarCriterios(List<Nota> listarCriterios) {
        this.listarCriterios = listarCriterios;
    }

    public List<Nota> getListarEstud() {
        return listarEstud;
    }

    public void setListarEstud(List<Nota> listarEstud) {
        this.listarEstud = listarEstud;
    }

    public List<Nota> getListarEstudiante() {
        return listarEstudiante;
    }

    public void setListarEstudiante(List<Nota> listarEstudiante) {
        this.listarEstudiante = listarEstudiante;
    }

    public List<Nota> getListaAula() {
        return listaAula;
    }

    public List<Nota> getListaEstu() {
        return listaEstu;
    }

    public void setListaEstu(List<Nota> listaEstu) {
        this.listaEstu = listaEstu;
    }

    public void setListaAula(List<Nota> listaAula) {
        this.listaAula = listaAula;
    }

    public List<Nota> getListaPorCursos() {
        return listaPorCursos;
    }

    public void setListaPorCursos(List<Nota> listaPorCursos) {
        this.listaPorCursos = listaPorCursos;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public NotaImpl getDao() {
        return dao;
    }

    public void setDao(NotaImpl dao) {
        this.dao = dao;
    }

    public List<Nota> getList() {
        return list;
    }

    public void setList(List<Nota> list) {
        this.list = list;
    }

    public ReporteImpl getRdao() {
        return rdao;
    }

    public void setRdao(ReporteImpl rdao) {
        this.rdao = rdao;
    }

}
