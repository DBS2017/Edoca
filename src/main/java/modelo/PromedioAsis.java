package modelo;

public class PromedioAsis {

    private String idAsis;
    private String FecAsis;
    private String Asisten;
    private String ASISTENCIAS;
    private String FALTAS;
    private String MALVESTIDO;
    private String CABELLO;
    private String idTraz;
    private String IdAulas;
    private String NomAula;
    private String AULA;
    private String idEstu;
    private String NomEstu;
    private String ApeEstu;
    private String CODCUR;
    private String NOMCUR;
    private String idDetCurso;
    private boolean check = true;

    private Criterio criterio;
    private Aula aula;

    public PromedioAsis() {

        aula = new Aula();
    }

    public String getIdAsis() {
        return idAsis;
    }

    public void setIdAsis(String idAsis) {
        this.idAsis = idAsis;
    }

    public String getFecAsis() {
        return FecAsis;
    }

    public void setFecAsis(String FecAsis) {
        this.FecAsis = FecAsis;
    }

    public String getAsisten() {
        return Asisten;
    }

    public void setAsisten(String Asisten) {
        this.Asisten = Asisten;
    }

    public String getIdTraz() {
        return idTraz;
    }

    public void setIdTraz(String idTraz) {
        this.idTraz = idTraz;
    }

    public String getIdAulas() {
        return IdAulas;
    }

    public void setIdAulas(String IdAulas) {
        this.IdAulas = IdAulas;
    }

    public String getNomAula() {
        return NomAula;
    }

    public void setNomAula(String NomAula) {
        this.NomAula = NomAula;
    }

    public String getAULA() {
        return AULA;
    }

    public void setAULA(String AULA) {
        this.AULA = AULA;
    }

    public String getIdEstu() {
        return idEstu;
    }

    public void setIdEstu(String idEstu) {
        this.idEstu = idEstu;
    }

    public String getNomEstu() {
        return NomEstu;
    }

    public void setNomEstu(String NomEstu) {
        this.NomEstu = NomEstu;
    }

    public String getApeEstu() {
        return ApeEstu;
    }

    public void setApeEstu(String ApeEstu) {
        this.ApeEstu = ApeEstu;
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

    public String getIdDetCurso() {
        return idDetCurso;
    }

    public void setIdDetCurso(String idDetCurso) {
        this.idDetCurso = idDetCurso;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public String getASISTENCIAS() {
        return ASISTENCIAS;
    }

    public void setASISTENCIAS(String ASISTENCIAS) {
        this.ASISTENCIAS = ASISTENCIAS;
    }

    public String getFALTAS() {
        return FALTAS;
    }

    public void setFALTAS(String FALTAS) {
        this.FALTAS = FALTAS;
    }

    public String getMALVESTIDO() {
        return MALVESTIDO;
    }

    public void setMALVESTIDO(String MALVESTIDO) {
        this.MALVESTIDO = MALVESTIDO;
    }

    public String getCABELLO() {
        return CABELLO;
    }

    public void setCABELLO(String CABELLO) {
        this.CABELLO = CABELLO;
    }

    
}
