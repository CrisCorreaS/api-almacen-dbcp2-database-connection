package org.feliz.almacen.api.dao.mariadb;

import javax.naming.*;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;

import org.slf4j.*;

import org.feliz.almacen.api.configuration.*;
import org.feliz.almacen.api.dao.*;


public class MariaDBFactoryDAO extends ProducerAbstractFactoryDAO{
	/*
	Ahora que ya tenemos estos datos configurados en el paquete org.feliz.almacen.api.configuration, ya no necesitamos crear estas variables estáticas
	
	private static final String URL = "jdbc:mariadb://localhost:3306/";
    private static final String BD_NAME = "almacen";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    */
	
	Logger logger = LoggerFactory.getLogger(MariaDBFactoryDAO.class);

    @Override
    public IClienteDAO getClienteDAO() {
        IClienteDAO resultado = new ClienteDAO();
        resultado.setDataSource(this.getDataSource());
        return resultado;
    }

    @Override
    public IVendedorDAO getVendedorDAO() {
        IVendedorDAO resultado = new VendedorDAO();
        resultado.setDataSource(this.getDataSource());
        return resultado;
    }

    @Override
    public IProductoDAO getProductoDAO() {
        IProductoDAO resultado = new ProductoDAO();
        resultado.setDataSource(this.getDataSource());
        return resultado;
    }

    @Override
    public ICompraDAO getCompraDAO() {
        ICompraDAO resultado = new CompraDAO();
        resultado.setDataSource(this.getDataSource());
        return resultado;
    }

    @Override
    public DataSource getDataSource() {
    	ConfigApplication config = ConfigApplication.getInstance();
		DataSource dataSource = null;
		if (config.isDataBaseWebServerPool()) {
			try {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				dataSource = (DataSource)envCtx.lookup("java:comp/env/jdbc/AlmacenDB");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} else {
			ConnectionFactory connectionFactory = 
					new DriverManagerConnectionFactory(
							config.getDataBaseConnectionURI(),
							config.getDataBaseUser(),
							config.getDataBaseUserPwd());
			PoolableConnectionFactory poolableConnectionFactory =
					new PoolableConnectionFactory(connectionFactory, null);
			ObjectPool<PoolableConnection> connectionPool =
					new GenericObjectPool<>(poolableConnectionFactory);
			poolableConnectionFactory.setPool(connectionPool);
			dataSource = new PoolingDataSource<PoolableConnection>(connectionPool);
		}
		return dataSource;
	}

}
