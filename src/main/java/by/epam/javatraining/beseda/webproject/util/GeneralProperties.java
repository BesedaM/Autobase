package by.epam.javatraining.beseda.webproject.util;

import java.util.ResourceBundle;

/**
 * Class designed for setting the base project properties.
 * 
 * @author Maryia_Biaseda
 *
 */
public class GeneralProperties {


	private static final ResourceBundle GENERAL_PROP;
	public static final String GENERAL_PROP_NAME;

	// Connection pool properties
	public static final int INITIAL_POOL_SIZE;
	public static final int WAIT_FOR_DB_RESPONSE;

	// Encoding properties
	public static final String PROJECT_ENCODING;
	public static final String SERVER_ENCODING;

	static {
		GENERAL_PROP_NAME = "general";
		
		GENERAL_PROP =  ResourceBundle.getBundle(GENERAL_PROP_NAME);
		INITIAL_POOL_SIZE = Integer.parseInt(GENERAL_PROP.getString("INITIAL_POOL_SIZE").trim());
		WAIT_FOR_DB_RESPONSE = Integer.parseInt(GENERAL_PROP.getString("WAIT_FOR_DB_RESPONSE").trim());

		PROJECT_ENCODING = GENERAL_PROP.getString("PROJECT_ENCODING");
		SERVER_ENCODING = GENERAL_PROP.getString("SERVER_ENCODING");
	}

}