package modelo;

import java.util.List;


public class Nota {
    
    private String CODREG;
    private String FECREG;
    private String NOTREG;
    private int CODMAT; //(ALUMNO)
    private int CODCRI; //CODIGO DE CRITERIO
    private int CODDET_AUL; //EL GRADO DEL ALUMNO
    private String CODEST; //CODIGO DE ESTUDIANTE
        
    private String IDMATRI;
    private String NOMCUR;
    private String NOMAUL;
    private String CODCUR;
    private String CODAUL; //CODIGO DE AULA 
    private String NOMCRI ;
    private String NOMPER ;
    private String APEPER ;

    private int REGNOT;
    private String NOTEXDI;
    private String NOTTADI;
    private String NOTRECU;
    private String NOTEXME;
    
    private String PROFIN;//PROMEDIO FINAL
    private List<Nota> lstPromedioFinal;//LA LISTA EN PRIMEDIO FINAL

    public String getIDMATRI() {
        return IDMATRI;
    }

    public void setIDMATRI(String IDMATRI) {
        this.IDMATRI = IDMATRI;
    }

    
    public int getREGNOT() {
        return REGNOT;
    }

    public void setREGNOT(int REGNOT) {
        this.REGNOT = REGNOT;
    }


    public List<Nota> getLstPromedioFinal() {
        return lstPromedioFinal;
    }

    public void setLstPromedioFinal(List<Nota> lstPromedioFinal) {
        this.lstPromedioFinal = lstPromedioFinal;
    }
        
    public String getNOTEXDI() {
        return NOTEXDI;
    }

    public void setNOTEXDI(String NOTEXDI) {
        this.NOTEXDI = NOTEXDI;
    }

        public String getNOTTADI() {
        return NOTTADI;
    }

    public void setNOTTADI(String NOTTADI) {
        this.NOTTADI = NOTTADI;
    }

    public String getNOTRECU() {
        return NOTRECU;
    }

    public void setNOTRECU(String NOTRECU) {
        this.NOTRECU = NOTRECU;
    }

    public String getNOTEXME() {
        return NOTEXME;
    }

    public void setNOTEXME(String NOTEXME) {
        this.NOTEXME = NOTEXME;
    }

    
    
    public String getPROFIN() {
        return PROFIN;
    }

    public void setPROFIN(String PROFIN) {
        this.PROFIN = PROFIN;
    }
           
    public String getCODEST() {
        return CODEST;
    }

    public void setCODEST(String CODEST) {
        this.CODEST = CODEST;
    }

    
    
    public String getNOMAUL() {
        return NOMAUL;
    }

    public void setNOMAUL(String NOMAUL) {
        this.NOMAUL = NOMAUL;
    }
        
    public String getNOMCUR() {
        return NOMCUR;
    }

    public void setNOMCUR(String NOMCUR) {
        this.NOMCUR = NOMCUR;
    }

    
    
    public String getCODCUR() {
        return CODCUR;
    }

    public void setCODCUR(String CODCUR) {
        this.CODCUR = CODCUR;
    }

    
    public String getCODAUL() {
        return CODAUL;
    }

    public void setCODAUL(String CODAUL) {
        this.CODAUL = CODAUL;
    }

    public String getNOMCRI() {
        return NOMCRI;
    }

    public void setNOMCRI(String NOMCRI) {
        this.NOMCRI = NOMCRI;
    }

    public String getNOMPER() {
        return NOMPER;
    }

    public void setNOMPER(String NOMPER) {
        this.NOMPER = NOMPER;
    }

    public String getAPEPER() {
        return APEPER;
    }

    public void setAPEPER(String APEPER) {
        this.APEPER = APEPER;
    }
    
    
    public String getCODREG() {
        return CODREG;
    }

    public void setCODREG(String CODREG) {
        this.CODREG = CODREG;
    }

    public String getFECREG() {
        return FECREG;
    }

    public void setFECREG(String FECREG) {
        this.FECREG = FECREG;
    }

    public String getNOTREG() {
        return NOTREG;
    }

    public void setNOTREG(String NOTREG) {
        this.NOTREG = NOTREG;
    }

    public int getCODMAT() {
        return CODMAT;
    }

    public void setCODMAT(int CODMAT) {
        this.CODMAT = CODMAT;
    }

    public int getCODCRI() {
        return CODCRI;
    }

    public void setCODCRI(int CODCRI) {
        this.CODCRI = CODCRI;
    }

    public int getCODDET_AUL() {
        return CODDET_AUL;
    }

    public void setCODDET_AUL(int CODDET_AUL) {
        this.CODDET_AUL = CODDET_AUL;
    }

  
    
    
}
