package dao.Impl;

import dao.CRUD;
import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Bimestre;
import modelo.Nota;

public class NotaImpl extends Conexion implements CRUD<Nota> {

    Format fechaFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void Registrar(Nota modelo) throws Exception {

        try {
            this.Conexion();
            String sql = "INSERT INTO REGISTRO(FECREG, NOTREG, CODMAT, CODCRI, CODDET_AUL)VALUES (TO_DATE(?,'DD/MM/YYYY'),?,?,?,?) ";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getFECREG());
            ps.setInt(2, modelo.getREGNOT());
            ps.setString(3, modelo.getIDMATRI());
            ps.setInt(4, modelo.getCODCRI());
            ps.setInt(5, modelo.getCODDET_AUL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void Modificar(Nota modelo) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE REGISTRO SET NOTREG = ? where CODREG = ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOTREG());
            ps.setString(2, modelo.getCODREG());
            System.out.println(modelo.getNOTREG());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void Modificar2(Nota modelo) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE REGISTRO SET NOTREG = ? where CODREG = ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOTREG());
            ps.setString(2, modelo.getCODREG());
            System.out.println(modelo.getNOTREG());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void Modificar3(Nota modelo) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE REGISTRO SET NOTREG = ? where CODREG = ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOTREG());
            ps.setString(2, modelo.getCODREG());
            System.out.println(modelo.getNOTREG());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void Modificar4(Nota modelo) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE REGISTRO SET NOTREG = ? where CODREG = ?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOTREG());
            ps.setString(2, modelo.getCODREG());
            System.out.println(modelo.getNOTREG());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void Eliminar(Nota modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Nota> ListarPorCurs(String idProf) throws Exception {
        ArrayList<Nota> listpCur = new ArrayList();
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT DISTINCT NOMCUR, CUR.CODCUR FROM DETALLE_AULA DETA "
                    + " INNER JOIN CURSO CUR "
                    + " ON DETA.CODCUR = CUR.CODCUR "
                    + " INNER JOIN AULA AU "
                    + " ON AU.CODAUL = DETA.CODAUL "
                    + " WHERE CODTUT = ? AND ESTCUR = 'A' AND ESTDETAUL = 'A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, idProf);
            rs = ps.executeQuery();
            while (rs.next()) {
                Nota not = new Nota();
                not.setCODCUR(rs.getString("CODCUR"));
                not.setNOMCUR(rs.getString("NOMCUR"));
                listpCur.add(not);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
        return listpCur;
    }

    public ArrayList<Nota> listarAulas(String codCur) throws Exception {
        try {
            ArrayList<Nota> listarCur = new ArrayList();
            ResultSet rs;
            Conexion();
            String sql = "SELECT  CODCUR,CODDET_AUL, CONCAT( GRAAUL, SECAUL) AS AULA  FROM DETALLE_AULA DET "
                    + " INNER JOIN AULA AUL "
                    + " ON DET.CODAUL = AUL.CODAUL "
                    + " WHERE CODCUR = ? AND ESTAUL = 'A' AND ESTDETAUL = 'A' ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, codCur);
            rs = ps.executeQuery();
            while (rs.next()) {
                Nota not = new Nota();
                not.setCODDET_AUL(rs.getInt("CODDET_AUL"));
                not.setNOMAUL(rs.getString("AULA"));
                not.setCODAUL(rs.getString("CODCUR"));
                listarCur.add(not);
            }
            return listarCur;
        } catch (SQLException e) {
            System.out.println("Error al lista de Aulas");
            throw e;
        } finally {
            cerrar();
        }
    }

    public void traerIDAula(Nota nota) throws Exception {
        ResultSet rs;
        try {
            Conexion();
            String sql = "SELECT CODAUL FROM DETALLE_AULA WHERE CODDET_AUL = ? AND ESTDETAUL = 'A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setInt(1, nota.getCODDET_AUL());
            rs = ps.executeQuery();
            while (rs.next()) {
                nota.setCODAUL(rs.getString("CODAUL"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
    }

    public List<Nota> ListarporAula(String CodAula) throws Exception {
        List<Nota> lisMatr;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT CODMAT, PER.IDPER, NOMPER, APEPER  FROM MATRICULA MAT "
                    + " INNER JOIN PERSONA PER "
                    + " ON MAT.IDEST = PER.IDPER "
                    + " WHERE CODAUL = ? AND ESTPER = 'A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, CodAula);
            rs = ps.executeQuery();
            lisMatr = new ArrayList<>();
            Nota nota;
            while (rs.next()) {
                nota = new Nota();
                nota.setIDMATRI(rs.getString("CODMAT"));
                nota.setCODEST(rs.getString("IDPER"));
                nota.setNOMPER(rs.getString("NOMPER"));
                nota.setAPEPER(rs.getString("APEPER"));
                lisMatr.add(nota);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
        return lisMatr;
    }

    public ArrayList<Nota> ListarCriterios() throws Exception {
        try {
            ArrayList<Nota> listar = new ArrayList<>();
            ResultSet rs;
            Conexion();
            String sql = " SELECT * FROM CRITERIO ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Nota nota = new Nota();
                nota.setCODCRI(rs.getInt("CODCRI"));
                nota.setNOMCRI(rs.getString("NOMCRI"));
                listar.add(nota);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }

    }
//listas a los estudiantes segun criterio y luego obtener los promedios de cada criterio 

    public List<Nota> list(String aula, String curso) throws Exception {
        List<Nota> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT * FROM VW_NOTAS_ESTU "
                    + " WHERE CODAUL = ? AND CODCUR = ? AND CODCRI = 3 order by NOMPER";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, aula);
            ps.setString(2, curso);
            lista = new ArrayList();
            rs = ps.executeQuery();
            Nota nota;
            while (rs.next()) {
                nota = new Nota();
                nota.setCODEST(rs.getString("IDPER"));
                nota.setCODREG(rs.getString("CODREG"));
                nota.setCODAUL(rs.getString("CODAUL"));
                nota.setNOMPER(rs.getString("NOMPER"));
                nota.setNOTREG(rs.getString("NOTREG"));
                nota.setFECREG(rs.getString("FECREG"));
                nota.setCODCRI(rs.getInt("CODCRI"));
                nota.setNOMCRI(rs.getString("NOMCRI"));
                nota.setCODCUR(rs.getString("CODCUR"));
                nota.setLstPromedioFinal(listPromFinal(nota.getCODAUL(), nota.getCODCUR(), nota.getCODEST()));
                lista.add(nota);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
    }
//esta vendria a ser una consulta para obetener el prmedio segun criterio, luego se pasa a la otra
//    y obtienes un promedio

    public List<Nota> listPromFinal(String CODAUL, String CODCUR, String CODESTU) throws Exception {
        List<Nota> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select nomper, NOTA from vw_Promedio_Estu WHERE CODAUL = ? AND CODCUR = ? AND IDPER = ? AND CODCRI = 3";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, CODAUL);
            ps.setString(2, CODCUR);
            ps.setString(3, CODESTU);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Nota prom;
            while (rs.next()) {
                prom = new Nota();
                prom.setPROFIN(rs.getString("NOTA"));
                list.add(prom);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Nota> ExmaMen(String aula, String curso) throws Exception {
        List<Nota> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT * FROM VW_NOTAS_ESTU "
                    + " WHERE CODAUL = ? AND CODCUR = ? AND CODCRI = 4 order by NOMPER";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, aula);
            ps.setString(2, curso);
            lista = new ArrayList();
            rs = ps.executeQuery();
            Nota nota;
            while (rs.next()) {
                nota = new Nota();
                nota.setCODEST(rs.getString("IDPER"));
                nota.setCODREG(rs.getString("CODREG"));
                nota.setCODAUL(rs.getString("CODAUL"));
                nota.setNOMPER(rs.getString("NOMPER"));
                nota.setNOTREG(rs.getString("NOTREG"));
                nota.setFECREG(rs.getString("FECREG"));
                nota.setCODCRI(rs.getInt("CODCRI"));
                nota.setNOMCRI(rs.getString("NOMCRI"));
                nota.setCODCUR(rs.getString("CODCUR"));
                nota.setLstPromedioFinal(listPromExaM(nota.getCODAUL(), nota.getCODCUR(), nota.getCODEST()));
                lista.add(nota);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
    }

    public List<Nota> listPromExaM(String CODAUL, String CODCUR, String CODESTU) throws Exception {
        List<Nota> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select nomper, NOTA from vw_Promedio_Estu WHERE CODAUL = ? AND CODCUR = ? AND IDPER = ? AND CODCRI = 4";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, CODAUL);
            ps.setString(2, CODCUR);
            ps.setString(3, CODESTU);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Nota prom;
            while (rs.next()) {
                prom = new Nota();
                prom.setPROFIN(rs.getString("NOTA"));
                list.add(prom);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Nota> ExmaDir(String aula, String curso) throws Exception {
        List<Nota> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT * FROM VW_NOTAS_ESTU "
                    + " WHERE CODAUL = ? AND CODCUR = ? AND CODCRI = 2 order by NOMPER";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, aula);
            ps.setString(2, curso);
            lista = new ArrayList();
            rs = ps.executeQuery();
            Nota nota;
            while (rs.next()) {
                nota = new Nota();
                nota.setCODEST(rs.getString("IDPER"));
                nota.setCODREG(rs.getString("CODREG"));
                nota.setCODAUL(rs.getString("CODAUL"));
                nota.setNOMPER(rs.getString("NOMPER"));
                nota.setNOTREG(rs.getString("NOTREG"));
                nota.setFECREG(rs.getString("FECREG"));
                nota.setCODCRI(rs.getInt("CODCRI"));
                nota.setNOMCRI(rs.getString("NOMCRI"));
                nota.setCODCUR(rs.getString("CODCUR"));
                nota.setLstPromedioFinal(listPromExaD(nota.getCODAUL(), nota.getCODCUR(), nota.getCODEST()));
                lista.add(nota);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
    }

    public List<Nota> listPromExaD(String CODAUL, String CODCUR, String CODESTU) throws Exception {
        List<Nota> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select nomper, NOTA from vw_Promedio_Estu WHERE CODAUL = ? AND CODCUR = ? AND IDPER = ? AND CODCRI = 2";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, CODAUL);
            ps.setString(2, CODCUR);
            ps.setString(3, CODESTU);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Nota prom;
            while (rs.next()) {
                prom = new Nota();
                prom.setPROFIN(rs.getString("NOTA"));
                list.add(prom);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Nota> TareDia(String aula, String curso) throws Exception {
        List<Nota> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " SELECT * FROM VW_NOTAS_ESTU "
                    + " WHERE CODAUL = ? AND CODCUR = ? AND CODCRI = 1 order by NOMPER";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, aula);
            ps.setString(2, curso);
            lista = new ArrayList();
            rs = ps.executeQuery();
            Nota nota;
            while (rs.next()) {
                nota = new Nota();
                nota.setCODEST(rs.getString("IDPER"));
                nota.setCODREG(rs.getString("CODREG"));
                nota.setCODAUL(rs.getString("CODAUL"));
                nota.setNOMPER(rs.getString("NOMPER"));
                nota.setNOTREG(rs.getString("NOTREG"));
                nota.setFECREG(rs.getString("FECREG"));
                nota.setCODCRI(rs.getInt("CODCRI"));
                nota.setNOMCRI(rs.getString("NOMCRI"));
                nota.setCODCUR(rs.getString("CODCUR"));
                nota.setLstPromedioFinal(listPromTarDi(nota.getCODAUL(), nota.getCODCUR(), nota.getCODEST()));
                lista.add(nota);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrar();
        }
    }

    public List<Nota> listPromTarDi(String CODAUL, String CODCUR, String CODESTU) throws Exception {
        List<Nota> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select nomper, NOTA from vw_Promedio_Estu WHERE CODAUL = ? AND CODCUR = ? AND IDPER = ? AND CODCRI = 1";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, CODAUL);
            ps.setString(2, CODCUR);
            ps.setString(3, CODESTU);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Nota prom;
            while (rs.next()) {
                prom = new Nota();
                prom.setPROFIN(rs.getString("NOTA"));
                list.add(prom);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Bimestre> lisPromedio(String CODAUL, String CODCUR) throws Exception {
        List<Bimestre> listBimes;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select * from BIMESTRE where CODCUR = ? AND CODAUL= ? order by nomper";
            PreparedStatement ps = getConectar().prepareCall(sql);
            ps.setString(1, CODCUR);
            ps.setString(2, CODAUL);
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
}
