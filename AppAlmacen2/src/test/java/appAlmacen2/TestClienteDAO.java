package appAlmacen2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.feliz.almacen.api.dao.IClienteDAO;
import org.feliz.almacen.api.dao.mariadb.MariaDBFactoryDAO;
import org.feliz.almacen.api.modelo.Cliente;
import org.feliz.almacen.api.modelo.ICliente;

public class TestClienteDAO {
	
	public static DataSource dataSource;
	public static IClienteDAO iClienteDAO;
	public static ICliente iCliente;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MariaDBFactoryDAO mariaDBFactoryDAO = new MariaDBFactoryDAO();
		dataSource = mariaDBFactoryDAO.getDataSource();
		iClienteDAO = mariaDBFactoryDAO.getClienteDAO();
		
		iClienteDAO.setDataSource(dataSource);
		iCliente = new Cliente(null, "Test", "Test", "Test", "000000000", "000000000");
	}

	@Test
	public void test() {
		long idCliente = iClienteDAO.save(iCliente);
		try {
			Connection connection = dataSource.getConnection();
			
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE idCliente=?");
			statement.setLong(1, idCliente);
			
			ResultSet result = statement.executeQuery();
			result.next();
			
			assertEquals("El nombre del cliente es distinto al introducido", iCliente.getNombreCliente(), result.getString("nombre"));
			assertEquals("El primer apellido del cliente es distinto al introducido", iCliente.getApellido1Cliente(), result.getString("apellido1"));
			assertEquals("El segundo apellido del cliente es distinto al introducido", iCliente.getApellido2Cliente(), result.getString("apellido2"));
			assertEquals("El DNI del cliente es distinto al introducido", iCliente.getNifCliente(), result.getString("DNI"));
			assertEquals("El teléfono del cliente es distinto al introducido", iCliente.getTelefonoCliente(), result.getString("telefono"));
			
			result.close();
			statement.close();
			
			statement = connection.prepareStatement("DELETE FROM cliente WHERE idCliente=?");
			statement.setLong(1, idCliente);
			
			assertTrue(statement.executeUpdate() == 1);
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
