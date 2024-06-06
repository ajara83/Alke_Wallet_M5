package com.curso04.m5.daos;

import com.curso04.m5.conexiondb.MysqlConexion;
import com.curso04.m5.modelo.Cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {

    // Método para obtener una cuenta por su ID
    public Cuenta obtenerCuentaPorId(int idCuenta) throws SQLException {
        String sql = "SELECT * FROM cuenta WHERE idcuenta = ?";
        Connection connection = MysqlConexion.getInstancia().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCuenta);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCuenta(resultSet);
                }
            }
        }
        return null;
    }
    
    // Método para obtener una cuenta por su ID
    public Cuenta obtenerCuentaCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM cuenta WHERE id_cliente = ?";
        Connection connection = MysqlConexion.getInstancia().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCuenta(resultSet);
                }
            }
        }
        return null;
    }

    

    // Método para obtener todas las cuentas disponibles
    public List<Cuenta> obtenerTodasLasCuentas() throws SQLException {
        String sql = "SELECT * FROM cuenta";
        List<Cuenta> cuentas = new ArrayList<>();
        Connection connection = MysqlConexion.getInstancia().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                cuentas.add(mapResultSetToCuenta(resultSet));
            }
        }
        return cuentas;
    }
    
    public List<Cuenta> obtenerCuentasExceptoPropia(int idCuenta) throws SQLException {
        String sql = "SELECT * FROM cuenta WHERE idCuenta != ?";
        
        List<Cuenta> cuentas = new ArrayList<>();
        Connection connection = MysqlConexion.getInstancia().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCuenta);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cuentas.add(mapResultSetToCuenta(resultSet));
                }
            }
        }
        return cuentas;
    }
    


    // Método para crear una nueva cuenta
    public void crearCuenta(Cuenta cuenta) throws SQLException {
        String sql = "INSERT INTO cuenta (id_cliente, numerocuenta, saldo) VALUES (?, ?, ?)";
        Connection connection = MysqlConexion.getInstancia().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cuenta.getIdCliente());
            statement.setInt(2, cuenta.getNumeroCuenta());
            statement.setFloat(3, cuenta.getSaldo());
            statement.executeUpdate();
        }
    }

    // Método para actualizar una cuenta
    public void actualizarCuenta(Cuenta cuenta) throws SQLException {
        String sql = "UPDATE cuenta SET id_cliente = ?, numerocuenta = ?, saldo = ? WHERE idCuenta = ?";
        Connection connection = MysqlConexion.getInstancia().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cuenta.getIdCliente());
            statement.setInt(2, cuenta.getNumeroCuenta());
            statement.setFloat(3, cuenta.getSaldo());
            statement.setInt(4, cuenta.getIdCuenta());
            statement.executeUpdate();
        }
    }

    // Método para eliminar una cuenta
    public void eliminarCuenta(int idCuenta) throws SQLException {
        String sql = "DELETE FROM cuenta WHERE idCuenta = ?";
        Connection connection = MysqlConexion.getInstancia().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCuenta);
            statement.executeUpdate();
        }
    }

    // Método para traspasar saldo de una cuenta a otra
    public void traspasarSaldo(int idCuentaOrigen, int idCuentaDestino, float monto) throws SQLException {
        String sqlRestarSaldo = "UPDATE cuenta SET saldo = saldo - ? WHERE idCuenta = ?";
        String sqlSumarSaldo = "UPDATE cuenta SET saldo = saldo + ? WHERE idCuenta = ?";
        Connection connection = MysqlConexion.getInstancia().getConnection();
        try {
            connection.setAutoCommit(false); // Iniciar transacción

            try (PreparedStatement restarSaldo = connection.prepareStatement(sqlRestarSaldo);
                 PreparedStatement sumarSaldo = connection.prepareStatement(sqlSumarSaldo)) {

                // Restar saldo de la cuenta de origen
                restarSaldo.setFloat(1, monto);
                restarSaldo.setInt(2, idCuentaOrigen);
                restarSaldo.executeUpdate();

                // Sumar saldo a la cuenta de destino
                sumarSaldo.setFloat(1, monto);
                sumarSaldo.setInt(2, idCuentaDestino);
                sumarSaldo.executeUpdate();

                connection.commit(); // Confirmar transacción
            } catch (SQLException e) {
                connection.rollback(); // Revertir transacción en caso de error
                throw e;
            } finally {
                connection.setAutoCommit(true); // Restaurar el estado de autocommit
            }
        } finally {
            connection.close(); // Cerrar conexión
        }
    }

    // Método para depositar saldo en una cuenta
    public void depositarSaldo(int idCuenta, float monto) throws SQLException {
        String sql = "UPDATE cuenta SET saldo = saldo + ? WHERE idCuenta = ?";
        Connection connection = MysqlConexion.getInstancia().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setFloat(1, monto);
            statement.setInt(2, idCuenta);
            statement.executeUpdate();
        }
    }

    // Método auxiliar para mapear un ResultSet a un objeto Cuenta
    private Cuenta mapResultSetToCuenta(ResultSet resultSet) throws SQLException {
        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(resultSet.getInt("idCuenta"));
        cuenta.setIdCliente(resultSet.getInt("id_cliente"));
        cuenta.setNumeroCuenta(resultSet.getInt("numerocuenta"));
        cuenta.setSaldo(resultSet.getFloat("saldo"));
        return cuenta;
    }
}
