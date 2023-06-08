package org.feliz.almacen.api.dao;

import javax.sql.DataSource;

public interface IFactoryDAO {
	
	public IClienteDAO getClienteDAO();
	public ICompraDAO getCompraDAO();
	public IProductoDAO getProductoDAO();
	public IVendedorDAO getVendedorDAO();
	
	public DataSource getDataSource();

}
