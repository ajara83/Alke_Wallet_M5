package com.curso04.m5.modelo;

public class Cliente {

	
	private int idCliente;
	private String rut;
    private String password;
    private String nombre;
    private String email;
    private Cuenta cuenta;
    
    
    


	public Cliente(int idCliente, String rut, String password, String nombre, String email, Cuenta cuenta) {
		
		this.idCliente = idCliente;
		this.rut = rut;
		this.password = password;
		this.nombre = nombre;
		this.email = email;
		this.cuenta = cuenta;
	}
	
	public Cliente(int idCliente, String rut, String password, String nombre, String email) {
		
		this.idCliente = idCliente;
		this.rut = rut;
		this.password = password;
		this.nombre = nombre;
		this.email = email;
	}
	
	public Cliente(String rut, String password, String nombre, String email) {
		
		
		this.rut = rut;
		this.password = password;
		this.nombre = nombre;
		this.email = email;
		
	}



	public Cliente()
    {
    	
    }
    
    

	public int getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(int string) {
		this.idCliente = string;
	}



	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
	
	public Cuenta getCuenta() {
		return cuenta;
	}



	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
    
}
