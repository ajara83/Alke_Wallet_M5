package com.curso04.m5.daos;

import com.curso04.m5.conexiondb.MysqlConexion;
import com.curso04.m5.modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Método para obtener un cliente por RUT
    public Cliente obtenerClientePorRut(String rut) throws SQLException {
        Connection connection = MysqlConexion.getInstancia().getConnection();
        String sql = "SELECT * FROM cliente WHERE rut = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, rut);
        ResultSet resultSet = statement.executeQuery();
        Cliente cliente = null;
        if (resultSet.next()) {
            cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("idCliente"));
            cliente.setRut(resultSet.getString("rut"));
            cliente.setPassword(resultSet.getString("password"));
            cliente.setNombre(resultSet.getString("nombre"));
            cliente.setEmail(resultSet.getString("email"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return cliente;
    }
    
 // Método para obtener un cliente por RUT
    public Cliente obtenerClientePorID(int id) throws SQLException {
        Connection connection = MysqlConexion.getInstancia().getConnection();
        String sql = "SELECT * FROM cliente WHERE idCliente = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Cliente cliente = null;
        if (resultSet.next()) {
            cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("idCliente"));
            cliente.setRut(resultSet.getString("rut"));
            cliente.setPassword(resultSet.getString("password"));
            cliente.setNombre(resultSet.getString("nombre"));
            cliente.setEmail(resultSet.getString("email"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return cliente;
    }
    
   

    // Método para obtener todos los clientes
    public List<Cliente> obtenerTodosLosClientes() throws SQLException {
        Connection connection = MysqlConexion.getInstancia().getConnection();
        String sql = "SELECT * FROM cliente";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Cliente> clientes = new ArrayList<>();
        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            
            cliente.setRut(resultSet.getString("rut"));
            cliente.setPassword(resultSet.getString("password"));
            cliente.setNombre(resultSet.getString("nombre"));
            cliente.setEmail(resultSet.getString("email"));
            clientes.add(cliente);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return clientes;
    }

    public Cliente crearCliente(Cliente cliente) throws SQLException {
        Connection connection = MysqlConexion.getInstancia().getConnection();
        String sql = "INSERT INTO cliente (rut, password, nombre, email) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, cliente.getRut());
        statement.setString(2, cliente.getPassword());
        statement.setString(3, cliente.getNombre());
        statement.setString(4, cliente.getEmail());
        statement.executeUpdate();
        
        // Obtener el ID generado
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            cliente.setIdCliente(generatedKeys.getInt(1));
        }
        
        generatedKeys.close();
        statement.close();
        connection.close();
        
        return cliente;
    }

    // Método para actualizar un cliente existente
    public void actualizarCliente(Cliente cliente) throws SQLException {
        Connection connection = MysqlConexion.getInstancia().getConnection();
        String sql = "UPDATE cliente SET password = ?, nombre = ?, email = ? WHERE rut = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cliente.getPassword());
        statement.setString(2, cliente.getNombre());
        statement.setString(3, cliente.getEmail());
        statement.setString(4, cliente.getRut());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    // Método para eliminar un cliente
    public void eliminarCliente(String rut) throws SQLException {
        Connection connection = MysqlConexion.getInstancia().getConnection();
        String sql = "DELETE FROM cliente WHERE rut = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, rut);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
