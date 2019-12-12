package controlador;

import dao.Impl.ListaDeNotasImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.ListaDeNotas;

@Named(value = "ListaDeNotasC")
@SessionScoped
public class ListaDeNotasC implements Serializable {

    private ListaDeNotas listadenotas = new ListaDeNotas();
    private List<ListaDeNotas> listarB1;
    private List<ListaDeNotas> listarB2;
    private List<ListaDeNotas> listarB3;
    private List<ListaDeNotas> listarB4;
    private List<ListaDeNotas> listarB5;
    private ListaDeNotas listado1;
    private ListaDeNotasImpl dao;
    private List<ListaDeNotas> filtrodebusqueda;

    @PostConstruct
    public void init() {
        try {
            LBimestre3();
        } catch (Exception e) {
            System.out.println("error init NotasBimestrales " + e.getMessage());
        }
    }



    //Listar Notas Bimestrales  
    public void LBimestre3() throws Exception {
        ListaDeNotasImpl Conexion;
        try {
            Conexion = new ListaDeNotasImpl();
            listarB3 = Conexion.listarB3();
        } catch (Exception e) {
            throw e;
        }
    }


    public ListaDeNotas getListadenotas() {
        return listadenotas;
    }

    public void setListadenotas(ListaDeNotas listadenotas) {
        this.listadenotas = listadenotas;
    }

    public ListaDeNotasImpl getDao() {
        return dao;
    }

    public void setDao(ListaDeNotasImpl dao) {
        this.dao = dao;
    }

    public ListaDeNotas getListado1() {
        return listado1;
    }

    public void setListado1(ListaDeNotas listado1) {
        this.listado1 = listado1;
    }

    public List<ListaDeNotas> getListarB1() {
        return listarB1;
    }

    public void setListarB1(List<ListaDeNotas> listarB1) {
        this.listarB1 = listarB1;
    }

    public List<ListaDeNotas> getListarB2() {
        return listarB2;
    }

    public void setListarB2(List<ListaDeNotas> listarB2) {
        this.listarB2 = listarB2;
    }

    public List<ListaDeNotas> getListarB3() {
        return listarB3;
    }

    public void setListarB3(List<ListaDeNotas> listarB3) {
        this.listarB3 = listarB3;
    }

    public List<ListaDeNotas> getListarB4() {
        return listarB4;
    }

    public void setListarB4(List<ListaDeNotas> listarB4) {
        this.listarB4 = listarB4;
    }

    public List<ListaDeNotas> getListarB5() {
        return listarB5;
    }

    public void setListarB5(List<ListaDeNotas> listarB5) {
        this.listarB5 = listarB5;
    }

    public List<ListaDeNotas> getFiltrodebusqueda() {
        return filtrodebusqueda;
    }

    public void setFiltrodebusqueda(List<ListaDeNotas> filtrodebusqueda) {
        this.filtrodebusqueda = filtrodebusqueda;
    }

}
