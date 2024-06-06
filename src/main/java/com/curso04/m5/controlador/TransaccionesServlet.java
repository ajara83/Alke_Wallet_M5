package com.curso04.m5.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class transacciones
 */
@WebServlet("/transacciones")
public class TransaccionesServlet extends HttpServlet {
	
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
        		
        		
        		List<Map<String, Object>> listaPersonalizada = new ArrayList<>();
        		
        		List<Transaccion> transacciones = transaccionDAO.obtenerTodasTransacciones();
        		
        		for (Transaccion transaccionRow : transacciones)
        		{
        			 // Cuentas
        			Cuenta cuentaOrigen = cuentaDAO.obtenerCuentaPorId(transaccionRow.getCuentaOrigen());
        			Cuenta cuentaDestino = cuentaDAO.obtenerCuentaPorId(transaccionRow.getCuentaDestino());
        			
        			 // Clientes
        			Cliente clienteOrigen = clienteDAO.obtenerClientePorID(cuentaOrigen.getIdCliente());
        			Cliente clienteDestino = clienteDAO.obtenerClientePorID(cuentaDestino.getIdCliente());
        			
        			//Cliente cltActual=new Cliente(cliente.);
        	        Map<String, Object> cuenta1 = new HashMap<>();
        	        cuenta1.put("ctaOrigen", cuentaOrigen.getNumeroCuenta());
        	        cuenta1.put("ctaDestino", cuentaDestino.getNumeroCuenta());
        	        cuenta1.put("clteOrigen", clienteOrigen.getNombre());
        	        cuenta1.put("clteDestino", clienteDestino.getNombre());
        	        cuenta1.put("monto", transaccionRow.getMonto());
        	        cuenta1.put("detalle", transaccionRow.getDetalle());
        	        
        	        listaPersonalizada.add(cuenta1);
        		}
        		
        		request.setAttribute("transacciones", listaPersonalizada);
        		
        		
        		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        	
        	
            // Continuar con la lógica de la página si la sesión es válida
            request.getRequestDispatcher("cartola.jsp").forward(request, response);
        }
	}

	

}
