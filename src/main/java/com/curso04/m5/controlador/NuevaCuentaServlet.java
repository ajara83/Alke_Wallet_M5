package com.curso04.m5.controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curso04.m5.daos.ClienteDAO;
import com.curso04.m5.daos.CuentaDAO;
import com.curso04.m5.modelo.Cliente;
import com.curso04.m5.modelo.Cuenta;

/**
 * Servlet implementation class NuevaCuentaServlet
 */
@WebServlet("/nuevaCuenta")
public class NuevaCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClienteDAO clienteDAO;
    private CuentaDAO cuentaDAO;

    public void init() {
        clienteDAO = new ClienteDAO();
        cuentaDAO = new CuentaDAO();
    }
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("nuevoCliente.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rut = request.getParameter("rut");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        
        Cliente cliente = new Cliente(rut, password, nombre, email);
        
        try {
			cliente=clienteDAO.crearCliente(cliente);
			//Numero cuenta tipo cuenta RUT
			String[] partes = cliente.getRut().split("-");
			int numeroCuenta = Integer.parseInt(partes[0]);
			Cuenta cuenta = new Cuenta(cliente.getIdCliente(), numeroCuenta, 0);
			cuentaDAO.crearCuenta(cuenta);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        response.sendRedirect("login.jsp");
        
	}

}
