package by.epam.javatraining.beseda.webproject.dao.util.dataloader;


import java.util.ResourceBundle;


public class DatabaseProperties {


	private static final String DATABASE_PROP_NAME;
	private static final ResourceBundle DATABASE_PROP;

	public static final String DATABASE_URL;

	public static final String DATABASE_USER;
	public static final String DATABASE_PASSWORD;

	public static final String DATABASE_ENCODING;

	public static final String UNICODE_PATCH;
	public static final String TIMEZONE_PATCH;

	private DatabaseProperties() {
	}

	static {
		DATABASE_PROP_NAME = "database";
		UNICODE_PATCH = "?useUnicode=true&characterEncoding=utf-8";
		TIMEZONE_PATCH = "&useLegacyDatetimeCode=false&serverTimezone=UTC";
		DATABASE_PROP = ResourceBundle.getBundle(DATABASE_PROP_NAME);
		DATABASE_URL = DATABASE_PROP.getString("DATABASE_URL").trim();
		DATABASE_USER = DATABASE_PROP.getString("DATABASE_USER").trim();
		DATABASE_PASSWORD = DATABASE_PROP.getString("DATABASE_PASSWORD").trim();
		DATABASE_ENCODING = DATABASE_PROP.getString("DATABASE_ENCODING").trim();
	}
}
