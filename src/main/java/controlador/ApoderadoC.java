package controlador;

import dao.Impl.ApoderadoImpl;
import dao.Impl.CursoImpl;
import dao.Impl.UbigeoImpl;
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
import modelo.Apoderado;

@Named(value = "apoderadoCon")
@SessionScoped
public class ApoderadoC implements Serializable {

    private Apoderado apoderado = new Apoderado();
    private ApoderadoImpl dao;
    private List<Apoderado> listadoApo;
    private List<Apoderado> filterApo;

    private String accionApo;

//    inicoalizamos las listas dentro del postConstruct
    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("error init Apoderado " + e.getMessage());
        }
    }

    public ApoderadoC() {

        dao = new ApoderadoImpl();
        apoderado = new Apoderado();
        listadoApo = new ArrayList();
    }
//Para usar un solo dialogo para registrar y modificar

    public void operarApoderado() throws Exception {
        switch (accionApo) {
            case "Registrar":
                registrar();
                break;
            case "Modificar":
                modificar();
                break;
        }
    }

    public void registrar() throws Exception {
        try {
            dao = new ApoderadoImpl();
            apoderado.setUbiApo(dao.obtenerCodigoUbigeo(apoderado.getNombreUbi()));//autocomplete 
            dao.registrar(apoderado);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {

        try {
            dao = new ApoderadoImpl();
            apoderado.setUbiApo(dao.obtenerCodigoUbigeo(apoderado.getNombreUbi()));
            dao.modificar(apoderado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Modificado"));
            listar();
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Al Modificar"));
            throw e;
        }
    }

    public void eliminar(Apoderado apo) throws Exception {
        try {
            dao = new ApoderadoImpl();
            dao.eliminar(apo);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Eliminado"));
        } catch (Exception e) {
            throw e;
        }
    }

    //usamos el leer para traer los datos que se van a modifcar
    public void leerApoderado(String codigoAlumno) throws Exception {

        try {
            dao = new ApoderadoImpl();
            apoderado = dao.leerApoderado(codigoAlumno);
            accionApo = "Modificar";

        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        apoderado = new Apoderado();

    }

    public void listar() throws Exception {
        ApoderadoImpl Conexion;
        try {
            Conexion = new ApoderadoImpl();
            listadoApo = Conexion.listarApo();
        } catch (Exception e) {
            throw e;
        }
    }

//    //Validación de registro de DNI
//    public boolean validarExistenciaApoderado(String dniApoderado) {
//        ApoderadoImpl dao;
//        try {
//            dao = new ApoderadoImpl();
//            Apoderado apoderadoValidacion = new Apoderado();
//            apoderadoValidacion = dao.validarExistenciaApoderado(dniApoderado);
//            String DNIPER = apoderadoValidacion.getDniApo().toLowerCase().trim();
//            if (DNIPER.equals(dniApoderado)) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//        }
//        return false;
//    }
    
//    autocomplete para ubigeo
    public List<String> completeTextUbi(String query) throws SQLException, Exception {
        ApoderadoImpl dao = new ApoderadoImpl();
        return dao.autocompleteUbigeo(query);
    }

    public List<String> completeTextidApo(String query) throws SQLException, Exception {
        ApoderadoImpl dao = new ApoderadoImpl();
        return dao.autocompleteApoderado(query);
    }

    //DESCARGAR REPORTE DE ALUMNOS
    public void REPORTE_PDF_APODERADOS(String CodigoApoderado) throws Exception {
        ApoderadoImpl reportApo = new ApoderadoImpl();
        try {
            Map<String, Object> parameters = new HashMap(); // Libro de parametros
            parameters.put(null, CodigoApoderado); //Insertamos un parametro
            reportApo.REPORTE_PDF_APODERADOS(parameters); //Pido exportar Reporte con los parametros
//            report.exportarPDF2(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

//codigo generado   
    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {

        this.apoderado = apoderado;
    }

    public List<Apoderado> getListadoApo() {
        return listadoApo;
    }

    public void setListadoApo(List<Apoderado> listadoApo) {
        this.listadoApo = listadoApo;
    }

    public ApoderadoImpl getDao() {
        return dao;
    }

    public void setDao(ApoderadoImpl dao) {
        this.dao = dao;
    }

    public String getAccionApo() {

        return accionApo;
    }

    public void setAccionApo(String accionApo) {
        limpiar();
        this.accionApo = accionApo;
    }

    public List<Apoderado> getFilterApo() {
        return filterApo;
    }

    public void setFilterApo(List<Apoderado> filterApo) {
        this.filterApo = filterApo;
    }

}
