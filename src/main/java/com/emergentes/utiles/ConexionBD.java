package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_biblio";
    static String usuario = "root";
    static String password = "";
    
    Connection conexion = null;
    
    public ConexionBD(){
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, password);
            if (conexion != null) {
                System.err.println("Connectado!" + conexion);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error de conexion: " + e.getMessage());
        } catch (SQLException e){
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar: " + e.getMessage());
        }
    }
}
