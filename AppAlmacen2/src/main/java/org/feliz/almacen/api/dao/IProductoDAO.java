package org.feliz.almacen.api.dao;

import org.feliz.almacen.api.modelo.IProducto;

public interface IProductoDAO extends IGenericDAO<IProducto, String>{
	
	public IProducto getProductoMasRentable();

}
