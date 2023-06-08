package org.feliz.almacen.api.dao;

import java.io.*;
import java.util.Properties;

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
        Properties config = new Properties();
        try {
            config.load(new FileInputStream("resources\\config.props"));
            return FactoryDAO.valueOf(config.getProperty("FactoryDAO"));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return FactoryDAO.MARIADB;
    }

}
