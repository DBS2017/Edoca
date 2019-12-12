package dao.Impl;

import dao.Conexion;
import dao.IPersonal;
import encriptacion.Encriptar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Personal;

public class PersonalImpl extends Conexion implements IPersonal {

    @Override
    public void registrar(Personal personal) throws Exception {
        String INSERT = "INSERT INTO PERSONA"
                + "( NOMPER, APEPER, TIPPER, DNIPER, CORPER, CELPER, SEXPER, ESTPER,CONTRPER, USUPER)"
                + " values (?, ?, ?, ?, ?, ?, ?,'A', ? , ?)";
        try {
            this.Conexion();
            PreparedStatement ps = this.getConectar().prepareStatement(INSERT);
            ps.setString(1, personal.getNomPer());
            ps.setString(2, personal.getApePer());
            ps.setString(3, personal.getCarPer());
            ps.setString(4, personal.getDniPer());
            ps.setString(5, personal.getCorPer());
            ps.setString(6, personal.getCelPer());
            ps.setString(7, personal.getSexPer());
            ps.setString(8, personal.getContPer());
            ps.setString(9, personal.getUsuPer());            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ingresar datos Impl" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public Personal leerPersonal(String codigoPer) throws Exception {
        Personal per = null;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT IDPER, NOMPER, APEPER, TIPPER, CORPER,DNIPER, CELPER, SEXPER, CONTRPER, USUPER FROM PERSONA WHERE IDPER LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, codigoPer);
            rs = ps.executeQuery();
            if (rs.next()) {
                per = new Personal();
                per.setIdPer(rs.getString("IDPER"));
                per.setNomPer(rs.getString("NOMPER"));
                per.setApePer(rs.getString("APEPER"));
                per.setCarPer(rs.getString("TIPPER"));
                per.setCorPer(rs.getString("CORPER"));
                per.setDniPer(rs.getString("DNIPER"));
                per.setCelPer(rs.getString("CELPER"));
                per.setSexPer(rs.getString("SEXPER"));
                per.setContPer(rs.getString("CONTRPER"));
                per.setUsuPer(rs.getString("USUPER"));

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
        return per;
    }

    @Override
    public void modificar(Personal personal) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE PERSONA SET NOMPER=?, APEPER=?, TIPPER=?, DNIPER=?, CORPER=?, CELPER=?, SEXPER=?, CONTRPER= ?, USUPER=? WHERE IDPER=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, personal.getNomPer());
            ps.setString(2, personal.getApePer());
            ps.setString(3, personal.getCarPer());
            ps.setString(4, personal.getDniPer());
            ps.setString(5, personal.getCorPer());
            ps.setString(6, personal.getCelPer());
            ps.setString(7, personal.getSexPer());
            ps.setString(8, personal.getContPer());
            ps.setString(9, personal.getUsuPer());
            ps.setString(10, personal.getIdPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Personal personal) throws Exception {        
        try {
            Conexion();
            String sql = "update PERSONA set estper='I' where IDPER=?";            
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, personal.getIdPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Personal> listarPer() throws Exception {
        List<Personal> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT * FROM PERSONA WHERE  TIPPER in('1', '2' ,'3') AND ESTPER = 'A' ";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Personal per = new Personal();
                per.setIdPer(rs.getString("IDPER"));
                per.setNomPer(rs.getString("NOMPER"));
                per.setApePer(rs.getString("APEPER"));
                per.setCarPer(rs.getString("TIPPER"));
                per.setDniPer(rs.getString("DNIPER"));
                per.setCorPer(rs.getString("CORPER"));
                per.setCelPer(rs.getString("CELPER"));
                per.setSexPer(rs.getString("SEXPER"));
                per.setContPer(rs.getString("CONTRPER"));
                per.setUsuPer(rs.getString("USUPER"));
                listado.add(per);
            }
        } catch (SQLException e) {
            throw e;

        } finally {
            this.cerrar();
        }
        return listado;

    }

}
