package org.feliz.almacen.api.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.feliz.almacen.api.dao.AbstractGenericDAO;
import org.feliz.almacen.api.dao.IProductoDAO;
import org.feliz.almacen.api.modelo.*;

public class ProductoDAO extends AbstractGenericDAO implements IProductoDAO{

	private static final String QUERY_FINDBYID = "SELECT * FROM producto WHERE idProducto=?";
	private static final String QUERY_FINDALL = "SELECT * FROM producto";
	private static final String QUERY_INSERT_NEW = "INSERT INTO producto(precio, nombre, codBarras, cantidadStock) values(?, ?, ?, ?)";
	private static final String QUERY_UPDATE = "UPDATE producto SET precio=?, nombre=?, codBarras=?, cantidadStock=? WHERE idProducto=?";
	private static final String QUERY_DELETE = "DELETE FROM producto WHERE idProducto=?";
    private static final String CLASS_NAME = ProductoDAO.class.getCanonicalName();
    private static final Logger logger = Logger.getLogger("ProductoDAO_Logger");

    
    //findById()
    @Override
    public List<IProducto> findById(String id) {
        List<IProducto> resultado = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_FINDBYID);
                statement.setLong(1, Long.parseLong(id));
                ResultSet resultSet = statement.executeQuery();
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    resultado.add(new Producto(resultSet.getString(1), resultSet.getFloat(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
                }
                resultSet.close();
                statement.close();
                connection.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("No se pueden leer los results");
        }
        return resultado;
    }
    
    
    
    //findAll()
    @Override
    public List<IProducto> findAll() {
        List<IProducto> resultado = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_FINDALL);
                ResultSet resultSet = statement.executeQuery();
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    resultado.add(new Producto(resultSet.getString(1), resultSet.getFloat(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
                }
                resultSet.close();
                statement.close();
                connection.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("No se pueden leer los resultados");
        }
        return resultado;
    }
    


    //findByExample()
    @Override
    public List<IProducto> findByExample(IProducto instance) {
        Connection connection = this.getConnection();
        if (connection != null) {
            try {
                String sqlSentence = "SELECT * FROM producto WHERE";
                sqlSentence = sqlSentence + (instance.getIdProducto() != null ? sqlSentence+" idProducto=? AND" : "");
                sqlSentence = sqlSentence + (instance.getPrecioProducto() != 0 ? sqlSentence+" precio=? AND" : "");
                sqlSentence = sqlSentence + (instance.getNombreProducto() != null ? sqlSentence+" nombre=? AND" : "");
                sqlSentence = sqlSentence + (instance.getCodBarras() != null ? sqlSentence+" codBarras=? AND" : "");
                sqlSentence = sqlSentence + (instance.getCantidadStock() != 0 ? sqlSentence+" cantidadStock=?" : "");
                if (sqlSentence.substring(sqlSentence.length() - 3).equals("AND")) {
                    sqlSentence = sqlSentence.substring(0, sqlSentence.length()-3);
                }
                PreparedStatement statement = connection.prepareStatement(sqlSentence);
                statement.execute(sqlSentence);
                ResultSet resultSet = statement.getResultSet();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    
    
    //save()
    @Override
    public long save(IProducto entity) {
        long result = -1L;
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_NEW, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setFloat(1, entity.getPrecioProducto());
                statement.setString(2, entity.getNombreProducto());
                statement.setString(3, entity.getCodBarras());
                statement.setInt(4, entity.getCantidadStock());
                if (statement.executeUpdate() == 1) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    resultSet.next();
                    result = resultSet.getLong("insert_id");
                    resultSet.close();
                }
                statement.close();
                connection.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    
    //update()
    @Override
    public boolean update(IProducto instance) {
        logger.entering(CLASS_NAME, "update", instance);
        boolean result = false;
        try (
        	Connection connection = this.getConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
			){
        		if (connection != null) {
        			logger.info("Connection is not null and statement has been made");
        			statement.setFloat(1, instance.getPrecioProducto());
        			statement.setString(2, instance.getNombreProducto());
        			statement.setString(3, instance.getCodBarras());
        			statement.setInt(4, instance.getCantidadStock());
        			statement.setString(5, instance.getIdProducto());
        			if (statement.executeUpdate() == 1) {
        				logger.fine("El update se ha ejecutado");
        				result = true;
        			}
        		}
        	
        	} catch (SQLException e) {
        		logger.severe("No se pudo ejecutar el update, la connection o el statement pueden ser null. 'Precio del Producto' o 'Nombre de Producto' pueden existir ya en la base de datos");
        	}
        return result;
    }

    

    //delete()
    @Override
    public boolean delete(IProducto instance) {
        boolean result = false;
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setLong(1, Long.parseLong(instance.getIdProducto()));
            result = statement.executeUpdate() == 1;
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

	@Override
	public IProducto getProductoMasRentable() {
		// TODO Auto-generated method stub
		return null;
	}

}
