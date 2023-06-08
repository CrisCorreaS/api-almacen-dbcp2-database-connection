package org.feliz.almacen.api.dao;

import org.feliz.almacen.api.modelo.ICompra;

public interface ICompraDAO extends IGenericDAO<ICompra, String>{
	
	public double getTotalCompra();

}
