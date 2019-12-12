package controlador;

import dao.Impl.ResultadoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Bimestre;
import modelo.Resultados;

@Named(value = "resultadosC")
@SessionScoped
public class ResultadosC implements Serializable {

    private ResultadoImpl dao;
    private Resultados resultado;
    private Bimestre bimestre = new Bimestre();
    private List<Resultados> listNotas;
    private List<Resultados> listEstu;
    private List<Resultados> listEstu2;
    private List<Resultados> listEstu3;
    private List<Resultados> listEstu4;
    private List<Resultados> lisTops;
    private List<Bimestre> listAul;
    private List<Bimestre> listAjax;
    private List<Bimestre> selecAlum;
    private List<Resultados> listAsistencia;

    @PostConstruct
    public void init() {
        try {
            listado();
            sele();
            selecAlu();
            topLista();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public ResultadosC() {

        resultado = new Resultados();
        listNotas = new ArrayList<>();
    }

    public void listado() throws Exception {
        try {
            dao = new ResultadoImpl();
            listNotas = dao.ListNotas();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listadoPorEstu(String Idprof) throws Exception {
        try {
            dao = new ResultadoImpl();
            listEstu = dao.listPorAlum(Idprof);
            listEstu2 = dao.listPorAlum2(Idprof);
            listEstu3 = dao.listPorAlum3(Idprof);
            listEstu4 = dao.listPorAlum4(Idprof);
        } catch (Exception e) {
            throw e;
        }
    }

    public void topLista() throws Exception {
        try {
            lisTops = dao.lisTop();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listAsis(String Idprof) throws Exception {
        try {
            listAsistencia = dao.PromediarAsis(Idprof);
        } catch (Exception e) {
            throw e;
        }
    }

    public void sele() throws Exception {
        try {
            dao = new ResultadoImpl();
            listAul = dao.lisSelec();
        } catch (Exception e) {
            throw e;
        }
    }

    public void selecAlu() throws Exception {
        try {
            dao = new ResultadoImpl();
            selecAlum = dao.lisSelecAlu();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listAulasA() throws Exception {
        try {
            listAjax = dao.listaBimest(bimestre.getAula(), bimestre.getESTUDIANTE());
        } catch (Exception e) {
            throw e;
        }

    }

    public List<Resultados> getListEstu2() {
        return listEstu2;
    }

    public void setListEstu2(List<Resultados> listEstu2) {
        this.listEstu2 = listEstu2;
    }

    public List<Resultados> getListEstu3() {
        return listEstu3;
    }

    public void setListEstu3(List<Resultados> listEstu3) {
        this.listEstu3 = listEstu3;
    }

    public List<Resultados> getListAsistencia() {
        return listAsistencia;
    }

    public void setListAsistencia(List<Resultados> listAsistencia) {
        this.listAsistencia = listAsistencia;
    }

    public List<Resultados> getListEstu4() {
        return listEstu4;
    }

    public void setListEstu4(List<Resultados> listEstu4) {
        this.listEstu4 = listEstu4;
    }

    public List<Bimestre> getSelecAlum() {
        return selecAlum;
    }

    public void setSelecAlum(List<Bimestre> selecAlum) {
        this.selecAlum = selecAlum;
    }

    public Bimestre getBimestre() {
        return bimestre;
    }

    public void setBimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
    }

    public List<Bimestre> getListAjax() {
        return listAjax;
    }

    public void setListAjax(List<Bimestre> listAjax) {
        this.listAjax = listAjax;
    }

    public List<Bimestre> getListAul() {
        return listAul;
    }

    public void setListAul(List<Bimestre> listAul) {
        this.listAul = listAul;
    }

    public List<Resultados> getListEstu() {
        return listEstu;
    }

    public void setListEstu(List<Resultados> listEstu) {
        this.listEstu = listEstu;
    }

    public ResultadoImpl getDao() {
        return dao;
    }

    public void setDao(ResultadoImpl dao) {
        this.dao = dao;
    }

    public Resultados getResultado() {
        return resultado;
    }

    public void setResultado(Resultados resultado) {
        this.resultado = resultado;
    }

    public List<Resultados> getListNotas() {
        return listNotas;
    }

    public void setListNotas(List<Resultados> listNotas) {
        this.listNotas = listNotas;
    }

    public List<Resultados> getLisTops() {
        return lisTops;
    }

    public void setLisTops(List<Resultados> lisTops) {
        this.lisTops = lisTops;
    }

}
