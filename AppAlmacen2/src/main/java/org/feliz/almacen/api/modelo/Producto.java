package org.feliz.almacen.api.modelo;

public class Producto implements IProducto{
	
	private String idProducto;
	private float precioProducto;
	private String nombreProducto;
	private String codBarras;
	private int cantidadStock;
	
	public Producto() {
		super();
	}
	
	
	public Producto(String idProducto, float precioProducto, String nombreProducto, String codBarras, int cantidadStock) {
		super();
		this.idProducto = idProducto;
		this.precioProducto = precioProducto;
		this.nombreProducto = nombreProducto;
		this.codBarras = codBarras;
		this.cantidadStock = cantidadStock;
	}


	public String getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}


	public float getPrecioProducto() {
		return precioProducto;
	}


	public void setPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}


	public String getNombreProducto() {
		return nombreProducto;
	}


	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}


	public String getCodBarras() {
		return codBarras;
	}


	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}


	public int getCantidadStock() {
		return cantidadStock;
	}


	public void setCantidadStock(int cantidadStock) {
		this.cantidadStock = cantidadStock;
	}
	
	@Override
	public String toString() {
		String resultado = "";
		resultado = resultado + "Id Producto: " + getIdProducto();
		resultado = resultado + "; PrecioProducto: " + getPrecioProducto();
		resultado = resultado + "; Nombre del Producto: " + getNombreProducto();
		resultado = resultado + "; Código de Barras: " + getCodBarras();
		resultado = resultado + "; Cantidad en Stock: " + getCantidadStock();
		
		return resultado;
	}

}
