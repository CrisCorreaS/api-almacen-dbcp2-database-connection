package org.feliz.almacen.api.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.feliz.almacen.api.dao.AbstractGenericDAO;
import org.feliz.almacen.api.dao.IVendedorDAO;
import org.feliz.almacen.api.modelo.*;


public class VendedorDAO extends AbstractGenericDAO implements IVendedorDAO{

	private static final String QUERY_FINDBYID = "SELECT * FROM vendedor WHERE idVendedor=?";
	private static final String QUERY_FINDALL = "SELECT * FROM vendedor";
	private static final String QUERY_INSERT_NEW = "INSERT INTO vendedor(nombre, apellido1, apellido2, dni) values(?, ?, ?, ?)";
	private static final String QUERY_UPDATE = "UPDATE vendedor SET dni=?, nombre=?, apellido1=?, apellido2=? WHERE idVendedor=?";
	private static final String QUERY_DELETE = "DELETE FROM vendedor WHERE idVendedor=?";
    private static final String CLASS_NAME = VendedorDAO.class.getCanonicalName();
    private static final Logger logger = Logger.getLogger("VendedorDAO_Logger");

    
    //findById()
    @Override
    public List<IVendedor> findById(String id) {
        List<IVendedor> resultado = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_FINDBYID);
                statement.setLong(1, Long.parseLong(id));
                ResultSet resultSet = statement.executeQuery();
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    resultado.add(new Vendedor(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
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
    public List<IVendedor> findAll() {
        List<IVendedor> resultado = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_FINDALL);
                ResultSet resultSet = statement.executeQuery();
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    resultado.add(new Vendedor(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
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
    public List<IVendedor> findByExample(IVendedor instance) {
        Connection connection = this.getConnection();
        if (connection != null) {
            try {
                String sqlSentence = "SELECT * FROM vendedor WHERE";
                sqlSentence = sqlSentence + (instance.getIdVendedor() != null ? sqlSentence+" idVendedor=? AND" : "");
                sqlSentence = sqlSentence + (instance.getNifVendedor() != null ? sqlSentence+" dni=? AND" : "");
                sqlSentence = sqlSentence + (instance.getNombreVendedor() != null ? sqlSentence+" nombre=? AND" : "");
                sqlSentence = sqlSentence + (instance.getApellido1Vendedor() != null ? sqlSentence+" apellido1=? AND" : "");
                sqlSentence = sqlSentence + (instance.getApellido2Vendedor() != null ? sqlSentence+" apellido2=?" : "");
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
    public long save(IVendedor entity) {
        long result = -1L;
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_NEW, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, entity.getNombreVendedor());
                statement.setString(2, entity.getApellido1Vendedor());
                statement.setString(3, entity.getApellido2Vendedor());
                statement.setString(4, entity.getNifVendedor());
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
    public boolean update(IVendedor instance) {
        logger.entering(CLASS_NAME, "update", instance);
        boolean result = false;
        try (
        	Connection connection = this.getConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
			){
        		if (connection != null) {
        			logger.info("Connection is not null and statement has been made");
        			statement.setString(1, instance.getNifVendedor());
        			statement.setString(2, instance.getNombreVendedor());
        			statement.setString(3, instance.getApellido1Vendedor());
        			statement.setString(4, instance.getApellido2Vendedor());
        			statement.setString(5, instance.getIdVendedor());
        			if (statement.executeUpdate() == 1) {
        				logger.fine("El update se ha ejecutado");
        				result = true;
        			}
        		}
        	
        	} catch (SQLException e) {
        		logger.severe("No se pudo ejecutar el update, la connection o el statement pueden ser null. 'DNI' o 'Nombre' pueden existir ya en la base de datos");
        	}
        return result;
    }

    

    //delete()
    @Override
    public boolean delete(IVendedor instance) {
        boolean result = false;
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setLong(1, Long.parseLong(instance.getIdVendedor()));
            result = statement.executeUpdate() == 1;
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    

	@Override
	public double getSueldoAnualVendedor(IVendedor variable1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
