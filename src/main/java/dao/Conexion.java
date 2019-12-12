package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conectar;

    public void Conexion() {
        try {
            if (conectar == null) {
                Class.forName("oracle.jdbc.driver.OracleDriver");                
                conectar = DriverManager.getConnection("jdbc:oracle:thin:@34.69.77.101:1521:XE", "db_EDOOCA", "db_EDOOCA-2019");
//                conectar = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "LOCAL02", "123");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    public void cerrar() throws SQLException {      //Cerrar la conexion
        if (conectar != null) {
            if (conectar.isClosed() == false) {
                conectar.close();
                conectar = null;
            }
        }
    }

    public Connection getConectar() {
        return conectar;
    }

    public void setConectar(Connection conectar) {
        this.conectar = conectar;
    }

    public static void main(String[] args) {
        Conexion dao = new Conexion();
        dao.Conexion();
        if (dao.getConectar() != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("No hay Conexion");
        }
    }
}
