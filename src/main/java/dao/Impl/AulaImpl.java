package dao.Impl;

import dao.Conexion;
import dao.IAula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Aula;

public class AulaImpl extends Conexion implements IAula {

    @Override
    public void registrar(Aula aula) throws Exception {
        try {
            this.Conexion();
            String sql = "insert into aula (SECAUL, GRAAUL, ESTAUL,CODTUT) VALUES (? , ? ,?  ,?)";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, aula.getSecAul());
            ps.setString(2, aula.getGraAul());
            ps.setString(3, "A");
            ps.setString(4, aula.getCodtut());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Aula aula) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE aula SET SECAUL=?,GRAAUL=?,CODTUT=? WHERE CODAUL=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, aula.getSecAul());
            ps.setString(2, aula.getGraAul());
            ps.setString(3, aula.getCodtut());
            ps.setString(4, aula.getCodAul());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public Aula leerAula(String codAula) throws Exception {
        Aula aula = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT  AULA.CODAUL, AULA.SECAUL, AULA.GRAAUL, "
                    + " DNIPER || '||' || NOMPER || APEPER AS TUTOR"
                    + " FROM AULA"
                    + " INNER JOIN PERSONA"
                    + " ON AULA.CODTUT = PERSONA.IDPER where CODAUL like ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, codAula);
            rs = ps.executeQuery();
            if (rs.next()) {
                aula = new Aula();
                aula.setCodAul(rs.getString("CODAUL"));
                aula.setSecAul(rs.getString("SECAUL"));
                aula.setGraAul(rs.getString("GRAAUL"));
                aula.setAutTut(rs.getString("TUTOR"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
        return aula;
    }

    @Override
    public void eliminar(Aula aula) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE AULA SET  ESTAUL= 'I'  where CODAUL=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, aula.getCodAul());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Aula> listarAul() throws Exception {
        List<Aula> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT  AULA.CODAUL,  AULA.SECAUL, AULA.GRAAUL, PERSONA.NOMPER || ' ' || PERSONA.APEPER AS TUTOR  FROM aula  "
                    + " INNER JOIN PERSONA "
                    + " ON AULA.CODTUT = PERSONA.IDPER where AULA.ESTAUL = 'A' AND TIPPER = '2' ";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Aula au = new Aula();
                au.setCodAul(rs.getString("CODAUL"));
                au.setSecAul(rs.getString("SECAUL"));
                au.setGraAul(rs.getString("GRAAUL"));
                au.setAutTut(rs.getString("TUTOR"));
                listado.add(au);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    //autocomplete Tutor
    public String obtenerCodigoTutor(String Tutor) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select IDPER from PERSONA WHERE DNIPER || '||' || NOMPER || APEPER = ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, Tutor);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDPER");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

// public void ObtCodTuto(String codtut) throws Exception {
//     
//        ResultSet rs;
//        try {
//            this.Conexion();
//            String sql = "Select personal.idper from  PERSONAL where CONCAT(DNIPER, ' || ' ,NOMPER, APEPER ) like '%?%'";
//            PreparedStatement ps = this.getConectar().prepareCall(sql);
//            rs = ps.executeQuery();
//            rs.next();
//            codtut.setCodtut(rs.getString("idper"));
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            this.cerrar();
//        }
//    }
    //AUTOCOMPLETE TUTOR
    public List<String> autocompleteTutor(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select DNIPER || '||' || NOMPER || APEPER AS TUTOR from PERSONA WHERE DNIPER LIKE ? AND ESTPER = 'A' AND TIPPER = '2' ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("TUTOR"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
