package dao.Impl;

import dao.Conexion;
import dao.IEstudiante;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Estudiante;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import servicios.Diagest;

public class EstudianteImpl extends Conexion implements IEstudiante {

    Format fechaFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void registrar(Estudiante estudiante) throws Exception {
        try {
            String INSERT = "INSERT INTO PERSONA"
                    + "( NOMPER, APEPER, FECHNACPER , DNIPER, PARPER, CORPER, IDUBI, SEXPER ,DIRPER, ESTPER,TIPPER,CONTRPER, USUPER)"
                    + " values (?,?,TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?,?,'A','4',?,?)";
            PreparedStatement ps = this.getConectar().prepareStatement(INSERT);
            ps.setString(1, estudiante.getNomEstu());
            ps.setString(2, estudiante.getApeEstu());
            ps.setString(3, estudiante.getDateEstu());
            ps.setString(4, estudiante.getDniEstu());
            ps.setString(5, estudiante.getParEstu());
            ps.setString(6, estudiante.getCorEstu());
            ps.setString(7, estudiante.getUbiEstu());
            ps.setString(8, estudiante.getSexEstu());
            ps.setString(9, estudiante.getDirEstu());
            ps.setString(10, estudiante.getPassEst());
            ps.setString(11, estudiante.getUsuPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al ingresar datos Impl" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public Estudiante leerEstudiante(String codigoEstudiante) throws Exception {
        Estudiante est = null;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select CONTRPER,USUPER, DIRPER, SEXPER, IDPER, NOMPER, APEPER,TO_CHAR(FECHNACPER,'DD/MM/YYYY') AS FECHNACPER, DNIPER, PARPER,CORPER,"
                    + " UBIGEO.IDUBI, DEPUBI ||' '||PROUBI ||' '|| DISUBI AS NOMBREUBIGEO from PERSONA "
                    + " left join UBIGEO "
                    + " on PERSONA.IDUBI = UBIGEO.IDUBI "
                    + " WHERE IDPER LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, codigoEstudiante);
            rs = ps.executeQuery();
            if (rs.next()) {
                est = new Estudiante();
                est.setIdEstu(rs.getString("IDPER"));
                est.setNomEstu(rs.getString("NOMPER"));
                est.setApeEstu(rs.getString("APEPER"));
                est.setDateEstu(rs.getString("FECHNACPER"));
                est.setDniEstu(rs.getString("DNIPER"));
                est.setParEstu(rs.getString("PARPER"));
                est.setCorEstu(rs.getString("CORPER"));
                est.setSexEstu(rs.getString("SEXPER"));
                est.setDirEstu(rs.getString("DIRPER"));
                est.setNomubigeo(rs.getString("nombreUbigeo"));
                est.setPassEst(rs.getString("CONTRPER"));
                est.setUsuPer(rs.getString("USUPER"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
        return est;
    }

    @Override
    public void modificar(Estudiante estudiante) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE PERSONA SET NOMPER=?, APEPER=?, FECHNACPER=TO_DATE( ?, 'DD/MM/YYYY'),  DNIPER=?, PARPER=?, CORPER=?, IDUBI=?, SEXPER=? ,DIRPER=?, CONTRPER =?, USUPER=? WHERE IDPER=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, estudiante.getNomEstu());
            ps.setString(2, estudiante.getApeEstu());
            ps.setString(3, estudiante.getDateEstu());
            ps.setString(4, estudiante.getDniEstu());
            ps.setString(5, estudiante.getParEstu());
            ps.setString(6, estudiante.getCorEstu());
            ps.setString(7, estudiante.getUbiEstu());
            ps.setString(8, estudiante.getSexEstu());
            ps.setString(9, estudiante.getDirEstu());
            ps.setString(10, estudiante.getPassEst());
            ps.setString(11, estudiante.getUsuPer());
            ps.setString(12, estudiante.getIdEstu());
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
    public void eliminar(Estudiante estudiante) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE PERSONA SET  ESTPER= 'I'  where IDPER=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, estudiante.getIdEstu());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Estudiante> listarEst() throws Exception {
        List<Estudiante> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select CONTRPER, USUPER,  DIRPER, SEXPER, IDPER, NOMPER, APEPER, TO_CHAR(FECHNACPER,'DD/MM/YYYY')AS FECHNACPER  , DNIPER, PARPER,CORPER,  DEPUBI ||' '||PROUBI ||' '|| DISUBI AS NOMBREUBIGEO from PERSONA "
                    + " INNER join UBIGEO on PERSONA.IDUBI = UBIGEO.IDUBI WHERE ESTPER = 'A' AND TIPPER= '4'";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante estu = new Estudiante();
                estu.setIdEstu(rs.getString("IDPER"));
                estu.setNomEstu(rs.getString("NOMPER"));
                estu.setApeEstu(rs.getString("APEPER"));
                estu.setDateEstu(rs.getString("FECHNACPER"));
                estu.setDniEstu(rs.getString("DNIPER"));
                estu.setParEstu(rs.getString("PARPER"));
                estu.setCorEstu(rs.getString("CORPER"));
                estu.setSexEstu(rs.getString("SEXPER"));
                estu.setDirEstu(rs.getString("DIRPER"));
                estu.setNomubigeo(rs.getString("nombreUbigeo"));
                estu.setPassEst(rs.getString("CONTRPER"));
                estu.setUsuPer(rs.getString("USUPER"));
                listado.add(estu);
            }
        } catch (SQLException e) {
            throw e;

        } finally {
            this.cerrar();
        }
        return listado;

    }

    //codigo de autocomplete implemen
    public String obtenerCodigoUbigeo(String Ubigeo) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT IDUBI FROM UBIGEO WHERE DEPUBI ||' '||PROUBI ||' '|| DISUBI LIKE ? ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, Ubigeo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDUBI");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteUbigeo(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT  DEPUBI ||' '||PROUBI ||' '|| DISUBI AS DISUBI FROM UBIGEO WHERE DISUBI LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("DISUBI"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    //Obtener Código del Estudiante
    public String ObtenerCodigoEstudiante(String Estudiante) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT IDPER FROM PERSONA WHERE DNIPER || ' ' || APEPER || ' '|| NOMPER LIKE ?";
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

    //AutoComplete del Estudiante
    public List<String> autocompleteEstudiante(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT DNIPER || ' ' || APEPER || ' '|| NOMPER AS NOMEST FROM PERSONA WHERE NOMPER LIKE ? AND TIPPER = '4'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("NOMEST"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    //METODO REPORTE_PDF_ALUMNO PARA LA DESCARGA
    public void REPORTE_PDF_ESTUDIANTE(Map parameters) throws JRException, IOException, Exception {
        this.Conexion();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Reporte_Estudiante.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.getConectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Reporte_Alumno.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    public List<Diagest> graficoAlumno(int Codigo) throws SQLException {
        List<Diagest> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT * FROM DIAGEST WHERE CODIGO = ?";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setInt(1, Codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Diagest estu = new Diagest();
                estu.setCODIGO(rs.getString("CODIGO"));
                estu.setAULA(rs.getString("AULA"));
                estu.setESTUDIANTE(rs.getString("ESTUDIANTE"));
                estu.setBIM1(rs.getString("BIM1"));
                estu.setBIM2(rs.getString("BIM2"));
                estu.setBIM3(rs.getString("BIM3"));
                estu.setBIM4(rs.getString("BIM4"));
                estu.setBIM5(rs.getString("BIM5"));
                listado.add(estu);
            }
        } catch (SQLException e) {
            System.out.println("ERROR EN LISTAR ");
            throw e;

        } finally {
            this.cerrar();
        }
        return listado;
    }

//    public void REPORTE_PDF_ESTUDIANTE(Map parameters) throws JRException, IOException, Exception {
//        Conexion();
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Reporte_Estudiante.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.getConectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=Reporte_Alumno.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
//    }
}
