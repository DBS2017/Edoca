package controlador;

import dao.Conexion;
import dao.Impl.CursoImpl;
import dao.Impl.DashboardImpl;
import dao.Impl.EstudianteImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Dashboard;
import modelo.Estudiante;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import servicios.Diagest;
import servicios.ReportGenerator;

@Named(value = "estudianteCon")
@SessionScoped

public class EstudianteC implements Serializable {

    private Estudiante estudiante = new Estudiante();
    private List<Dashboard> dashboard;
    private PieChartModel chartmodel;
    private EstudianteImpl dao;
    private List<Estudiante> listadoEst;
    private String accionEst;
    private PieChartModel grafico;
    private String contador;
    private List<Estudiante> filteredEstu;
    private List<Diagest> listagrafico;
    private int estu;
    private String var1;
    private String var2;

    int CodigoBuscar;

    private LineChartModel lineModel;

    @PostConstruct
    public void init() {
        try {
            createLineModel();
            listar();
            lstCantDistCañete();
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

    }

    public int getEstu() {
        return estu;
    }

    public void setEstu(int estu) {
        this.estu = estu;
    }

    public EstudianteC() {
        dao = new EstudianteImpl();
        estudiante = new Estudiante();
        listadoEst = new ArrayList();

    }

    public void lstCantDistCañete() throws Exception {
        DashboardImpl dao;
        try {
            dao = new DashboardImpl();
            dashboard = dao.listarGrafEst();
            ListCountSex(dashboard);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void ListCountSex(List<Dashboard> lista) {

        chartmodel = new PieChartModel();
        for (Dashboard alu : dashboard) {
            chartmodel.set(alu.getSexest(), alu.getSexcount());
        }
        chartmodel.setTitle("CANTIDAD DE ESTUDIANTES POR GÉNERO");
        chartmodel.setLegendPosition("r");
        chartmodel.setShowDataLabels(true);
        chartmodel.setDiameter(220);
    }

    public void operarEstudiante() throws Exception {
        switch (accionEst) {
            case "Registrar":
                registrar();
                break;
            case "Modificar":
                modificar();
                break;
        }
    }

    public void limpiar() {
        estudiante = new Estudiante();
    }

    public void registrar() throws Exception {
        try {
            dao = new EstudianteImpl();
            estudiante.setUbiEstu(dao.obtenerCodigoUbigeo(estudiante.getNomubigeo()));
            dao.registrar(estudiante);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Completado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao = new EstudianteImpl();
            estudiante.setUbiEstu(dao.obtenerCodigoUbigeo(estudiante.getNomubigeo()));
            dao.modificar(estudiante);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Modificado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Estudiante est) throws Exception {
        try {
            dao = new EstudianteImpl();
            dao.eliminar(est);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Eliminado..."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerEstudiante(String codigoAlumno) throws Exception {

        try {
            dao = new EstudianteImpl();
            estudiante = dao.leerEstudiante(codigoAlumno);
            accionEst = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {

        try {
            dao = new EstudianteImpl();
            listadoEst = dao.listarEst();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeText(String query) throws SQLException, Exception {
        EstudianteImpl Conexion = new EstudianteImpl();
        return Conexion.autocompleteUbigeo(query);
    }

    public List<String> completeTextidEst(String query) throws SQLException, Exception {
        EstudianteImpl Conexion = new EstudianteImpl();
        return Conexion.autocompleteEstudiante(query);
    }

    // DESCARGAR REPORTE DE ALUMNOS
    //controlador
//    public void REPORTE_PDF_ESTUDIANTE(int CodigoEstudiante) throws Exception {
//        EstudianteImpl estudianteImpl = new EstudianteImpl();
//        try {
//            Map<String, Object> parameters = new HashMap(); // Libro de parametros
//            parameters.put(null, CodigoEstudiante); //Insertamos un parametro
//            estudianteImpl.REPORTE_PDF_ESTUDIANTE(parameters); //Pido exportar Reporte con los parametros
////            report.exportarPDF2(parameters);
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    public void descargarPDFreg_est() throws Exception {
        ReportGenerator report;
        try {
            report = new ReportGenerator();
            Map<String, Object> parameters = new HashMap();
            parameters.put("DNIPER", getEstu());
            report.exp_list_est(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public void descargarPDFaulaCurso() throws Exception {
        ReportGenerator report;
        try {
            report = new ReportGenerator();
            Map<String, Object> parameters = new HashMap();
            parameters.put("NOMCUR", getVar1());
            parameters.put("AULA", getVar2());
            report.exp_aula_curs(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public void createLineModel() throws SQLException {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Number> values = new ArrayList<>();
        //Traendo mis datos
        listagrafico = dao.graficoAlumno(CodigoBuscar);

        for (Diagest grafico : listagrafico) {
            values.add(Integer.parseInt(grafico.getBIM1()));
            values.add(Integer.parseInt(grafico.getBIM2()));
            values.add(Integer.parseInt(grafico.getBIM3()));
            values.add(Integer.parseInt(grafico.getBIM4()));
            values.add(Integer.parseInt(grafico.getBIM5()));
        }
        values.add(20);
        dataSet.setData(values);
        dataSet.setFill(false);
        for (Diagest grafico : listagrafico) {
             dataSet.setLabel(grafico.getESTUDIANTE() +" -- "+ grafico.getAULA());
        }
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        for (Diagest grafico : listagrafico) {
            labels.add("BIM1");
            labels.add("BIM2");
            labels.add("BIM3");
            labels.add("BIM4");
            labels.add("BIM5");
        }
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Rendimiento Académico");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
    }

//codigo generado
    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Estudiante> getListadoEst() {
        return listadoEst;
    }

    public void setListadoEst(List<Estudiante> listadoEst) {
        this.listadoEst = listadoEst;
    }

    public EstudianteImpl getDao() {
        return dao;
    }

    public void setDao(EstudianteImpl dao) {
        this.dao = dao;
    }

    public String getAccionEst() {
        return accionEst;
    }

    public void setAccionEst(String accionEst) {
        this.accionEst = accionEst;
    }

    public PieChartModel getGrafico() {
        return grafico;
    }

    public void setGrafico(PieChartModel grafico) {
        this.grafico = grafico;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public List<Dashboard> getDashboard() {
        return dashboard;
    }

    public PieChartModel getChartmodel() {
        return chartmodel;
    }

    public void setChartmodel(PieChartModel chartmodel) {
        this.chartmodel = chartmodel;
    }

    public void setDashboard(List<Dashboard> dashboard) {
        this.dashboard = dashboard;
    }

    public List<Estudiante> getFilteredEstu() {
        return filteredEstu;
    }

    public void setFilteredEstu(List<Estudiante> filteredEstu) {
        this.filteredEstu = filteredEstu;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public List<Diagest> getListagrafico() {
        return listagrafico;
    }

    public void setListagrafico(List<Diagest> listagrafico) {
        this.listagrafico = listagrafico;
    }

    public int getCodigoBuscar() {
        return CodigoBuscar;
    }

    public void setCodigoBuscar(int CodigoBuscar) {
        this.CodigoBuscar = CodigoBuscar;
    }

}
