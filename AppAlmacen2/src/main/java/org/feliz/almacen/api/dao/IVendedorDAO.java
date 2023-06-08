package org.feliz.almacen.api.dao;

import org.feliz.almacen.api.modelo.IVendedor;

public interface IVendedorDAO extends IGenericDAO<IVendedor, String>{
	
	public double getSueldoAnualVendedor(IVendedor vendedor);

}
