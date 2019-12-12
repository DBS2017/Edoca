package dao.Impl;

import dao.Conexion;
import dao.IApoderado;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Apoderado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ApoderadoImpl extends Conexion implements IApoderado {

    @Override
    public void registrar(Apoderado apoderado) throws Exception {
        try {
            Conexion();
            String sql = "insert into PERSONA (NOMPER, APEPER, OCUPER, DIRPER, SEXPER, CELPER, DNIPER, IDUBI,ESTPER, TIPPER) values(?,?,?,?,?,?,?,?,'A','5')";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, apoderado.getNomApo());
            ps.setString(2, apoderado.getApeApo());
            ps.setString(3, apoderado.getOcuApo());
            ps.setString(4, apoderado.getDirApo());
            ps.setString(5, apoderado.getSexApo());
            ps.setString(6, apoderado.getCelApo());
            ps.setString(7, apoderado.getDniApo());
            ps.setString(8, apoderado.getUbiApo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al registrar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public Apoderado leerApoderado(String codigoApoderado) throws Exception {
        Apoderado apo = null;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT IDPER, NOMPER, APEPER, OCUPER, DIRPER, SEXPER, CELPER, DNIPER,"
                    + " UBIGEO.DISUBI||' - '|| UBIGEO.PROUBI||' - '|| UBIGEO.DEPUBI AS NOMBREUBIGEO from PERSONA  inner join UBIGEO on PERSONA.IDUBI = UBIGEO.IDUBI where IDPER like ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, codigoApoderado);
            rs = ps.executeQuery();
            if (rs.next()) {
                apo = new Apoderado();
                apo.setIdApo(rs.getInt("IDPER"));
                apo.setNomApo(rs.getString("NOMPER"));
                apo.setApeApo(rs.getString("APEPER"));
                apo.setOcuApo(rs.getString("OCUPER"));
                apo.setDirApo(rs.getString("DIRPER"));
                apo.setSexApo(rs.getString("SEXPER"));
                apo.setCelApo(rs.getString("CELPER"));
                apo.setDniApo(rs.getString("DNIPER"));
                apo.setNombreUbi(rs.getString("NOMBREUBIGEO"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
        return apo;
    }

    @Override
    public void modificar(Apoderado apoderado) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE PERSONA SET NOMPER=?, APEPER=?, OCUPER=?, DIRPER=?, SEXPER=?,CELPER=?, DNIPER=?, IDUBI=? WHERE IDPER=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, apoderado.getNomApo());
            ps.setString(2, apoderado.getApeApo());
            ps.setString(3, apoderado.getOcuApo());
            ps.setString(4, apoderado.getDirApo());
            ps.setString(5, apoderado.getSexApo());
            ps.setString(6, apoderado.getCelApo());
            ps.setString(7, apoderado.getDniApo());
            ps.setString(8, apoderado.getUbiApo());
            ps.setInt(9, apoderado.getIdApo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en update" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Apoderado apoderado) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE PERSONA SET ESTPER = 'I' where IDPER = ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setInt(1, apoderado.getIdApo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Apoderado> listarApo() throws Exception {
        List<Apoderado> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT IDPER, NOMPER, APEPER, OCUPER, DIRPER, SEXPER, CELPER, DNIPER, UBIGEO.DISUBI||' - '|| UBIGEO.PROUBI||' - '|| UBIGEO.DEPUBI AS NOMBREUBIGEO FROM PERSONA inner join UBIGEO on PERSONA.IDUBI = UBIGEO.IDUBI WHERE  ESTPER = 'A' AND TIPPER= '5'";
            listado = new ArrayList();
            Statement st = this.getConectar().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Apoderado apo = new Apoderado();
                apo.setIdApo(rs.getInt("IDPER"));
                apo.setNomApo(rs.getString("NOMPER"));
                apo.setApeApo(rs.getString("APEPER"));
                apo.setOcuApo(rs.getString("OCUPER"));
                apo.setDirApo(rs.getString("DIRPER"));
                apo.setSexApo(rs.getString("SEXPER"));
                apo.setCelApo(rs.getString("CELPER"));
                apo.setDniApo(rs.getString("DNIPER"));
                apo.setNombreUbi(rs.getString("NOMBREUBIGEO"));
                listado.add(apo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public String obtenerCodigoUbigeo(String Ubigeo) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "Select IDUBI from ubigeo where UBIGEO.DISUBI||' - '|| UBIGEO.PROUBI||' - '|| UBIGEO.DEPUBI like ?";
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

    public List<String> autocompleteUbigeo(String ubi) throws SQLException, Exception {
        ResultSet rs;
        List<String> Lista;
        try {
            this.Conexion();
            String sql = "Select UBIGEO.DISUBI||' - '|| UBIGEO.PROUBI||' - '|| UBIGEO.DEPUBI AS DISUBI FROM UBIGEO WHERE DISUBI LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + ubi + "%");
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

    //Validacion de DNI registrado
    public Apoderado validarExistenciaApoderado(String DNIPER) throws Exception {
        try {
            this.Conexion();
            String sql = "Select IDPER, DNIPER from PERSONA where DNIPER = '" + DNIPER + "'";
            Statement st = this.getConectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Apoderado apoderado = new Apoderado();
            while (rs.next()) {
                apoderado.setIdApo(rs.getInt("IDPER"));
                apoderado.setDniApo(rs.getString("DNIPER"));
                break;
            }
            return apoderado;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
//Obtener Código del Apoderado

    public String ObtenerCodigoApoderado(String Apoderado) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT IDPER FROM PERSONA WHERE CONCAT(DNIPER, ' ', APEPER, ' ', NOMPER) LIKE ? AND TIPPER='5' ";
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
            String sql = "SELECT CONCAT(DNIPER, ' ', APEPER, ' ', NOMPER) AS NOMAPO FROM PERSONA WHERE NOMPER LIKE ? AND TIPPER='5'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("NOMAPO"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    //METODO REPORTE_PDF_ALUMNO PARA LA DESCARGA
    public void REPORTE_PDF_APODERADOS(Map parameters) throws JRException, IOException, Exception {
        Conexion();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Apoderados_General.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.getConectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Reporte General de los apoderados.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
}
