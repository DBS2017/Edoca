package dao.Impl;

import dao.Conexion;
import dao.ICurso_Aula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Curso_Aula;

public class Curso_AulaImpl extends Conexion implements ICurso_Aula {

    @Override
    public void insertar(Curso_Aula curso_aula) throws Exception {
        try {
            this.Conexion();
            String sql = "Insert into detalle_aula ( codcur, codaul, idper, ESTDETAUL) values (? , ?, ?,'A')";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, curso_aula.getCodCur());
            ps.setString(2, curso_aula.getCodAul());
            ps.setString(3, curso_aula.getIdPer());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en insertar " + e.getMessage());
            throw e;
        } finally {
            cerrar();
        }
    }

    public Curso_Aula leerCurso(String codiAulCurs) throws Exception {
        Curso_Aula aulCurs = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "select CODDET_AUL, nomcur , GRAAUL || SECAUL as aula, DNIPER ||','||APEPER ||' '||NOMPER AS PERSONAL from detalle_aula "
                    + " INNER JOIN aula "
                    + " on aula.CODAUL = detalle_aula.CODAUL "
                    + " inner join curso "
                    + " on curso.CODCUR = detalle_aula.CODCUR "
                    + " inner join persona "
                    + " on persona.idper = detalle_aula.idper where CODDET_AUL like ? AND  ESTDETAUL = 'A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, codiAulCurs);
            rs = ps.executeQuery();
            if (rs.next()) {
                aulCurs = new Curso_Aula();
                aulCurs.setCodCurAul(rs.getString("CODDET_AUL"));
                aulCurs.setAutCur_Aul(rs.getString("nomcur"));
                aulCurs.setCheAul(rs.getString("aula"));
                aulCurs.setAutPer(rs.getString("PERSONAL"));

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
        return aulCurs;
    }

    @Override
    public void modificar(Curso_Aula curso_aula) throws Exception {
        try {
            this.Conexion();
            String sql = "update detalle_aula set codcur= ?, codaul= ?, idper = ? where coddet_aul=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, curso_aula.getCodCur());
            ps.setString(2, curso_aula.getCodAul());
            ps.setString(3, curso_aula.getIdPer());
            ps.setString(4, curso_aula.getCodCurAul());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
    }

    @Override
    public void eliminar(Curso_Aula curso_aula) throws Exception {
        try {
            this.Conexion();
            String sql = " UPDATE detalle_aula SET ESTDETAUL='I' where CODDET_AUL=? ";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, curso_aula.getCodCurAul());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en eliminar " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Curso_Aula> listCurAul() throws Exception {
        List<Curso_Aula> listadoDetAul;
        ResultSet rs;
        try {
            Conexion();
            String sql = " SELECT CODDET_AUL , NOMCUR ,"
                    + " GRAAUL || SECAUL AS AULA, "
                    + " DNIPER ||','||APEPER ||' '||NOMPER AS PERSONAL "
                    + " FROM DETALLE_AULA "
                    + " INNER JOIN AULA ON AULA.CODAUL = DETALLE_AULA.CODAUL "
                    + " INNER JOIN CURSO ON CURSO.CODCUR = DETALLE_AULA.CODCUR "
                    + " inner JOIN PERSONA ON DETALLE_AULA.IDPER = PERSONA.IDPER WHERE ESTDETAUL ='A' AND TIPPER = '3' AND ESTAUL = 'A' AND ESTCUR = 'A'  order by GRAAUL ";
            listadoDetAul = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Curso_Aula curAul = new Curso_Aula();
                curAul.setCodCurAul(rs.getString("CODDET_AUL"));
                curAul.setAutCur_Aul(rs.getString("nomcur"));
                curAul.setCheAul(rs.getString("aula"));
                curAul.setAutPer(rs.getString("PERSONAL"));

                listadoDetAul.add(curAul);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
        return listadoDetAul;
    }

    //Autcomplet curso
    public String obtCurso(String Curso) throws Exception {
        Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT CODCUR FROM curso where NOMCUR like ? ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, Curso);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODCUR");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autoCurso(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select NOMCUR  from curso where NOMCUR like ? AND ESTCUR = 'A' ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("NOMCUR"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    //autocomplete aula
    public String obtAula(String Aula) throws Exception {
        Conexion();
        ResultSet rs;
        try {
            String sql = "select CODAUL from aula where GRAAUL || SECAUL like ? ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, Aula);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODAUL");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autoAul(String consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select distinct GRAAUL || SECAUL as SECAUL  from aula where GRAAUL like ? AND ESTAUL LIKE 'A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("SECAUL"));
            }
            return Lista;
        } catch (SQLException e) {
            System.out.println("Error de autAula " + e.getMessage());
            throw e;
        }
    }

    //autocomplete Tutor
    public String obtenerCodigoTutor(String Tutor) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "Select idper from  PERSONA where DNIPER ||','||APEPER ||' '||NOMPER like ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, Tutor);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("idper");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    //AUTOCOMPLETE TUTOR
    public List<String> autocomplPer(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT DNIPER ||','||APEPER ||' '||NOMPER AS PER  FROM PERSONA WHERE DNIPER LIKE ? AND ESTPER= 'A' AND TIPPER='3' ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("PER"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }
}
