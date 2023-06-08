package org.feliz.almacen.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigApplication extends Configuracion{
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ConfigApplication.class);

	static ConfigApplication instance;


	public ConfigApplication() {
		// TODO Auto-generated constructor stub
	}

	public static final ConfigApplication getInstance() {

		logger.debug("Inicio");

		if (instance == null) {
			instance = new ConfigApplication();

			String mruta = System.getProperty(APP_CONFIG_FILE_KEY, PATH_CONFIGURATION);
			
			logger.info("mruta: " + mruta);

			instance.loadProperties(mruta);
		}
		logger.debug("Final");

		return instance;
	}

	
	public String getDataBaseUserPwd() {

		return getProperty(DB_USER_PWD);
	}

	
	public String getDataBaseUser() {

		return getProperty(DB_USER);
	}

	
	public String getDataBaseConnectionURI() {

		return getProperty(DB_CONNECTION_URI);
	}

	
	public boolean isDataBaseWebServerPool() {

		return getPropertyBoolean(DB_CONNECTION_WEB_SERVER_DS, "false");
	}

	
	public boolean isModCLI() {
		return getPropertyBoolean(KEY_MOD_CLI, String.valueOf(DEFAULT_MOD_CLI));
	}

	
	public void setModCLI(boolean mod) {

		setPropertyString(KEY_MOD_CLI, String.valueOf(mod));
	}

	public String getFactoryDAO(){
		return getProperty(FACTORY_DAO);
	}
	

}
