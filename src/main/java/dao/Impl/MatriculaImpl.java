package dao.Impl;

import dao.Conexion;
import dao.IMatricula;
//import java.io.File;
//import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
//import javax.faces.context.FacesContext;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
import modelo.Matricula;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;

public class MatriculaImpl extends Conexion implements IMatricula {

    @Override
    public void registrar(Matricula matricula) throws Exception {
        try {
            String INSERT = "INSERT INTO MATRICULA"
                    + "( FECMAT,IDEST, IDAPO, CODAUL, ESTMAT)"
                    + " VALUES (SYSDATE, ?, ?, ?, 'A')";
            PreparedStatement ps = this.getConectar().prepareStatement(INSERT);
            ps.setString(1, matricula.getIdEst());
            ps.setString(2, matricula.getIdApo());
            ps.setString(3, matricula.getIdAul());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ingresar datos Impl" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public Matricula leerMatri(String codMatri) throws Exception {
        Matricula matr = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT CODMAT, FECMAT, EST.APEPER||' '||EST.NOMPER|| '('||EST.DNIPER||')' AS ESTUDIANTE, "
                    + " APO.APEPER||' '||APO.NOMPER|| '('||APO.DNIPER||')' AS APODERADO, GRAAUL ||' '||SECAUL AS "
                    + " AULA FROM  MATRICULA MAT INNER JOIN PERSONA EST ON EST.IDPER = MAT.IDEST INNER "
                    + " JOIN PERSONA APO "
                    + " ON APO.IDPER = MAT.IDAPO INNER JOIN AULA ON AULA.CODAUL = MAT.CODAUL WHERE CODMAT LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, codMatri);
            rs = ps.executeQuery();
            if (rs.next()) {
                matr = new Matricula();
                matr.setCodMat(rs.getString("CODMAT"));
                matr.setFecMat(rs.getString("FECMAT"));
                matr.setAutEst(rs.getString("ESTUDIANTE"));
                matr.setAutApo(rs.getString("APODERADO"));
                matr.setAutAul(rs.getString("AULA"));
            }
        } catch (SQLException e) {
            throw e;

        } finally {
            cerrar();
        }
        return matr;
    }

    @Override
    public void modificar(Matricula matricula) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE MATRICULA SET  IDEST=?, IDAPO=?, CODAUL=? WHERE CODMAT=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, matricula.getIdEst());
            ps.setString(2, matricula.getIdApo());
            ps.setString(3, matricula.getIdAul());
            ps.setString(4, matricula.getCodMat());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("error en update" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Matricula matricula) throws Exception {
        try {
            this.Conexion();
            String sql = "update MATRICULA SET ESTMAT='I' where CODMAT=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, matricula.getCodMat());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Matricula> listarMat() throws Exception {
        List<Matricula> listado;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT ESTMAT , CODMAT, TO_CHAR(FECMAT, 'DD/MM/YYYY' ) AS FECMAT , EST.APEPER|| ' ' || EST.NOMPER AS ESTUDIANTE, "
                    + " APO.APEPER|| ' ' || APO.NOMPER AS APODERADO, GRAAUL ||' '||SECAUL AS AULA  "
                    + " FROM  MATRICULA MAT INNER JOIN PERSONA EST ON EST.IDPER = MAT.IDEST "
                    + " INNER JOIN PERSONA APO "
                    + " ON APO.IDPER = MAT.IDAPO "
                    + " INNER JOIN AULA "
                    + " ON AULA.CODAUL = MAT.CODAUL "
                    + " WHERE ESTMAT = 'A' AND ESTAUL = 'A' AND FECMAT BETWEEN (SYSDATE-365) AND SYSDATE ";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Matricula mat = new Matricula();
                mat.setCodMat(rs.getString("CODMAT"));
                mat.setFecMat(rs.getString("FECMAT"));
                mat.setAutEst(rs.getString("ESTUDIANTE"));
                mat.setAutApo(rs.getString("APODERADO"));
                mat.setAutAul(rs.getString("AULA"));
                listado.add(mat);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listado;
    }

    //AUTOCOMPLETE 
    //Obtener Código del Estudiante
    public String ObtenerCodigoEstudiante(String Estudiante) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT IDPER FROM PERSONA WHERE APEPER||' '||NOMPER|| '('||DNIPER||')' LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, Estudiante);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDPER");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    //AutoComplete del  Estudiante
    public List<String> autocompleteEstudiante(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT  APEPER||' '||NOMPER|| '('||DNIPER||')' AS ESTUDIANTE FROM PERSONA WHERE DNIPER LIKE ? and ESTPER = 'A' AND TIPPER = '4'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("ESTUDIANTE"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    //----------------------------------------------------------------------------------------
    //Obtener Código del Apoderado
    public String ObtenerCodigoApoderado(String Apoderado) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT IDPER FROM PERSONA WHERE APEPER||' '||NOMPER|| '('||DNIPER||')' LIKE ? ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, Apoderado);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDPER");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    //AutoComplete del Apoderado
    public List<String> autocompleteApoderado(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT APEPER||' '||NOMPER|| '('||DNIPER||')' AS APODERADO FROM PERSONA WHERE ESTPER = 'A' AND TIPPER = '5' AND DNIPER like ? ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("APODERADO"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    //------------------------------------------------------------------------------------
    //autocomplete aula
    public String obteAula(String Aula) throws Exception {
        Conexion();
        ResultSet rs;
        try {
            String sql = "select CODAUL from aula where GRAAUL ||' '||SECAUL like ?";
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

    public List<String> autoAula(String consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select GRAAUL ||' '||SECAUL as SECAUL  from aula where GRAAUL like ? AND ESTAUL = 'A' ";
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
}

//    //METODO REPORTE_PDF_ALUMNO PARA LA DESCARGA
//    public void REPORTE_PDF_MATRICULA(Map parameters) throws JRException, IOException, Exception {
//        Conexion();
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Matricula.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.getConectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=Reporte General de matriculas.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
//    }
//
//}
