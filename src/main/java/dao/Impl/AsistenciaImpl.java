package dao.Impl;

import dao.Conexion;
import dao.IAsistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Asistencia;

public class AsistenciaImpl extends Conexion implements IAsistencia {

    @Override
    public void ingresar(Asistencia asistencia) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO ASISTENCIA (FECASI ,TIPASI, CODMAT) VALUES (to_date(?,'DD/MM/YYYY'),?,?)";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, asistencia.getFechAsis());
            ps.setString(2, asistencia.getTipAsis());
            ps.setInt(3, asistencia.getCodMat());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<Asistencia> listarPorAula(String aulas) throws Exception {
        List<Asistencia> listar;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "select au.CODAUL , mat.CODMAT , graaul || secaul as salon ,"
                    + " nomper, apeper "
                    + " from matricula mat "
                    + " inner join aula au "
                    + " on mat.codaul = au.codaul "
                    + " inner join PERSONA est "
                    + " on mat.idest= est.IDPER "
                    + " where au.codaul = ? ";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, aulas);
            rs = ps.executeQuery();
            listar = new ArrayList<>();
            Asistencia asisten;
            while (rs.next()) {
                asisten = new Asistencia();
                asisten.setCodMat(rs.getInt("CODMAT"));
                asisten.setCodAul(rs.getString("CODAUL"));
                asisten.setNomAul(rs.getString("salon"));
                asisten.setNomEst(rs.getString("nomper"));
                asisten.setApeEst(rs.getString("apeper"));
                listar.add(asisten);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public ArrayList<Asistencia> listAulas(String idProf) throws Exception {
        ArrayList<Asistencia> listar;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "select distinct au.CODAUL  , graaul || secaul  as salon from matricula mat "
                    + "                    inner join aula au on mat.codaul = au.codaul where codtut = ? ";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, idProf);
            rs = ps.executeQuery();
            listar = new ArrayList<>();
            Asistencia asisten;
            while (rs.next()) {
                asisten = new Asistencia();
                
                asisten.setCodAul(rs.getString("CODAUL"));
                asisten.setNomAul(rs.getString("salon"));
                listar.add(asisten);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }

    }

    public void traeridaula(Asistencia asistensia) throws Exception {
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT CODAUL FROM DETALLE_AULA WHERE CODDET_AUL = ? ";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, asistensia.getCodDet_Cur());
            rs = ps.executeQuery();
            rs.next();
            asistensia.setCodAul(rs.getString("CODAUL"));
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<Asistencia> listAsisten() throws Exception {
        this.Conexion();
        List<Asistencia> list;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM VW_LISTASIS";
            PreparedStatement ps = getConectar().prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Asistencia asis;
            while (rs.next()) {
                asis = new Asistencia();
                asis.setFechAsis(rs.getString("FECASI"));
                asis.setCodAul(rs.getString("CODAUL"));
                asis.setNomAul(rs.getString("SALON"));
                list.add(asis);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Asistencia> listAsis(String fech, String idaulas) throws Exception {
        List<Asistencia> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "SELECT CODASI, FECHA ,CODAUL, AULA,IDPER,ESTUD, TIPASI  FROM VW_LISTARASIS WHERE FECHA= ? AND AULA =?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setObject(1, fech);
            ps.setString(2, idaulas);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Asistencia asis;
            while (rs.next()) {
                asis = new Asistencia();
                asis.setCodAsis(rs.getString("CODASI"));
                asis.setFechAsis(rs.getString("FECHA"));
                asis.setCodAul(rs.getString("CODAUL"));
                asis.setNomAul(rs.getString("AULA"));
                asis.setIDPER(rs.getString("IDPER"));
                asis.setNomEst(rs.getString("ESTUD"));
                asis.setTipAsis(rs.getString("TIPASI"));
                list.add(asis);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    public String leeraula(String aula) throws Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select CODAUL from AULA where graaul || secaul  = ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, aula);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("CODAUL");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void actualiAsis(Asistencia asisten) throws Exception {

        try {
            this.Conexion();
            String sql = "update ASISTENCIA set tipasi=? where CODASI=?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, asisten.getTipAsis());
            ps.setString(2, asisten.getCodAsis());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
