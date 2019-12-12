package dao.Impl;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Promedio;

public class PromedioImpl extends Conexion {

    public List<Promedio> ListarporAula(String CodAula, String codCurso) throws Exception {
        List<Promedio> listar;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "select * from VW_NOTAS_ESTU where codaul = ? and codcur = ? order by nomper";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, CodAula);
            ps.setString(2, codCurso);
            rs = ps.executeQuery();
            listar = new ArrayList();
            Promedio prom;
            while (rs.next()) {
                prom = new Promedio();
                prom.setCODESTU(rs.getString("idper"));
                prom.getNota().setNotReg(rs.getString("notreg"));
                prom.setNOMEST(rs.getString("nomper"));
                prom.setNOMCUR(rs.getString("nomcur"));
                prom.getCriterio().setNomCri(rs.getString("nomcri"));
                prom.getCriterio().setCodCri(rs.getString("codcri"));
                prom.getNota().setFechReg(rs.getString("fecreg"));
                prom.setCODCUR(rs.getString("codcur"));
                prom.setCODAUL(rs.getString("codaul"));
                prom.setLstPromedioFinal(listPromFinal(prom.getCODAUL(), prom.getCODCUR(), prom.getCODESTU()));
                listar.add(prom);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public ArrayList<Promedio> listarPorCurs() throws Exception {
        try {
            ArrayList<Promedio> ListpCur = new ArrayList<>();
            ResultSet rs;
            this.Conexion();
            String sql = "select detalle_aula.codcur as codcur, nomcur from detalle_aula "
                    + " inner join curso "
                    + " on curso.codcur = detalle_aula.codcur "
                    + " where idper =? ";
            PreparedStatement ps = getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            ListpCur = new ArrayList();
            while (rs.next()) {
                Promedio ListporCur = new Promedio();
                ListporCur.setCODCUR(rs.getString("codcur"));
                ListporCur.setNOMCUR(rs.getString("nomcur"));
                ListpCur.add(ListporCur);
            }
            return ListpCur;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void traerIdAula(Promedio promedio) throws Exception {
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "select codaul from DETALLE_AULA where coddet_aul = ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, promedio.getCODDETCUR());
            rs = ps.executeQuery();
            rs.next();
            promedio.setCODAUL(rs.getString("codaul"));
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
//promediar las notas del estudiante usando los parametros del codigo de aula, codigo de currso y el identificador del estudiante
    public List<Promedio> listPromFinal(String CODAUL, String CODCUR, String CODESTU) throws Exception {
        List<Promedio> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select nomper, NOTA from VW_PROMEDIO_ESTU WHERE CODAUL = ? AND CODCUR = ? AND IDPER = ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, CODAUL);
            ps.setString(2, CODCUR);
            ps.setString(3, CODESTU);
            
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Promedio prom;
            while (rs.next()) {
                prom = new Promedio();
                prom.setPROMFINAL(rs.getString("NOTA"));
                list.add(prom);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }

    }

}
