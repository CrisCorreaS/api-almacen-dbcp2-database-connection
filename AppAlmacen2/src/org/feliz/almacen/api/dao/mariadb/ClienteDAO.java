package org.feliz.almacen.api.dao.mariadb;

import java.util.List;
import java.util.logging.*;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.feliz.almacen.api.dao.AbstractGenericDAO;
import org.feliz.almacen.api.dao.IClienteDAO;
import org.feliz.almacen.api.modelo.ICliente;
import org.feliz.almacen.api.modelo.Cliente;


public class ClienteDAO extends AbstractGenericDAO implements IClienteDAO{

	private static final String QUERY_FINDBYID = "SELECT * FROM cliente WHERE idCliente=?";
	private static final String QUERY_FINDALL = "SELECT * FROM cliente";
	private static final String QUERY_INSERT_NEW = "INSERT INTO cliente(nombre, apellido1, apellido2, dni, telefono) values(?, ?, ?, ?, ?)";
	private static final String QUERY_UPDATE = "UPDATE cliente SET dni=?, telefono=?, nombre=?, apellido1=?, apellido2=? WHERE idCliente=?";
	private static final String QUERY_DELETE = "DELETE FROM cliente WHERE idCliente=?";
    private static final String CLASS_NAME = ClienteDAO.class.getCanonicalName();
    private static final Logger logger = Logger.getLogger("ClienteDAO_Logger");

    
    //findById()
    @Override
    public List<ICliente> findById(String id) {
        List<ICliente> resultado = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_FINDBYID);
                statement.setLong(1, Long.parseLong(id));
                ResultSet resultSet = statement.executeQuery();
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    resultado.add(new Cliente(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
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
    public List<ICliente> findAll() {
        List<ICliente> resultado = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_FINDALL);
                ResultSet resultSet = statement.executeQuery();
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    resultado.add(new Cliente(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
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
    public List<ICliente> findByExample(ICliente instance) {
        Connection connection = this.getConnection();
        if (connection != null) {
            try {
                String sqlSentence = "SELECT * FROM cliente WHERE";
                sqlSentence = sqlSentence + (instance.getIdCliente() != null ? sqlSentence+" idCliente=? AND" : "");
                sqlSentence = sqlSentence + (instance.getNifCliente() != null ? sqlSentence+" dni=? AND" : "");
                sqlSentence = sqlSentence + (instance.getNombreCliente() != null ? sqlSentence+" nombre=? AND" : "");
                sqlSentence = sqlSentence + (instance.getApellido1Cliente() != null ? sqlSentence+" apellido1=? AND" : "");
                sqlSentence = sqlSentence + (instance.getApellido2Cliente() != null ? sqlSentence+" apellido2=? AND" : "");
                sqlSentence = sqlSentence + (instance.getTelefonoCliente() != null ? sqlSentence+" telefono=?" : "");
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
    public long save(ICliente entity) {
        long result = -1L;
        try {
            Connection connection = this.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_NEW, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, entity.getNombreCliente());
                statement.setString(2, entity.getApellido1Cliente());
                statement.setString(3, entity.getApellido2Cliente());
                statement.setString(4, entity.getNifCliente());
                statement.setString(5, entity.getTelefonoCliente());
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
    public boolean update(ICliente instance) {
        logger.entering(CLASS_NAME, "update", instance);
        boolean result = false;
        try (
        	Connection connection = this.getConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
			){
        		if (connection != null) {
        			logger.info("Connection is not null and statement has been made");
        			statement.setString(1, instance.getNifCliente());
        			statement.setString(2, instance.getTelefonoCliente());
        			statement.setString(3, instance.getNombreCliente());
        			statement.setString(4, instance.getApellido1Cliente());
        			statement.setString(5, instance.getApellido2Cliente());
        			statement.setString(6, instance.getIdCliente());
        			if (statement.executeUpdate() == 1) {
        				logger.fine("El update se ha ejecutado");
        				result = true;
        			}
        		}
        	
        	} catch (SQLException e) {
        		logger.severe("No se pudo ejecutar el update, la connection o el statement pueden ser null. 'DNI' o 'Telefono' pueden existir ya en la base de datos");
        	}
        return result;
    }

    

    //delete()
    @Override
    public boolean delete(ICliente instance) {
        boolean result = false;
        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setLong(1, Long.parseLong(instance.getIdCliente()));
            result = statement.executeUpdate() == 1;
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    

    
    
    @Override
    public double getFacturacionAnual(int year) {
        return 0.0;
    }

    
}
	


