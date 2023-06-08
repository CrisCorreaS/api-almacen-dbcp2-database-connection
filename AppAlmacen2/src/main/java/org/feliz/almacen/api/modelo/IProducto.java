package org.feliz.almacen.api.modelo;

public interface IProducto {
	
	public String getIdProducto();
	public void setIdProducto(String idProducto);
	public float getPrecioProducto();
	public String getNombreProducto();
	public String getCodBarras();
	public int getCantidadStock();
	public String toString();

}
