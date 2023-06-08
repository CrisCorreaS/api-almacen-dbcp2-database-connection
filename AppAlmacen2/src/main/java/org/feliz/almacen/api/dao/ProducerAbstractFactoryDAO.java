package org.feliz.almacen.api.dao;

import java.io.*;
import java.util.Properties;

import org.feliz.almacen.api.configuration.ConfigApplication;
import org.feliz.almacen.api.dao.mariadb.MariaDBFactoryDAO;


public abstract class ProducerAbstractFactoryDAO implements IFactoryDAO {
	
	private static enum FactoryDAO{MARIADB, POSTGRESQL, MYSQL};
	
	public static IFactoryDAO getFactoryDAO() throws IllegalArgumentException {
        FactoryDAO whichFactoryDAO = getPropertiesFactoryDAO();
        switch (whichFactoryDAO) {
            case MARIADB: {
                return new MariaDBFactoryDAO();
            }
            
            //Habría que hacer los case para POSTGRESQL y MYSQL
            
            default:
				return new MariaDBFactoryDAO();
        }
    }
	
	private static FactoryDAO getPropertiesFactoryDAO() {
        
		FactoryDAO resultado= FactoryDAO.MARIADB; 
		
		try {
            String factoryDAO=ConfigApplication.getInstance().getFactoryDAO();
            resultado= FactoryDAO.valueOf(factoryDAO);
            
		} catch (Exception e) {
			//TOdo face logger
			e.printStackTrace();
		
		}
        
        return resultado;
    }

}
