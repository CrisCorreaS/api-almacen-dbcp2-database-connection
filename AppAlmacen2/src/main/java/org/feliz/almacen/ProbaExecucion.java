package org.feliz.almacen;


import org.feliz.almacen.api.*;

public class ProbaExecucion {
	
	public static void main(String[] args) {
        MotorApiServicioNegocio api = new MotorApiServicioNegocio();
        System.out.println("Clientes: " + api.getClientesById("1"));
        System.out.println("Compras: " + api.getComprasById("1"));
        System.out.println("Productos: " + api.getProductosById("1"));
        System.out.println("Vendedores: " + api.getVendedoresById("1"));
    }

}
