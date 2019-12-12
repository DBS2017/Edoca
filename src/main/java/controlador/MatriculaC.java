package controlador;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
//import dao.Impl.CursoImpl;
import dao.Impl.DashboardImpl;
import dao.Impl.MatriculaImpl;
import java.io.Serializable;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
//import javax.xml.bind.DatatypeConverter;
import modelo.Matricula;
import modelo.NEstuAula;
//import org.exolab.castor.types.Date;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;

@Named(value = "matriculaC")
@SessionScoped
public class MatriculaC implements Serializable {

    private Matricula matricula = new Matricula();
    private MatriculaImpl dao;
    private List<Matricula> listadoMat;
    private String acctionMatr;

    private BarChartModel barra;
    private List<NEstuAula> NEstuAula;
    DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @PostConstruct
    public void init() {
        try {
            createBarras();
            listar();
            lstCantAlu();
        } catch (Exception e) {
            System.out.println("Error init Matricula" + e.getMessage());
        }
    }

    public MatriculaC() {
        dao = new MatriculaImpl();
        matricula = new Matricula();
        listadoMat = new ArrayList();

    }

    public void lstCantAlu() throws Exception {
        DashboardImpl dao;
        try {
            dao = new DashboardImpl();
            NEstuAula = dao.listarCantEst();
            createBarras();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void createBarras() {
        try {
            barra = new BarChartModel();
            for (int i = 0; i < NEstuAula.size(); i++) {
                ChartSeries serie = new BarChartSeries();
                serie.setLabel(NEstuAula.get(i).getIDEST());
                serie.set(NEstuAula.get(i).getCONTESTU(), NEstuAula.get(i).getCONTESTU());
                barra.addSeries(serie);
                barra.setLegendPosition("ne");
            }
            Axis xAxis = barra.getAxis(AxisType.X);
            xAxis.setLabel("FRACTAL");
            barra = getBarra();
            barra.setTitle("NÚMERO DE ESTUDIANTES POR AULA");
            barra.setAnimate(true);

            Axis yAxis = barra.getAxis(AxisType.Y);
            yAxis.setMax(30);
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }

    public void fechaActual() throws Exception {

    }

    public void operadorMatri() throws Exception {
        switch (acctionMatr) {
            case "Matricular":
                registrar();
                break;
            case "Modificar":
                modificar();
                break;
        }
    }

    public void limpiar() {
        matricula = new Matricula();
    }

    public void registrar() throws Exception {
        try {
           
            matricula.setIdEst(dao.ObtenerCodigoEstudiante(matricula.getAutEst()));
            matricula.setIdApo(dao.ObtenerCodigoApoderado(matricula.getAutApo()));
            matricula.setIdAul(dao.obteAula(matricula.getAutAul()));
            dao.registrar(matricula);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Matricula", "Registrada"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao = new MatriculaImpl();
            matricula.setIdEst(dao.ObtenerCodigoEstudiante(matricula.getAutEst()));
            matricula.setIdApo(dao.ObtenerCodigoApoderado(matricula.getAutApo()));
            matricula.setIdAul(dao.obteAula(matricula.getAutAul()));
            dao.modificar(matricula);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Matricula", "Modificada"));

        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Matricula mat) throws Exception {
        try {
            dao = new MatriculaImpl();
            dao.eliminar(mat);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Matricula", "Eliminada"));

        } catch (Exception e) {
            throw e;
        }
    }

    public void leerMatri(String codiMatri) throws Exception {
        try {
            dao = new MatriculaImpl();
            matricula = dao.leerMatri(codiMatri);
            acctionMatr = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            dao = new MatriculaImpl();
            listadoMat = dao.listarMat();
        } catch (Exception e) {
            throw e;
        }

    }

    public List<String> completeTextidEst(String query) throws SQLException, Exception {
        MatriculaImpl Conexion = new MatriculaImpl();
        return Conexion.autocompleteEstudiante(query);
    }

    public List<String> completeTextidApo(String query) throws SQLException, Exception {
        MatriculaImpl Conexion = new MatriculaImpl();
        return Conexion.autocompleteApoderado(query);
    }

    public List<String> compltAula(String query) throws SQLException, Exception {
        MatriculaImpl Conexion = new MatriculaImpl();
        return Conexion.autoAula(query);
    }



    //codigo generado
    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public String getAcctionMatr() {
        return acctionMatr;
    }

    public void setAcctionMatr(String acctionMatr) {
        limpiar();
        this.acctionMatr = acctionMatr;
    }

    public MatriculaImpl getDao() {
        return dao;
    }

    public void setDao(MatriculaImpl dao) {
        this.dao = dao;
    }

    public List<Matricula> getListadoMat() {
        return listadoMat;
    }

    public void setListadoMat(List<Matricula> listadoMat) {
        this.listadoMat = listadoMat;
    }

    public BarChartModel getBarra() {
        return barra;
    }

    public void setBarra(BarChartModel barra) {
        this.barra = barra;
    }

    public List<NEstuAula> getNEstuAula() {
        return NEstuAula;
    }

    public void setNEstuAula(List<NEstuAula> NEstuAula) {
        this.NEstuAula = NEstuAula;
    }

}
