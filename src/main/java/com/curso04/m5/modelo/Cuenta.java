package com.curso04.m5.modelo;

public class Cuenta {
    private int idCuenta;
    private int idCliente;
    private int id_divisa;
    private int numeroCuenta;
    private float saldo;
    
    

    public Cuenta(int idCliente, int id_divisa, int numeroCuenta, float saldo) {		
		this.idCliente = idCliente;
		this.id_divisa = id_divisa;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}
    
    public Cuenta(int idCliente, int numeroCuenta, float saldo) {		
		this.idCliente = idCliente;		
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}
    
    public Cuenta()
    {
    	
    }

	// Getters y Setters
    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getId_divisa() {
		return id_divisa;
	}

	public void setId_divisa(int id_divisa) {
		this.id_divisa = id_divisa;
	}

	public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
