package org.feliz.almacen.api.dao.mariadb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.feliz.almacen.api.dao.AbstractGenericDAO;
import org.feliz.almacen.api.dao.ICompraDAO;
import org.feliz.almacen.api.modelo.*;

public class CompraDAO extends AbstractGenericDAO implements ICompraDAO{

	private static final String QUERY_FINDBYID = "SELECT * FROM compra WHERE idCompra=?";
	private static final String QUERY_FINDALL = "SELECT * FROM compra";
	private static final String QUERY_INSERT_NEW = "INSERT INTO compra(precioTotal, fechaCompra, idCliente, idVendedor) values(?, ?, ?, ?)";
	private static final String QUERY_UPDATE = "UPDATE compra SET precioCompra=?, fechaCompra=?, idCliente=?, idVendedor=? WHERE idCompra=?";
	private static final String QUERY_DELETE = "DELETE FROM compra WHERE idCompra=?";
    private static final String CLASS_NAME = CompraDAO.class.getCanonicalName();
    private static final Logger logger = Logger.getLogger("CompraDAO_Logger");

    
    //findById()
    @Override
    public List<ICompra> findById(String id) {
        List<ICompra> resultado = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_FINDBYID);
                statement.setLong(1, Long.parseLong(id));
                ResultSet resultSet = statement.executeQuery();
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    resultado.add(new Compra(resultSet.getString(1), resultSet.getFloat(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
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
    public List<ICompra> findAll() {
        List<ICompra> resultado = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_FINDALL);
                ResultSet resultSet = statement.executeQuery();
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    resultado.add(new Compra(resultSet.getString(1), resultSet.getFloat(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
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
    public List<ICompra> findByExample(ICompra instance) {
        Connection connection = this.getConnection();
        if (connection != null) {
            try {
                String sqlSentence = "SELECT * FROM compra WHERE";
                sqlSentence = sqlSentence + (instance.getIdCompra() != null ? sqlSentence+" idCompra=? AND" : "");
                sqlSentence = sqlSentence + (instance.getPrecioCompra() != 0 ? sqlSentence+" precioTotal=? AND" : "");
                sqlSentence = sqlSentence + (instance.getFechaCompra() != null ? sqlSentence+" fechaCompra=? AND" : "");
                sqlSentence = sqlSentence + (instance.getIdCliente() != null ? sqlSentence+" idCliente=? AND" : "");
                sqlSentence = sqlSentence + (instance.getIdVendedor() != null ? sqlSentence+" IdVendedor=?" : "");
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
    public long save(ICompra entity) {
        long result = -1L;
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_NEW, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setFloat(1, entity.getPrecioCompra());
                statement.setString(2, entity.getFechaCompra());
                statement.setString(3, entity.getIdCliente());
                statement.setString(4, entity.getIdVendedor());
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
    public boolean update(ICompra instance) {
        logger.entering(CLASS_NAME, "update", instance);
        boolean result = false;
        try (
        	Connection connection = this.getConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
			){
        		if (connection != null) {
        			logger.info("Connection is not null and statement has been made");
        			statement.setFloat(1, instance.getPrecioCompra());
        			statement.setString(2, instance.getFechaCompra());
        			statement.setString(3, instance.getIdCliente());
        			statement.setString(4, instance.getIdVendedor());
        			statement.setString(5, instance.getIdCompra());
        			if (statement.executeUpdate() == 1) {
        				logger.fine("El update se ha ejecutado");
        				result = true;
        			}
        		}
        	
        	} catch (SQLException e) {
        		logger.severe("No se pudo ejecutar el update, la connection o el statement pueden ser null. 'Precio Compra Total' o 'Fecha Compra' pueden existir ya en la base de datos");
        	}
        return result;
    }

    

    //delete()
    @Override
    public boolean delete(ICompra instance) {
        boolean result = false;
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setLong(1, Long.parseLong(instance.getIdCompra()));
            result = statement.executeUpdate() == 1;
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    

	@Override
	public double getTotalCompra() {
		// TODO Auto-generated method stub
		return 0;
	}

}
