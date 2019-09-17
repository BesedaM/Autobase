package by.epam.javatraining.beseda.webproject.controller.command.util.constant;

public class CommandConstant {

	public static final String TIMEZONE;
	public static final String DATE_FORMAT;
	public static final String TIME_FORMAT;
	public static final int MINIMUM_COMPANY_NAME_LENGTH;

	static {
		TIMEZONE = "UTC";
		DATE_FORMAT = "yyyy-MM-dd";
		TIME_FORMAT = "HH:mm";
		MINIMUM_COMPANY_NAME_LENGTH = 3;
	}
}