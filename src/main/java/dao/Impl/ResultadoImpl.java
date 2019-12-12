package dao.Impl;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Bimestre;
import modelo.Resultados;

public class ResultadoImpl extends Conexion {

    public List<Resultados> ListNotas() throws Exception {

        List<Resultados> lisNota;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM RESULTADOS";
            PreparedStatement ps = getConectar().prepareStatement(sql);

            lisNota = new ArrayList<>();
            rs = ps.executeQuery();
            Resultados not;
            while (rs.next()) {
                not = new Resultados();
                not.setNomCur(rs.getString("NOMCUR"));
                not.setNomAlum(rs.getString("NOMPER"));
                not.setApeAlum(rs.getString("APEPER"));
                not.setNota(rs.getString("NOTA"));
                not.setIdAlum(rs.getString("IDPER"));
                lisNota.add(not);
            }
            return lisNota;
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
    }

    public List<Resultados> lisTop() throws Exception {
        List<Resultados> lisTops = null;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT CODIGO, AULA, ESTUDIANTE,(BIM1 + BIM2 + BIM3 + BIM4 + BIM5)/5 AS PROM FROM DIAGEST ORDER BY AULA, PROM DESC";
            PreparedStatement ps = getConectar().prepareCall(sql);
            lisTops = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Resultados rst = new Resultados();
                rst.setIdAlum(rs.getString("CODIGO"));
                rst.setSecAlum(rs.getString("AULA"));
                rst.setNomAlum(rs.getString("ESTUDIANTE"));
                rst.setNota(rs.getString("PROM"));
                lisTops.add(rst);
            }
            return lisTops;
        } catch (SQLException e) {
            throw e;
        }

    }

    public List<Bimestre> lisBimes(String idProf) throws Exception {
        List<Bimestre> listBimes;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select * from BIMESTRE where codtut like ? order by nomper";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, idProf);
            listBimes = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Bimestre bim = new Bimestre();
                bim.setApeBim(rs.getString("APEPER"));
                bim.setNomBim(rs.getString("NOMPER"));
                bim.setNomCuBim(rs.getString("NOMCUR"));
                bim.setSecciones(rs.getString("Secciones"));
                bim.setCodMat(rs.getString("CODMAT"));
                bim.setCodTut(rs.getString("CODTUT"));
                bim.setBim1(rs.getString("Bimestre1"));
                bim.setBim2(rs.getString("Bimestre2"));
                bim.setBim3(rs.getString("Bimestre3"));
                bim.setBim4(rs.getString("Bimestre4"));
                bim.setBim5(rs.getString("Bimestre5"));
                listBimes.add(bim);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listBimes;
    }

    public List<Bimestre> listaBimest(String aulas, String estudiante) throws Exception {
        List<Bimestre> listaBimes;
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT CONCAT(APEPER,NOMPER)AS ALUN ,NOMCUR,CODMAT,Secciones,CODTUT,Bimestre1,Bimestre2,Bimestre3,Bimestre4,Bimestre5 FROM BIMESTRE WHERE Secciones = ? and CONCAT(APEPER, NOMPER)LIKE ?";
            PreparedStatement ps = getConectar().prepareCall(sql);
            listaBimes = new ArrayList<>();
            ps.setString(1, aulas);
            ps.setString(2, estudiante);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bimestre bim = new Bimestre();
                bim.setESTUBIM(rs.getString("ALUN"));
                bim.setNomCuBim(rs.getString("NOMCUR"));
                bim.setCodMat(rs.getString("CODMAT"));
                bim.setSecciones(rs.getString("Secciones"));
                bim.setCodTut(rs.getString("CODTUT"));
                bim.setBim1(rs.getString("Bimestre1"));
                bim.setBim2(rs.getString("Bimestre2"));
                bim.setBim3(rs.getString("Bimestre3"));
                bim.setBim4(rs.getString("Bimestre4"));
                bim.setBim5(rs.getString("Bimestre5"));
                listaBimes.add(bim);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listaBimes;
    }

    public List<Bimestre> lisSelec() throws Exception {
        List<Bimestre> lisSele;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT DISTINCT CODAUL, CONCAT(GRAAUL,SECAUL)AS Salon FROM AULA";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            lisSele = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Bimestre auls = new Bimestre();
                auls.setCODAUL(rs.getString("CODAUL"));
                auls.setAula(rs.getString("Salon"));
                lisSele.add(auls);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lisSele;
    }

    public List<Bimestre> lisSelecAlu() throws Exception {
        List<Bimestre> lisSelectA;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select IDPER ,CONCAT(APEPER, NOMPER)AS Alum from PERSONA WHERE TIPPER = '4' and ESTPER = 'A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            lisSelectA = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Bimestre bimestre = new Bimestre();
                bimestre.setCODAUL(rs.getString("IDPER"));
                bimestre.setESTUDIANTE(rs.getString("Alum"));
                lisSelectA.add(bimestre);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lisSelectA;
    }

    public List<Resultados> listPorAlum(String IdProf) throws Exception {
        List<Resultados> listados;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT * FROM VW_NOTAS_ESTU "
                    + " WHERE IDPER = ? AND CODCRI = 1 order by NOMCUR ";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, IdProf);
            listados = new ArrayList();
            rs = ps.executeQuery();

            while (rs.next()) {
                Resultados not = new Resultados();
                not.setIdAlum(rs.getString("IDPER"));
                not.setCODREG(rs.getString("CODREG"));
                not.setNomAlum(rs.getString("NOMPER"));
                not.setApeAlum(rs.getString("APEPER"));
                not.setNota(rs.getString("NOTREG"));
                not.setFECREG(rs.getString("FECREG"));
                not.setNOMCRI(rs.getString("NOMCRI"));
                not.setCODCRI(rs.getString("CODCRI"));
                not.setNomCur(rs.getString("NOMCUR"));
                not.setPromedio(listaProme1(not.getIdAlum()));
                listados.add(not);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listados;

    }

    public List<Resultados> listaProme1(String CODESTU) throws Exception {
        List<Resultados> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select nomper, NOTA from vw_Promedio_Estu "
                    + " WHERE  IDPER = ? AND CODCRI = 1 order by NOMCUR";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);

            ps.setString(1, CODESTU);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Resultados prom;
            while (rs.next()) {
                prom = new Resultados();
                prom.setProFinal(rs.getString("NOTA"));
                list.add(prom);
            }

        } catch (SQLException e) {
            throw e;
        }

        return list;

    }

    public List<Resultados> listPorAlum2(String IdProf) throws Exception {
        List<Resultados> listados;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT * FROM VW_NOTAS_ESTU "
                    + " WHERE IDPER = ? AND CODCRI = 2 order by NOMCUR ";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, IdProf);
            listados = new ArrayList();
            rs = ps.executeQuery();

            while (rs.next()) {
                Resultados not = new Resultados();
                not.setIdAlum(rs.getString("IDPER"));
                not.setCODREG(rs.getString("CODREG"));
                not.setNomAlum(rs.getString("NOMPER"));
                not.setApeAlum(rs.getString("APEPER"));
                not.setNota(rs.getString("NOTREG"));
                not.setFECREG(rs.getString("FECREG"));
                not.setNOMCRI(rs.getString("NOMCRI"));
                not.setCODCRI(rs.getString("CODCRI"));
                not.setNomCur(rs.getString("NOMCUR"));
                not.setPromedio(listaProme2(not.getIdAlum()));
                listados.add(not);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listados;

    }

    public List<Resultados> listaProme2(String CODESTU) throws Exception {
        List<Resultados> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select nomper, NOTA from vw_Promedio_Estu "
                    + " WHERE  IDPER = ? AND CODCRI = 2 order by NOMCUR";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);

            ps.setString(1, CODESTU);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Resultados prom;
            while (rs.next()) {
                prom = new Resultados();
                prom.setProFinal(rs.getString("NOTA"));
                list.add(prom);
            }

        } catch (SQLException e) {
            throw e;
        }

        return list;

    }

    public List<Resultados> listPorAlum3(String IdProf) throws Exception {
        List<Resultados> listados;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT * FROM VW_NOTAS_ESTU "
                    + " WHERE IDPER = ? AND CODCRI = 3 order by NOMCUR ";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, IdProf);
            listados = new ArrayList();
            rs = ps.executeQuery();

            while (rs.next()) {
                Resultados not = new Resultados();
                not.setIdAlum(rs.getString("IDPER"));
                not.setCODREG(rs.getString("CODREG"));
                not.setNomAlum(rs.getString("NOMPER"));
                not.setApeAlum(rs.getString("APEPER"));
                not.setNota(rs.getString("NOTREG"));
                not.setFECREG(rs.getString("FECREG"));
                not.setNOMCRI(rs.getString("NOMCRI"));
                not.setCODCRI(rs.getString("CODCRI"));
                not.setNomCur(rs.getString("NOMCUR"));
                not.setPromedio(listaProme3(not.getIdAlum()));
                listados.add(not);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listados;

    }

    public List<Resultados> listaProme3(String CODESTU) throws Exception {
        List<Resultados> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select nomper, NOTA from vw_Promedio_Estu "
                    + " WHERE  IDPER = ? AND CODCRI = 3 order by NOMCUR";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);

            ps.setString(1, CODESTU);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Resultados prom;
            while (rs.next()) {
                prom = new Resultados();
                prom.setProFinal(rs.getString("NOTA"));
                list.add(prom);
            }

        } catch (SQLException e) {
            throw e;
        }

        return list;

    }

    public List<Resultados> listPorAlum4(String IdProf) throws Exception {
        List<Resultados> listados;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT * FROM VW_NOTAS_ESTU "
                    + " WHERE IDPER = ? AND CODCRI = 4 order by NOMCUR ";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, IdProf);
            listados = new ArrayList();
            rs = ps.executeQuery();

            while (rs.next()) {
                Resultados not = new Resultados();
                not.setIdAlum(rs.getString("IDPER"));
                not.setCODREG(rs.getString("CODREG"));
                not.setNomAlum(rs.getString("NOMPER"));
                not.setApeAlum(rs.getString("APEPER"));
                not.setNota(rs.getString("NOTREG"));
                not.setFECREG(rs.getString("FECREG"));
                not.setNOMCRI(rs.getString("NOMCRI"));
                not.setCODCRI(rs.getString("CODCRI"));
                not.setNomCur(rs.getString("NOMCUR"));
                not.setPromedio(listaProme4(not.getIdAlum()));
                listados.add(not);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listados;

    }

    public List<Resultados> listaProme4(String CODESTU) throws Exception {
        List<Resultados> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select nomper, NOTA from vw_Promedio_Estu "
                    + " WHERE  IDPER = ? AND CODCRI = 4 order by NOMCUR";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);

            ps.setString(1, CODESTU);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Resultados prom;
            while (rs.next()) {
                prom = new Resultados();
                prom.setProFinal(rs.getString("NOTA"));
                list.add(prom);
            }

        } catch (SQLException e) {
            throw e;
        }

        return list;

    }
    
    public List<Resultados> PromediarAsis(String IDPROF) throws SQLException {
        List<Resultados> listar;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_ASIS_PROM WHERE IDPER = ? ";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, IDPROF);
            rs = ps.executeQuery();
            listar = new ArrayList();
            Resultados prom;
            while (rs.next()) {
                prom = new Resultados();
                prom.setNomAlum(rs.getString("NOM"));
                prom.setApeAlum(rs.getString("APEL"));
                prom.setASIS(rs.getString("ASIS"));
                prom.setFALT(rs.getString("FAL"));
                prom.setCMA(rs.getString("CMA"));
                prom.setMV(rs.getString("MV"));
                listar.add(prom);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listar;

    }

}
