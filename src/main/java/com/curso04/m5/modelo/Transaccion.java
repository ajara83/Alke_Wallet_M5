package com.curso04.m5.modelo;

public class Transaccion {
	
	private int idTransaccion;
	private int cuentaOrigen;
	private int cuentaDestino;
	private float monto;
	private String detalle;
	
	
	
	public Transaccion(int idTransaccion, int cuentaOrigen, int cuentaDestino, float monto, String detalle) {
		super();
		this.idTransaccion = idTransaccion;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.monto = monto;
		this.detalle = detalle;
	}
	
	public Transaccion(int cuentaOrigen, int cuentaDestino, float monto, String detalle) {
		
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.monto = monto;
		this.detalle = detalle;
	}

	public Transaccion()
	{
		
	}
	
	public int getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public int getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(int cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public int getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(int cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	

}
