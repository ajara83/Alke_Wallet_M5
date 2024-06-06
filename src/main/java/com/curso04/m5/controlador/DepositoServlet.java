package com.curso04.m5.controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.curso04.m5.daos.ClienteDAO;
import com.curso04.m5.daos.CuentaDAO;
import com.curso04.m5.daos.TransaccionDAO;
import com.curso04.m5.modelo.Cliente;
import com.curso04.m5.modelo.Transaccion;
import com.curso04.m5.modelo.Cuenta;

/**
 * Servlet implementation class DepositoServlet
 */
@WebServlet("/deposito")
public class DepositoServlet extends HttpServlet {
    
    private ClienteDAO clienteDAO;
    private CuentaDAO cuentaDAO;
    private TransaccionDAO transaccionDAO;

    public void init() {
        clienteDAO = new ClienteDAO();
        cuentaDAO = new CuentaDAO();
        transaccionDAO = new TransaccionDAO();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // No crear una nueva sesión si no existe
        if (session == null || session.getAttribute("cliente") == null) {
            response.sendRedirect("login.jsp"); // Redirigir a login si no hay sesión
        } else {
            
        	Cliente clt = (Cliente) session.getAttribute("cliente");
        	try {
        		request.setAttribute("cuenta", cuentaDAO.obtenerCuentaCliente(clt.getIdCliente()));
			} catch (SQLException e) {				
				e.printStackTrace();
			}
        	
            request.getRequestDispatcher("deposito.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false); // No crear una nueva sesión si no existe
    	float montoDeposito = Float.parseFloat(request.getParameter("montoDeposito"));
    	int idCuenta  = Integer.parseInt(request.getParameter("idCuenta"));
    	Cuenta cuenta = new Cuenta();
    	
    	try {
			cuentaDAO.depositarSaldo(idCuenta, montoDeposito);
			
			Cliente clt = (Cliente) session.getAttribute("cliente");
        	try {
        		cuenta = cuentaDAO.obtenerCuentaCliente(clt.getIdCliente());
        		request.setAttribute("cuenta", cuenta);
			} catch (SQLException e) {				
				e.printStackTrace();
			}
        	
        	Transaccion transaccion = new Transaccion(cuenta.getIdCuenta(), cuenta.getIdCuenta(), montoDeposito, "DEPOSITO");
        	
        	transaccionDAO.agregarTransaccion(transaccion);
        	
        	request.setAttribute("showAlert", "OK");
        	
            request.getRequestDispatcher("deposito.jsp?showAlert=1").forward(request, response);			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}
