package com.curso04.m5.conexiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.curso04.m5.constantes.Constants;

public class MysqlConexion {
    
    private static MysqlConexion instancia;
    private Connection connection;
    
    private MysqlConexion() throws SQLException {
        try {
            Class.forName(Constants.DRIVER_MYSQL);
            connection = DriverManager.getConnection(Constants.URL, Constants.USUARIO, Constants.PASSWORD);
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }
    
    /**
     * Crear un método de creación estático que actúe como constructor. 
     * Tras bambalinas, este método invoca al constructor privado para crear un objeto 
     * y lo guarda en un campo estático.
     */
    public static synchronized MysqlConexion getInstancia() throws SQLException {
        if (instancia == null || instancia.getConnection().isClosed()) {
            instancia = new MysqlConexion();
        }
        return instancia;
    }
    
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(Constants.URL, Constants.USUARIO, Constants.PASSWORD);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
