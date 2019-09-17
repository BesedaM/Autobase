package by.epam.javatraining.beseda.webproject.dao.util.dataloader;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.resourceloader.GeneralProperties.PROJECT_LOCATION;

public class DatabaseProperties {

	private static final Logger log = Logger.getLogger(ERROR_LOGGER);

	private static final String DATABASE_PROP_LOCATION;
	private static final Properties DATABASE_PROP;

	public static final String DATABASE_URL;

	public static final String DATABASE_USER;
	public static final String DATABASE_PASSWORD;

	public static final String DATABASE_ENCODING;

	public static final String UNICODE_PATCH;
	public static final String TIMEZONE_PATCH;

	private DatabaseProperties() {
	}

	static {
		DATABASE_PROP_LOCATION = PROJECT_LOCATION + "src/main/resources/database.properties";
		UNICODE_PATCH = "?useUnicode=true&characterEncoding=utf-8";
		TIMEZONE_PATCH = "&useLegacyDatetimeCode=false&serverTimezone=UTC";

		DATABASE_PROP = new Properties();
		loadProperties();
		DATABASE_URL = DATABASE_PROP.getProperty("DATABASE_URL").trim() + UNICODE_PATCH + TIMEZONE_PATCH;
		DATABASE_USER = DATABASE_PROP.getProperty("DATABASE_USER").trim();
		DATABASE_PASSWORD = DATABASE_PROP.getProperty("DATABASE_PASSWORD").trim();
		DATABASE_ENCODING = DATABASE_PROP.getProperty("DATABASE_ENCODING").trim();
	}

	private static void loadProperties() {
		try {
			DATABASE_PROP.load(new FileInputStream(DATABASE_PROP_LOCATION));
		} catch (IOException ex) {
			log.error("Error loading database properties file: " + ex);
		}
	}
}
