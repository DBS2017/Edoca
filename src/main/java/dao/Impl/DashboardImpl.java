package dao.Impl;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Dashboard;
import modelo.NEstuAula;

public class DashboardImpl extends Conexion {

    public List<Dashboard> listarGrafEst() throws SQLException {
        List<Dashboard> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select count(SEXPER)as sexocount ,SEXPER from PERSONA where ESTPER ='A'  group by SEXPER";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Dashboard estu = new Dashboard();
                estu.setSexcount(rs.getInt("sexocount"));
                estu.setSexest(rs.getString("SEXPER"));
                listado.add(estu);
            }
        } catch (SQLException e) {
            throw e;

        }
        return listado;
    }

    public List<NEstuAula> listarCantEst() throws SQLException {
        List<NEstuAula> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select count(CODMAT)as CONTESTU , SALON from VW_AULA group by SALON";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NEstuAula cant = new NEstuAula();
                cant.setCONTESTU(rs.getInt("CONTESTU"));
                cant.setIDEST(rs.getString("SALON"));
                listado.add(cant);
            }
        } catch (SQLException e) {
            throw e;

        }
        return listado;
    }
}
