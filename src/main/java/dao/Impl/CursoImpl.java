package dao.Impl;

import dao.Conexion;
import dao.ICurso;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Curso;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class CursoImpl extends Conexion implements ICurso {

    @Override
    public void registrar(Curso curso) throws Exception {
        try {
            this.Conexion();
            String sql = "INSERT INTO CURSO "
                    + " (NOMCUR,ESTCUR)"
                    + " values (?, 'A')";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, curso.getNOMCUR());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Curso curso) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE CURSO SET NOMCUR=? WHERE CODCUR=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, curso.getNOMCUR());            
            ps.setInt(2, curso.getCODCUR());
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
    public void eliminar(Curso curso) throws Exception {
        try {
            this.Conexion();
            String sql = "update CURSO set ESTCUR = 'I' WHERE CODCUR=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setInt(1, curso.getCODCUR());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Curso> listarCur() throws Exception {
        List<Curso> listado;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT * FROM CURSO where ESTCUR='A' ";
            listado = new ArrayList();
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Curso cur = new Curso();
                cur.setCODCUR(rs.getInt("CODCUR"));
                cur.setNOMCUR(rs.getString("NOMCUR"));
                cur.setESTCUR(rs.getString("ESTCUR"));
                listado.add(cur);
            }
        } catch (SQLException e) {
            throw e;

        } finally {
            this.cerrar();
        }
        return listado;

    }
    //METODO REPORTE_PDF_ALUMNO PARA LA DESCARGA
    public void REPORTE_PDF_CURSO(Map parameters) throws JRException, IOException, Exception {
        Conexion();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Profesores_Curso_Aula.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.getConectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Reporte General del curso.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
}
