package controlador;

import dao.Impl.AsistenciaImpl;
import dao.Impl.ReporteImpl;
import dao.Impl.ResultadoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Asistencia;
import modelo.Bimestre;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@Named(value = "asistenciaC")
@SessionScoped
public class AsistenciaC implements Serializable {

    Asistencia asis = new Asistencia();
    private List<Asistencia> listaEstu;
    private List<Bimestre> listBimestre;
    private List<Bimestre> listBimestres;
    private List<Asistencia> listarAula;
    private List<Asistencia> listaPorFechas;
    private List<Asistencia> ListNotasPorEstu;
    private List<Asistencia> listShedul = new ArrayList<>();
    private List<Asistencia> listdialog;
    private ScheduleModel Calendario = new DefaultScheduleModel();
    DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private ScheduleEvent event = new DefaultScheduleEvent();

    @PostConstruct
    public void inicio() {
        try {
            listarEstu();
            listarAsisten();
        } catch (Exception ex) {
            Logger.getLogger(AsistenciaC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarAsisten() throws Exception {
        AsistenciaImpl dao;
        try {
            dao = new AsistenciaImpl();
            listShedul = dao.listAsisten();
            Calendario = new DefaultScheduleModel();
            for (Asistencia AsisShedul : listShedul) { // la lista que tenemos en la BD la mostrando una por una (como algoritmo)
                ScheduleEvent evento = new DefaultScheduleEvent(AsisShedul.getNomAul(), DateFormat.parse(AsisShedul.getFechAsis()), DateFormat.parse(AsisShedul.getFechAsis()));
                Calendario.addEvent(evento);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void ingresarAsis() throws Exception {
        AsistenciaImpl dao;
        try {
            dao = new AsistenciaImpl();
            Calendar calendar = Calendar.getInstance(); //instanciamos el objeto calendar
            calendar.setTime(event.getStartDate()); //Establecemos la fecha seleccionada
            calendar.add(Calendar.DAY_OF_YEAR, 1); //Sumamos un día a la fecha que seleccionamos anteriormente
            for (Asistencia agregarAsis : listaEstu) {//Pasar la lista para ingresar

                agregarAsis.setFechAsis(DateFormat.format(calendar.getTime()));

                dao.ingresar(agregarAsis);
            }
            limpiar();
            listarAsisten(); //Listamos para mostrar los cambios de manera automática
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "INGRESADO CORRECTAMENTE"));
        } catch (Exception e) {
            throw e;
        }
    }

//se ingresa por un ajax en cual actualiza los datos sin que se tengan  que precionar un boton
    public void actualiAsis(Asistencia Asiste) throws Exception {
        AsistenciaImpl dao;
        try {
            dao = new AsistenciaImpl();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event.getStartDate());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            dao.actualiAsis(Asiste);
            listarAsisten();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CORRECTO", "ACTUALIZADO CORRECTAMENTE"));
        } catch (Exception e) {
            throw e;
        }

    }

    public void onDateSelect(SelectEvent selectEvent) throws Exception { //Creamos un método para seleccionar la fecha y guardarlo en el objeto ""event
        limpiar();
        event = new DefaultScheduleEvent(null, (Date) selectEvent.getObject(), (Date) selectEvent.getObject()); //la fecha seleccionada la guardamos en el modelo evento
    }

    public void onEventSelect(SelectEvent selectEvent) throws Exception {
        event = (ScheduleEvent) selectEvent.getObject();
        listAsis(DateFormat.format(event.getStartDate()), event.getTitle());
    }

    public void limpiar() {
        asis = new Asistencia();
    }

    public void listarEstu() throws Exception {
        AsistenciaImpl dao;
        try {
            dao = new AsistenciaImpl();
            listaEstu = dao.listarPorAula(asis.getCodAul());
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarAulas(String idProf) throws Exception {
        AsistenciaImpl dao;
        try {
            dao = new AsistenciaImpl();
            listarAula = dao.listAulas(idProf);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listAsis(String fechaasis, String idaulas) throws Exception {
        AsistenciaImpl dao;
        try {
            dao = new AsistenciaImpl();
            listdialog = dao.listAsis(fechaasis, idaulas);

        } catch (Exception e) {
            throw e;
        }
    }
//lista de notas por bimestre 
    public void lisBim(String idProf) throws Exception {
        ResultadoImpl dao;
        try {
            dao = new ResultadoImpl();
            listBimestre = dao.lisBimes(idProf);
        } catch (Exception e) {
            throw e;
        }
    }
//reporte de  registro de aula 

    public void RALUM(String CODMAT) throws Exception {
        ReporteImpl reporteImpl = new ReporteImpl();
        try {
            HashMap parameters = new HashMap(); // Libro de parametros  
            parameters.put("CODMAT", CODMAT); //Insertamos un parametro            
            reporteImpl.RAlumn(parameters); //Pido exportar Reporte con los parametros
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Bimestre> getListBimestres() {
        return listBimestres;
    }

    public void setListBimestres(List<Bimestre> listBimestres) {
        this.listBimestres = listBimestres;
    }

    public Asistencia getAsis() {
        return asis;
    }

    public void setAsis(Asistencia asis) {
        this.asis = asis;
    }

    public List<Asistencia> getListaEstu() {
        return listaEstu;
    }

    public void setListaEstu(List<Asistencia> listaEstu) {
        this.listaEstu = listaEstu;
    }

    public List<Bimestre> getListBimestre() {
        return listBimestre;
    }

    public void setListBimestre(List<Bimestre> listBimestre) {
        this.listBimestre = listBimestre;
    }

    public List<Asistencia> getListarAula() {
        return listarAula;
    }

    public void setListarAula(List<Asistencia> listarAula) {
        this.listarAula = listarAula;
    }

    public List<Asistencia> getListaPorFechas() {
        return listaPorFechas;
    }

    public void setListaPorFechas(List<Asistencia> listaPorFechas) {
        this.listaPorFechas = listaPorFechas;
    }

    public List<Asistencia> getListNotasPorEstu() {
        return ListNotasPorEstu;
    }

    public void setListNotasPorEstu(List<Asistencia> ListNotasPorEstu) {
        this.ListNotasPorEstu = ListNotasPorEstu;
    }

    public List<Asistencia> getListShedul() {
        return listShedul;
    }

    public void setListShedul(List<Asistencia> listShedul) {
        this.listShedul = listShedul;
    }

    public List<Asistencia> getListdialog() {
        return listdialog;
    }

    public void setListdialog(List<Asistencia> listdialog) {
        this.listdialog = listdialog;
    }

    public ScheduleModel getCalendario() {
        return Calendario;
    }

    public void setCalendario(ScheduleModel Calendario) {
        this.Calendario = Calendario;
    }

    public DateFormat getDateFormat() {
        return DateFormat;
    }

    public void setDateFormat(DateFormat DateFormat) {
        this.DateFormat = DateFormat;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

}
