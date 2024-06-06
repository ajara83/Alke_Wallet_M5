package com.curso04.m5.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso04.m5.modelo.Transaccion;
import com.curso04.m5.conexiondb.MysqlConexion;

public class TransaccionDAO {

    // agregar una transacción
    public void agregarTransaccion(Transaccion transaccion) throws SQLException {
        String query = "INSERT INTO transaccion (cuentaOrigen, cuentaDestino, monto, detalle) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = MysqlConexion.getInstancia().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, transaccion.getCuentaOrigen());
            preparedStatement.setInt(2, transaccion.getCuentaDestino());
            preparedStatement.setFloat(3, transaccion.getMonto());
            preparedStatement.setString(4, transaccion.getDetalle());
            
            preparedStatement.executeUpdate();
        }
    }

    

    // Obtener todas las transacciones
    public List<Transaccion> obtenerTodasTransacciones() throws SQLException {
        String query = "SELECT * FROM transaccion";
        List<Transaccion> transacciones = new ArrayList<>();
        
        try (Connection connection = MysqlConexion.getInstancia().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                Transaccion transaccion = new Transaccion(
                    resultSet.getInt("idTransaccion"),
                    resultSet.getInt("cuentaOrigen"),
                    resultSet.getInt("cuentaDestino"),
                    resultSet.getFloat("monto"),
                    resultSet.getString("detalle")
                );
                transacciones.add(transaccion);
            }
        }
        
        return transacciones;
    }

    
}
