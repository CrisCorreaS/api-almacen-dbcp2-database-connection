package org.feliz.almacen.api.modelo;

public class Compra implements ICompra{
	
	private String idCompra;
	private float precioCompra;
	private String fechaCompra;
	private String idCliente;
	private String idVendedor;
	
	public Compra() {
		super();
	}
	
	
	public Compra(String idCompra, float precioCompra, String fechaCompra, String idCliente, String idVendedor) {
		super();
		this.idCompra = idCompra;
		this.precioCompra = precioCompra;
		this.fechaCompra = fechaCompra;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
	}

	public String getIdCompra() {
		return idCompra;
	}


	public void setIdCompra(String idCompra) {
		this.idCompra = idCompra;
	}


	public float getPrecioCompra() {
		return precioCompra;
	}


	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}


	public String getFechaCompra() {
		return fechaCompra;
	}


	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}


	public String getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	public String getIdVendedor() {
		return idVendedor;
	}


	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	
	@Override
	public String toString() {
		String resultado = "";
		resultado = resultado + "Id Compra: " + getIdCompra();
		resultado = resultado + "; Precio de la Compra: " + getPrecioCompra();
		resultado = resultado + "; Fecha de la Compra: " + getFechaCompra();
		resultado = resultado + "; Id Vendedor:" + getFechaCompra();
		resultado = resultado + "; Fecha de la Compra" + getFechaCompra();
		
		return resultado;
	}

}
