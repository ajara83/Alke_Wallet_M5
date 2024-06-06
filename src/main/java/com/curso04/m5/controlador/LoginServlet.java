package com.curso04.m5.controlador;

import com.curso04.m5.daos.ClienteDAO;
import com.curso04.m5.daos.CuentaDAO;
import com.curso04.m5.modelo.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private ClienteDAO clienteDAO;
    private CuentaDAO cuentaDAO;

    public void init() {
        clienteDAO = new ClienteDAO();
        cuentaDAO = new CuentaDAO();
    }
    
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // false no crea una nueva sesión si no existe

        if (session == null || session.getAttribute("cliente") == null) {
            // Redirige al usuario a la página de inicio de sesión si no hay una sesión abierta
            response.sendRedirect("login.jsp");
        } else {
            // Redirige a la página de inicio si hay una sesión válida
            response.sendRedirect("inicio.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rut = request.getParameter("rut");
        String password = request.getParameter("password");
        

        try {
            Cliente cliente = clienteDAO.obtenerClientePorRut(rut);
            if (cliente != null && cliente.getPassword().equals(password)) {
                HttpSession session = request.getSession(); // Crea una nueva sesión si no existe
                session.setAttribute("cliente", cliente);
                request.setAttribute("cuenta", cuentaDAO.obtenerCuentaCliente(cliente.getIdCliente()));
                request.setAttribute("otro", "data");
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
