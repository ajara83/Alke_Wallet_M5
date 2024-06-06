package com.curso04.m5.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.curso04.m5.modelo.Cuenta;
import com.curso04.m5.modelo.Transaccion;

/**
 * Servlet implementation class TransferenciaServlet
 */
@WebServlet("/transferencia")
public class TransferenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferenciaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
        	Cliente cliente = (Cliente) session.getAttribute("cliente");       	
        	
        	try {
        		Cuenta cuenta = cuentaDAO.obtenerCuentaCliente(cliente.getIdCliente());
        		request.setAttribute("cuenta", cuenta);
        		
        		//Envio todas las cuentas disponibles excepto la propia
        		List<Cuenta> cuentas = cuentaDAO.obtenerCuentasExceptoPropia(cuenta.getIdCuenta());
        		
        		List<Cliente> clientesTransf = new ArrayList<>();
        		
        		Cliente tCliente = new Cliente();
        		
        		for (Cuenta cuentarow : cuentas) {
        			
        			tCliente=clienteDAO.obtenerClientePorID(cuentarow.getIdCliente());
        			tCliente.setCuenta(cuentarow);
        			clientesTransf.add(tCliente);       			
        			request.setAttribute("clientesTransf", clientesTransf);
                }
        		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        	
        	
            // Continuar con la lógica de la página si la sesión es válida
            request.getRequestDispatcher("transferencia.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCuenta  = Integer.parseInt(request.getParameter("idCuenta"));
		int idCuentaFinal  = Integer.parseInt(request.getParameter("idCuentaFinal"));
		float monto  = Float.parseFloat(request.getParameter("monto"));
		
		HttpSession session = request.getSession(false);
		Cuenta cuenta = new Cuenta();
		
		try {
			cuentaDAO.depositarSaldo(idCuenta, monto);
			
			Cliente clt = (Cliente) session.getAttribute("cliente");
        	try {
        		cuenta = cuentaDAO.obtenerCuentaCliente(clt.getIdCliente());
        		request.setAttribute("cuenta", cuenta);
        		
        		cuentaDAO.traspasarSaldo(idCuenta, idCuentaFinal, monto);
        		Transaccion transaccion = new Transaccion(idCuenta, idCuentaFinal, monto, "TRANSFERENCIA");            	
            	transaccionDAO.agregarTransaccion(transaccion);
        		
			} catch (SQLException e) {				
				e.printStackTrace();
			}
        	
        	
        	
        	request.setAttribute("showAlert", "OK");
        	
            request.getRequestDispatcher("inicio.jsp").forward(request, response);			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
