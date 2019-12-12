package dao.Impl;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Login;

public class LoginImpl extends Conexion{
    
    public Login startSession(String User, String Pass) throws SQLException {
        this.Conexion();
        ResultSet rs;
        Login usuario = null;
        try {
            String sql = "SELECT IDPER, NOMPER, APEPER, DNIPER, CORPER, TIPPER FROM PERSONA  WHERE CONTRPER = ? AND USUPER = ? and ESTPER= 'A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, User);
            ps.setString(2, Pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Login();
                usuario.setCodProf(rs.getString("IDPER"));
                usuario.setNomProf(rs.getString("NOMPER"));
                usuario.setApeProf(rs.getString("APEPER"));
                usuario.setDniProf(rs.getString("DNIPER"));
                usuario.setCorProf(rs.getString("CORPER"));
                usuario.setNiveProf(rs.getString("TIPPER"));
                usuario.setUseProf(User);
                usuario.setContProf(Pass);
            }
            return usuario;
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }
    
}
