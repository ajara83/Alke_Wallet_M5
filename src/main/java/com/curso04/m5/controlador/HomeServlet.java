package com.curso04.m5.controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.curso04.m5.modelo.Cliente;
import com.curso04.m5.modelo.Cuenta;
import com.curso04.m5.daos.ClienteDAO;
import com.curso04.m5.daos.CuentaDAO;

@WebServlet("/inicio")
public class HomeServlet extends HttpServlet {
	
	private ClienteDAO clienteDAO;
    private CuentaDAO cuentaDAO;

    public void init() {
        clienteDAO = new ClienteDAO();
        cuentaDAO = new CuentaDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // No crear una nueva sesión si no existe
        if (session == null || session.getAttribute("cliente") == null) {
        	
        	
            response.sendRedirect("login.jsp"); // Redirigir a login si no hay sesión
        } else {
        	Cliente cliente = (Cliente) session.getAttribute("cliente");       	
        	
        	try {
        		request.setAttribute("cuenta", cuentaDAO.obtenerCuentaCliente(cliente.getIdCliente()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // Continuar con la lógica de la página si la sesión es válida
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        }
    }
}


