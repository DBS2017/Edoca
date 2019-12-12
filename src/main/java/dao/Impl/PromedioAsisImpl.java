package dao.Impl;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.PromedioAsis;

public class PromedioAsisImpl extends Conexion {

    public List<PromedioAsis> listarPorCurs() throws Exception {
        try {
            ArrayList<PromedioAsis> ListpCur = new ArrayList<>();
            ResultSet rs;
            this.Conexion();
            String sql = "select curso.codcur as codcur, NOMCUR  from detalle_aula Inner join curso on detalle_aula.codcur = curso.codcur where idper = ?";
            PreparedStatement ps = getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            ListpCur = new ArrayList();
            while (rs.next()) {
                PromedioAsis ListporCur = new PromedioAsis();
                ListporCur.setCODCUR(rs.getString("codcur"));
                ListporCur.setNOMCUR(rs.getString("NOMCUR"));
                ListpCur.add(ListporCur);
            }
            return ListpCur;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<PromedioAsis> ListarporAula(String codAula, String codCur) throws SQLException {
        List<PromedioAsis> listar;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " select NOTREG, NOMPER, NOMCUR, NOMCRI, FECREG, GRAAUL, SECAUL from REGISTRO "
                    + " inner join MATRICULA "
                    + " on MATRICULA.codmat = registro.codmat "
                    + " inner join aula "
                    + " on aula.codaul = MATRICULA.codaul "
                    + " inner join PERSONA  "
                    + " on PERSONA.IDPER = MATRICULA.idest "
                    + " inner join detalle_aula "
                    + " on detalle_aula.coddet_aul = registro.coddet_aul "
                    + " inner join curso "
                    + " on curso.codcur = detalle_aula.codcur "
                    + " inner join CRITERIO "
                    + " on CRITERIO.CODCRI = registro.CODCRI "
                    + " WHERE MATRICULA.CODAUL= ? AND CURSO.CODCUR = ? AND TIPPER = '4'";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, codAula);
            ps.setString(2, codCur);
            rs = ps.executeQuery();
            listar = new ArrayList();
            PromedioAsis prom;
            while (rs.next()) {
                prom = new PromedioAsis();
                prom.setNomEstu(rs.getString("NOMEST"));
//                prom.setNOMCUR(rs.getString("NOMCUR"));
//                prom.getAULA().setGRAAU(rs.getString("GRAAUL"));
//                prom.getAULA().setSECAUL(rs.getString("SECAUL"));
                listar.add(prom);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listar;
    }

    public List<PromedioAsis> PromediarAsis(String codAula) throws SQLException {
        List<PromedioAsis> listar;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT NOMBRES,APELLIDOS,ASISTENCIAS,FALTAS,CABELLOANTIRREGLAMENTARIO,MALVESTIDO from VW_CONTROLASISTENCIAS WHERE AULA = ?";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, codAula);
            rs = ps.executeQuery();
            listar = new ArrayList();
            PromedioAsis prom;
            while (rs.next()) {
                prom = new PromedioAsis();
                prom.setNomEstu(rs.getString("NOMBRES"));
                prom.setApeEstu(rs.getString("APELLIDOS"));
                prom.setASISTENCIAS(rs.getString("ASISTENCIAS"));
                prom.setFALTAS(rs.getString("FALTAS"));
                prom.setCABELLO(rs.getString("CABELLOANTIRREGLAMENTARIO"));
                prom.setMALVESTIDO(rs.getString("MALVESTIDO"));
                listar.add(prom);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listar;

    }

}
