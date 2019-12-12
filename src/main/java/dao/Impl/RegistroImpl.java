package dao.Impl;

import dao.Conexion;
import dao.IRegistro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Registro;

public class RegistroImpl extends Conexion implements IRegistro {

    @Override
    public void crearNota(Registro registro) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO REGISTRO (FECREG, NOTREG, CODMAT, CODCRI, CODDET_AUL) VALUES (TO_DATE(?,'DD/MM/YYYY'),?,?,?,?)";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, registro.getFechReg());
            ps.setString(2, registro.getNotReg());
            ps.setString(3, registro.getCodMatr());
            ps.setString(4, registro.getCodCri());
            ps.setString(5, registro.getCodDetAul());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    //conbos anidados obtener codigo de curso
    public ArrayList<Registro> listarAulas(String codCur) throws Exception {
        try {
            ArrayList<Registro> listarCur = new ArrayList<>();
            ResultSet rs;
            this.Conexion();
            String sql = "SELECT CODDET_AUL, GRAAUL|| ' - ' ||SECAUL AS AULAS, AL.CODAUL as AULA from DETALLE_AULA DAL inner join AULA AL on AL.CODAUL = DAL.CODAUL WHERE CODCUR = ?";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, codCur);
            rs = ps.executeQuery();
            while (rs.next()) {
                Registro not = new Registro();
                not.setCodDetAul(rs.getString("CODDET_AUL"));
                not.setNomAul(rs.getString("AULAS"));
                not.setCodAul(rs.getString("AULA"));
                listarCur.add(not);
            }
            return listarCur;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }

    }

    public void traerIDAula(Registro registro) throws Exception {
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT CODAUL FROM DETALLE_AULA WHERE CODDET_AUL= ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, registro.getCodDetAul());
            rs = ps.executeQuery();
            while (rs.next()) {
                registro.setCodAul(rs.getString("CODAUL"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<Registro> ListarporAula(String CodAula) throws Exception {
        List<Registro> listMatr;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "select codmat ,est.idper, nomper,  apeper "
                    + " from matricula mat"
                    + " inner join PERSONA est"
                    + " on mat.idest = est.IDPER "
                    + " where codaul = ? ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, CodAula);
            rs = ps.executeQuery();
            listMatr = new ArrayList<>();
            Registro regi;
            while (rs.next()) {
                regi = new Registro();
                regi.setCodMatr(rs.getString("codmat"));
                regi.setCodEst(rs.getString("idper"));
                regi.setNomEst(rs.getString("nomper"));
                regi.setApeEst(rs.getString("apeper"));
                listMatr.add(regi);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listMatr;
    }

    public ArrayList<Registro> ListarCriterios() throws Exception {
        try {
            ArrayList<Registro> listar = new ArrayList<>();
            ResultSet rs;
            this.Conexion();
            String sql = "SELECT * FROM CRITERIO";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Registro regis = new Registro();
                regis.setCodCri(rs.getString("CODCRI"));
                regis.setNomCri(rs.getString("NOMCRI"));
                listar.add(regis);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public ArrayList<Registro> listarPorCurs(String IdProf) throws Exception {

        try {
            ArrayList<Registro> ListpCur = new ArrayList();
            ResultSet rs;
            this.Conexion();
            String sql = "select distinct CURSO.NOMCUR,CURSO.CODCUR"
                    + " from detalle_aula "
                    + " inner join CURSO "
                    + " on detalle_aula.codcur = curso.codcur where idper LIKE ? ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, IdProf);
            rs = ps.executeQuery();
            while (rs.next()) {
                Registro rgs = new Registro();
                rgs.setCodCur(rs.getString("CODCUR"));
                rgs.setNomCur(rs.getString("NOMCUR"));
                ListpCur.add(rgs);
            }
            return ListpCur;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public ArrayList<Registro> listarFechas(String criterio, String detalle) throws Exception {
        try {
            ArrayList<Registro> listar = new ArrayList<>();
            ResultSet rs;
            this.Conexion();
            String sql = "select DISTINCT TO_DATE(FECREG,'DD/MM/YYYY')as fecha "
                    + " from REGISTRO "
                    + " where CODCRI = ? and CODDET_AUL = ?  order by fecha desc";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, criterio);
            ps.setString(2, detalle);
            rs = ps.executeQuery();
            while (rs.next()) {
                Registro Not = new Registro();
                Not.setFechReg(rs.getString("fecha"));
                listar.add(Not);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<Registro> ListarNotPorEstu(String detcur, String criterio, String fecha) throws Exception {
        List<Registro> listar;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM  VWREGISTRO WHERE CODDET_AUL = ? AND  NOMCRI = ? AND FECHA= ?";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, detcur);
            ps.setString(2, criterio);
            ps.setString(3, fecha);
            rs = ps.executeQuery();
            listar = new ArrayList();
            Registro notas;
            while (rs.next()) {
                notas = new Registro();
                notas.setCodMatr(rs.getString("CODDET_AUL"));
                notas.setNomEst(rs.getString("NOMEST"));
                notas.setApeEst(rs.getString("APEEST"));
                notas.setCosReg(rs.getString("CODREG"));
                notas.setNotReg(rs.getString("NOTREG"));
                listar.add(notas);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listar;
    }

    public List<Registro> listshe(String iddetcrit) throws Exception {
        List<Registro> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "SELECT * FROM VWLISTSHE WHERE CODDET_AUL= ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, iddetcrit);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Registro notas;
            while (rs.next()) {
                notas = new Registro();
                notas.setFechReg(rs.getString("FECHA"));
                notas.setCodCri(rs.getString("NOMCRI"));
                list.add(notas);
            }
        } catch (SQLException e) {
            throw e;
        }
        return list;

    }

    public void actualiNot(Registro notas) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE REGISTRO SET NOTREG=? WHERE CODREG=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, notas.getNotReg());
            ps.setString(2, notas.getCosReg());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
