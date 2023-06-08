package org.feliz.almacen.api.modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements ICliente{
	
	private String idCliente;
	private String nifCliente;
	private String nombreCliente;
	private String telefonoCliente;
	private String apellido1Cliente;
	private String apellido2Cliente;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String idCliente) {
		super();
		this.idCliente=idCliente;
	}
	
	
	public Cliente(String idCliente, String nifCliente, String nombreCliente, String telefonoCliente, String apellido1Cliente, String apellido2Cliente) {
	
		this.idCliente = idCliente;
		this.nifCliente = nifCliente;
		this.nombreCliente = nombreCliente;
		this.telefonoCliente = telefonoCliente;
		this.apellido1Cliente = apellido1Cliente;
		this.apellido2Cliente = apellido2Cliente;
	}
	
	
	public String getIdCliente() {
		return this.idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getNifCliente() {
		return this.nifCliente;
	}
	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}
	public String getNombreCliente() {
		return this.nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getTelefonoCliente() {
		return this.telefonoCliente;
	}
	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}
	public String getApellido1Cliente() {
		return this.apellido1Cliente;
	}
	public void setApellido1Cliente(String apellido1Cliente) {
		this.apellido1Cliente = apellido1Cliente;
	}
	public String getApellido2Cliente() {
		return this.apellido2Cliente;
	}
	public void setApellido2Cliente(String apellido2Cliente) {
		this.apellido2Cliente = apellido2Cliente;
	}

	@Override
	public String toString() {
		String resultado = "";
		resultado = resultado + "Id Cliente: " + getIdCliente();
		resultado = resultado + "; DNI: " + getNifCliente();
		resultado = resultado + "; Nombre: " + getNombreCliente();
		resultado = resultado + "; Primer apellido: " + getApellido1Cliente();
		resultado = resultado + "; Segundo apellido: " + getApellido2Cliente();
		resultado = resultado + "; Teléfono: " + getTelefonoCliente();
		
		return resultado;
	}
	
	
	public List<String> toValueList() {
		List<String> resultado = new ArrayList<String>(); 
		resultado.add(getIdCliente());
		
		/*
		resultado = resultado + "; DNI: " + getNif();
		resultado = resultado + "; Nombre: " + getNombre();
		resultado = resultado + "; Primer apellido: " + getApellido1();
		resultado = resultado + "; Segundo apellido: " + getApellido2();
		resultado = resultado + "; Teléfono: " + getTelefono();
		*/
		return resultado;
	}
	

}
