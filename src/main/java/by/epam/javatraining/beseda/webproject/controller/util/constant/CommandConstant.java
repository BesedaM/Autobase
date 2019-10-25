package by.epam.javatraining.beseda.webproject.controller.util.constant;

public class CommandConstant {

	public static final String TIMEZONE;
	public static final String DATE_FORMAT;
	public static final String TIME_FORMAT;
	public static final int MINIMUM_COMPANY_NAME_LENGTH;
	public static final String CONTEXT_TO_REPLACE;
	public static final String EMPTY_STRING = "";
	public static final String FIRST_PAGE_DELETE_FROM_START;
	public static final String FIRST_PAGE_DELETE_FROM_END;

	static {
		TIMEZONE = "UTC";
		DATE_FORMAT = "yyyy-MM-dd";
		TIME_FORMAT = "HH:mm";
		MINIMUM_COMPANY_NAME_LENGTH = 3;
		CONTEXT_TO_REPLACE = "/Trucking_company";
		FIRST_PAGE_DELETE_FROM_START="/Trucking_company/view/";
		FIRST_PAGE_DELETE_FROM_END=".jsp";
	}
}