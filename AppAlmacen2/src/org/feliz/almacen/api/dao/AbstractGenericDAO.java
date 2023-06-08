package org.feliz.almacen.api.dao;

import java.sql.*;

import javax.sql.DataSource;

public abstract class AbstractGenericDAO {
	
	private DataSource dataSource = null;
	private Connection connection = null;
	
	
	public boolean setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		return this.dataSource != null;
	}
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	protected boolean setConnection(Connection connection) {
		this.connection=connection;
		return this.connection != null;
	}
	
	protected Connection getConnection() {
		try {
			if(null == this.connection || this.connection.isClosed()) {
				this.setConnection(this.dataSource.getConnection());
			}
		}catch(SQLException e) {
			//poner un logger y mirar qué hacer con la excepción; si no se puede hacer nada, devolver la excepción
			e.printStackTrace();
		}
		return this.connection;
	}
}
