package by.epam.javatraining.beseda.webproject.util.resourceloader;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class GeneralProperties {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);

    private static final Properties GENERAL_PROP;
    public static final String PROJECT_LOCATION;
    public static final String LOG4J_XML_CONFIG_RELATIVE_PATH;
    public static final String GENERAL_PROP_LOCATION;

    //Connection pool properties
    public static final int INITIAL_POOL_SIZE;
    public static final int WAIT_FOR_DB_RESPONSE;

    //Encoding properties
    public static final String PROJECT_ENCODING;
    public static final String SERVER_ENCODING;

    static {
        PROJECT_LOCATION = "C:/Users/User/IdeaProjects/Autobase/";
        GENERAL_PROP_LOCATION = "src/main/resources/general.properties";
        GENERAL_PROP = new Properties();
        loadProperties();

        LOG4J_XML_CONFIG_RELATIVE_PATH = GENERAL_PROP.getProperty("LOG4J_XML_CONFIG_RELATIVE_PATH").trim();
        INITIAL_POOL_SIZE = Integer.parseInt(GENERAL_PROP.getProperty("INITIAL_POOL_SIZE").trim());
        WAIT_FOR_DB_RESPONSE = Integer.parseInt(GENERAL_PROP.getProperty("WAIT_FOR_DB_RESPONSE").trim());

        PROJECT_ENCODING = GENERAL_PROP.getProperty("PROJECT_ENCODING");
        SERVER_ENCODING = GENERAL_PROP.getProperty("SERVER_ENCODING");
    }

    private static void loadProperties() {
        try {
            GENERAL_PROP.load(new FileInputStream(PROJECT_LOCATION + GENERAL_PROP_LOCATION));
        } catch (IOException ex) {
            log.error("Error loading general properties file:" + ex);
        }

    }
}
