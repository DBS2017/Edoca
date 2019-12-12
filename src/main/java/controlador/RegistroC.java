package controlador;

import dao.Impl.RegistroImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Registro;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@Named(value = "registroC")
@SessionScoped
public class RegistroC implements Serializable {

    Registro registro = new Registro();

    private List<Registro> listaAula;
    private List<Registro> listaEstu;
    private List<Registro> listaCriterios;
    private List<Registro> listaPorCursos;
    private List<Registro> listaPorFechas;
    private List<Registro> filterCurso;
    private List<Registro> ListNotasPorEstu;
    private List<Registro> listSchedule = new ArrayList<>();
    private ScheduleModel calendar = new DefaultScheduleModel();
    DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private ScheduleEvent event = new DefaultScheduleEvent();

    private RegistroImpl dao;

    @PostConstruct
    public void init() {
        try {
            listarCriterios();
            listarFecNota();
        } catch (Exception ex) {
            Logger.getLogger(RegistroC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RegistroC() {
        dao = new RegistroImpl();
    }

    public void limpiar() {
        registro = new Registro();
    }

    public void listarNota() throws Exception {
        try {
            listSchedule = dao.listshe(registro.getCodCur());
            calendar = new DefaultScheduleModel();
            for (Registro AsisShedul : listSchedule) { // la lista que tenemos en la BD la mostrando una por una (como algoritmo)
                ScheduleEvent evento = new DefaultScheduleEvent(AsisShedul.getCodCri(), DateFormat.parse(AsisShedul.getFechReg()), DateFormat.parse(AsisShedul.getFechReg()), registro.getCodCri()); //estos son los datos que vamos a ir jalando en este bucle
                calendar.addEvent(evento);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void agregarNota() throws Exception {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event.getStartDate());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            for (Registro agreNot : listaEstu) {
                agreNot.setFechReg(registro.getFechReg());
                agreNot.setCodCri(registro.getCodCri());
                agreNot.setCodDetAul(registro.getCodDetAul());
                agreNot.setFechReg(DateFormat.format(calendar.getTime()));
                dao.crearNota(agreNot);
            }
            listarPorAula();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Correctamente"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualiNota(Registro registros) throws Exception {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event.getStartDate());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            dao.actualiNot(registros);
            listarNota();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CORRECTO", "ACTUALIZADO CORRECTAMENTE"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void lisPorCurso(String IdProf) throws Exception {
        try {
            dao = new RegistroImpl();
            listaPorCursos = dao.listarPorCurs(IdProf);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarAula() throws Exception {

        try {
            dao = new RegistroImpl();
            listaAula = dao.listarAulas(registro.getCodCur());
        } catch (Exception e) {
            throw e;
        }
    }
//ajax2

    public void listarPorAula() throws Exception {
        try {
            dao = new RegistroImpl();
            dao.traerIDAula(registro);
            listaEstu = dao.ListarporAula(registro.getCodAul());
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarCriterios() throws Exception {

        try {
            dao = new RegistroImpl();
            listaCriterios = dao.ListarCriterios();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarFecNota() throws Exception {

        try {
            dao = new RegistroImpl();
            listaPorFechas = dao.listarFechas(registro.getCodCri(), registro.getCodDetAul());
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarNotPorestu() throws Exception {

        try {
            dao = new RegistroImpl();
            ListNotasPorEstu = dao.ListarNotPorEstu(registro.getCodDetAul(), registro.getCodCri(), registro.getFechReg());
        } catch (Exception e) {
            throw e;
        }

    }

    public void limpiarNotas() {
        ListNotasPorEstu = null;
        registro.setFechReg(null);
    }

    public void listAsis(String detcurso, String idaulas) throws Exception {

        try {
            dao = new RegistroImpl();
            listaPorFechas = dao.listarFechas(detcurso, idaulas);
        } catch (Exception e) {
            throw e;
        }
    }

    public void onDateSelect(SelectEvent selectEvent) throws Exception { //Creamos un método para seleccionar la fecha y guardarlo en el objeto ""event
        event = new DefaultScheduleEvent(null, (Date) selectEvent.getObject(), (Date) selectEvent.getObject()); //la fecha seleccionada la guardamos en el modelo evento
    }

    public void onEventSelect(SelectEvent selectEvent) throws Exception {
        try {
            dao = new RegistroImpl();
            event = (ScheduleEvent) selectEvent.getObject();
            ListNotasPorEstu = dao.ListarNotPorEstu(registro.getCodDetAul(), event.getTitle(), DateFormat.format(event.getStartDate()));
        } catch (Exception e) {
            throw e;
        }
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public List<Registro> getListaAula() {
        return listaAula;
    }

    public void setListaAula(List<Registro> listaAula) {
        this.listaAula = listaAula;
    }

    public List<Registro> getListaEstu() {
        return listaEstu;
    }

    public void setListaEstu(List<Registro> listaEstu) {
        this.listaEstu = listaEstu;
    }

    public List<Registro> getListaCriterios() {
        return listaCriterios;
    }

    public void setListaCriterios(List<Registro> listaCriterios) {
        this.listaCriterios = listaCriterios;
    }

    public List<Registro> getListaPorCursos() {
        return listaPorCursos;
    }

    public void setListaPorCursos(List<Registro> listaPorCursos) {
        this.listaPorCursos = listaPorCursos;
    }

    public List<Registro> getListaPorFechas() {
        return listaPorFechas;
    }

    public void setListaPorFechas(List<Registro> listaPorFechas) {
        this.listaPorFechas = listaPorFechas;
    }

    public List<Registro> getListNotasPorEstu() {
        return ListNotasPorEstu;
    }

    public void setListNotasPorEstu(List<Registro> ListNotasPorEstu) {
        this.ListNotasPorEstu = ListNotasPorEstu;
    }

    public List<Registro> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<Registro> listSchedule) {
        this.listSchedule = listSchedule;
    }

    public ScheduleModel getCalendar() {
        return calendar;
    }

    public void setCalendar(ScheduleModel calendar) {
        this.calendar = calendar;
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

    public RegistroImpl getDao() {
        return dao;
    }

    public void setDao(RegistroImpl dao) {
        this.dao = dao;
    }

    public List<Registro> getFilterCurso() {
        return filterCurso;
    }

    public void setFilterCurso(List<Registro> filterCurso) {
        this.filterCurso = filterCurso;
    }

}
