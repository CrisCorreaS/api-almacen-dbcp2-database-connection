package appAlmacen2;

import static org.junit.Assert.*;

import org.feliz.almacen.api.MotorApiServicioNegocio;
import org.junit.Test;

public class TestMotorApiServicioNegocio {
	
	@Test
	public void test() {
		MotorApiServicioNegocio motorApiServicioNegocio = new MotorApiServicioNegocio();
		assertNotNull("MotorApi devuelve nulo", motorApiServicioNegocio.listaClientes());
		assertTrue(motorApiServicioNegocio.listaClientes().size()>0);
	}

}
