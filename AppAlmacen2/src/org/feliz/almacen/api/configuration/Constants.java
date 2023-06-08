package org.feliz.almacen.api.configuration;

import java.io.Serializable;

public interface Constants extends Serializable{
	
	public static final String PATH_CONFIGURATION = "config.properties";
	public static final String APP_CONFIG_FILE_KEY = "appConfigFile";

	public static final String FACTORY_DAO = "configuration.FactoryDAO";

	public static final String DB_USER = "database.user";
	public static final String DB_USER_PWD = "database.password";
	public static final String DB_CONNECTION_URI = "database.url";
	public static final String DB_CONNECTION_WEB_SERVER_DS = "datadase.DB_CONNECTION_WEB_SERVER_DS";
	public static String KEY_MOD_CLI = "Mod_CLI";
	public static boolean DEFAULT_MOD_CLI = false;

}
