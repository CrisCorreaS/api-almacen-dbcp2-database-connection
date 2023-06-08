package org.feliz.almacen.api.modelo;

public class Vendedor implements IVendedor{
	
	private String idVendedor;
	private String nifVendedor;
	private String nombreVendedor;
	private String apellido1Vendedor;
	private String apellido2Vendedor;
	
	public Vendedor() {
		super();
	}
	
	public Vendedor(String idVendedor) {
		super();
		this.idVendedor=idVendedor;
	}
	
	
	public Vendedor(String idVendedor, String nifVendedor, String nombreVendedor, String apellido1Vendedor, String apellido2Vendedor) {
	
		this.idVendedor = idVendedor;
		this.nifVendedor = nifVendedor;
		this.nombreVendedor = nombreVendedor;
		this.apellido1Vendedor = apellido1Vendedor;
		this.apellido2Vendedor = apellido2Vendedor;
	}
	
	
	public String getIdVendedor() {
		return this.idVendedor;
	}
	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}
	public String getNifVendedor() {
		return this.nifVendedor;
	}
	public void setNifVendedor(String nifVendedor) {
		this.nifVendedor = nifVendedor;
	}
	public String getNombreVendedor() {
		return this.nombreVendedor;
	}
	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}
	
	public String getApellido1Vendedor() {
		return this.apellido1Vendedor;
	}
	public void setApellido1Vendedor(String apellido1Vendedor) {
		this.apellido1Vendedor = apellido1Vendedor;
	}
	public String getApellido2Vendedor() {
		return this.apellido2Vendedor;
	}
	public void setApellido2Vendedor(String apellido2Vendedor) {
		this.apellido2Vendedor = apellido2Vendedor;
	}

	@Override
	public String toString() {
		String resultado = "";
		resultado = resultado + "Id Vendedor: " + getIdVendedor();
		resultado = resultado + "; DNI: " + getNifVendedor();
		resultado = resultado + "; Nombre: " + getNombreVendedor();
		resultado = resultado + "; Primer apellido: " + getApellido1Vendedor();
		resultado = resultado + "; Segundo apellido: " + getApellido2Vendedor();
		
		return resultado;
	}

}
