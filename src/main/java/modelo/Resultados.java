package modelo;

import java.util.List;

public class Resultados {

    private String idAlum;
    private String CODREG;
    private String nota;
    private String CODAUL;
    private String nomAlum;
    private String apeAlum;
    private String nomCur;
    private String tipAlum;
    private String FECREG;
    private String CODCRI;
    private String NOMCRI;
    private String CODCUR;

    private String gradAlum;
    private String secAlum;

    private String ProFinal;
    private List<Resultados> Promedio;

    
    private String FALT ;
    private String ASIS;
    private String CMA;
    private String MV;

    public String getFALT() {
        return FALT;
    }

    public void setFALT(String FALT) {
        this.FALT = FALT;
    }

    public String getASIS() {
        return ASIS;
    }

    public void setASIS(String ASIS) {
        this.ASIS = ASIS;
    }

    public String getCMA() {
        return CMA;
    }

    public void setCMA(String CMA) {
        this.CMA = CMA;
    }

    public String getMV() {
        return MV;
    }

    public void setMV(String MV) {
        this.MV = MV;
    }
            
    
    
    public String getProFinal() {
        return ProFinal;
    }

    public String getCODREG() {
        return CODREG;
    }

    public void setCODREG(String CODREG) {
        this.CODREG = CODREG;
    }

    public String getCODAUL() {
        return CODAUL;
    }

    public void setCODAUL(String CODAUL) {
        this.CODAUL = CODAUL;
    }

    public String getFECREG() {
        return FECREG;
    }

    public void setFECREG(String FECREG) {
        this.FECREG = FECREG;
    }

    public String getCODCRI() {
        return CODCRI;
    }

    public void setCODCRI(String CODCRI) {
        this.CODCRI = CODCRI;
    }

    public String getNOMCRI() {
        return NOMCRI;
    }

    public void setNOMCRI(String NOMCRI) {
        this.NOMCRI = NOMCRI;
    }

    public String getCODCUR() {
        return CODCUR;
    }

    public void setCODCUR(String CODCUR) {
        this.CODCUR = CODCUR;
    }

    public void setProFinal(String ProFinal) {
        this.ProFinal = ProFinal;
    }

    public List<Resultados> getPromedio() {
        return Promedio;
    }

    public void setPromedio(List<Resultados> Promedio) {
        this.Promedio = Promedio;
    }

    public String getGradAlum() {
        return gradAlum;
    }

    public void setGradAlum(String gradAlum) {
        this.gradAlum = gradAlum;
    }

    public String getSecAlum() {
        return secAlum;
    }

    public void setSecAlum(String secAlum) {
        this.secAlum = secAlum;
    }

    public String getIdAlum() {
        return idAlum;
    }

    public void setIdAlum(String idAlum) {
        this.idAlum = idAlum;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getNomAlum() {
        return nomAlum;
    }

    public void setNomAlum(String nomAlum) {
        this.nomAlum = nomAlum;
    }

    public String getApeAlum() {
        return apeAlum;
    }

    public void setApeAlum(String apeAlum) {
        this.apeAlum = apeAlum;
    }

    public String getNomCur() {
        return nomCur;
    }

    public void setNomCur(String nomCur) {
        this.nomCur = nomCur;
    }

    public String getTipAlum() {
        return tipAlum;
    }

    public void setTipAlum(String tipAlum) {
        this.tipAlum = tipAlum;
    }

}
