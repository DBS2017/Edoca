package modelo;

public class Asistencia {

    private String codAsis;
    private String fechAsis;
    private String tipAsis;
    private int codMat;
    private String codAul;
    private String nomAul;
    private String nomEst;
    private String apeEst;
    private String codDet_Cur;

    private String IDPER;

    public String getApeEst() {
        return apeEst;
    }

    public void setApeEst(String apeEst) {
        this.apeEst = apeEst;
    }

    public String getCodDet_Cur() {
        return codDet_Cur;
    }

    public void setCodDet_Cur(String codDet_Cur) {
        this.codDet_Cur = codDet_Cur;
    }

    public String getNomEst() {
        return nomEst;
    }

    public void setNomEst(String nomEst) {
        this.nomEst = nomEst;
    }

    public String getNomAul() {
        return nomAul;
    }

    public void setNomAul(String nomAul) {
        this.nomAul = nomAul;
    }

    public String getCodAul() {
        return codAul;
    }

    public void setCodAul(String codAul) {
        this.codAul = codAul;
    }

    public String getCodAsis() {
        return codAsis;
    }

    public void setCodAsis(String codAsis) {
        this.codAsis = codAsis;
    }

    public String getFechAsis() {
        return fechAsis;
    }

    public void setFechAsis(String fechAsis) {
        this.fechAsis = fechAsis;
    }

    public String getTipAsis() {
        return tipAsis;
    }

    public void setTipAsis(String tipAsis) {
        this.tipAsis = tipAsis;
    }

    public int getCodMat() {
        return codMat;
    }

    public void setCodMat(int codMat) {
        this.codMat = codMat;
    }

    public String getIDPER() {
        return IDPER;
    }

    public void setIDPER(String IDPER) {
        this.IDPER = IDPER;

    }
}
