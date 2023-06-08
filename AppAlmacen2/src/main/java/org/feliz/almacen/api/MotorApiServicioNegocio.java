package org.feliz.almacen.api;

import java.util.*;

import javax.sql.DataSource;

import org.feliz.almacen.api.dao.*;
import org.feliz.almacen.api.modelo.*;

public class MotorApiServicioNegocio {
	
	private IClienteDAO clienteDAO;
    private ICompraDAO compraDAO;
    private IProductoDAO productoDAO;
    private IVendedorDAO vendedorDAO;

    public MotorApiServicioNegocio() {
        clienteDAO = ProducerAbstractFactoryDAO.getFactoryDAO().getClienteDAO();
        compraDAO = ProducerAbstractFactoryDAO.getFactoryDAO().getCompraDAO();
        productoDAO = ProducerAbstractFactoryDAO.getFactoryDAO().getProductoDAO();
        vendedorDAO = ProducerAbstractFactoryDAO.getFactoryDAO().getVendedorDAO();
    }

    public MotorApiServicioNegocio(DataSource dataSource) {
        this();
        setDataSource(dataSource);
    }

    public void setDataSource(DataSource dataSource) {
        clienteDAO.setDataSource(dataSource);
        compraDAO.setDataSource(dataSource);
        productoDAO.setDataSource(dataSource);
        vendedorDAO.setDataSource(dataSource);
    }

    
    // CLIENTES
    public List<ICliente> getClientesById(String id) {
        return clienteDAO.findById(id);
    }

    public long saveCliente(ICliente cliente) {
        long idCliente = clienteDAO.save(cliente);
        cliente.setIdCliente(String.valueOf(idCliente));
        return idCliente;
    }

    public List<ICliente> listaClientes() {
        return clienteDAO.findAll();
    }

    public boolean updateCliente(ICliente cliente) {
        return clienteDAO.update(cliente);
    }

    public boolean borrarCliente(String idCliente) {
        return clienteDAO.delete(clienteDAO.findById(idCliente).get(0));
    }

    
    // COMPRAS
    public List<ICompra> getComprasById(String id) {
        return compraDAO.findById(id);
    }

    public long saveCompra(ICompra compra) {
        long idCompra = compraDAO.save(compra);
        compra.setIdCompra(String.valueOf(idCompra));
        return idCompra;
    }

    public List<ICompra> listaCompras() {
        return compraDAO.findAll();
    }

    public boolean updateCompra(ICompra compra) {
        return compraDAO.update(compra);
    }

    public boolean borrarCompra(String idCompra) {
        return compraDAO.delete(compraDAO.findById(idCompra).get(0));
    }

    
    // PRODUCTOS
    public List<IProducto> getProductosById(String id) {
        return productoDAO.findById(id);
    }

    public long saveProducto(IProducto producto) {
        long idProducto = productoDAO.save(producto);
        producto.setIdProducto(String.valueOf(idProducto));
        return idProducto;
    }

    public List<IProducto> listaProductos() {
        return productoDAO.findAll();
    }

    public boolean updateProducto(IProducto producto) {
        return productoDAO.update(producto);
    }

    public boolean borrarProducto(String idProducto) {
        return productoDAO.delete(productoDAO.findById(idProducto).get(0));
    }

    
    // VENDEDORES
    public List<IVendedor> getVendedoresById(String id) {
        return vendedorDAO.findById(id);
    }

    public long saveVendedor(IVendedor vendedor) {
        long idVendedor = vendedorDAO.save(vendedor);
        vendedor.setIdVendedor(String.valueOf(idVendedor));
        return idVendedor;
    }

    public List<IVendedor> listaVendedores() {
        return vendedorDAO.findAll();
    }

    public boolean updateVendedor(IVendedor vendedor) {
        return vendedorDAO.update(vendedor);
    }

    public boolean borrarVendedor(String idVendedor) {
        return vendedorDAO.delete(vendedorDAO.findById(idVendedor).get(0));
    }

}
