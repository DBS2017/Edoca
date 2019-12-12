package dao.Impl;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ListaDeNotas;

public class ListaDeNotasImpl extends Conexion {


    public List<ListaDeNotas> listarB3() throws Exception {
        List<ListaDeNotas> listado3;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM LISTADENOTAS";
            PreparedStatement ps = getConectar().prepareStatement(sql);
            listado3 = new ArrayList();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                ListaDeNotas b1 = new ListaDeNotas();
                b1.setCODMAT(rs.getInt("CODMAT"));
                b1.setGRADO(rs.getString("GRADO"));
                b1.setSECCIÓN(rs.getString("SECCIÓN"));
                b1.setESTUDIANTE(rs.getString("ESTUDIANTE"));
                b1.setCURSO(rs.getString("CURSO"));
                b1.setBIMESTRE1(rs.getString("BIMESTRE1"));
                b1.setBIMESTRE2(rs.getString("BIMESTRE2"));
                b1.setBIMESTRE3(rs.getString("BIMESTRE3"));
                b1.setBIMESTRE4(rs.getString("BIMESTRE4"));
                b1.setBIMESTRE5(rs.getString("BIMESTRE5"));
                listado3.add(b1);
            }
            return listado3;
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
    }
}
