package dao.Impl;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Help;

public class HelpImpl extends Conexion {

    public List<Help> Ayuda() throws Exception {
        List<Help> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT * FROM CONSULTAS ";
            lista = new ArrayList<>();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Help list = new Help();
                list.setIDHEP(rs.getString("IDHELP"));
                list.setDNIPER(rs.getString("DNIPER"));
                list.setCONSULT(rs.getString("CONSULTA"));
                lista.add(list);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

}
