package modelo;

import java.util.List;

public class Promedio {
    
    private String CODTRAZ;
    private String CODESTU;
    private String NOMEST;
    private String APEEST;
    private String CODAUL;
    private String CODCUR;
    private String NOMCUR;
    private String CODDETCUR;
    private String PROMEDIO;
    private String PROMFINAL;
    private Criterio criterio;
    private Registro nota;
    private Aula aula;
    
    private List<Promedio> lstPromedioFinal;

    public Promedio() {
        criterio = new Criterio();
        nota = new Registro();
        aula = new Aula();
    }

    public String getCODTRAZ() {
        return CODTRAZ;
    }

    public void setCODTRAZ(String CODTRAZ) {
        this.CODTRAZ = CODTRAZ;
    }

    public String getCODESTU() {
        return CODESTU;
    }

    public void setCODESTU(String CODESTU) {
        this.CODESTU = CODESTU;
    }

    public String getNOMEST() {
        return NOMEST;
    }

    public void setNOMEST(String NOMEST) {
        this.NOMEST = NOMEST;
    }

    public String getAPEEST() {
        return APEEST;
    }

    public void setAPEEST(String APEEST) {
        this.APEEST = APEEST;
    }

    public String getCODAUL() {
        return CODAUL;
    }

    public void setCODAUL(String CODAUL) {
        this.CODAUL = CODAUL;
    }

    public String getCODCUR() {
        return CODCUR;
    }

    public void setCODCUR(String CODCUR) {
        this.CODCUR = CODCUR;
    }

    public String getNOMCUR() {
        return NOMCUR;
    }

    public void setNOMCUR(String NOMCUR) {
        this.NOMCUR = NOMCUR;
    }

    public String getCODDETCUR() {
        return CODDETCUR;
    }

    public void setCODDETCUR(String CODDETCUR) {
        this.CODDETCUR = CODDETCUR;
    }

    public String getPROMEDIO() {
        return PROMEDIO;
    }

    public void setPROMEDIO(String PROMEDIO) {
        this.PROMEDIO = PROMEDIO;
    }

    public String getPROMFINAL() {
        return PROMFINAL;
    }

    public void setPROMFINAL(String PROMFINAL) {
        this.PROMFINAL = PROMFINAL;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    public Registro getNota() {
        return nota;
    }

    public void setNota(Registro nota) {
        this.nota = nota;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public List<Promedio> getLstPromedioFinal() {
        return lstPromedioFinal;
    }

    public void setLstPromedioFinal(List<Promedio> lstPromedioFinal) {
        this.lstPromedioFinal = lstPromedioFinal;
    }
    
    
    
}
