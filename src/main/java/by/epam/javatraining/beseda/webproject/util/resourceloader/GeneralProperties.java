package by.epam.javatraining.beseda.webproject.util.resourceloader;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GeneralProperties {

    private static Logger log = Logger.getLogger(GeneralProperties.class.getSimpleName());

    private static final Properties GENERAL_PROP;
    public static final String PROJECT_LOCATION;
    public static final String LOG4J_XML_CONFIG_RELATIVE_PATH;
    public static final String GENERAL_PROP_LOCATION = "src\\main\\resources\\general.properties";

    //Connection pool properties
    public static final int INITIAL_POOL_SIZE;
    public static final int WAIT_FOR_DB_RESPONSE;

    static {
        GENERAL_PROP = new Properties();
        loadProperties();

        PROJECT_LOCATION = GENERAL_PROP.getProperty("PROJECT_LOCATION").trim();
        LOG4J_XML_CONFIG_RELATIVE_PATH = GENERAL_PROP.getProperty("LOG4J_XML_CONFIG_RELATIVE_PATH").trim();
        INITIAL_POOL_SIZE = Integer.parseInt(GENERAL_PROP.getProperty("INITIAL_POOL_SIZE").trim());
        WAIT_FOR_DB_RESPONSE = Integer.parseInt(GENERAL_PROP.getProperty("WAIT_FOR_DB_RESPONSE").trim());
    }

    private static void loadProperties() {
        try {
            GENERAL_PROP.load(new FileInputStream(GENERAL_PROP_LOCATION));
        } catch (IOException ex) {
            log.error("Error loading general properties file:" + ex);
        }

    }
}
